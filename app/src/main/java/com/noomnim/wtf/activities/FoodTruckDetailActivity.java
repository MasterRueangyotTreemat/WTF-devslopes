package com.noomnim.wtf.activities;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.noomnim.wtf.R;
import com.noomnim.wtf.model.FoodTruck;

public class FoodTruckDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FoodTruck foodTruck;
    private TextView truckName;
    private TextView foodType;
    private TextView avgCost;
    public static final  String EXTRA_ITEM_TRUCK = "TRUCK";
    private Button addReviewBtn;
    private Button viewReviewBtn;
    private Button modifyTruckBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.detail_view );

        truckName = (TextView) findViewById( R.id.detail_truck_name );
        foodType = (TextView) findViewById( R.id.detail_food_type );
        avgCost = (TextView) findViewById( R.id.detail_food_cost );

        addReviewBtn = (Button) findViewById( R.id.add_reivew_btn );
        viewReviewBtn = (Button) findViewById( R.id.view_review_btn );
        modifyTruckBtn = (Button) findViewById( R.id.modify_truck_btn );

        viewReviewBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadReviews( foodTruck );
            }
        } );



        foodTruck = getIntent().getParcelableExtra( FoodTrucksListsActivity.EXTRA_ITEM_TRUCK );
        //System.out.println( foodTruck.getName() );
        updateUI();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng foodTruckLocation = new LatLng( foodTruck.getLatitude(), foodTruck.getLongtitude() );
        mMap.addMarker( new MarkerOptions().position( foodTruckLocation ).title( foodTruck.getName() ) );
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom( foodTruckLocation ,10) );
        setUpMap();
    }

    private void updateUI(){
        truckName.setText( foodTruck.getName() );
        foodType.setText( foodTruck.getFoodtype() );
        avgCost.setText("$" + foodTruck.getAvgcost() );
    }

    private void setUpMap(){
        mMap.setMapType( GoogleMap.MAP_TYPE_NORMAL );
        mMap.setTrafficEnabled( true );
        mMap.setIndoorEnabled( true );
        mMap.setBuildingsEnabled( true );
        mMap.getUiSettings().setZoomControlsEnabled( true );


    }

    public void loadReviews(FoodTruck truck){
        Intent intent = new Intent( FoodTruckDetailActivity.this, ReviewsActivity.class );
        intent.putExtra( FoodTruckDetailActivity.EXTRA_ITEM_TRUCK, truck );
        startActivity( intent );
    }
}
