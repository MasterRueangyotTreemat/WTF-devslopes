package com.noomnim.wtf.data;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.noomnim.wtf.R;
import com.noomnim.wtf.activities.FoodTrucksListsActivity;
import com.noomnim.wtf.activities.ReviewsActivity;
import com.noomnim.wtf.adapter.FoodTruckAdapter;
import com.noomnim.wtf.constants.Constants;
import com.noomnim.wtf.model.FoodTruck;
import com.noomnim.wtf.model.FoodTruckReview;
import com.noomnim.wtf.view.ItemDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataService {
    private static DataService instance = new DataService();

    public static DataService getInstance(){
        return  instance;
    }

    private DataService(){

    }

    //Request all the FoodTrucks

    public ArrayList<FoodTruck> downloadAllFoodTrucks(Context context, final FoodTrucksListsActivity.TrucksDownloaded listener){
        String url = Constants.GET_FOOD_TRUCKS;
        final ArrayList<FoodTruck> foodTrucksList = new ArrayList<>(  );

        final JsonArrayRequest getTrucks = new JsonArrayRequest( Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println( response.toString() );
                Log.i( "API", "Err " + response.toString() );

                try {
                    JSONArray foodTrucks = response;
                    Log.d( "JSON ARRAY", String.valueOf( foodTrucks ) );
                    for (int x = 0; x < foodTrucks.length(); x++) {
                        JSONObject foodTruck = foodTrucks.getJSONObject( x );
                        String name = foodTruck.getString( "name" );
                        String id = foodTruck.getString( "_id" );
                        String foodType = foodTruck.getString( "foodtype" );
                        Double avgcost = foodTruck.getDouble( "avgcost" );

                        JSONObject geometry = foodTruck.getJSONObject( "geometry" );
                        JSONObject coordinates = geometry.getJSONObject( "coordinates" );
                        Double latitude = coordinates.getDouble( "lat" );
                        Double longtitude = coordinates.getDouble( "long" );

                        FoodTruck newFoodTruck = new FoodTruck( id, name, foodType, avgcost, latitude, longtitude );
                        foodTrucksList.add( newFoodTruck );
                        Log.i( "MSG", "JSON " + name );

                    }
                } catch (JSONException e) {
                    Log.v( "JSON", "EXC " + e.getLocalizedMessage() );
                }

//                Log.d( "GET JSON" , "This is the food truck name " + foodTrucksList.get( 1 ).getName() );
//                System.out.println( "This is the food truck name " + foodTrucksList.get( 1 ).getName() );

                listener.success( true );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API", "Err " + error.getLocalizedMessage());
            }
        } );

        Volley.newRequestQueue( context ).add( getTrucks );
        return foodTrucksList;
    }


    //Request all the FoodTrucks Reviews

    public ArrayList<FoodTruckReview> downloadReviews(Context context, FoodTruck foodTruck, final ReviewsActivity.ReviewInterface listener){
        String url = Constants.GET_REVIEWS + foodTruck.getId();
        final ArrayList<FoodTruckReview> reviewsList = new ArrayList<>(  );

        final JsonArrayRequest getReviews = new JsonArrayRequest( Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println( response.toString() );
                Log.i( "API", "Err " + response.toString() );

                try {
                    JSONArray reviews = response;
                    Log.d( "JSON ARRAY", String.valueOf( reviews ) );
                    for (int x = 0; x < reviews.length(); x++) {
                        JSONObject review = reviews.getJSONObject( x );
                        String title = review.getString( "title" );
                        String id = review.getString( "_id" );
                        String text = review.getString( "text" );

                        FoodTruckReview newFoodTruckReview = new FoodTruckReview( id, title, text );
                        reviewsList.add( newFoodTruckReview );
                        Log.i( "MSG", "JSON " + title );

                    }
                } catch (JSONException e) {
                    Log.v( "JSON", "EXC " + e.getLocalizedMessage() );
                }

//                Log.d( "GET JSON" , "This is the food truck name " + foodTrucksList.get( 1 ).getName() );
//                System.out.println( "This is the food truck name " + foodTrucksList.get( 1 ).getName() );
                listener.success( true );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API", "Err " + error.getLocalizedMessage());
            }
        } );

        Volley.newRequestQueue( context ).add( getReviews );
        return reviewsList;
    }





}
