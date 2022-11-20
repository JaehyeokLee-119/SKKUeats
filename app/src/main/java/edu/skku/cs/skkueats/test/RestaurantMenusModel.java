package edu.skku.cs.skkueats.test;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestaurantMenusModel implements RestaurantMenusContract.contactModel {
    public String restaurantName;
    private ArrayList<RestaurantMenu> menuArray;
    private RestaurantMenusContract.contactView view;

    public RestaurantMenusModel(RestaurantMenusContract.contactView view, String restaurantName) {
        this.restaurantName = restaurantName;
        this.view = view;
        menuArray = new ArrayList<>();
        fetchMenus(restaurantName);
    }

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String res = response.body().string();

            JSONObject json = null;
            JSONArray menu = null;
            try {
                json = new JSONObject(res);
                menu = json.getJSONArray("menu");
                Log.v("ket", menu.toString());
                JSONObject tempJson = new JSONObject();
                for(int i=0; i<menu.length(); i++){
                    tempJson = menu.getJSONObject(i);

                    menuArray.add(new RestaurantMenu(tempJson.getString("menu_name"), tempJson.getString("main_category") + '/' + tempJson.getString("sub_category"), tempJson.getInt("price")));
                }
                Log.v("ket", menuArray.toString());
                pushMenusToViewer(menuArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    @Override
    public void fetchMenus(String restaurantName) {
        if (restaurantName.isEmpty())
            return;
        // restaurantName으로 db에 요청해서 리뷰 정보를 다 받아와야 함

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("http://3.39.192.139:5000/menus?restaurant_name=" + restaurantName)
                .build();
        client.newCall(request).enqueue(callback);


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
            view.showMenu(restaurantMenu.menu, restaurantMenu.grade, restaurantMenu.price);
        }
    }
}
