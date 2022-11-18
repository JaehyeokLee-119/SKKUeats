package edu.skku.cs.skkueats.SearchActivity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.skku.cs.skkueats.ApplicationGlobal;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivityModel implements SearchActivityContract.contactModel {
    private ArrayList<SearchResult> searchResultsArray;
    private SearchActivityContract.contactView view;
    private ApplicationGlobal applicationGlobal;
    private String serverUrl;
    public SearchActivityModel(SearchActivityContract.contactView view, ApplicationGlobal applicationGlobal) {
        this.view = view;
        this.serverUrl = applicationGlobal.getServerURL();
        searchResultsArray = new ArrayList<>();
        this.applicationGlobal = applicationGlobal;
    }

    public void searchResultReset() {
        // 초기화가 잘 안됨
        searchResultsArray.clear();
        pushSearchResultsToViewer(searchResultsArray);
    }

    @Override
    public void fetchSearchResults(String queryContent, int queryType) {
        /*
        DB에 가게 이름/메뉴 이름으로 쿼리 보내서
        가게, 가게의 위치, 메뉴들, 저장된 리뷰들 모두 가져오기
            > 리뷰 평점 평균내기
         */


        if (queryContent.isEmpty()) {
            searchResultReset();
        } else {
            OkHttpClient client = new OkHttpClient();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(serverUrl+"search?search="+queryContent).newBuilder();
            //urlBuilder.addQueryParameter("search",queryContent);
            String url = urlBuilder.build().toString();
            Request req = new Request.Builder().url(url).build();

            //Toast.makeText((Context) view, (CharSequence) url, Toast.LENGTH_SHORT).show();



            client.newCall(req).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    final String myResponse = response.body().string();

                    String restaurant_name;
                    String menu_name;
                    try {
                        JSONObject jsObj = new JSONObject(myResponse);
                        restaurant_name = jsObj.getJSONObject("menu").getString("restaurant_name");
                        menu_name = jsObj.getJSONObject("menu").getString("menu_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String res = response.body().string();

                    JSONObject json = null;
                    JSONArray restraurant = null;
                    try {
                        json = new JSONObject(res);
                        restraurant = json.getJSONArray("menu");

                        JSONObject tempJson = new JSONObject();
                        for(int i=0; i<restraurant.length(); i++){
                            tempJson = restraurant.getJSONObject(i);
                            searchResultsArray.add(new SearchResult(tempJson.getString("restaurant_name"),
                                    tempJson.getString("menu_name"),
                                    tempJson.getInt("price"),
                                    ""));
                        }

                        pushSearchResultsToViewer(searchResultsArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    pushSearchResultsToViewer(searchResultsArray);

                }
            });



        }

        //pushSearchResultsToViewer(searchResultsArray);

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
