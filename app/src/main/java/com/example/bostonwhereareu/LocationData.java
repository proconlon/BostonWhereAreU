package com.example.bostonwhereareu;

import android.annotation.SuppressLint;

public class LocationData {
    private int xCoordinate;
    private int yCoordinate;
    private String BU_name;

    public LocationData(int xCoordinate, int yCoordinate, String placeName) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.BU_name = placeName;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public String getPlaceName() {
        return BU_name;
    }
}
