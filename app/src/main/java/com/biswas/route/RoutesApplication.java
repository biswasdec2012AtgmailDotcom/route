package com.biswas.route;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.biswas.route.data.RouteFactory;
import com.biswas.route.data.RouteService;


/**
 * Created by Rajan on 12/30/2017.
 */

public class RoutesApplication extends Application {

    private static RoutesApplication mInstance;
    private Typeface routeFontRegular = null;
    private Typeface routeFontRegularBold = null;
    private Typeface routeFontMedium = null;
    private RouteService mRouteService = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public RouteService getRedmartApiService() {
        if (mRouteService == null) {
            mRouteService = RouteFactory.create(this);
        }
        return mRouteService;
    }

    public Typeface getRouteFontRegular() {
        if (routeFontRegular == null) {
            routeFontRegular = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Regular.ttf");
        }
        return routeFontRegular;
    }

    public Typeface getRouteFontMedium() {
        if (routeFontMedium == null) {
            routeFontMedium = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Medium.ttf");
        }
        return routeFontMedium;
    }

    public Typeface getRedmartFontBold() {
        if (routeFontRegularBold == null) {
            routeFontRegularBold = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Bold.ttf");
        }
        return routeFontRegularBold;
    }
}
