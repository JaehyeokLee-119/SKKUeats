package edu.skku.cs.skkueats.CampusRestaurantMenus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;

class RestaurantMenu {
    public String restaurantName;
    public String locations;

    public RestaurantMenu(String restaurantName, String locations) {
        this.restaurantName = restaurantName;
        this.locations = locations;
    }

    public RestaurantMenu(edu.skku.cs.skkueats.CampusRestaurantMenus.RestaurantMenu menuRecommends) {
        this.restaurantName = menuRecommends.restaurantName;
        this.locations = menuRecommends.locations;
    }
}


public class CampusCafeMenusAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<RestaurantMenu> items;
    private String id;

    public CampusCafeMenusAdapter(Context mContext, ArrayList<RestaurantMenu> items, String id) {
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
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_recommend_item, viewGroup, false);
        }
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

        restaurantName.setText(items.get(i).restaurantName);
        menuName.setText("");
        menuContent.setText(items.get(i).locations);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext.getApplicationContext(), RestaurantInfoView.class);
                intent.putExtra("id", id);
                intent.putExtra("RestaurantName", items.get(i).restaurantName);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
