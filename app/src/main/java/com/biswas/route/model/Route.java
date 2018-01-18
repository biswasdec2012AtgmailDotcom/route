package com.biswas.route.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Route implements Serializable {
    @SerializedName("id")
    public String mId;

    @SerializedName("name")
    public String mTitle;

    @SerializedName("description")
    public String mDesc;

    @SerializedName("accessible")
    public boolean mAccessible;

    @SerializedName("image")
    public String mImage;

    @SerializedName("stops")
    public ArrayList<Stop> mStops = new ArrayList<>();
}
