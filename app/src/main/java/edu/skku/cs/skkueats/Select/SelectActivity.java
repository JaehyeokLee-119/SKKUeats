package edu.skku.cs.skkueats.Select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.CampusRestaurantMenus.CampusCafeMenusView;
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

    private String id;
    private String pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_select);
        textViewSelectRecommend = findViewById(R.id.textViewSelectRecommend);
        textViewSelectSearch = findViewById(R.id.textViewSelectSearch);
        textViewSelectMyprofile = findViewById(R.id.textViewSelectMyProfile);
        textViewSelectCampusCafe = findViewById(R.id.textViewSelectCampusCafe);


        id = getIntent().getStringExtra("id");
        pw = getIntent().getStringExtra("pw");

        textViewSelectRecommend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RecommendConditionsView.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }

        });

        textViewSelectSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SearchActivityView.class);
                String userId = id;
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        textViewSelectMyprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyProfileView.class);
                String userId = id;
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });



        textViewSelectCampusCafe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CampusCafeMenusView.class);
                startActivity(intent);
            }

        });
    }

}