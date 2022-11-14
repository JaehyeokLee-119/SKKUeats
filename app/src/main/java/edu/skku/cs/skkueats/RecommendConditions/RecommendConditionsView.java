package edu.skku.cs.skkueats.RecommendConditions;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class RecommendConditionsView extends AppCompatActivity implements RecommendConditionsContract.contactView {
    private Bundle savedInstanceState;
    private RecommendConditionsModel model;

    ConstraintLayout completeButton;
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_conditions);
        this.savedInstanceState = savedInstanceState;

        /*
            0. Activity에 있는 View를 초기화해줌
                - 완료 버튼 클릭 시 조건(이벤트) 설정

            1. 처음에는 대분류, 소분류까지만 열려 있음 (가격은 비활성화 상태)
            2. 비활성화 조건의 화살표를 누르면 조건이 열리며,
                아래 조건이 비활성화된 채 나타나서 활성화시킬 수 있음
            
            
         */

        initView();

        //model = new RecommendConditionsModel(this, recommendQueryCondition);
    }

    @Override
    public void initView() {
        completeButton = findViewById(R.id.boxRecommendComplete);

    }
}