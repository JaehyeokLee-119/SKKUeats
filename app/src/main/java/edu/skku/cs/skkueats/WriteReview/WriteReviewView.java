package edu.skku.cs.skkueats.WriteReview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.skku.cs.skkueats.R;

public class WriteReviewView extends AppCompatActivity implements WriteReviewContract.contactView {
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
    }

    @Override
    public void initView(){

    }
}
