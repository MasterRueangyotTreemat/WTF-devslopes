package com.noomnim.wtf.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.noomnim.wtf.R;
import com.noomnim.wtf.model.FoodTruck;

public class FoodTruckHolder extends RecyclerView.ViewHolder {
    private TextView truckName;
    private TextView foodType;
    private TextView avgCost;
    // Place to binding Widget
    public FoodTruckHolder(@NonNull View itemView) {
        super( itemView );
        this.truckName = (TextView) itemView.findViewById( R.id.food_truck_name );
        this.foodType = (TextView) itemView.findViewById( R.id.food_truck_type );
        this.avgCost = (TextView) itemView.findViewById( R.id.food_truck_cost );
    }
    public void updateUI(FoodTruck truck){
        truckName.setText( truck.getName() );
        foodType.setText( truck.getFoodtype() );
        avgCost.setText("$" + truck.getAvgcost() );
    }
}
