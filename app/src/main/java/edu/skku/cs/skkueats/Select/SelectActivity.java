package edu.skku.cs.skkueats.Select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.MenuRecommendedList.MenuRecommendsView;
import edu.skku.cs.skkueats.MyProfile.MyProfileView;
import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RecommendConditions.RecommendConditionsView;
import edu.skku.cs.skkueats.SearchActivity.SearchActivityView;

public class SelectActivity extends AppCompatActivity {
    TextView textViewSelectRecommend;
    TextView textViewSelectSearch;
    TextView textViewSelectMyprofile;
    TextView textViewSelectCampusCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_select);
        textViewSelectRecommend = findViewById(R.id.textViewSelectRecommend);
        textViewSelectSearch = findViewById(R.id.textViewSelectSearch);
        textViewSelectMyprofile = findViewById(R.id.textViewSelectMyProfile);
        textViewSelectCampusCafe = findViewById(R.id.textViewSelectCampusCafe);

        textViewSelectRecommend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RecommendConditionsView.class);
                startActivity(intent);
            }

        });

        textViewSelectSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SearchActivityView.class);
                String userId = "DummyID";
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        textViewSelectMyprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyProfileView.class);
                String userId = "DummyID";
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });



        textViewSelectCampusCafe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RecommendConditionsView.class);
                startActivity(intent);
            }

        });
    }

}