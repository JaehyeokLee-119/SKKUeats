package edu.skku.cs.skkueats.RecommendConditions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import edu.skku.cs.skkueats.MenuRecommendedList.MenuRecommendsView;
import edu.skku.cs.skkueats.R;


public class RecommendConditionsView extends AppCompatActivity implements RecommendConditionsContract.contactView {
    private Bundle savedInstanceState;
    private RecommendConditionsModel model;

    ConstraintLayout completeButton;

    ConstraintLayout boxBigCategory;
    ConstraintLayout boxSmallCategory;
    ConstraintLayout boxPrice;
    ConstraintLayout boxPurpose;
    ConstraintLayout boxLocation;

    RadioGroup bigCategoryCondition;
    RadioGroup smallCategoryCondition;
    ConstraintLayout priceCondition;
    RadioGroup purposeCondition;
    RadioGroup locationCondition;

    TextView textViewBigCategoryArrow;
    TextView textViewSmallCategoryArrow;
    TextView textViewPriceArrow;
    TextView textViewPurposeArrow;
    TextView textViewLocationArrow;


    CheckBox checkBoxActivatePrice;
    TextView textViewPriceTitleText;
    TextView textViewPriceText;
    SeekBar seekBarPrice;
    SeekBar seekBarMinGrade;

    CheckBox checkBoxBigKorean;
    CheckBox checkBoxBigChinese;
    CheckBox checkBoxBigJapanese;
    CheckBox checkBoxBigWestern;
    CheckBox checkBoxBigAsian;
    CheckBox checkBoxBigFast;
    CheckBox checkBoxBigAll;

    CheckBox checkBoxPurposeSolo   ;
    CheckBox checkBoxPurposeFriend ;
    CheckBox checkBoxPurposeCouple ;
    CheckBox checkBoxPurposeGroup  ;
    CheckBox checkBoxPurposeAlcohol;
    CheckBox checkBoxPurposeAll    ;


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
    private String id;
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

        id = getIntent().getStringExtra("id");
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

