package edu.skku.cs.skkueats.MyProfile;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class MyProfileView extends AppCompatActivity implements MyProfileContract.contactView {
    private ArrayList<MyProfileReview> reviewArray = new ArrayList<>();
    private ListView reviewList;
    private MyProfileReviewAdapter myProfileReviewAdapter;
    private Bundle savedInstanceState;
    private String userId;
    private MyProfileModel model;
    private TextView textViewMyId;

    /*
    각 식당의 상세 정보를 눌렀을 때 나오는 창 (추천 혹은 검색 결과에서)
    Intent시, Extra로 "RestaurantName"을 받는다.
        { "RestaurantName": 가게이름 }
    모델(RestaurantInfoModel)에서 가게이름을 바탕으로 DB에서 가게 정보를 조회하도록 한다

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        this.savedInstanceState = savedInstanceState;

        /*
        View 생성시:
            0. Activity에 있는 View를 초기화해줌
                - initView()
            1. Intent로부터 사용자id Extra를 받아옴
            2. 모델에게 DB 통신을 시켜서 사용자id에 따른 리뷰들을
                - 모델은 view에게 리뷰를 표시
         */

        userId = getIntent().getStringExtra("id");
        initView();

        textViewMyId.setText(userId);
        model = new MyProfileModel(this, userId);
    }

    private void initView() {
        reviewList = findViewById(R.id.listViewMyReviews);
        textViewMyId = findViewById(R.id.textViewMyID);
        //textViewSeeMenu.setPaintFlags(textViewSeeMenu.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        model.fetchReviews(userId);
    }

    @Override
    public void showReview(String restaurantName, String menu, int grade, String content, boolean isTroll) {
        /*
        받은 정보를 바탕으로 ListView에 Review를 추가하여 화면에 표시한다
         */
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                reviewArray.add(new MyProfileReview(restaurantName, menu, grade, content, isTroll));
                myProfileReviewAdapter = new MyProfileReviewAdapter(getApplicationContext(), reviewArray, userId);
                reviewList.setAdapter(myProfileReviewAdapter);
            }
        });

    }
}