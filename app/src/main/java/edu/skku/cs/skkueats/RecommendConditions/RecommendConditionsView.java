package edu.skku.cs.skkueats.RecommendConditions;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class RecommendConditionsView extends AppCompatActivity implements RecommendConditionsContract.contactView {
    private Bundle savedInstanceState;
    private RecommendConditionsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_conditions);
        this.savedInstanceState = savedInstanceState;

        /*
            0. Activity에 있는 View를 초기화해줌
         */

        initView();

        //model = new RecommendConditionsModel(this, recommendQueryCondition);
    }

    @Override
    public void initView() {

    }
}