package edu.skku.cs.skkueats.Select;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import edu.skku.cs.skkueats.MenuRecommends.MenuRecommendsView;
import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;


public class MainActivity extends AppCompatActivity {
    Button button;
    Button button2;

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
        button = findViewById(R.id.migaTest);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MenuRecommendsView.class);
                String[] conditions = new String[]{"대분류", "소분류", "5000", "3.5", "위치", "5"};
                intent.putExtra("conditions", conditions);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RestaurantInfoView.class);
                intent.putExtra("RestaurantName","미가라멘");
                startActivity(intent);
            }
        });

    }

}