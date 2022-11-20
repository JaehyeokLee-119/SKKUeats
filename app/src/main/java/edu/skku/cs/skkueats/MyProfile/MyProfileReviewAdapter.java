package edu.skku.cs.skkueats.MyProfile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;


public class MyProfileReviewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<MyProfileReview> items;
    private String id;

    public MyProfileReviewAdapter(Context mContext, ArrayList<MyProfileReview> items, String id) {
        this.mContext = mContext;
        this.items = items;
        this.id = id;
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
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.profile_review_item, viewGroup, false);
        }
        TextView textViewRestaurantName = view.findViewById(R.id.textViewPRIrestaurantName);
        TextView textViewMenuName = view.findViewById(R.id.textViewPRImenuName);
        TextView textViewGradeStar = view.findViewById(R.id.textViewPRIGradeStar);
        TextView textViewReviewContent = view.findViewById(R.id.textViewPRIReviewContent);

        textViewRestaurantName.setText("식당: "+items.get(i).restaurantName);
        textViewMenuName.setText("메뉴: "+items.get(i).menu);
        String starString = "";
        int j = 0;
        for (; j < items.get(i).grade; j++) {
            starString += "★";
        }

        for (;j < 5; j++) {
            starString += "☆";
        }

        textViewGradeStar.setText(starString);
        textViewReviewContent.setText(items.get(i).content);


//        view.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(mContext.getApplicationContext(), RestaurantInfoView.class);
//                intent.putExtra("RestaurantName", items.get(i).restaurantName);
//                intent.putExtra("id", id);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
//            }
//        });


        // ListView 클릭 비활성화
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;

            }
        });
        return view;
    }
}
