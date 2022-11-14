package edu.skku.cs.skkueats.Select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.MenuRecommendedList.MenuRecommendsView;
import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.SearchActivity.SearchActivityView;

public class SelectActivity extends AppCompatActivity {
    TextView textViewSelectRecommend;
    TextView textViewSelectSearch;
    TextView textViewSelectMyprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_select);
        textViewSelectRecommend = findViewById(R.id.textViewSelectRecommend);
        textViewSelectSearch = findViewById(R.id.textViewSelectSearch);
        textViewSelectMyprofile = findViewById(R.id.textViewSelectMyProfile);

        textViewSelectRecommend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MenuRecommendsView.class);
                String[] conditions = new String[]{"대분류", "소분류", "5000", "3.5", "위치", "5"};
                intent.putExtra("conditions", conditions);
                startActivity(intent);
            }
        });

        textViewSelectSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SearchActivityView.class);
                String userID = "DummyID";
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });

        textViewSelectMyprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                Intent intent = new Intent(getApplicationContext(), SearchActivityView.class);
//                startActivity(intent);
            }
        });
    }

}