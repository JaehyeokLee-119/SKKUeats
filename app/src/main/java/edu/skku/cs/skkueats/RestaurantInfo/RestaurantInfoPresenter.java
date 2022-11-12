package edu.skku.cs.skkueats.RestaurantInfo;

public class RestaurantInfoPresenter implements RestaurantInfoContract.contactPresenter {
    RestaurantInfoContract.contactView view;
    RestaurantInfoModel model;
    public RestaurantInfoPresenter(RestaurantInfoContract.contactView view) {
        this.view = view;
        this.model = new RestaurantInfoModel();
    }

    @Override
    public void initRestaurant() {

    }
}
