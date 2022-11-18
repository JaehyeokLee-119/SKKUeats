package edu.skku.cs.skkueats.Login;

import org.json.JSONException;

public interface LoginContract {
    interface contactView {
        void initView();

        void loginSuccess();

        void loginFail();
    }

    interface contactModel {
        void verifyId(String id, String password) throws JSONException;
    }
}
