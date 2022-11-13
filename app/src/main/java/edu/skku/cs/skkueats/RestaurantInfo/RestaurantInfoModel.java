package edu.skku.cs.skkueats.RestaurantInfo;

import java.util.ArrayList;

public class RestaurantInfoModel implements RestaurantInfoContract.contactModel {
    public String restaurantName;
    private ArrayList<RestaurantReview> reviewArray;

    public RestaurantInfoModel(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public void fetchReviews(final onFinished_Listener onFinished_listener, String restaurantName) {
        reviewArray.add(new RestaurantReview("rPwjd1", "돈가스김치나베", 5,
                "돈가스김치나베 맛있어요", false));

        reviewArray.add(new RestaurantReview("rPwjd15", "카베동", 3,
                "리뷰내용", false));
        reviewArray.add(new RestaurantReview("rPwjd15", "카베동", 3,
                "리뷰내용", false));

        reviewArray.add(new RestaurantReview("rPw24jd1", "치킨동", 2,
                "치킨동맛있어요", true));
        reviewArray.add(new RestaurantReview("r125jd1", "치킨동", 2,
                "치킨동맛있어요", true));
        reviewArray.add(new RestaurantReview("rPw521d1", "카베동", 3,
                "리뷰내용", false));
        onFinished_listener.onFinished(reviewArray);
    }

    @Override
    public void sendRequest(onFinished_Listener onFinished_listener) {

    }
    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */
}
