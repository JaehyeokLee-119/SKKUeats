package edu.skku.cs.skkueats.Register;

public class RegisterModel implements RegisterContract.contactModel {
    private RegisterContract.contactView view;
    private Boolean sendEmailCheck = false;
    private Boolean verifyCodeCheck = false;

    public RegisterModel(RegisterContract.contactView view) {
        this.view = view;
    }

    @Override
    public boolean sendEmail(String email){
        String skkuEmail = email + "@g.skku.edu";
        // add sendEmail logic
        sendEmailCheck = true;
        return true;
    }

    @Override
    public boolean verifyCode(String code){
        //add verifyCode logic
        verifyCodeCheck = true;
        return true;
    }

    @Override
    public boolean signup(String id, String pw, String email){
        //add signup logic
        if(sendEmailCheck && verifyCodeCheck){
            return true;
        }else {
            return false;
        }
    }



}
