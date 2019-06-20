package com.noomnim.wtf.model;

public class FoodTruck {
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
}
