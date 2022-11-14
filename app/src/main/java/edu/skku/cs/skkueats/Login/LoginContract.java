package edu.skku.cs.skkueats.Login;

public interface LoginContract {
    interface contactView {
        void initView();

        void loginSuccess();

        void loginFail();
    }

    interface contactModel {
        void verifyId(String id, String password);
    }
}
