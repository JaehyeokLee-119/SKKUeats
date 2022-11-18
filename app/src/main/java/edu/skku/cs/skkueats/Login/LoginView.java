package edu.skku.cs.skkueats.Login;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.ApplicationGlobal;
import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.Register.RegisterView;
import edu.skku.cs.skkueats.Select.SelectActivity;


public class LoginView extends AppCompatActivity implements LoginContract.contactView {
    private Bundle savedInstanceState;
    private LoginModel model;
    private EditText editId;
    private EditText editPassword;
    private Button login;
    private Button signup;

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
        model = new LoginModel(this, (ApplicationGlobal) getApplication());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.verifyId(editId.getText().toString(), editPassword.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginView.this, RegisterView.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initView() {
        editId = findViewById(R.id.editTextLoginID);
        editPassword = findViewById(R.id.editTextLoginPW);
        login = findViewById(R.id.buttonlogin);
        signup = findViewById(R.id.buttonsignup);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginView.this, SelectActivity.class);
        intent.putExtra("id", editId.getText().toString());
        intent.putExtra("pw", editPassword.getText().toString());
        startActivity(intent);
    }

    @Override
    public void loginFail() {
        Toast alert = Toast.makeText(this.getApplicationContext(), "아이디 혹은 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT);
        alert.show();
    }

}