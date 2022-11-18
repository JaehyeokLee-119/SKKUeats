package edu.skku.cs.skkueats.Register;

import java.util.ArrayList;

public interface RegisterContract {
    interface contactView {
        void initView();

        void signupFail();

        void signupSuccess();
    }

    interface contactModel {
        boolean sendEmail(String email);

        boolean verifyCode(String code);

        void signup(String id, String pw, String pw2, String email);
    }
}
