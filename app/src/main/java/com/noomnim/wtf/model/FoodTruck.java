package com.noomnim.wtf.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodTruck implements Parcelable {
    private String id = "";
    private String name = "";
    private String foodtype = "";
    private Double avgcost = 0.0;
    private Double latitude = 0.0;
    private Double longtitude = 0.0;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public Double getAvgcost() {
        return avgcost;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public FoodTruck(String id, String name, String foodtype, Double avgcost, Double latitude, Double longtitude) {
        this.id = id;
        this.name = name;
        this.foodtype = foodtype;
        this.avgcost = avgcost;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    private FoodTruck(Parcel in){
        id = in.readString();
        name = in.readString();
        foodtype = in.readString();
        avgcost = in.readDouble();
        latitude = in.readDouble();
        longtitude = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString( id );
        dest.writeString( name );
        dest.writeString( foodtype );
        dest.writeDouble( avgcost );
        dest.writeDouble( latitude );
        dest.writeDouble( longtitude );
    }

    public static final  Parcelable.Creator<FoodTruck> CREATOR = new Creator<FoodTruck>() {
        @Override
        public FoodTruck createFromParcel(Parcel source) {
            return new FoodTruck( source );
        }

        @Override
        public FoodTruck[] newArray(int size) {
            return new FoodTruck[size];
        }
    };


}
