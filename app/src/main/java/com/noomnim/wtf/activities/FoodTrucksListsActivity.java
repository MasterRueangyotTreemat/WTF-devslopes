package com.noomnim.wtf.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.noomnim.wtf.R;
import com.noomnim.wtf.adapter.FoodTruckAdapter;
import com.noomnim.wtf.data.DataService;
import com.noomnim.wtf.model.FoodTruck;
import com.noomnim.wtf.view.ItemDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodTrucksListsActivity extends AppCompatActivity {

    // Variable
    private FoodTruckAdapter adapter;
    private ArrayList<FoodTruck> trucks = new ArrayList<>(  );
    private static FoodTrucksListsActivity foodTrucksListsActivity;
    public static final  String EXTRA_ITEM_TRUCK = "TRUCK";

    public static FoodTrucksListsActivity getFoodTrucksListsActivity() {
        return foodTrucksListsActivity;
    }

    public static void setFoodTrucksListsActivity(FoodTrucksListsActivity foodTrucksListsActivity) {
        FoodTrucksListsActivity.foodTrucksListsActivity = foodTrucksListsActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_food_trucks_lists );

        foodTrucksListsActivity.setFoodTrucksListsActivity(this);

        TrucksDownloaded listener = new TrucksDownloaded() {
            @Override
            public void success(Boolean succes) {
                if(succes){
                    setUpRecycler();
                }
            }
        };

        setUpRecycler();
        trucks = DataService.getInstance().downloadAllFoodTrucks( this, listener );

    }

    private void setUpRecycler(){
        RecyclerView recyclerView = (RecyclerView) findViewById( R.id.recycler_foodtruck );
        recyclerView.setHasFixedSize( true );
        adapter = new FoodTruckAdapter( trucks );
        recyclerView.setAdapter( adapter );
        LinearLayoutManager layoutManager = new LinearLayoutManager( getBaseContext() );
        layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.addItemDecoration( new ItemDecorator( 0,0,0,10 ) );

    }


    public interface TrucksDownloaded {
        void success(Boolean succes);
    }

    public void loadFoodTruckDetailActivity(FoodTruck truck){
        Intent intent = new Intent( FoodTrucksListsActivity.this, FoodTruckDetailActivity.class );
        intent.putExtra( FoodTrucksListsActivity.EXTRA_ITEM_TRUCK, truck );
        startActivity( intent );
    }
}
