package edu.skku.cs.skkueats.RestaurantInfo;

public interface RestaurantInfoContract {
    interface contactView {
        void newReview(String writer, String menu, int grade, String content, boolean isTroll);
    }

    interface contactModel {

    }

    interface contactPresenter {
        void initRestaurant();

    }
}
