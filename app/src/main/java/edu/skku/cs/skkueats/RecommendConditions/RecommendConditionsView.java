package edu.skku.cs.skkueats.RecommendConditions;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class RecommendConditionsView extends AppCompatActivity implements RecommendConditionsContract.contactView {
    private ArrayList<RecommendConditionItem> recommendsArray = new ArrayList<>();
    private ListView listViewRecommendCondition;
    private RecommendConditionsAdapter restaurantReviewAdapter;
    private Bundle savedInstanceState;
    private RecommendConditionsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_conditions);
        this.savedInstanceState = savedInstanceState;

        /*
            0. Activity에 있는 View를 초기화해줌
            1. recommendQueryCondition에다가 Intent로부터 조건들을 받아옴
                [대분류, 소분류, 가격, 최소평점, 위치]
            2. recommendQueryCondition를 넣어서 model을 만든다
            -> model은 [대분류, 소분류, 가격, 최소평점, 위치, 추천최대개수]로 이루어짐
         */

        initView();

        String[] conditions = getIntent().getStringArrayExtra("conditions");
        RecommendQueryCondition recommendQueryCondition = new RecommendQueryCondition(
                conditions[0], conditions[1], Integer.parseInt(conditions[2]),
                Double.parseDouble(conditions[3]), conditions[4], Integer.parseInt(conditions[5])
        );

        // 받은 조건 Intent를 일단 테스트용으로 토스트 띄워보기
        Toast.makeText(getApplicationContext(), conditions[0]+" "+conditions[1]+" "+conditions[2]+" "+
                conditions[3]+" "+conditions[4]+" "+conditions[5], Toast.LENGTH_SHORT).show();
        
        
        //model = new RecommendConditionsModel(this, recommendQueryCondition);
    }

    @Override
    public void initView() {
        listViewRecommendCondition = findViewById(R.id.listViewRecommendCondition);

    }


    @Override
    public void showRecommend(RecommendConditionItem recommendConditionItem) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        recommendsArray.add(new RecommendConditionItem(recommendConditionItem));
//        restaurantReviewAdapter = new MenuRecommendsAdapter(getApplicationContext(), recommendsArray);
        restaurantReviewAdapter = new RecommendConditionsAdapter(this, recommendsArray);
        listViewRecommendCondition.setAdapter(restaurantReviewAdapter);
    }
}