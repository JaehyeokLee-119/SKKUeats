package edu.skku.cs.skkueats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.skku.cs.skkueats.Login.LoginView;
import edu.skku.cs.skkueats.MenuRecommendedList.MenuRecommendsView;
import edu.skku.cs.skkueats.RecommendConditions.RecommendConditionsView;
import edu.skku.cs.skkueats.Select.SelectActivity;


public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;
    Button button3;

    /*
    앱 실행시 가장 먼저 나오는 창
    버튼: 누르면 미가라멘 가게의 상세정보로 가는 인텐트를 함
    
    테스트 용도로 사용
    버튼 만들어서 액티비티 연결해서 테스트용도로 쓰세요
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        button = findViewById(R.id.loginTest);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RecommendConditionsView.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
    }

}