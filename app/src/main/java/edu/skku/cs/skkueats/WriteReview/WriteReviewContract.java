package edu.skku.cs.skkueats.WriteReview;

public interface WriteReviewContract {
    interface contactModel {
        void fetchMenus(String restaurantName);
        int reviewUpload();
    }

    interface contactView {
        void setSpinnerMenus(String[] menuList);
        void initView();
    }
}
