package com.noomnim.wtf.activities;

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
import com.noomnim.wtf.model.FoodTruck;
import com.noomnim.wtf.view.ItemDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodTrucksListsActivity extends AppCompatActivity {

    // Variable
    private FoodTruckAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_food_trucks_lists );

        String url = "http://10.0.2.2:3005/api/v1/foodtruck";
        final ArrayList<FoodTruck> foodTrucksList = new ArrayList<>(  );

        final JsonArrayRequest getTrucks = new JsonArrayRequest( Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println( response.toString() );
                Log.i("API", "Err " + response.toString(  ));

                try{
                    JSONArray foodTrucks = response;
                    Log.d( "JSON ARRAY" , String.valueOf( foodTrucks ) );
                    for(int x = 0; x < foodTrucks.length(); x++){
                        JSONObject foodTruck = foodTrucks.getJSONObject( x );
                        String name = foodTruck.getString( "name" );
                        String id = foodTruck.getString( "_id" );
                        String foodType = foodTruck.getString( "foodtype" );
                        Double avgcost = foodTruck.getDouble( "avgcost" );

                        JSONObject geometry = foodTruck.getJSONObject( "geometry" );
                        JSONObject coordinates = geometry.getJSONObject( "coordinates" );
                        Double latitude = coordinates.getDouble( "lat" );
                        Double longtitude = coordinates.getDouble( "long" );

                        FoodTruck newFoodTruck = new FoodTruck( id,name,foodType,avgcost,latitude,longtitude );
                        foodTrucksList.add( newFoodTruck );
                        Log.i("MSG","JSON " + name);

                    }
                }catch (JSONException e){
                    Log.v("JSON","EXC " + e.getLocalizedMessage());
                }

//                Log.d( "GET JSON" , "This is the food truck name " + foodTrucksList.get( 1 ).getName() );
//                System.out.println( "This is the food truck name " + foodTrucksList.get( 1 ).getName() );
                RecyclerView recyclerView = (RecyclerView) findViewById( R.id.recycler_foodtruck );
                recyclerView.setHasFixedSize( true );
                adapter = new FoodTruckAdapter( foodTrucksList );
                recyclerView.setAdapter( adapter );
                LinearLayoutManager layoutManager = new LinearLayoutManager( getBaseContext() );
                layoutManager.setOrientation( LinearLayoutManager.VERTICAL );
                recyclerView.setLayoutManager( layoutManager );
                recyclerView.addItemDecoration( new ItemDecorator( 0,0,0,10 ) );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API", "Err " + error.getLocalizedMessage());
            }
        } );

        Volley.newRequestQueue( this ).add( getTrucks );
    }
}
