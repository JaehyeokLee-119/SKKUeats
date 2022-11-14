package edu.skku.cs.skkueats.MyProfile;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

public interface MyProfileContract {
    interface contactView {
        void showReview(String userId, String menu, int grade, String content, boolean isTroll);
    }

    interface contactModel {
        void fetchReviews(String userId);
        void pushReviewsToViewer(ArrayList<MyProfileReview> reviewArrays);
    }
}
