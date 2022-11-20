package edu.skku.cs.skkueats.SearchActivity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.skku.cs.skkueats.ApplicationGlobal;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivityModel implements SearchActivityContract.contactModel {
    public ArrayList<SearchResult> searchResultsArray;
    private ArrayList<String> menuArray;
    private String menuString;
    private SearchActivityContract.contactView view;
    private ApplicationGlobal applicationGlobal;
    private String serverUrl;
    public SearchActivityModel(SearchActivityContract.contactView view, ApplicationGlobal applicationGlobal) {
        this.view = view;
        this.serverUrl = applicationGlobal.getServerURL();
        searchResultsArray = new ArrayList<>();
        menuArray = new ArrayList<>();
        this.applicationGlobal = applicationGlobal;
    }

    public void searchResultReset() {
        // 초기화가 잘 안됨
        searchResultsArray.clear();
        pushSearchResultsToViewer(searchResultsArray);
    }

    private Callback callback_menu = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String ex = sw.toString();
            Log.v("aaa", ex);
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            final String myResponse = response.body().string();

            JSONArray menu = null;
            JSONObject json = null;

            try {
                json = new JSONObject(myResponse);
                menu = json.getJSONArray("menu");
                JSONObject tempJson = new JSONObject();
                for(int i=0; i<menu.length(); i++) {
                    tempJson = menu.getJSONObject(i);
                    searchResultsArray.add(new SearchResult(
                            tempJson.getString("restaurant_name"),
                            tempJson.getString("menu_name"),
                            tempJson.getInt("price"),
                            tempJson.getString("main_category"), 1));
                }

                pushSearchResultsToViewer(searchResultsArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Callback callback_restaurant = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            final String myResponse = response.body().string();

            JSONArray restaurant = null;
            JSONObject json = null;
            try {
                json = new JSONObject(myResponse);
                restaurant = json.getJSONArray("restaurant");
                JSONObject tempJson = new JSONObject();
                for(int i=0; i<restaurant.length(); i++) {
                    tempJson = restaurant.getJSONObject(i);
                    String r_name = tempJson.getString("restaurant_name");
                    String loc = tempJson.getString("location");

                    OkHttpClient client = new OkHttpClient();
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(serverUrl+"menus?restaurant_name=" + r_name).newBuilder();
                    String url = urlBuilder.build().toString();
                    Request req = new Request.Builder().url(url).build();

                    Response res = client.newCall(req).execute();

                    final String myResponse2 = res.body().string();
                    JSONArray menus = null;
                    JSONObject json_menu = null;
                    try {
                        json_menu = new JSONObject(myResponse2);
                        menus = json_menu.getJSONArray("menu");
                        JSONObject tmpJson = new JSONObject();
                        for(int k=0; k<menus.length(); k++) {
                            tmpJson = menus.getJSONObject(k);
                            menuArray.add(tmpJson.getString("menu_name"));
                        }
                        menuString = "";
                        for(int j=0; j<menuArray.size(); j++) {
                            menuString += menuArray.get(j);
                            if(j == menuArray.size() - 1)
                                break;
                            menuString += ", ";
                        }
                        menuArray.clear();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    searchResultsArray.add(new SearchResult(r_name, menuString, 0, loc, 0));
                }

                pushSearchResultsToViewer(searchResultsArray);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void fetchSearchResults(String queryContent, int queryType) {
        /*
        DB에 가게 이름/메뉴 이름으로 쿼리 보내서
        가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
            > 리뷰 평점 평균내기
         */
        if (queryContent.isEmpty()) {
            searchResultReset();
        } else if(queryType == 0){      // 가게 검색
            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(serverUrl+"search?search="+queryContent).newBuilder();
            String url = urlBuilder.build().toString();
            Request req = new Request.Builder().url(url).build();
            //Toast.makeText((Context) view, (CharSequence) url, Toast.LENGTH_SHORT).show();

            client.newCall(req).enqueue(callback_restaurant);
        } else if(queryType == 1) {     // 메뉴 검색
            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(serverUrl+"search?search="+queryContent).newBuilder();
            String url = urlBuilder.build().toString();
            Request req = new Request.Builder().url(url).build();

            client.newCall(req).enqueue(callback_menu);
        }
    }

    @Override
    public void pushSearchResultsToViewer(ArrayList<SearchResult> searchResultsArray) {
        SearchResult searchResult;
        for(int i = 0; i < searchResultsArray.size(); i++) {
            searchResult = new SearchResult(searchResultsArray.get(i));
            view.showSearchResult(searchResult);
        }
    }
}
