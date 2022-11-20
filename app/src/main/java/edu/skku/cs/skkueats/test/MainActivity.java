package edu.skku.cs.skkueats.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;

import edu.skku.cs.skkueats.ApplicationGlobal;
import edu.skku.cs.skkueats.Login.LoginView;
import edu.skku.cs.skkueats.MenuRecommendedList.MenuRecommendsView;
import edu.skku.cs.skkueats.MyProfile.MyProfileContract;
import edu.skku.cs.skkueats.MyProfile.MyProfileModel;
import edu.skku.cs.skkueats.MyProfile.MyProfileReview;
import edu.skku.cs.skkueats.MyProfile.MyProfileReviewAdapter;
import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.RecommendConditions.RecommendConditionsView;
import edu.skku.cs.skkueats.Register.RegisterView;
import edu.skku.cs.skkueats.Select.SelectActivity;


public class MainActivity extends AppCompatActivity implements RestaurantMenusContract.contactView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        RestaurantMenusModel model = new RestaurantMenusModel(this, "미가라멘 성대본점");
        model = new RestaurantMenusModel(this, "미가라점");
        model = new RestaurantMenusModel(this, "기숙식당");
        model = new RestaurantMenusModel(this, "");
    }

    @Override
    public void showMenu(String menu, String grade, int price) {
        Log.i("result", menu+"|"+grade+"|"+price);
    }
}