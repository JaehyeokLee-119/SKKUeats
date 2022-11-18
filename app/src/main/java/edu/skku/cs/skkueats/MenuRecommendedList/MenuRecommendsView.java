package edu.skku.cs.skkueats.MenuRecommendedList;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class MenuRecommendsView extends AppCompatActivity implements MenuRecommendsContract.contactView {
    private ArrayList<MenuRecommends> recommendsArray = new ArrayList<>();
    private ListView recommendsList;
    private MenuRecommendsAdapter restaurantReviewAdapter;
    private Bundle savedInstanceState;
    private MenuRecommendsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_recommends);
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
        
        
        model = new MenuRecommendsModel(this, recommendQueryCondition);
    }

    @Override
    public void initView() {
        recommendsList = findViewById(R.id.listViewRecommends);

    }


    @Override
    public void showRecommend(MenuRecommends menuRecommends) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        recommendsArray.add(new MenuRecommends(menuRecommends));
//        restaurantReviewAdapter = new MenuRecommendsAdapter(getApplicationContext(), recommendsArray);
        restaurantReviewAdapter = new MenuRecommendsAdapter(this, recommendsArray);
        recommendsList.setAdapter(restaurantReviewAdapter);
    }
}