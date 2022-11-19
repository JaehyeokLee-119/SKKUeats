package edu.skku.cs.skkueats.MyProfile;

import android.util.Log;

import com.naver.maps.geometry.LatLng;

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

public class MyProfileModel implements MyProfileContract.contactModel {
    public String userId;
    private ArrayList<MyProfileReview> reviewArray;
    private MyProfileContract.contactView view;

    public MyProfileModel(MyProfileContract.contactView view, String userId) {
        this.userId = userId;
        this.view = view;
        Log.v("result", userId);
        reviewArray = new ArrayList<>();
        fetchReviews(userId);
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
                Log.v("result", res.toString());
                json = new JSONObject(res);
                menu = json.getJSONArray("reviews");
                JSONObject tempJson = new JSONObject();
                for(int i=0; i<menu.length(); i++){
                    tempJson = menu.getJSONObject(i);
                    reviewArray.add(new MyProfileReview(tempJson.getString("restaurant_name"), tempJson.getString("menu_name"), tempJson.getInt("grade"), tempJson.getString("review_contents"), tempJson.getBoolean("validity")));
                }

                // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
                pushReviewsToViewer(reviewArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void fetchReviews(String userId) {
        // restaurantName으로 db에 요청해서 리뷰 정보를 다 받아와야 함

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("http://3.39.192.139:5000/users/" + userId + "/reviews")
                .build();
        client.newCall(request).enqueue(callback);



    }

    @Override
    public void pushReviewsToViewer(ArrayList<MyProfileReview> reviewArrays) {
        MyProfileReview myProfileReview;
        for(int i = 0; i < reviewArrays.size(); i++) {
            myProfileReview = new MyProfileReview(reviewArrays.get(i));
            view.showReview(myProfileReview.restaurantName, myProfileReview.menu, myProfileReview.grade,
                    myProfileReview.content, myProfileReview.isTroll);
        }
    }
}
