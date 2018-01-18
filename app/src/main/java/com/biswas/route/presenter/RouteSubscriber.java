package com.biswas.route.presenter;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;


import com.biswas.route.R;
import com.biswas.route.data.RouteResponse;
import com.biswas.route.model.CommonMethods;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class RouteSubscriber<T> implements Observer<T> {
    private Context mContext;
    private boolean manageEroor;

    public RouteSubscriber(Context context, boolean manageError) {
        this.manageEroor = manageError;
        this.mContext = context;
        if (context instanceof Activity && manageError)
            this.manageEroor = true;
        else this.manageEroor = false;
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onComplete();
        RouteResponse response = (RouteResponse) t;
        if (response.mRoutes != null && response.mRoutes.size()>0) {
            //for other stuffs
        } else
            CommonMethods.showOkAlertDialog(mContext.getResources().getText(R.string.no_data_found_try_again).toString(), mContext.getResources().getString(R.string.app_name)
                    , mContext, true);
    }

    @Override
    public void onError(Throwable e) {
        onComplete();
        if (manageEroor)
            CommonMethods.showOkAlertDialog(mContext.getResources().getText(R.string.network_error).toString(), mContext.getResources().getString(R.string.app_name)
                    , mContext, true);
    }

    @Override
    public abstract void onComplete();
}
