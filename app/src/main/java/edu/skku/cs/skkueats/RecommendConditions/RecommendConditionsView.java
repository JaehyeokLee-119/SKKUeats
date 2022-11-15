package edu.skku.cs.skkueats.RecommendConditions;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import edu.skku.cs.skkueats.MenuRecommendedList.MenuRecommendsView;
import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;


public class RecommendConditionsView extends AppCompatActivity implements RecommendConditionsContract.contactView {
    private Bundle savedInstanceState;
    private RecommendConditionsModel model;

    ConstraintLayout completeButton;

    ConstraintLayout boxBigCategory;
    ConstraintLayout boxSmallCategory;
    ConstraintLayout boxPrice;
    ConstraintLayout boxMinGrade;
    ConstraintLayout boxLocation;

    RadioGroup bigCategoryCondition;
    RadioGroup smallCategoryCondition;
    ConstraintLayout priceCondition;
    ConstraintLayout minGradeCondition;
    RadioGroup locationCondition;

    TextView textViewBigCategoryArrow;
    TextView textViewSmallCategoryArrow;
    TextView textViewPriceArrow;
    TextView textViewMinGradeArrow;
    TextView textViewLocationArrow;

    private int visibilityLevel; // 화면에 표시되는 조건의 개수

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

        boxSmallCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visibilityLevel <= 1)
                    visibilityLevel = 2;
                showConditions(visibilityLevel);
            }
        });

        boxPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visibilityLevel <= 2)
                    visibilityLevel = 3;
                showConditions(visibilityLevel);
            }
        });

        boxMinGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visibilityLevel <= 3)
                    visibilityLevel = 4;
                showConditions(visibilityLevel);
            }
        });

        boxLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visibilityLevel <= 4)
                    visibilityLevel = 5;
                showConditions(visibilityLevel);
            }
        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuRecommendsView.class);
                String[] conditions = new String[]{"대분류", "소분류", "5000", "3.5", "위치", "5"};
                intent.putExtra("conditions", conditions);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initView() {
        completeButton = findViewById(R.id.boxRecommendComplete);

        boxBigCategory = findViewById(R.id.constraintLayoutBigCategory);
        boxSmallCategory = findViewById(R.id.constraintLayoutSmallCategory);
        boxPrice = findViewById(R.id.constraintLayoutPrice);
        boxMinGrade = findViewById(R.id.constraintLayoutMinGrade);
        boxLocation = findViewById(R.id.constraintLayoutLocation);

        bigCategoryCondition = findViewById(R.id.BigCategoryCondition);
        smallCategoryCondition = findViewById(R.id.SmallCategoryCondition);
        priceCondition = findViewById(R.id.PriceCondition);
        minGradeCondition = findViewById(R.id.MinGradeCondition);
        locationCondition = findViewById(R.id.LocationCondition);

        textViewBigCategoryArrow = findViewById(R.id.textViewBigCategoryArrow);
        textViewSmallCategoryArrow = findViewById(R.id.textViewSmallCategoryArrow);
        textViewPriceArrow = findViewById(R.id.textViewPriceArrow);
        textViewMinGradeArrow = findViewById(R.id.textViewMinGradeArrow);
        textViewLocationArrow = findViewById(R.id.textViewLocationArrow);

        // 초기에는 대분류 빼곤 다 비활성화상태
        smallCategoryCondition.setVisibility(View.GONE);
        boxPrice.setVisibility(View.GONE);
        priceCondition.setVisibility(View.GONE);
        boxMinGrade.setVisibility(View.GONE);
        minGradeCondition.setVisibility(View.GONE);
        boxLocation.setVisibility(View.GONE);
        locationCondition.setVisibility(View.GONE);

        visibilityLevel = 1;
        showConditions(visibilityLevel);
    }

    public void showConditions(int visibilityLevel) {
        if (visibilityLevel >= 0) {
            boxBigCategory.setVisibility(View.VISIBLE);
            if (visibilityLevel == 0) {
                textViewBigCategoryArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.black));
            } else {
                textViewBigCategoryArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.blackGrey));
            }
        }

        if (visibilityLevel >= 1) {
            boxBigCategory.setVisibility(View.VISIBLE);
            bigCategoryCondition.setVisibility(View.VISIBLE);
            boxSmallCategory.setVisibility(View.VISIBLE);
            if (visibilityLevel == 1) {
                textViewSmallCategoryArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.black));
            } else {
                textViewSmallCategoryArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.blackGrey));
            }
        }

        if (visibilityLevel >= 2) {
            smallCategoryCondition.setVisibility(View.VISIBLE);
            boxPrice.setVisibility(View.VISIBLE);
            if (visibilityLevel == 2) {
                textViewPriceArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.black));
            } else {
                textViewPriceArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.blackGrey));
            }
        }

        if (visibilityLevel >= 3) {
            priceCondition.setVisibility(View.VISIBLE);
            boxMinGrade.setVisibility(View.VISIBLE);
            if (visibilityLevel == 3) {
                textViewMinGradeArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.black));
            } else {
                textViewMinGradeArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.blackGrey));
            }
        }

        if (visibilityLevel >= 4) {
            minGradeCondition.setVisibility(View.VISIBLE);
            boxLocation.setVisibility(View.VISIBLE);
            if (visibilityLevel == 4) {
                textViewLocationArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.black));
            } else {
                textViewLocationArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.blackGrey));
            }
        }

        if (visibilityLevel >= 5) {
            locationCondition.setVisibility(View.VISIBLE);
            textViewLocationArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                    R.color.blackGrey));
        }

    }
}