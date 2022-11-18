package edu.skku.cs.skkueats.WriteReview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.SearchActivity.SearchActivityModel;

public class WriteReviewView extends AppCompatActivity implements WriteReviewContract.contactView {
    private Bundle savedInstanceState;
    private TextView textReviewRestaurantName;
    private String id;
    private String restaurantName;
    private Spinner spinner;
    private WriteReviewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        initView();


        restaurantName = getIntent().getStringExtra("RestaurantName");
        id = getIntent().getStringExtra("id");
        textReviewRestaurantName.setText(restaurantName);

        model = new WriteReviewModel(this, restaurantName);

    }

    @Override
    public void initView(){
        textReviewRestaurantName = findViewById(R.id.textReviewRestaurantName);
        spinner = findViewById(R.id.spinner);

    }

    @Override
    public void setSpinnerMenus(String[] menuList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.spinner_menu_item,
                menuList);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }
}
