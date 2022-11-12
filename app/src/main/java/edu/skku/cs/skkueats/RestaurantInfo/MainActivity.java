package edu.skku.cs.skkueats.RestaurantInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, RestaurantInfoContract.contactView {
    private MapView mapView;
    private static NaverMap naverMap;
    private ArrayList<RestaurantReview> reviewArray = new ArrayList<>();
    private ListView reviewList;
    private RestaurantReviewAdapter restaurantReviewAdapter;
    private RestaurantInfoPresenter presenter;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.savedInstanceState = savedInstanceState;
        presenter = new RestaurantInfoPresenter(this);
        initView();

        newReview("rPwjd1", "돈가스김치나베", 5, "돈가스김치나베 맛있어요", false);
        newReview("rPwjd15", "카베동", 3, "리뷰내용", false);
        newReview("rPw55jd1", "치킨동", 4, "치킨동맛있어요", true);
        newReview("rPw24jd1", "치킨동", 2, "치킨동맛있어요", true);
        newReview("r125jd1", "치킨동", 2, "치킨동맛있어요", true);
        newReview("rPw24d1", "치킨동", 2, "치킨동맛있어요", true);
        newReview("rPw521d1", "치킨동", 2, "치킨동맛있어요", true);

    }

    private void initView() {
        mapView = (MapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        reviewList = findViewById(R.id.listViewReviews);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        LatLng latLng = new LatLng(37.29718,  126.97018);
        CameraPosition cameraPosition = new CameraPosition(
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
    public void newReview(String writer, String menu, int grade, String content, boolean isTroll) {
        reviewArray.add(new RestaurantReview(writer, menu, grade, content, isTroll));
        restaurantReviewAdapter = new RestaurantReviewAdapter(getApplicationContext(), reviewArray);
        reviewList.setAdapter(restaurantReviewAdapter);
    }
}