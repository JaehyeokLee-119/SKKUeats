package edu.skku.cs.skkueats.Login;

public class LoginModel implements LoginContract.contactModel {
    private LoginContract.contactView view;

    public LoginModel(LoginContract.contactView view) {
        this.view = view;
    }
}
