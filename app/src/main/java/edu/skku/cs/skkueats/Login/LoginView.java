package edu.skku.cs.skkueats.Login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.R;


public class LoginView extends AppCompatActivity implements LoginContract.contactView {
    private Bundle savedInstanceState;
    private LoginModel model;

    /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.savedInstanceState = savedInstanceState;

        /*
        View 생성시:
            0. Activity에 있는 View를 초기화해줌

         */

        initView();
        model = new LoginModel(this);
    }

    @Override
    public void initView() {

    }


}