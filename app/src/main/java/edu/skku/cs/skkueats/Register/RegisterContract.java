package edu.skku.cs.skkueats.Register;

import java.util.ArrayList;

public interface RegisterContract {
    interface contactView {
        void initView();

    }

    interface contactModel {
        boolean sendEmail(String email);

        boolean verifyCode(String code);

        boolean signup(String id, String pw, String email);
    }
}
