package edu.skku.cs.skkueats.SearchActivity;

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

class SearchResult {
    public String restaurantName;
    public String menuName;
    public int price;
    public String locations;

    public SearchResult(String restaurantName, String menuName, int price, String locations) {
        this.restaurantName = restaurantName;
        this.menuName = menuName;
        this.price = price;
        this.locations = locations;
    }
    public SearchResult(SearchResult searchResult) {
        this.restaurantName = searchResult.restaurantName;
        this.menuName = searchResult.menuName;
        this.price = searchResult.price;
        this.locations = searchResult.locations;
    }
}


public class SearchActivityAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SearchResult> items;

    public SearchActivityAdapter(Context mContext, ArrayList<SearchResult> items) {
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
            view = inflater.inflate(R.layout.search_result_item, viewGroup, false);
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
                intent.putExtra("RestaurantName",items.get(i).restaurantName);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