        boxPurpose.setOnClickListener(new View.OnClickListener() {
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

        checkBoxActivatePrice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                priceTextChange();
            }
        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int priceValue;
                if (checkBoxActivatePrice.isChecked() && visibilityLevel >= 3) {
                    priceValue = seekBarPrice.getProgress()*1000;
                } else {
                    priceValue = 0;
                }

                if (priceValue == 0) {
                    priceValue = 999999;
                }

                RecommendQueryCondition recommendQueryCondition = new RecommendQueryCondition(
                        makeBigCategoryQueryString(),
                        makeSmallCategoryQueryString(),
                        priceValue,
                        makePurposeQueryString(),
                        makeLocationQueryString(),
                        3
                );

                Intent intent = new Intent(getApplicationContext(), MenuRecommendsView.class);
                String[] conditions = new String[]{
                        recommendQueryCondition.bigCategory,
                        recommendQueryCondition.smallCategory,
                        Integer.toString(recommendQueryCondition.price),
                        recommendQueryCondition.purpose,
                        recommendQueryCondition.location,
                        Integer.toString(recommendQueryCondition.maxNum)};
                intent.putExtra("conditions", conditions);
                intent.putExtra("id", id);
                /*Toast.makeText(getApplicationContext(), conditions[0]+" "+conditions[1]+" "+conditions[2]+" "+
                        conditions[3]+" "+conditions[4]+" "+conditions[5], Toast.LENGTH_SHORT).show();
                */
                startActivity(intent);
            }
        });





    }

    public void priceTextChange() {
        if (checkBoxActivatePrice.isChecked())
            textViewPriceTitleText.setText(String.format("최대가격: %d ₩", seekBarPrice.getProgress()*1000));
        else
            textViewPriceTitleText.setText(String.format("최대가격: 상관없음"));
    }


    @Override
    public void initView() {
        completeButton = findViewById(R.id.boxRecommendComplete);

        boxBigCategory = findViewById(R.id.constraintLayoutBigCategory);
        boxSmallCategory = findViewById(R.id.constraintLayoutSmallCategory);
        boxPrice = findViewById(R.id.constraintLayoutPrice);
        boxPurpose = findViewById(R.id.constraintLayoutPurpose);
        boxLocation = findViewById(R.id.constraintLayoutLocation);

        bigCategoryCondition = findViewById(R.id.BigCategoryCondition);
        smallCategoryCondition = findViewById(R.id.SmallCategoryCondition);
        priceCondition = findViewById(R.id.PriceCondition);
        purposeCondition = findViewById(R.id.PurposeCondition);
        locationCondition = findViewById(R.id.LocationCondition);

        textViewBigCategoryArrow = findViewById(R.id.textViewBigCategoryArrow);
        textViewSmallCategoryArrow = findViewById(R.id.textViewSmallCategoryArrow);
        textViewPriceArrow = findViewById(R.id.textViewPriceArrow);
        textViewPurposeArrow = findViewById(R.id.textViewPurposeArrow);
        textViewLocationArrow = findViewById(R.id.textViewLocationArrow);

        seekBarPrice = findViewById(R.id.seekBarPrice);
        textViewPriceTitleText = findViewById(R.id.textViewPriceTitleText);
        textViewPriceText = findViewById(R.id.textViewPriceText);
        checkBoxActivatePrice = findViewById(R.id.checkBoxActivatePrice);

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

        checkBoxPurposeSolo   =   findViewById(R.id.checkBoxPurposeSolo);
        checkBoxPurposeFriend    =   findViewById(R.id.checkBoxPurposeFriend);
        checkBoxPurposeCouple      =   findViewById(R.id.checkBoxPurposeCouple);
        checkBoxPurposeGroup   =   findViewById(R.id.checkBoxPurposeGroup);
        checkBoxPurposeAlcohol   =   findViewById(R.id.checkBoxPurposeAlcohol);
        checkBoxPurposeAll         =   findViewById(R.id.checkBoxPurposeAll);

        checkBoxLocationFrontDoor   =   findViewById(R.id.checkBoxLocationFrontDoor);
        checkBoxLocationBackDoor    =   findViewById(R.id.checkBoxLocationBackDoor);
        checkBoxLocationAcross      =   findViewById(R.id.checkBoxLocationAcross);
        checkBoxLocationElsewhere   =   findViewById(R.id.checkBoxLocationElsewhere);
        checkBoxLocationAll         =   findViewById(R.id.checkBoxLocationAll);


        // 초기에는 대분류 빼곤 다 비활성화상태
        smallCategoryCondition.setVisibility(View.GONE);
        boxPrice.setVisibility(View.GONE);
        priceCondition.setVisibility(View.GONE);
        boxPurpose.setVisibility(View.GONE);
        purposeCondition.setVisibility(View.GONE);
        boxLocation.setVisibility(View.GONE);
        locationCondition.setVisibility(View.GONE);

        visibilityLevel = 1;
        showConditions(visibilityLevel);
    }


    public String makeBigCategoryQueryString() {
        String res = "";
        if (checkBoxBigAll.isChecked()) {
            res += "모두";
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
            return "모두";
        }
        return res;
    }
    public String makeSmallCategoryQueryString() {
        String res = "";
        if (checkBoxSmallAll.isChecked()) {
            res += "모두";
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
            return "모두";
        }
        return res;
    }

    public String makePurposeQueryString() {
        String res = "";
        if (checkBoxPurposeAll.isChecked()) {
            res += "모두";
        } else {
            if (checkBoxPurposeSolo.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "혼밥";
            }
            if (checkBoxPurposeAlcohol.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "술";
            }
            if (checkBoxPurposeCouple.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "연인";
            }
            if (checkBoxPurposeFriend.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "친구";
            }
            if (checkBoxPurposeGroup.isChecked()) {
                if (!res.isEmpty()) {
                    res += ",";
                }
                res += "모임";
            }
        }
        if (res.isEmpty()) {
            return "모두";
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
            boxPurpose.setVisibility(View.VISIBLE);
            if (visibilityLevel == 3) {
                textViewPurposeArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.black));
            } else {
                textViewPurposeArrow.setTextColor(ContextCompat.getColor(getApplicationContext(),
                        R.color.blackGrey));
            }
        }

        if (visibilityLevel >= 4) {
            purposeCondition.setVisibility(View.VISIBLE);
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