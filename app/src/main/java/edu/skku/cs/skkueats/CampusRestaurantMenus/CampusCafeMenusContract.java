package edu.skku.cs.skkueats.CampusRestaurantMenus;

import java.util.ArrayList;

public interface CampusCafeMenusContract {
    interface contactView {
        void showCafes(RestaurantMenu restaurantMenu);
    }

    interface contactModel {
        void fetchCampusCafeMenus();
        void pushMenusToViewer(ArrayList<RestaurantMenu> reviewArrays);
    }
}
