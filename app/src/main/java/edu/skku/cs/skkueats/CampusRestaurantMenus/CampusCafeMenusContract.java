package edu.skku.cs.skkueats.CampusRestaurantMenus;

import java.util.ArrayList;

public interface CampusCafeMenusContract {
    interface contactView {
        void showMenuLunch(String menu, double grade, int price);
        void showMenuDinner(String menu, double grade, int price);
    }

    interface contactModel {
        void fetchCampusCafeMenus();
        void pushMenusToViewer(ArrayList<RestaurantMenu> reviewArrays);
    }
}
