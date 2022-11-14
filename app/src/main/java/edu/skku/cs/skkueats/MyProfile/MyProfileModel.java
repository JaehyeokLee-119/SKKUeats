package edu.skku.cs.skkueats.MyProfile;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

public class MyProfileModel implements MyProfileContract.contactModel {
    public String userId;
    private ArrayList<MyProfileReview> reviewArray;
    private MyProfileContract.contactView view;

    public MyProfileModel(MyProfileContract.contactView view, String userId) {
        this.userId = userId;
        this.view = view;
        reviewArray = new ArrayList<>();
        fetchReviews(userId);
    }

    @Override
    public void fetchReviews(String userId) {
        // restaurantName으로 db에 요청해서 리뷰 정보를 다 받아와야 함
        reviewArray.add(new MyProfileReview("미가라멘", "돈가스김치나베", 5,
                "돈가스김치나베 맛있어요", false));

        reviewArray.add(new MyProfileReview("미가라멘", "카베동", 3,
                "리뷰내용", false));
        reviewArray.add(new MyProfileReview("미가라멘", "카베동", 3,
                "리뷰내용", false));

        reviewArray.add(new MyProfileReview("미가라멘", "치킨동", 2,
                "치킨동맛있어요", true));
        reviewArray.add(new MyProfileReview("미가라멘", "치킨동", 2,
                "치킨동맛있어요", true));
        reviewArray.add(new MyProfileReview("미가라멘", "카베동", 3,
                "리뷰내용", false));

        // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
        pushReviewsToViewer(reviewArray);
    }

    @Override
    public void pushReviewsToViewer(ArrayList<MyProfileReview> reviewArrays) {
        MyProfileReview myProfileReview;
        for(int i = 0; i < reviewArrays.size(); i++) {
            myProfileReview = new MyProfileReview(reviewArrays.get(i));
            view.showReview(myProfileReview.restaurantName, myProfileReview.menu, myProfileReview.grade,
                    myProfileReview.content, myProfileReview.isTroll);
        }
    }
}
