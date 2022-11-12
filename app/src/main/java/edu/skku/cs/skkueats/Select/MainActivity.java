package edu.skku.cs.skkueats.Select;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;


public class MainActivity extends AppCompatActivity {
    Button button;

    /*
    앱 실행시 가장 먼저 나오는 창
    지금은 우선 버튼 하나만 구현됨
    버튼: 누르면 미가라멘 가게의 상세정보로 가는 인텐트를 함
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        button = findViewById(R.id.migaTest);

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