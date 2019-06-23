package com.noomnim.wtf.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.noomnim.wtf.R;
import com.noomnim.wtf.adapter.FoodTruckAdapter;
import com.noomnim.wtf.constants.Constants;
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
    private FloatingActionButton addTruckBtn;
    public static final  String EXTRA_ITEM_TRUCK = "TRUCK";
    SharedPreferences prefs;

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

        addTruckBtn = (FloatingActionButton) findViewById( R.id.addTruckBtn );

        TrucksDownloaded listener = new TrucksDownloaded() {
            @Override
            public void success(Boolean success) {
                if(success){
                    setUpRecycler();
                }
            }
        };

        setUpRecycler();
        trucks = DataService.getInstance().downloadAllFoodTrucks( this, listener );


        prefs = PreferenceManager.getDefaultSharedPreferences( this );

        addTruckBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddTruck();
            }
        } );
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
        void success(Boolean success);
    }

    public void loadFoodTruckDetailActivity(FoodTruck truck){
        Intent intent = new Intent( FoodTrucksListsActivity.this, FoodTruckDetailActivity.class );
        intent.putExtra( FoodTrucksListsActivity.EXTRA_ITEM_TRUCK, truck );
        startActivity( intent );
    }

    public void loadAddTruck(){
        if(prefs.getBoolean( Constants.IS_LOGGED_IN, false )){
            Intent intent =  new Intent( FoodTrucksListsActivity.this, AddTruckActivity.class );
            startActivity( intent );
        }else{
            Intent intent =  new Intent( FoodTrucksListsActivity.this, LoginActivity.class );
            Toast.makeText( getBaseContext(),"Please Login to add food truck" ,Toast.LENGTH_SHORT).show();
            startActivity( intent );
        }
    }
}
