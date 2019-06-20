package com.noomnim.wtf.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodTruckReview implements Parcelable {
    private String id = "";
    private String title = "";
    private String text = "";

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public FoodTruckReview(String id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public void FoodTruckReview(Parcel in){
        id = in.readString();
        title = in.readString();
        text = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( id );
        dest.writeString( title );
        dest.writeString( text );
    }
}
