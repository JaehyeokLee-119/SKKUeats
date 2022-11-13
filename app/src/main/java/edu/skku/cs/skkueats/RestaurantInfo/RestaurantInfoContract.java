package edu.skku.cs.skkueats.RestaurantInfo;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

public interface RestaurantInfoContract {
    interface contactView {
        void showReview(String writer, String menu, int grade, String content, boolean isTroll);
        void setMapCamera(double latitude, double longitude);
    }

    interface contactModel {
        void fetchReviews(String restaurantName);
        void pushReviewsToViewer(ArrayList<RestaurantReview> reviewArrays);
        void fetchLatLng(String restaurantName);
        void pushMapCamera(LatLng latLng);
    }
}
