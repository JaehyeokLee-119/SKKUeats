package edu.skku.cs.skkueats.CampusRestaurantMenus;

import java.util.ArrayList;

public class CampusCafeMenusModel implements CampusCafeMenusContract.contactModel {
    private ArrayList<RestaurantMenu> cafeArray;
    private CampusCafeMenusContract.contactView view;

    public CampusCafeMenusModel(CampusCafeMenusContract.contactView view) {
        this.view = view;
        cafeArray = new ArrayList<>();
        fetchCampusCafeMenus();
    }

    @Override
    public void fetchCampusCafeMenus() {
        // restaurantName으로 db에 요청해서 리뷰 정보를 다 받아와야 함
        cafeArray.add(new RestaurantMenu("학생회관(행단골)", "학내"));
        cafeArray.add(new RestaurantMenu("교직원식당(구시재)", "학내"));
        cafeArray.add(new RestaurantMenu("공대식당(해오름)", "학내"));
        cafeArray.add(new RestaurantMenu("기숙사식당", "학내"));

        // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
        pushMenusToViewer(cafeArray);
    }
    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */

    @Override
    public void pushMenusToViewer(ArrayList<RestaurantMenu> menuArray) {
        RestaurantMenu restaurantMenu;
        for(int i = 0; i < menuArray.size(); i++) {
            restaurantMenu = new RestaurantMenu(menuArray.get(i));
            view.showCafes(restaurantMenu);
        }
    }
}
