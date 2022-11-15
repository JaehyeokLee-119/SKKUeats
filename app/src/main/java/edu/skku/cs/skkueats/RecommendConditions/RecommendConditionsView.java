package edu.skku.cs.skkueats.RecommendConditions;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
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


    CheckBox checkBoxActivatePrice;
    CheckBox checkBoxActivateMinGrade;
    TextView textViewPriceText;
    TextView textViewMinGradeText;
    SeekBar seekBarPrice;
    SeekBar seekBarMinGrade;

    CheckBox checkBoxBigKorean;
    CheckBox checkBoxBigChinese;
    CheckBox checkBoxBigJapanese;
    CheckBox checkBoxBigWestern;
    CheckBox checkBoxBigAsian;
    CheckBox checkBoxBigFast;
    CheckBox checkBoxBigAll;

    CheckBox checkBoxSmallRice;
    CheckBox checkBoxSmallSoup;
    CheckBox checkBoxSmallMeat;
    CheckBox checkBoxSmallNoodle;
    CheckBox checkBoxSmallWithAlcohol;
    CheckBox checkBoxSmallPizza;
    CheckBox checkBoxSmallChicken;
    CheckBox checkBoxSmallAll;

    CheckBox checkBoxLocationFrontDoor;
    CheckBox checkBoxLocationBackDoor;
    CheckBox checkBoxLocationAcross;
    CheckBox checkBoxLocationElsewhere;
    CheckBox checkBoxLocationAll;

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

            가격은 seekBarPrice.getProgress()*1000
            최소 평점은 (double)seekBarMinGrade.getProgress()/2
            
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

                priceTextChange();

            }
        });

        boxMinGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visibilityLevel <= 3)
                    visibilityLevel = 4;
                showConditions(visibilityLevel);
                minGradeTextChange();
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

        seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceTextChange();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textViewPriceText.setText(String.format(": %d₩", seekBar.getProgress()*1000));
            }
        });

        seekBarMinGrade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minGradeTextChange();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //textViewPriceText.setText(String.format(": %d₩", seekBar.getProgress()*1000));
            }
        });
        checkBoxActivatePrice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                priceTextChange();
            }
        });
        checkBoxActivateMinGrade.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                minGradeTextChange();
            }
        });
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int priceValue;
                double minGradeValue;
                if (checkBoxActivatePrice.isChecked() && visibilityLevel >= 3) {
                    priceValue = seekBarPrice.getProgress()*1000;
                } else {
                    priceValue = 0;
                }
                if (checkBoxActivateMinGrade.isChecked()  && visibilityLevel >= 4) {
                    minGradeValue = (double)seekBarMinGrade.getProgress()/2;
                } else {
                    minGradeValue = 0;
                }
                RecommendQueryCondition recommendQueryCondition = new RecommendQueryCondition(
                        makeBigCategoryQueryString(),
                        makeSmallCategoryQueryString(),
                        priceValue,
                        minGradeValue,
                        makeLocationQueryString(),
                        5
                );



                Intent intent = new Intent(getApplicationContext(), MenuRecommendsView.class);
                String[] conditions = new String[]{
                        recommendQueryCondition.bigCategory,
                        recommendQueryCondition.smallCategory,
                        Integer.toString(recommendQueryCondition.price),
                        Double.toString(recommendQueryCondition.minGrade),
                        recommendQueryCondition.location,
                        Integer.toString(recommendQueryCondition.maxNum)};
                intent.putExtra("conditions", conditions);
                startActivity(intent);
            }
        });





    }

    public void priceTextChange() {
        if (checkBoxActivatePrice.isChecked())
            textViewPriceText.setText(String.format(": %d ₩", seekBarPrice.getProgress()*1000));
        else
            textViewPriceText.setText(String.format(": 상관없음"));
    }

    public void minGradeTextChange() {
        if (checkBoxActivateMinGrade.isChecked())
            textViewMinGradeText.setText(String.format(": %.1f", (double)seekBarMinGrade.getProgress()/2));
        else
            textViewMinGradeText.setText(String.format(": 상관없음"));
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

        seekBarPrice = findViewById(R.id.seekBarPrice);
        seekBarMinGrade = findViewById(R.id.seekBarMinGrade);
        textViewPriceText = findViewById(R.id.textViewPriceText);
        textViewMinGradeText = findViewById(R.id.textViewMinGradeText);
        checkBoxActivatePrice = findViewById(R.id.checkBoxActivatePrice);
        checkBoxActivateMinGrade = findViewById(R.id.checkBoxActivateMinGrade);

        checkBoxBigKorean           =   findViewById(R.id.checkBoxBigKorean);
        checkBoxBigChinese          =   findViewById(R.id.checkBoxBigChinese);
        checkBoxBigJapanese         =   findViewById(R.id.checkBoxBigJapanese);
        checkBoxBigWestern          =   findViewById(R.id.checkBoxBigWestern);
        checkBoxBigAsian            =   findViewById(R.id.checkBoxBigAsian);
        checkBoxBigFast             =   findViewById(R.id.checkBoxBigFast);
        checkBoxBigAll              =   findViewById(R.id.checkBoxBigAll);
        checkBoxSmallRice           =   findViewById(R.id.checkBoxSmallRice);
        checkBoxSmallSoup           =   findViewById(R.id.checkBoxSmallSoup);
        checkBoxSmallMeat           =   findViewById(R.id.checkBoxSmallMeat);
        checkBoxSmallNoodle         =   findViewById(R.id.checkBoxSmallNoodle);
        checkBoxSmallWithAlcohol    =   findViewById(R.id.checkBoxSmallWithAlcohol);
        checkBoxSmallPizza          =   findViewById(R.id.checkBoxSmallPizza);
        checkBoxSmallChicken        =   findViewById(R.id.checkBoxSmallChicken);
        checkBoxSmallAll            =   findViewById(R.id.checkBoxSmallAll);
        checkBoxLocationFrontDoor   =   findViewById(R.id.checkBoxLocationFrontDoor);
        checkBoxLocationBackDoor    =   findViewById(R.id.checkBoxLocationBackDoor);
        checkBoxLocationAcross      =   findViewById(R.id.checkBoxLocationAcross);
        checkBoxLocationElsewhere   =   findViewById(R.id.checkBoxLocationElsewhere);
        checkBoxLocationAll         =   findViewById(R.id.checkBoxLocationAll);


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


    public String makeBigCategoryQueryString() {
        String res = "";
        if (checkBoxBigAll.isChecked()) {
            res += "뭐든지";
        } else {
            if (checkBoxBigKorean.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "한식";
            }
            if (checkBoxBigChinese.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "중식";
            }
            if (checkBoxBigJapanese.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "일식";
            }
            if (checkBoxBigWestern.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "양식";
            }
            if (checkBoxBigAsian.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "아시안";
            }
            if (checkBoxBigFast.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "패스트푸드";
            }
        }
        if (res.isEmpty()) {
            return "뭐든지";
        }
        return res;
    }
    public String makeSmallCategoryQueryString() {
        String res = "";
        if (checkBoxSmallAll.isChecked()) {
            res += "뭐든지";
        } else {
            if (checkBoxSmallRice.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "밥";
            }
            if (checkBoxSmallSoup.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "탕";
            }
            if (checkBoxSmallMeat.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "고기";
            }
            if (checkBoxSmallNoodle.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "면";
            }
            if (checkBoxSmallWithAlcohol.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "안주";
            }
            if (checkBoxSmallPizza.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "피자";
            }
            if (checkBoxSmallChicken.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "치킨";
            }
        }
        if (res.isEmpty()) {
            return "뭐든지";
        }
        return res;
    }
    public String makeLocationQueryString() {
        String res = "";

        if (checkBoxLocationAll.isChecked()) {
            res += "모두";
        } else {
            if (checkBoxLocationFrontDoor.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "정문쪽";
            }
            if (checkBoxLocationBackDoor.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "후문쪽";
            }
            if (checkBoxLocationAcross.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "길건너";
            }
            if (checkBoxLocationElsewhere.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "멀리";
            }
        }
        if (res.isEmpty()) {
            return "모두";
        }
        return res;
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