package edu.skku.cs.skkueats.RestaurantInfo;

import java.util.ArrayList;

public class RestaurantInfoModel implements RestaurantInfoContract.contactModel {
    public String restaurantName;
    private ArrayList<RestaurantReview> reviewArray = new ArrayList<>();

}
