package edu.skku.cs.skkueats.WriteReview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.R;

public class WriteReviewView extends AppCompatActivity implements WriteReviewContract.contactView {
    private Bundle savedInstanceState;
    private TextView textReviewRestaurantName;
    private String id;
    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        initView();


        restaurantName = getIntent().getStringExtra("RestaurantName");
        id = getIntent().getStringExtra("id");
        textReviewRestaurantName.setText(restaurantName);


    }

    @Override
    public void initView(){
        textReviewRestaurantName = findViewById(R.id.textReviewRestaurantName);
    }
}
