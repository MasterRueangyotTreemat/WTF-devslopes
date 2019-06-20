package com.noomnim.wtf.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noomnim.wtf.R;
import com.noomnim.wtf.activities.FoodTrucksListsActivity;
import com.noomnim.wtf.holder.FoodTruckHolder;
import com.noomnim.wtf.model.FoodTruck;

import java.util.ArrayList;

public class FoodTruckAdapter extends RecyclerView.Adapter<FoodTruckHolder> {

    private ArrayList<FoodTruck> trucks;
    public  FoodTruckAdapter(ArrayList<FoodTruck> trucks){
        this.trucks = trucks;
    }

    @NonNull
    @Override
    public FoodTruckHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //initialize view
        View truckCard = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.card_foodtruck, viewGroup, false );
        return new FoodTruckHolder( truckCard );
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTruckHolder foodTruckHolder, int i) {
        final  FoodTruck truck = trucks.get( i );
        foodTruckHolder.updateUI( truck );

        foodTruckHolder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodTrucksListsActivity.getFoodTrucksListsActivity().loadFoodTruckDetailActivity(truck);
            }
        } );
    }

    @Override
    public int getItemCount() {
        return trucks.size();
    }
}
