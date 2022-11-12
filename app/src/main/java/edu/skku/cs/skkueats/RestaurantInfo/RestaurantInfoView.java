package edu.skku.cs.skkueats.RestaurantInfo;

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


public class RestaurantInfoView extends AppCompatActivity implements OnMapReadyCallback, RestaurantInfoContract.contactView {
    private MapView mapView;
    private static NaverMap naverMap;
    private ArrayList<RestaurantReview> reviewArray = new ArrayList<>();
    private ListView reviewList;
    private RestaurantReviewAdapter restaurantReviewAdapter;
    private RestaurantInfoPresenter presenter;
    private Bundle savedInstanceState;
    private String restaurantName;
    private TextView textViewRestaurantName;
    private LatLng latLng; // 지도에 표시할 위도/경도
    private CameraPosition cameraPosition; // 지도에 표시할 camera position

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

        restaurantName = getIntent().getStringExtra("RestaurantName");
            // 가게 DB에 가게 이름으로 쿼리를 보내서 위도, 경도를 받아옴
            // 리뷰 DB에 가게 이름으로 쿼리를 보내서 리뷰 정보들을 받아옴

        initView();

        showReview("rPwjd1", "돈가스김치나베", 5, "돈가스김치나베 맛있어요", false);
        showReview("rPwjd15", "에비동", 3, "리뷰내용", false);
        showReview("rPw55jd1", "치킨동", 4, "치킨동맛있어요", true);
        showReview("rPw24jd1", "치킨동", 2, "치킨동맛있어요", true);
        showReview("r125jd1", "치킨동", 2, "치킨동맛있어요", true);
        showReview("rPw24d1", "치킨동", 2, "치킨동맛있어요", true);
        showReview("rPw521d1", "치킨동", 2, "치킨동맛있어요", true);

        //presenter = new RestaurantInfoPresenter(this, restaurantName);
        //setMapCamera(37.29718,  126.97018);
    }

    private void initView() {
        textViewRestaurantName = findViewById(R.id.textViewRestaurantName);
        reviewList = findViewById(R.id.listViewReviews);
        textViewRestaurantName.setText(restaurantName);
        latLng = new LatLng(37.293363799999916,  126.9746702539383);

        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        RestaurantInfoView.naverMap = naverMap;
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
    }

    @Override
    public void showReview(String writer, String menu, int grade, String content, boolean isTroll) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        reviewArray.add(new RestaurantReview(writer, menu, grade, content, isTroll));
        restaurantReviewAdapter = new RestaurantReviewAdapter(getApplicationContext(), reviewArray);
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