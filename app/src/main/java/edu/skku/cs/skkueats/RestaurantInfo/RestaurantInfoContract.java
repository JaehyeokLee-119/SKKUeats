package edu.skku.cs.skkueats.RestaurantInfo;

public interface RestaurantInfoContract {
    interface contactView {
        void showReview(String writer, String menu, int grade, String content, boolean isTroll);
        void setMapCamera(double latitude, double longitude);
    }

    interface contactModel {

    }

    interface contactPresenter {
        void initRestaurant();
        void onMenuButtonTouched();
        void onNewReviewButtonTouched();
    }

}
