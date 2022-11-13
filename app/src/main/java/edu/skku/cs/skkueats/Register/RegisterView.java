package edu.skku.cs.skkueats.Register;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class RegisterView extends AppCompatActivity implements RegisterContract.contactView {
    private Bundle savedInstanceState;
    private RegisterModel model;

    /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.savedInstanceState = savedInstanceState;

        /*
        View 생성시:
            0. Activity에 있는 View를 초기화해줌

         */

        initView();
        model = new RegisterModel(this);
    }

    @Override
    public void initView() {

    }


}