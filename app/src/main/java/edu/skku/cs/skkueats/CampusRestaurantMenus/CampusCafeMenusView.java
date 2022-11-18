package edu.skku.cs.skkueats.CampusRestaurantMenus;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class CampusCafeMenusView extends AppCompatActivity implements CampusCafeMenusContract.contactView {
    private ArrayList<RestaurantMenu> menuArrayLunch = new ArrayList<>();
    private ArrayList<RestaurantMenu> menuArrayDinner = new ArrayList<>();
    private ListView campusCafeLunchListView;
    private ListView campusCafeDinnerListView;
    private CampusCafeMenusAdapter campusCafeLunchMenusAdapter;
    private CampusCafeMenusAdapter campusCafeDinnerMenusAdapter;
    private Bundle savedInstanceState;
    private CampusCafeMenusModel model;

    /*
    각 식당의 상세 정보를 눌렀을 때 나오는 창 (추천 혹은 검색 결과에서)
    Intent시, Extra로 "RestaurantName"을 받는다.
        { "RestaurantName": 가게이름 }
    모델(RestaurantInfoModel)에서 가게이름을 바탕으로 DB에서 가게 정보를 조회하도록 한다

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_cafe_menus);
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
        model = new CampusCafeMenusModel(this);
    }

    private void initView() {
        campusCafeLunchListView = findViewById(R.id.listViewCampusCafeLunch);
        campusCafeDinnerListView = findViewById(R.id.listViewCampusCafeDinner);

    }

    @Override
    public void showMenuLunch(String menu, double grade, int price) {
        /*
        받은 정보를 바탕으로 ListView에 menu를 추가하여 화면에 표시한다
         */
        menuArrayLunch.add(new RestaurantMenu(menu, grade, price));
        campusCafeLunchMenusAdapter = new CampusCafeMenusAdapter(getApplicationContext(), menuArrayLunch);
        campusCafeLunchListView.setAdapter(campusCafeLunchMenusAdapter);
    }

    @Override
    public void showMenuDinner(String menu, double grade, int price) {
        menuArrayDinner.add(new RestaurantMenu(menu, grade, price));
        campusCafeDinnerMenusAdapter = new CampusCafeMenusAdapter(getApplicationContext(), menuArrayDinner);
        campusCafeDinnerListView.setAdapter(campusCafeDinnerMenusAdapter);
    }
}