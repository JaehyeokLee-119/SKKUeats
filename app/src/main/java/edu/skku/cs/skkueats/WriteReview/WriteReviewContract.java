package edu.skku.cs.skkueats.WriteReview;

public interface WriteReviewContract {
    interface contactModel {
        void fetchMenus(String restaurantName);

        void reviewUpload(MenuReview menuReview, String id);
    }

    interface contactView {
        void setSpinnerMenus(String[] menuList);
        void reviewComplete();
        void initView();
    }
}
