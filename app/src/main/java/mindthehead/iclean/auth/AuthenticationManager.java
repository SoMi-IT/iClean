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
    private static final String URL_LOGIN = "https://emotionalselling.mthdigital.it/api/login";
    private static final String BODY_NAME = "name";
    private static final String BODY_PASSWORD = "password";
    private static final String RESPONSE_TOKEN = "token";
    private static final String RESPONSE_ERROR = "error_message";
    private AuthenticationManagerListener listener;
    private final JSONObject jsonBody = new JSONObject();


    public void startAuth(String user, String psw) {

        try {

            jsonBody.put(BODY_NAME, user);
            jsonBody.put(BODY_PASSWORD, psw);

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

        public void run() throws Exception {

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
                    analyzeResponse(responseBody.string());
                }

            }

        }//onResponse


    }//AsynchronousGet


    private void analyzeResponse(String response) {

        try {

            JSONObject jsonResponse = new JSONObject(response);
            String token = jsonResponse.getString(RESPONSE_TOKEN);

            if (!token.equals("")) {

                listener.onLoginSuccessful();
            }
            else {

                listener.onLoginError(jsonResponse.getString(RESPONSE_ERROR));
            }

        } catch (JSONException e) {
            listener.onLoginError(e.toString());
            e.printStackTrace();
        }

    }//analyzeResponse


}//AuthenticationManager