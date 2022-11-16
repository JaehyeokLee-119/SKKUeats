package edu.skku.cs.skkueats.CampusRestaurantMenus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;

class RestaurantMenu {
    public String menu;
    public double grade;
    public int price;

    public RestaurantMenu(String menu, double grade, int price) {
        this.menu = menu;
        this.grade = grade;
        this.price = price;
    }
    public RestaurantMenu(RestaurantMenu restaurantmenu) {
        this.menu = restaurantmenu.menu;
        this.grade = restaurantmenu.grade;
        this.price = restaurantmenu.price;
    }
}


public class CampusCafeMenusAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<RestaurantMenu> items;

    public CampusCafeMenusAdapter(Context mContext, ArrayList<RestaurantMenu> items) {
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
            view = inflater.inflate(R.layout.restaurant_menu_item, viewGroup, false);
        }
        TextView textViewMenuName = view.findViewById(R.id.textViewRMImenuName);
        TextView textViewGrade = view.findViewById(R.id.textViewRMIgrade);
        TextView textViewPrice = view.findViewById(R.id.textViewRMIprice);

        textViewMenuName.setText(items.get(i).menu);
        textViewGrade.setText(Double.toString(items.get(i).grade)+" 점");
        textViewPrice.setText(items.get(i).price+"  ₩");

        // ListView 클릭 비활성화
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;

            }});

        return view;
    }
}
