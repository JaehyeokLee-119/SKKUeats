package edu.skku.cs.skkueats.SearchActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Dimension;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;

class SearchResult {
    public String restaurantName;
    public String menus;
    public int price;
    public String locations;
    public int type;

    public SearchResult(String restaurantName, String menus, int price, String locations, int type) {
        this.restaurantName = restaurantName;
        this.menus = menus;
        this.price = price;
        this.locations = locations;
        this.type = type;
    }
    public SearchResult(SearchResult searchResult) {
        this.restaurantName = searchResult.restaurantName;
        this.menus = searchResult.menus;
        this.price = searchResult.price;
        this.locations = searchResult.locations;
        this.type = searchResult.type;
    }
}


public class SearchActivityAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SearchResult> items;
    private String id;

    public SearchActivityAdapter(Context mContext, ArrayList<SearchResult> items, String id) {
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
            view = inflater.inflate(R.layout.search_result_item, viewGroup, false);
        }
        TextView textViewRestaurantName = view.findViewById(R.id.textViewSRIrestaurantName);
        TextView textViewGrade = view.findViewById(R.id.textViewSRIgrade);
        TextView textViewMenus = view.findViewById(R.id.textViewSRImenus);
        TextView textViewLocation = view.findViewById(R.id.textViewSRIlocation);
        ConstraintLayout box = view.findViewById(R.id.boxSRIresult);

        
        // 박스모양+색깔 랜덤배정
        /*int shapeItems[] = {R.drawable.shape_for_soft_rectangle_pastelblue,
                R.drawable.shape_for_soft_rectangle_pastelred,
                R.drawable.shape_for_soft_rectangle_pastelgreen,
                R.drawable.shape_for_soft_rectangle_pastelorange,
                R.drawable.shape_for_soft_rectangle_pastelpurple};*/
        int shapeItems[] = {R.drawable.shape_for_soft_rectangle_grey};
        Random rand = new Random();
        box.setBackgroundResource(shapeItems[rand.nextInt(shapeItems.length)]);

        textViewRestaurantName.setText(items.get(i).restaurantName);
        if(items.get(i).type == 1) {
            textViewGrade.setText(items.get(i).price+" ₩");
            textViewLocation.setText(items.get(i).locations);
        }
        else if (items.get(i).type == 0) {
            textViewGrade.setText(items.get(i).locations);
            textViewLocation.setText("");
            textViewMenus.setTextSize(Dimension.SP, 10);
        }
        textViewMenus.setText(items.get(i).menus);


        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext.getApplicationContext(), RestaurantInfoView.class);
                intent.putExtra("RestaurantName",items.get(i).restaurantName);
                intent.putExtra("id", id);
                mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        return view;
    }
}
