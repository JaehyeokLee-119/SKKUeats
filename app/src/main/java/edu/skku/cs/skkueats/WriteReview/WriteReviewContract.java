package edu.skku.cs.skkueats.WriteReview;

import org.json.JSONException;

public interface WriteReviewContract {
    interface contactModel {
        void fetchMenus(String restaurantName);

        void reviewUpload(MenuReview menuReview, String id) throws JSONException;
    }

    interface contactView {
        void setSpinnerMenus(String[] menuList);
        void reviewComplete(String strToShow);
        void initView();
    }
}
