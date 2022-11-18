package edu.skku.cs.skkueats.WriteReview;

import java.util.ArrayList;

class MenuReview {
    public String restaurantName;
    public String menu;
    public double grade;
    public String reviewContent;

    public MenuReview(String restaurantName, String menu, double grade, String reviewContent) {
        this.restaurantName = restaurantName;
        this.menu = menu;
        this.grade = grade;
        this.reviewContent = reviewContent;
    }
    public MenuReview(MenuReview menuReview) {
        this.restaurantName = menuReview.restaurantName;
        this.menu = menuReview.menu;
        this.grade = menuReview.grade;
        this.reviewContent = menuReview.reviewContent;
    }
}

public class WriteReviewModel implements WriteReviewContract.contactModel{
    private WriteReviewContract.contactView view;
    private ArrayList<String> menus;
    private String restaurantName;

    public WriteReviewModel(WriteReviewContract.contactView view, String restaurantName) {
        this.view = view;
        this.menus = new ArrayList<>();
        this.restaurantName = restaurantName;
        fetchMenus(restaurantName);
    }

    @Override
    public void fetchMenus(String restaurantName) {
        menus.add("미가돈코츠라멘");
        menus.add("사케동");
        menus.add("쇼유라멘");
        menus.add("치킨동");

        String[] menuStringArray = menus.toArray(new String[menus.size()]);
        view.setSpinnerMenus(menuStringArray);
    }

    @Override
    public void reviewUpload(MenuReview menuReview, String id) {
        // 리뷰(menuReview)를 서버에 보냄

        view.reviewComplete();
    }


}
