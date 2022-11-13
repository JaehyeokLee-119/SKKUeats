package edu.skku.cs.skkueats.RestaurantInfo;

import java.util.ArrayList;

public interface RestaurantInfoContract {
    interface contactView {
        void showReview(String writer, String menu, int grade, String content, boolean isTroll);
        void setMapCamera(double latitude, double longitude);
    }

    interface contactModel {
        interface onFinished_Listener{
            void onFinished(ArrayList<RestaurantReview> reviewArray);
        }
        void sendRequest(onFinished_Listener onFinished_listener);
    }

    interface contactPresenter {
        void initRestaurant();
        void onMenuButtonTouched();
        void onNewReviewButtonTouched();
        void pushReviewsToViewer(ArrayList<RestaurantReview> reviewArrays);
    }

}
