package com.biswas.route.data;

import android.content.Context;

import com.biswas.route.BuildConfig;
import com.biswas.route.utils.ConnectivityInterceptor;
import com.google.gson.Gson;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RouteFactory
{
    private static RouteService mInterface;

    public static RouteService create(Context context) {

        if (mInterface == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new ConnectivityInterceptor(context));
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.ROUTE_BASE_URL)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();
            mInterface = retrofit.create(RouteService.class);
        }
        return mInterface;
    }
}

