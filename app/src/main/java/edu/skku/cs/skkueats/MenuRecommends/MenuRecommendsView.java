package edu.skku.cs.skkueats.MenuRecommends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RestaurantInfo.RestaurantInfoView;


public class MenuRecommendsView extends AppCompatActivity implements MenuRecommendsContract.contactView {
    private ArrayList<MenuRecommends> recommendsArray = new ArrayList<>();
    private ListView recommendsList;
    private MenuRecommendsAdapter restaurantReviewAdapter;
    private Bundle savedInstanceState;
    private MenuRecommendsModel model;

    /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_recommends);
        this.savedInstanceState = savedInstanceState;

        /*
        View 생성시:
            0. Activity에 있는 View를 초기화해줌

         */

        initView();
        model = new MenuRecommendsModel(this);
    }

    @Override
    public void initView() {
        recommendsList = findViewById(R.id.listViewRecommends);

    }


    @Override
    public void showRecommend(MenuRecommends menuRecommends) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        recommendsArray.add(new MenuRecommends(menuRecommends));
//        restaurantReviewAdapter = new MenuRecommendsAdapter(getApplicationContext(), recommendsArray);
        restaurantReviewAdapter = new MenuRecommendsAdapter(this, recommendsArray);
        recommendsList.setAdapter(restaurantReviewAdapter);
    }
}