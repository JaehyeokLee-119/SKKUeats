package edu.skku.cs.skkueats.RestaurantInfo;

public class RestaurantInfoPresenter implements RestaurantInfoContract.contactPresenter {
    RestaurantInfoContract.contactView view;
    RestaurantInfoModel model;
    String restaurantName;
    public RestaurantInfoPresenter(RestaurantInfoContract.contactView view, String restaurantName) {
        this.view = view;
        this.model = new RestaurantInfoModel();
        this.restaurantName = restaurantName;

        initRestaurant();
        /*
        view.showReview("rPwjd1", "돈가스김치나베", 5, "돈가스김치나베 맛있어요", false);
        view.showReview("rPwjd15", "카베동", 3, "리뷰내용", false);
        view.showReview("rPw55jd1", "치킨동", 4, "치킨동맛있어요", true);
        view.showReview("rPw24jd1", "치킨동", 2, "치킨동맛있어요", true);
        view.showReview("r125jd1", "치킨동", 2, "치킨동맛있어요", true);
        view.showReview("rPw24d1", "치킨동", 2, "치킨동맛있어요", true);
        view.showReview("rPw521d1", "치킨동", 2, "치킨동맛있어요", true);
        */
    }

    @Override
    public void initRestaurant() {
        //view.setMapCamera(37.29718,  126.97018);
    }

    @Override
    public void onMenuButtonTouched() {

    }

    @Override
    public void onNewReviewButtonTouched() {

    }

}
