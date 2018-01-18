package com.biswas.route.data;

import com.biswas.route.model.Route;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by bishwajeetbiswas on 02/10/17.
 */

public class RouteResponse
{
    @SerializedName("routes")
    public ArrayList<Route> mRoutes;
}
