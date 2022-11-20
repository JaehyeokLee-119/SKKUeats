package edu.skku.cs.skkueats.MyProfile;

public class MyProfileReview {
    public String restaurantName;
    public String menu;
    public int grade;
    public String content;
    public boolean isTroll;

    public MyProfileReview(String restaurantName, String menu, int grade, String content, boolean isTroll) {
        this.restaurantName = restaurantName;
        this.menu = menu;
        this.grade = grade;
        this.content = content;
        this.isTroll = isTroll;
    }

    public MyProfileReview(MyProfileReview myProfileReview) {
        this.restaurantName = myProfileReview.restaurantName;
        this.menu = myProfileReview.menu;
        this.grade = myProfileReview.grade;
        this.content = myProfileReview.content;
        this.isTroll = myProfileReview.isTroll;
    }
}
