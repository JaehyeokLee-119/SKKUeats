package edu.skku.cs.skkueats.CampusCafeInfo;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

import edu.skku.cs.skkueats.CampusCafeInfo.RestaurantReview;

public class CampusCafeInfoModel implements CampusCafeInfoContract.contactModel {
    public String restaurantName;
    private ArrayList<RestaurantReview> reviewArray;
    private CampusCafeInfoContract.contactView view;
    private LatLng latLng; // 지도에 표시할 위도/경도

    public CampusCafeInfoModel(CampusCafeInfoContract.contactView view, String restaurantName) {
        this.restaurantName = restaurantName;
        this.view = view;
        reviewArray = new ArrayList<>();
        latLng = new LatLng(37.293363799999916,  126.9746702539383);
        fetchReviews(restaurantName);
    }

    public void onMapLoaded() {
        fetchLatLng(restaurantName);
    }

    @Override
    public void fetchReviews(String restaurantName) {
        // restaurantName으로 db에 요청해서 리뷰 정보를 다 받아와야 함
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

        if (restaurantName.equals("학생회관(행단골)")) {

        } else if (restaurantName.equals("교직원식당(구시재)")){

        } else if (restaurantName.equals("공대식당(해오름)")){

        } else { //if (restaurantName == "기숙사식당"){

        }

        // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
        pushReviewsToViewer(reviewArray);
    }

    @Override
    public void fetchLatLng(String restaurantName) {
        // 4가지 학식 식당 종류에 따라 정의된 위도/경도를 표시
        if (restaurantName.equals("학생회관(행단골)")) {
            latLng = new LatLng(37.29718,  126.97018);
        } else if (restaurantName.equals("교직원식당(구시재)")){
            latLng = new LatLng(37.29718,  126.97018);
        } else if (restaurantName.equals("공대식당(해오름)")){
            latLng = new LatLng(37.29718,  126.97018);
        } else { //if (restaurantName == "기숙사식당"){
            latLng = new LatLng(37.29718,  126.97018);
        }

        // 위도, 경도 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
        pushMapCamera(latLng);
    }
    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */

    @Override
    public void pushReviewsToViewer(ArrayList<RestaurantReview> reviewArrays) {
        RestaurantReview restaurantReview;
        for(int i = 0; i < reviewArrays.size(); i++) {
            restaurantReview = new RestaurantReview(reviewArrays.get(i));
            view.showReview(restaurantReview.writer, restaurantReview.menu, restaurantReview.grade,
                    restaurantReview.content, restaurantReview.isTroll);
        }
    }

    @Override
    public void pushMapCamera(LatLng latLng) {
        view.setMapCamera(latLng.latitude, latLng.longitude);
    }
}