package mindthehead.iclean.auth;


import android.util.Log;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;


public class AuthenticationManager {


    private AuthenticationManagerListener listener;


    public void startAuth(String user, String psw) {

        try {
            new AsynchronousGet().run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//startAuth


    public void setListener(AuthenticationManagerListener _listener){

        listener = _listener;

    }//setListener


    public final class AsynchronousGet implements Callback{

        private final OkHttpClient client2 = new OkHttpClient();


        public void run() throws Exception {

            Request request = new Request.Builder()
                    .url("https://mediclean.icleanfm.it/testuser")
                    .build();

            client2.newCall(request).enqueue(this);

            /*HttpUrl.Builder urlBuilder = HttpUrl.parse("https://mediclean.icleanfm.it/logintest").newBuilder();
            urlBuilder.addQueryParameter("username", "cadmin");
            urlBuilder.addQueryParameter("password", "MTH2021");
            String url = urlBuilder.build().toString();

            Request request2 = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request2).enqueue(this);

            /*HttpUrl.Builder urlBuilder = HttpUrl.parse("https://mediclean.icleanfm.it/logintest").newBuilder();
            String url = urlBuilder.build().toString();

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new BasicAuthInterceptor("cadmin", "MTH2021"))
                    .build();

            Request request2 = new Request.Builder()
                    .url(url)
                    .build();
            client.newCall(request2).enqueue(this);*/

        }//run

        /*public class BasicAuthInterceptor implements Interceptor {

            private String credentials;

            public BasicAuthInterceptor(String user, String password) {
                this.credentials = Credentials.basic(user, password);
            }

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request authenticatedRequest = request.newBuilder()
                        .header("Authorization", credentials).build();
                return chain.proceed(authenticatedRequest);
            }

        }*/

        public void onFailure(@NotNull Call call, @NotNull IOException e) {

            e.printStackTrace();
            listener.onLoginError(e.toString());

        }//onFailure


        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            try (ResponseBody responseBody = response.body()) {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                listener.onLoginSuccessful();

                Log.d("XXX", responseBody.string() +"eeee");

            }

        }//onResponse


    }//AsynchronousGet


}//AuthenticationManager