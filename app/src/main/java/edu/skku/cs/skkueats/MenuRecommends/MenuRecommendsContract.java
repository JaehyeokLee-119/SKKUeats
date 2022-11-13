package edu.skku.cs.skkueats.MenuRecommends;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

public interface MenuRecommendsContract {
    interface contactView {
        void showRecommend(MenuRecommends menuRecommends);
    }

    interface contactModel {
        void fetchRecommends(String restaurantName);
        void pushRecommendsToViewer(ArrayList<MenuRecommends> reviewArrays);
    }
}
