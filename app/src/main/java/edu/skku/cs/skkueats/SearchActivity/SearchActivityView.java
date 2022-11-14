package edu.skku.cs.skkueats.SearchActivity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class SearchActivityView extends AppCompatActivity implements SearchActivityContract.contactView {
    private ArrayList<SearchResult> searchResultArray = new ArrayList<>();
    private ListView searchResultList;
    private SearchActivityAdapter searchActivityAdapter;
    private Bundle savedInstanceState;
    private SearchActivityModel model;

    /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.savedInstanceState = savedInstanceState;

        /*
        View 생성시:
            0. Activity에 있는 View를 초기화해줌

         */

        initView();
        model = new SearchActivityModel(this);
    }

    @Override
    public void initView() {
        searchResultList = findViewById(R.id.listViewSearchResults);

    }


    @Override
    public void showRecommend(SearchResult searchResult) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        searchResultArray.add(new SearchResult(searchResult));
//        restaurantReviewAdapter = new MenuRecommendsAdapter(getApplicationContext(), recommendsArray);
        searchActivityAdapter = new SearchActivityAdapter(this, searchResultArray);
        searchResultList.setAdapter(searchActivityAdapter);
    }
}