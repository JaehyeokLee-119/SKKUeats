package edu.skku.cs.skkueats.MenuRecommendedList;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class RecommendQueryCondition {
    /*
    추천 조건들을 설정한 것
    1. 대분류
    2. 소분류
    3. 가격
    4. 최소평점
    5. 위치
     */
    public String bigCategory;      // 대분류 조건
    public String smallCategory;    // 소분류 조건
    public int price;               // 가격 조건
    public String purpose;         // 최소 평점 조건
    public String location;         // 가게 위치 조건
    public int maxNum;              // 추천받을 식사메뉴의 최대 개수

    public RecommendQueryCondition(String bigCategory, String smallCategory,
                                   int price, String purpose, String location, int maxNum) {
        this.bigCategory = bigCategory;
        this.smallCategory = smallCategory;
        this.price = price;
        this.purpose = purpose;
        this.location = location;
        this.maxNum = maxNum;
    }
}
public class MenuRecommendsModel implements MenuRecommendsContract.contactModel {
    private ArrayList<MenuRecommends> recommendsArray;
    private MenuRecommendsContract.contactView view;
    private RecommendQueryCondition recommendQueryCondition;

    public MenuRecommendsModel(MenuRecommendsContract.contactView view, RecommendQueryCondition recommendQueryCondition) {
        this.view = view;
        recommendsArray = new ArrayList<>();
        this.recommendQueryCondition = recommendQueryCondition;
        fetchRecommends();
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
                menu = json.getJSONArray("menu");
                JSONObject tempJson = new JSONObject();
                Integer result_length = menu.length();
                /*
                if(recommendQueryCondition.maxNum<menu.length()){
                    result_length = recommendQueryCondition.maxNum;
                }
                */
                for(int i=0; i<result_length; i++){
                    tempJson = menu.getJSONObject(i);
                    recommendsArray.add(new MenuRecommends(tempJson.getString("restaurant_name"), tempJson.getString("menu_name"), tempJson.getInt("price"), tempJson.getString("main_category")));
                }

                Random random = new Random();
                random.setSeed(System.currentTimeMillis());
                // 메뉴 정보 Response가 오면 아래 메소드를 실행시켜서 review를 view에 표시
                while(recommendsArray.size() > recommendQueryCondition.maxNum) {
                    int toRemove = random.nextInt(recommendsArray.size());
                    recommendsArray.remove(toRemove);
                }
                pushRecommendsToViewer(recommendsArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void fetchRecommends() {
        // 추천 쿼리는 RecommendQueryCondition 형태로 주어짐
        // 이걸로 백엔드에 쿼리를 보내서 [가게, 메뉴, 가격, 위치]를 '개수'만큼 받아온다

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("http://3.39.192.139:5000/recommend?mainCategory=" + recommendQueryCondition.bigCategory + "&subCategory=" + recommendQueryCondition.smallCategory + "&location=" + recommendQueryCondition.location + "&price=0-" + recommendQueryCondition.price+ "&purpose=" + recommendQueryCondition.purpose)
                .build();
        client.newCall(request).enqueue(callback);

    }

    /*
    DB에 가게 이름으로 쿼리 보내서
    가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
     */

    @Override
    public void pushRecommendsToViewer(ArrayList<MenuRecommends> recommendsArrays) {
        MenuRecommends menuRecommends;
        for(int i = 0; i < recommendsArrays.size(); i++) {
            menuRecommends = new MenuRecommends(recommendsArrays.get(i));
            view.showRecommend(menuRecommends);
        }
    }
}
