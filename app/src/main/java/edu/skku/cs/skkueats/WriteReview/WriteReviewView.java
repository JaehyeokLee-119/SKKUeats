package edu.skku.cs.skkueats.WriteReview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.SearchActivity.SearchActivityModel;
import edu.skku.cs.skkueats.SearchActivity.SearchActivityView;

public class WriteReviewView extends AppCompatActivity implements WriteReviewContract.contactView {
    private Bundle savedInstanceState;
    private TextView textReviewRestaurantName;
    private String id;
    private String restaurantName;
    private Spinner spinner;
    private WriteReviewModel model;
    private RatingBar ratingBar;
    private Button reviewUploadButton;
    private EditText editTextReviewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        initView();


        restaurantName = getIntent().getStringExtra("RestaurantName");
        id = getIntent().getStringExtra("id");
        textReviewRestaurantName.setText(restaurantName);

        model = new WriteReviewModel(this, restaurantName);

        reviewUploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String menuName;
                menuName = spinner.getSelectedItem().toString();
                double grade;
                grade = (double) ratingBar.getRating();
                String reviewContent;
                reviewContent = editTextReviewContent.getText().toString();

                MenuReview menuReview = new MenuReview(restaurantName, menuName, grade, reviewContent);
                model.reviewUpload(menuReview, id);
            }
        });

    }

    @Override
    public void reviewComplete() {
        Toast.makeText(this, "리뷰가 등록되었습니다", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void initView(){
        textReviewRestaurantName = findViewById(R.id.textReviewRestaurantName);
        spinner = findViewById(R.id.spinner);
        reviewUploadButton = findViewById(R.id.reviewUploadButton);
        ratingBar = findViewById(R.id.ratingBar);
        editTextReviewContent = findViewById(R.id.editTextReviewContent);

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
