package edu.skku.cs.skkueats.WriteReview;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class MenuReview {
    public String restaurantName;
    public String menu;
    public Integer grade;
    public String reviewContent;

    public MenuReview(String restaurantName, String menu, Integer grade, String reviewContent) {
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
    private boolean check = false;
    private MenuReview menuReview;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public WriteReviewModel(WriteReviewContract.contactView view, String restaurantName) {
        this.view = view;
        this.menus = new ArrayList<>();
        this.restaurantName = restaurantName;
        fetchMenus(restaurantName);
    }

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String res = response.body().string();
            Log.v("result", res);

            JSONObject json = null;
            try {
                json = new JSONObject(res);
                check = json.getBoolean("reviewSuccess");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(check){
                String strToShow= menuReview.reviewContent+"/ 점수는: "+Double.toString(menuReview.grade);
                view.reviewComplete(strToShow);
            }else{
                String strToShow= "리뷰작성에 실패하였습니다.";
                view.reviewComplete(strToShow);
            }

        }
    };

    private Callback callback2 = new Callback() {
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

                JSONObject tempJson = new JSONObject();
                for(int i=0; i<menu.length(); i++){
                    tempJson = menu.getJSONObject(i);
                    menus.add(tempJson.getString("menu_name"));
                }

                String[] menuStringArray = menus.toArray(new String[menus.size()]);
                view.setSpinnerMenus(menuStringArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    };


    @Override
    public void fetchMenus(String restaurantName) {

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("http://3.39.192.139:5000/menus?restaurant_name=" + restaurantName)
                .build();


        client.newCall(request).enqueue(callback2);

        /*
        menus.add("미가돈코츠라멘");
        menus.add("사케동");
        menus.add("쇼유라멘");
        menus.add("치킨동");

        String[] menuStringArray = menus.toArray(new String[menus.size()]);
        view.setSpinnerMenus(menuStringArray);
        */

    }

    @Override
    public void reviewUpload(MenuReview menuReview, String id) throws JSONException {
        // 리뷰(menuReview)를 서버에 보냄

        this.menuReview = menuReview;


        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("restaurantName", menuReview.restaurantName)
                .put("menuName", menuReview.menu)
                .put("grade", menuReview.grade.toString())
                .put("id", id)
                .put("reviewContents", menuReview.reviewContent);

        RequestBody body = RequestBody.create(JSON, json.toString());


        Request request = new Request.Builder()
                .url("http://3.39.192.139:5000/reviews")
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);

    }


}
