package edu.skku.cs.skkueats.RestaurantInfo;

public class RestaurantInfoPresenter implements RestaurantInfoContract.contactPresenter {
    RestaurantInfoContract.contactView view;
    RestaurantInfoModel model;
    String restaurantName;
    public RestaurantInfoPresenter(RestaurantInfoContract.contactView view, String restaurantName) {
        this.view = view;
        this.model = new RestaurantInfoModel();
        this.restaurantName = restaurantName;
    }

    @Override
    public void initRestaurant() {

    }
}
