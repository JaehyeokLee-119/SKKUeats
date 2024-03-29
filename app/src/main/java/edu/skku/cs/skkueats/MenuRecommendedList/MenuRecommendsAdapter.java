package edu.skku.cs.skkueats.MenuRecommendedList;

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


class MenuRecommends {
    public String restaurantName;
    public String menuName;
    public int price;
    public String locations;

    public MenuRecommends(String restaurantName, String menuName, int price, String locations) {
        this.restaurantName = restaurantName;
        this.menuName = menuName;
        this.price = price;
        this.locations = locations;
    }
    public MenuRecommends(MenuRecommends menuRecommends) {
        this.restaurantName = menuRecommends.restaurantName;
        this.menuName = menuRecommends.menuName;
        this.price = menuRecommends.price;
        this.locations = menuRecommends.locations;
    }
}


public class MenuRecommendsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<MenuRecommends> items;
    private String id;

    public MenuRecommendsAdapter(Context mContext, ArrayList<MenuRecommends> items, String id) {
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
        menuName.setText(items.get(i).menuName);
        menuContent.setText(Integer.toString(items.get(i).price)+" ₩\n"+items.get(i).locations);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext.getApplicationContext(), RestaurantInfoView.class);
                intent.putExtra("id", id);
                intent.putExtra("RestaurantName", items.get(i).restaurantName);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
