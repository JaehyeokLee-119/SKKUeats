package edu.skku.cs.skkueats.WriteReview;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
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

        this.menuReview = menuReview;

        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("restaurantName", menuReview.restaurantName)
                .add("menuName", menuReview.menu)
                .add("grade", menuReview.grade.toString())
                .add("reviewContents", menuReview.reviewContent)
                .build();
        Request request = new Request.Builder()
                .url("http://3.39.192.139:5000/reviews")
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);

    }


}
