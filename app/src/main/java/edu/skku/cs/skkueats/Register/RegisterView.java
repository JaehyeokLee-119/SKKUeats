package edu.skku.cs.skkueats.Register;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import edu.skku.cs.skkueats.ApplicationGlobal;
import edu.skku.cs.skkueats.R;


public class RegisterView extends AppCompatActivity implements RegisterContract.contactView {
    private Bundle savedInstanceState;
    private RegisterModel model;
    private Button buttonsend;
    private Button buttonverify;
    private Button buttonsignup;
    private EditText editid;
    private EditText editpw;
    private EditText editpw2;
    private EditText editemail;
    private EditText editcode;
    private String verifycode ;
    private Boolean verifyCodeCheck = false;
    private String alertText;

    private GmailSender gmailSender;
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
        model = new RegisterModel(this, (ApplicationGlobal) getApplication());

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editemail.getText().toString();
                if(email.equals("")){
                    Toast alert = Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT);
                    alert.show();
                }else {

                    new Thread(){
                        @Override
                        public void run(){
                            super.run();

                            verifycode = gmailSender.createEmailCode();
                            String skkuEmail = email + "@g.skku.edu";
                            Log.e("log", skkuEmail);
                            try {
                                gmailSender.sendMail("SKKUeats 인증코드", "인증코드 : " + verifycode, skkuEmail);
                            }catch(SendFailedException e) {

                                //쓰레드에서는 Toast를 띄우지 못하여 runOnUiThread를 호출해야 한다.
                                RegisterView.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }catch(MessagingException e){
                                System.out.println("인터넷 문제"+e);

                                //쓰레드에서는 Toast를 띄우지 못하여 runOnUiThread를 호출해야 한다.
                                RegisterView.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),"인터넷 연결을 확인 해 주십시오", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            //쓰레드에서는 Toast를 띄우지 못하여 runOnUiThread를 호출해야 한다.
                            RegisterView.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "송신 완료", Toast.LENGTH_SHORT).show();
                                }
                            });



                        }
                    }.start();


                }
            }
        });

        buttonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editcode.getText().toString();
                if(code.equals("")){
                    Toast alert = Toast.makeText(getApplicationContext(), "인증코드를 입력하세요.", Toast.LENGTH_SHORT);
                    alert.show();
                }else{
                    Boolean result = code.equals(verifycode);
                    if(result){
                        verifyCodeCheck = true;



                        Toast alert = Toast.makeText(getApplicationContext(), "인증코드 인증 성공", Toast.LENGTH_SHORT);
                        alert.show();
                    }else{
                        Toast alert = Toast.makeText(getApplicationContext(), "인증코드 인증에 실패했습니다.", Toast.LENGTH_SHORT);
                        alert.show();
                    }
                }
            }
        });

        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editid.getText().toString();
                String pw = editpw.getText().toString();
                String pw2 = editpw2.getText().toString();
                String email = editemail.getText().toString();
                String alertText;

                if(!pw.equals(pw2)){
                    alertText = "비밀번호가 일치하지 않습니다.";
                    Toast alert = Toast.makeText(getApplicationContext(), alertText, Toast.LENGTH_SHORT);
                    alert.show();
                }else if(id.equals("") || pw.equals("")){
                    alertText = "빈칸을 채워넣어주세요.";
                    Toast alert = Toast.makeText(getApplicationContext(), alertText, Toast.LENGTH_SHORT);
                    alert.show();
                }else if(verifyCodeCheck == false){
                    alertText = "인증을 먼저 진행해주세요";
                    Toast alert = Toast.makeText(getApplicationContext(), alertText, Toast.LENGTH_SHORT);
                    alert.show();
                }else{
                    try {
                        model.signup(id, pw, pw2, email);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        });


    }

    @Override
    public void initView() {
        buttonsend = findViewById(R.id.buttonsend);
        buttonverify = findViewById(R.id.buttonverify);
        buttonsignup = findViewById(R.id.buttonsignupresult);
        editid = findViewById(R.id.editregisterid);
        editpw = findViewById(R.id.editregisterpw);
        editpw2 = findViewById(R.id.editregisterpw2);
        editemail = findViewById(R.id.editregisteremail);
        editcode = findViewById(R.id.editregistercode);
        gmailSender = new GmailSender("jiyun2974@gmail.com", "lxebminqblgulnrr");
    }

    @Override
    public void signupFail() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertText = "회원가입실패";
                Toast alert = Toast.makeText(getApplicationContext(), alertText, Toast.LENGTH_SHORT);
                alert.show();
            }
        });

    }

    @Override
    public void signupSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertText = "회원가입성공";
                Toast alert = Toast.makeText(getApplicationContext(), alertText, Toast.LENGTH_SHORT);
                alert.show();
                finish();
            }
        });
    }


}