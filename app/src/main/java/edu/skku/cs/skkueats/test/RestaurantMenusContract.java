package edu.skku.cs.skkueats.test;

import java.util.ArrayList;

public interface RestaurantMenusContract {
    interface contactView {
        void showMenu(String menu, String grade, int price);
    }

    interface contactModel {
        void fetchMenus(String restaurantName);
        void pushMenusToViewer(ArrayList<RestaurantMenu> reviewArrays);
    }
}
