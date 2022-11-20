package edu.skku.cs.skkueats.CampusCafeInfo;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import edu.skku.cs.skkueats.RestaurantMenus.RestaurantMenusView;
import edu.skku.cs.skkueats.WriteReview.WriteReviewView;


public class CampusCafeInfoView extends AppCompatActivity implements OnMapReadyCallback, CampusCafeInfoContract.contactView {
    private MapView mapView;
    private static NaverMap naverMap;
    private ArrayList<RestaurantReview> reviewArray = new ArrayList<>();
    private ListView reviewList;
    private CampusCafeReviewAdapter restaurantReviewAdapter;
    private Bundle savedInstanceState;
    private String restaurantName;
    private TextView textViewRestaurantName;
    private TextView textViewSeeMenu;
    private LatLng latLng; // 지도에 표시할 위도/경도
    private CameraPosition cameraPosition; // 지도에 표시할 camera position
    private CampusCafeInfoModel model;
    private Button buttonInfoWrite;
    private String id;

    /*
    각 식당의 상세 정보를 눌렀을 때 나오는 창 (추천 혹은 검색 결과에서)
    Intent시, Extra로 "RestaurantName"을 받는다.
        { "RestaurantName": 가게이름 }
    모델(RestaurantInfoModel)에서 가게이름을 바탕으로 DB에서 가게 정보를 조회하도록 한다

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_info);
        this.savedInstanceState = savedInstanceState;

        id = getIntent().getStringExtra("id");
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

        restaurantName = getIntent().getStringExtra("RestaurantName");
        initView();
        model = new CampusCafeInfoModel(this, restaurantName);

        textViewSeeMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*Intent intent = new Intent(getApplicationContext(), RestaurantMenusView.class);
                intent.putExtra("RestaurantName", restaurantName);
                startActivity(intent);*/

                if (restaurantName.equals("학생회관(행단골)")) {
                    String intentURL = "https://www.skku.edu/skku/campus/support/welfare_11_1.do?mode=info&conspaceCd=20201104&srResId=3&srShowTime=D&srCategory=L";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(intentURL));
                    startActivity(intent);
                } else if (restaurantName.equals("교직원식당(구시재)")){
                    String intentURL = "https://www.skku.edu/skku/campus/support/welfare_11_1.do?mode=info&conspaceCd=20201040&srResId=11&srShowTime=W&srCategory=L";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(intentURL));
                    startActivity(intent);
                } else if (restaurantName.equals("공대식당(해오름)")){
                    String intentURL = "https://www.skku.edu/skku/campus/support/welfare_11_1.do?mode=info&conspaceCd=20201251&srResId=12&srShowTime=D&srCategory=L";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(intentURL));
                    startActivity(intent);
                } else { //if (restaurantName == "기숙사식당"){
                    String intentURL = "https://dorm.skku.edu/dorm_suwon/lifeguide/dorm_restaurant_table.jsp";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(intentURL));
                    startActivity(intent);
                }
            }
        });

        buttonInfoWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), WriteReviewView.class);
                intent.putExtra("RestaurantName", restaurantName);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        textViewRestaurantName = findViewById(R.id.textViewRestaurantNameToSeeMenu);
        reviewList = findViewById(R.id.listViewRestaurantMenus);
        textViewRestaurantName.setText(restaurantName);
        textViewSeeMenu = findViewById(R.id.textViewSeeMenu);
        latLng = new LatLng(37.293363799999916,  126.9746702539383);

        buttonInfoWrite = findViewById(R.id.buttonReviewWrite);
        textViewSeeMenu.setPaintFlags(textViewSeeMenu.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        CampusCafeInfoView.naverMap = naverMap;
        cameraPosition = new CameraPosition(
                latLng,  // 위치 지정
                17,                           // 줌 레벨
                28,
                0
        );
        Marker marker = new Marker();
        naverMap.setMapType(NaverMap.MapType.Terrain);
        marker.setPosition(latLng);
        marker.setMap(naverMap);
        naverMap.setCameraPosition(cameraPosition);
        model.onMapLoaded();
    }

    @Override
    public void showReview(String writer, String menu, int grade, String content, boolean isTroll) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        reviewArray.add(new RestaurantReview(writer, menu, grade, content, isTroll));
        restaurantReviewAdapter = new CampusCafeReviewAdapter(getApplicationContext(), reviewArray);
        reviewList.setAdapter(restaurantReviewAdapter);
    }

    public void setMapCamera(double latitude, double longitude) {
        /* 
        RestaurantInfo Presenter에서 실행할 메소드
        DB에서 가게의 위도, 경도를 받아오고 난 뒤 view의 setMapCamera를 실행하면,
        네이버지도가 가게 위치를 가리킨다
         */
        latLng = new LatLng(latitude,  longitude);
        cameraPosition = new CameraPosition(
                latLng,  // 위치 지정
                17,                           // 줌 레벨
                28,
                0
        );
        Marker marker = new Marker();
        marker.setPosition(latLng);
        marker.setMap(naverMap);
        naverMap.setCameraPosition(cameraPosition);
    }
}