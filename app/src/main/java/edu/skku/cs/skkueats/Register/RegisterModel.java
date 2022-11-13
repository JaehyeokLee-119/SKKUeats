package edu.skku.cs.skkueats.Register;

import java.util.ArrayList;

public class RegisterModel implements RegisterContract.contactModel {
    private RegisterContract.contactView view;

    public RegisterModel(RegisterContract.contactView view) {
        this.view = view;
    }
}
