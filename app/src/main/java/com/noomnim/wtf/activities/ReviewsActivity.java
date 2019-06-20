package com.noomnim.wtf.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.noomnim.wtf.R;
import com.noomnim.wtf.adapter.FoodTruckAdapter;
import com.noomnim.wtf.adapter.ReviewAdapter;
import com.noomnim.wtf.data.DataService;
import com.noomnim.wtf.model.FoodTruck;
import com.noomnim.wtf.model.FoodTruckReview;
import com.noomnim.wtf.view.ItemDecorator;

import java.util.ArrayList;

public class ReviewsActivity extends AppCompatActivity {

    // Variable
    private FoodTruck foodTruck;
    private ArrayList<FoodTruckReview> reviews = new ArrayList<>(  );
    private ReviewAdapter adapter;

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
                    //System.out.println( reviews );
                    setUpRecycler();
                    if(reviews.size() == 0) {
                        Toast.makeText( getBaseContext(), "No reviews for this Food Truck", Toast.LENGTH_SHORT ).show();
                    }
                }
            }
        };
        reviews = DataService.getInstance().downloadReviews( this, foodTruck, listener );
    }



    public interface ReviewInterface{
        void success(Boolean success);
    }

    private void setUpRecycler(){
        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.recycler_reviews );
        recyclerView.setHasFixedSize( true );
        adapter = new ReviewAdapter( reviews );
        recyclerView.setAdapter( adapter );
        LinearLayoutManager layoutManager = new LinearLayoutManager( getBaseContext() );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.addItemDecoration( new ItemDecorator( 10,10,10,10 ) );

    }
}
