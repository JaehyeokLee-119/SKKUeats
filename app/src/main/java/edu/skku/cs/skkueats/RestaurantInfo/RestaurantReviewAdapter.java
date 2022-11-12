package edu.skku.cs.skkueats.RestaurantInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;

class RestaurantReview {
    public String writer;
    public String menu;
    public int grade;
    public String content;
    public boolean isTroll;

    public RestaurantReview(String writer, String menu, int grade, String content, boolean isTroll) {
        this.writer = writer;
        this.menu = menu;
        this.grade = grade;
        this.content = content;
        this.isTroll = isTroll;
    }
}


public class RestaurantReviewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<RestaurantReview> items;

    public RestaurantReviewAdapter(Context mContext, ArrayList<RestaurantReview> items) {
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
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.restaurant_review_item, viewGroup, false);
        }
        TextView writerName = view.findViewById(R.id.textViewRRIWriter);
        TextView menuName = view.findViewById(R.id.textViewRRIMenuName);
        TextView gradeStar = view.findViewById(R.id.textViewRRIGradeStar);
        TextView reviewContent = view.findViewById(R.id.textViewRRIReviewContent);

        writerName.setText("작성자: "+items.get(i).writer);
        menuName.setText("메뉴: "+items.get(i).menu);
        String starString = "";
        int j = 0;
        for (; j < items.get(i).grade; j++) {
            starString += "★";
        }

        for (;j < 5; j++) {
            starString += "☆";
        }

        gradeStar.setText(starString);
        reviewContent.setText(items.get(i).content);

        // 클릭 비활성화
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;

            }});

        return view;
    }
}
