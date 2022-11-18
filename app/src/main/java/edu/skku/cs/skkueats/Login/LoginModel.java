package edu.skku.cs.skkueats.Login;

import android.provider.MediaStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.skku.cs.skkueats.ApplicationGlobal;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginModel implements LoginContract.contactModel {
    private LoginContract.contactView view;
    private ApplicationGlobal applicationGlobal;
    private String serverUrl;
    private Boolean check = false;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public LoginModel(LoginContract.contactView view, ApplicationGlobal applicationGlobal) {
        this.view = view;
        this.applicationGlobal = applicationGlobal;
        this.serverUrl = applicationGlobal.getServerURL();
    }

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String res = response.body().string();

            JSONObject json = null;
            try {
                json = new JSONObject(res);
                check = json.getBoolean("loginSuccess");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(check){
                view.loginSuccess();
            }else{
                view.loginFail();
            }

        }
    };

    @Override
    public void verifyId(String id, String password) throws JSONException {
        //서버에서 인증 받아오기

        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("id", id)
                .put("password", password);

        RequestBody body = RequestBody.create(JSON, json.toString());



        Request request = new Request.Builder()
                .url(serverUrl + "users/log-in")
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);

    }
}
