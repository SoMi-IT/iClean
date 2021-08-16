package mindthehead.iclean.work.sync;


import android.app.Activity;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import mindthehead.iclean.auth.AuthenticationManagerListener;
import mindthehead.iclean.work.settings.UserDataManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SyncManager {

    private Activity activity;

    private final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String URL_LOGIN = "https://mediclean.icleanfm.it/api/user/tasksign";
    private static final String BODY_NAME = "username";
    private static final String BODY_PASSWORD = "password";
    private static final String BODY_TASKS_DONE = "tasksDone";
    private static final String RESPONSE_ERROR = "error_message";
    private static final String RESPONSE_OK = "message";

    private SyncManagerListener listener;

    private final JSONObject jsonBody = new JSONObject();


    public void startSync(Activity _activity,  String tasksDone) {

        activity = _activity;

        try {
            JSONArray jsonArray = new JSONArray(tasksDone);
            jsonBody.put(BODY_TASKS_DONE, jsonArray);

            new AsynchronousGet().run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//startAuth


    public void setListener(SyncManagerListener _listener){

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
            listener.onSyncError(e.toString());

        }//onFailure


        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            try (ResponseBody responseBody = response.body()) {

                if (!response.isSuccessful()) {
                    listener.onSyncError("Unexpected code " + response);
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
            String message = jsonResponse.getString(RESPONSE_OK);

            if (!message.equals("")) {

                listener.onSyncSuccessful(message);

            } else {

                listener.onSyncError(jsonResponse.getString(RESPONSE_ERROR));

            }

        } catch (JSONException e) {
            listener.onSyncError(e.toString());
            e.printStackTrace();
        }

    }//analyzeResponse



}//AuthenticationManager