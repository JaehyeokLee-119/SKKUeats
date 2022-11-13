package edu.skku.cs.skkueats.RestaurantInfo;

import java.util.ArrayList;

public class RestaurantInfoPresenter implements RestaurantInfoContract.contactPresenter {
    private RestaurantInfoContract.contactView view;
    private RestaurantInfoModel model;
    private String restaurantName;
    private ArrayList<RestaurantReview> reviewArray;

    public RestaurantInfoPresenter(RestaurantInfoContract.contactView view, String restaurantName) {
        this.view = view;
        this.restaurantName = restaurantName;
        this.model = new RestaurantInfoModel(restaurantName);
        this.reviewArray = new ArrayList<>();

        initRestaurant();

        view.showReview("rPwjd1", "돈가스김치나베", 5, "돈가스김치나베 맛있어요", false);
        view.showReview("rPwjd15", "카베동", 3, "리뷰내용", false);
        view.showReview("rPw55jd1", "치킨동", 4, "치킨동맛있어요", true);
        view.showReview("rPw24jd1", "치킨동", 2, "치킨동맛있어요", true);
        view.showReview("r125jd1", "치킨동", 2, "치킨동맛있어요", true);
        view.showReview("rPw24d1", "치킨동", 2, "치킨동맛있어요", true);
        view.showReview("rPw521d1", "치킨동", 2, "치킨동맛있어요", true);

        /*
        model에게 db에서 review 받아오게 시키기
        model에게 db에서 가게 메뉴 정보, 가게 위치(위도, 경도) 받아오게 시키기
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

    @Override
    public void pushReviewsToViewer(ArrayList<RestaurantReview> reviewArrays) {
        RestaurantReview restaurantReview;
        for(int i = 0; i < reviewArrays.size(); i++) {
            restaurantReview = new RestaurantReview(reviewArrays.get(i));
            view.showReview(restaurantReview.writer, restaurantReview.menu, restaurantReview.grade,
                    restaurantReview.content, restaurantReview.isTroll);
        }
    }

}
