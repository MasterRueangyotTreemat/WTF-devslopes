package com.noomnim.wtf.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.noomnim.wtf.R;
import com.noomnim.wtf.data.DataService;
import com.noomnim.wtf.model.FoodTruck;
import com.noomnim.wtf.model.FoodTruckReview;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    // Variable
    private FoodTruck foodTruck;
    private ArrayList<FoodTruckReview> reviews = new ArrayList<>(  );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reviews );

        foodTruck = getIntent().getParcelableExtra( FoodTrucksListsActivity.EXTRA_ITEM_TRUCK );
        System.out.println( foodTruck.getName() );

        ReviewInterface listener = new ReviewInterface() {
            @Override
            public void success(Boolean success) {
                if(success){
                    System.out.println( reviews );
                }
            }
        };
        reviews = DataService.getInstance().downloadReviews( this, foodTruck, listener );
    }

    public interface ReviewInterface{
        void success(Boolean success);
    }
}
