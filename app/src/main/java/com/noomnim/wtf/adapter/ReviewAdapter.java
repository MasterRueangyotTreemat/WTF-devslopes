package com.noomnim.wtf.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noomnim.wtf.R;
import com.noomnim.wtf.holder.FoodTruckHolder;
import com.noomnim.wtf.holder.ReviewHolder;
import com.noomnim.wtf.model.FoodTruckReview;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder> {

    private ArrayList<FoodTruckReview> reviews;

    public ReviewAdapter(ArrayList<FoodTruckReview> reviews){
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View reviewCard = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.card_view, viewGroup, false );
        return new ReviewHolder( reviewCard );
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder reviewHolder, int i) {
        final FoodTruckReview review = reviews.get( i );
        reviewHolder.updateUI( review );
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
