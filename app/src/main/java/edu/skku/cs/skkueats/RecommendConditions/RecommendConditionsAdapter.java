package edu.skku.cs.skkueats.RecommendConditions;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;


class RecommendConditionItem {
    public String conditionName;
    public String itemType;
        // =="Radio" =="SeekBar"
    public boolean conditionActivated;
    public int price;
    public double minGrade;


    public RecommendConditionItem(String conditionName, boolean conditionActivated, int price) {
        this.conditionName = conditionName;
        this.conditionActivated = conditionActivated;
        this.price = price;
    }
    public RecommendConditionItem(RecommendConditionItem recommendConditionItem) {
        this.conditionName = recommendConditionItem.conditionName;
        this.conditionActivated = recommendConditionItem.conditionActivated;
        this.price = recommendConditionItem.price;
        this.minGrade = recommendConditionItem.minGrade;
    }
}


public class RecommendConditionsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<RecommendConditionItem> items;

    public RecommendConditionsAdapter(Context mContext, ArrayList<RecommendConditionItem> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if (items.get(i).itemType=="Radio") {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.recommend_condition_big_category_item, viewGroup, false);
            }
            TextView textViewRCCIconditionActivation = view.findViewById(R.id.textViewBigCategoryArrow);

        }
        /* 결과적인 형태
            대분류 ↓
            ― 조건선택 (조건 이름에 맞는 적절한 형태의 조건)
            소분류 ↓
            ― 조건선택
            가격 ↓ (화살표 클릭시 조건선택,다음조건이름이 추가되면서 [추천받기] 버튼은 아래로 밀린다)
            [  추천받기  ]

        (가격의 화살표를 눌렀을 때)

            대분류 ↓
            ― 조건선택 (조건 이름에 맞는 적절한 형태의 조건)
            소분류 ↓
            ― 조건선택
            가격 ↓
            ― 조건선택(SeekBar)
            최소평점 ↓
            [  추천받기  ]

         */



        TextView restaurantName = view.findViewById(R.id.textViewMMIrestaurantName);
        TextView menuName = view.findViewById(R.id.textViewMMImenuName);
        TextView menuContent = view.findViewById(R.id.textViewMMImenuContent);
        ConstraintLayout box = view.findViewById(R.id.boxRecommendComplete);

        
        // 박스모양+색깔 랜덤배정
        int shapeItems[] = {R.drawable.shape_for_soft_rectangle_pastelblue,
                R.drawable.shape_for_soft_rectangle_pastelred,
                R.drawable.shape_for_soft_rectangle_pastelgreen,
                R.drawable.shape_for_soft_rectangle_pastelorange,
                R.drawable.shape_for_soft_rectangle_pastelpurple};
        Random rand = new Random();
        box.setBackgroundResource(shapeItems[rand.nextInt(shapeItems.length)]);

        restaurantName.setText(items.get(i).conditionName);


        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext.getApplicationContext(), RestaurantInfoView.class);
                intent.putExtra("RestaurantName",items.get(i).conditionName);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
