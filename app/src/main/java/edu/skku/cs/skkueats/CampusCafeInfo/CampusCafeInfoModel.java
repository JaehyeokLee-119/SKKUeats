package edu.skku.cs.skkueats.CampusCafeInfo;

import android.content.Intent;
import android.net.Uri;

import com.naver.maps.geometry.LatLng;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.skku.cs.skkueats.CampusCafeInfo.RestaurantReview;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CampusCafeInfoModel implements CampusCafeInfoContract.contactModel {
    public String restaurantName;
    private ArrayList<RestaurantReview> reviewArray;
    private CampusCafeInfoContract.contactView view;
    private LatLng latLng; // 지도에 표시할 위도/경도

    public CampusCafeInfoModel(CampusCafeInfoContract.contactView view, String restaurantName) {
        this.restaurantName = restaurantName;
        this.view = view;
        reviewArray = new ArrayList<>();
        fetchReviews(restaurantName);


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
                menu = json.getJSONArray("reviews");

                JSONObject tempJson = new JSONObject();
                reviewArray.clear();
                for(int i=0; i<menu.length(); i++){
                    tempJson = menu.getJSONObject(i);
                    reviewArray.add(new edu.skku.cs.skkueats.CampusCafeInfo.RestaurantReview(tempJson.getString("user_id"), tempJson.getString("menu_name"), tempJson.getInt("grade"), tempJson.getString("review_contents"), tempJson.getBoolean("validity")));
                }

                // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
                pushReviewsToViewer(reviewArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void onMapLoaded() {
        fetchLatLng(restaurantName);
    }

    @Override
    public void fetchReviews(String restaurantName) {
        // restaurantName으로 db에 요청해서 리뷰 정보를 다 받아와야 함
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://3.39.192.139:5000/reviews?restaurantName=" + restaurantName)
                .build();
        client.newCall(request).enqueue(callback);

        // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
    }

    @Override
    public void fetchLatLng(String restaurantName) {
        // 4가지 학식 식당 종류에 따라 정의된 위도/경도를 표시
        if (restaurantName.equals("학생회관(행단골)")) {
            latLng = new LatLng(37.294378,  126.973842);
        } else if (restaurantName.equals("교직원식당(구시재)")){
            latLng = new LatLng(37.294019,  126.972540);
        } else if (restaurantName.equals("공대식당(해오름)")){
            latLng = new LatLng(37.2950720,  126.9774357);
        } else { //if (restaurantName == "기숙사식당"){
            latLng = new LatLng(37.296234,  126.972526);
        }

        // 위도, 경도 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
        pushMapCamera(latLng);
    }
    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */

    @Override
    public void pushReviewsToViewer(ArrayList<RestaurantReview> reviewArrays) {
        RestaurantReview restaurantReview;
        for(int i = 0; i < reviewArrays.size(); i++) {
            restaurantReview = new RestaurantReview(reviewArrays.get(i));
            view.showReview(restaurantReview.writer, restaurantReview.menu, restaurantReview.grade,
                    restaurantReview.content, restaurantReview.isTroll);
        }
    }

    @Override
    public void pushMapCamera(LatLng latLng) {
        view.setMapCamera(latLng.latitude, latLng.longitude);
    }
}
