package mindthehead.iclean.auth;


import android.util.Log;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AuthenticationManager {


    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String URL_LOGIN = "https://mediclean.icleanfm.it/api/user/login";
    private static final String BODY_NAME = "username";
    private static final String BODY_PASSWORD = "password";
    private static final String RESPONSE_TOKEN = "token";
    private static final String RESPONSE_ERROR = "error_message";
    private static final String RESPONSE_USERID = "user_id";
    private static final String RESPONSE_USERNAME = "user";
    private static final String RESPONSE_SCHEDULES = "schedules";
    private static final String RESPONSE_TASKS = "tasks";

    private AuthenticationManagerListener listener;
    private final JSONObject jsonBody = new JSONObject();

    private boolean downloadNeeded;

    public void startAuth(boolean _downloadNeeded, String user, String psw) {

        try {

            jsonBody.put(BODY_NAME, user);
            jsonBody.put(BODY_PASSWORD, psw);
            downloadNeeded = _downloadNeeded;

            new AsynchronousGet().run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//startAuth


    public void setListener(AuthenticationManagerListener _listener){

        listener = _listener;

    }//setListener


    public final class AsynchronousGet implements Callback{

        private final OkHttpClient client = new OkHttpClient();

        public void run() {

            RequestBody body = RequestBody.create(jsonBody.toString(), JSON);

            Request request = new Request.Builder()
                    .url(URL_LOGIN)
                    .post(body)
                    .build();

            client.newCall(request).enqueue(this);

        }//run

        public void onFailure(@NotNull Call call, @NotNull IOException e) {

            e.printStackTrace();
            listener.onLoginError(e.toString());

        }//onFailure


        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            try (ResponseBody responseBody = response.body()) {

                if (!response.isSuccessful()) {
                    listener.onLoginError("Unexpected code " + response);
                    throw new IOException("Unexpected code " + response);
                }else {
                    if (responseBody != null) analyzeResponse(responseBody.string());

                }

            }

        }//onResponse


    }//AsynchronousGet


    private void analyzeResponse(String response) {

        try {

            JSONObject jsonResponse = new JSONObject(response);
            String token = jsonResponse.getString(RESPONSE_TOKEN);

            if (!token.equals("")) {

                if(downloadNeeded) {
                    Log.d("XXX", "RESPONSE: " + jsonResponse.toString());
                    saveResponse(jsonResponse);

                } else listener.onLoginSuccessful(null, null, null, null);

            } else {
                listener.onLoginError(jsonResponse.getString(RESPONSE_ERROR));
            }

        } catch (JSONException e) {
            listener.onLoginError(e.toString());
            e.printStackTrace();
        }

    }//analyzeResponse


    private void saveResponse(JSONObject jsonResponse) {

        String userid;
        String username;
        String schedules;
        String tasks;

        try {

            userid = jsonResponse.getString(RESPONSE_USERID);
            if (userid.length() == 0) userid = null;

        } catch (JSONException e) {
            userid = "";
        }

        try {

            username = jsonResponse.getString(RESPONSE_USERNAME);
            if (username.length() == 0) username = null;

        } catch (JSONException e) {
            username = "";
        }

        try {

            schedules = jsonResponse.getJSONArray(RESPONSE_SCHEDULES).toString();

        } catch (JSONException e) {
            schedules = "";
        }

        try {

            tasks = jsonResponse.getJSONArray(RESPONSE_TASKS).toString();

        } catch (JSONException e) {
            tasks = "";
        }

        listener.onLoginSuccessful(userid, username, schedules, tasks);

    }//analyzeResponse


}//AuthenticationManager