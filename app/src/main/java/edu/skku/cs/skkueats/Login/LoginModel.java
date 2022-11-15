package edu.skku.cs.skkueats.Login;

public class LoginModel implements LoginContract.contactModel {
    private LoginContract.contactView view;

    public LoginModel(LoginContract.contactView view) {
        this.view = view;
    }

    @Override
    public void verifyId(String id, String password){
        boolean check = true;
        //서버에서 인증 받아오기
        if(check){
            view.loginSuccess();
        }else{
            view.loginFail();
        }
    }
}
