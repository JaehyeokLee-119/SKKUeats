package edu.skku.cs.skkueats.MenuRecommends;

import android.os.Bundle;
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


public class MenuRecommendsView extends AppCompatActivity implements MenuRecommendsContract.contactView {
    private ArrayList<MenuRecommends> recommendsArray = new ArrayList<>();
    private ListView recommendsList;
    private MenuRecommendsAdapter restaurantReviewAdapter;
    private Bundle savedInstanceState;
    private String restaurantName;
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
                - initView()
            1. Intent로부터 가게이름 Extra를 받아옴
            2. 모델에게 DB 통신을 시켜서 가게메뉴들, 가게위치, 리뷰들을 받아옴
                - model = new model
                // 가게 DB에 가게 이름으로 쿼리를 보내서 위도, 경도를 받아옴
                // 리뷰 DB에 가게 이름으로 쿼리를 보내서 리뷰 정보들을 받아옴
                - 모델은 view에게 리뷰를 표시
            3. 네이버지도가 로딩이 완료되면, model에게 onMapLoaded를 실행시켜 카메라 위치를 재설정하도록 함
         */

        initView();
        model = new MenuRecommendsModel(this, restaurantName);
    }

    private void initView() {
        recommendsList = findViewById(R.id.listViewRecommends);

    }

    @Override
    public void showRecommend(MenuRecommends menuRecommends) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        recommendsArray.add(new MenuRecommends(menuRecommends));
        restaurantReviewAdapter = new MenuRecommendsAdapter(getApplicationContext(), recommendsArray);
        recommendsList.setAdapter(restaurantReviewAdapter);
    }
}