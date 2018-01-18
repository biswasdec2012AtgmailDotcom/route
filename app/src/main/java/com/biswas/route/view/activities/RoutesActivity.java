package com.biswas.route.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;


import com.biswas.route.R;
import com.biswas.route.RoutesApplication;
import com.biswas.route.data.RouteResponse;
import com.biswas.route.model.CommonMethods;
import com.biswas.route.model.Route;
import com.biswas.route.presenter.ListItemClickListener;
import com.biswas.route.presenter.RouteSubscriber;
import com.biswas.route.view.adapters.RoutesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RoutesActivity extends RoutesBaseActivity
{

    @BindView(R.id.rv_products)
    RecyclerView rvProducts;

    private RoutesApplication mApp;
    private ArrayList<Route> routes;
    private RoutesAdapter routesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);
        mApp = (RoutesApplication) getApplication();
        ButterKnife.bind(this);
        getRoutes();
    }

    @Override
    public void handleActionBar()
    {
        showToolbar(false);
    }

    @Override
    public void loadValuesFromIntent()
    {

    }

    @Override
    public void initViews()
    {
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false);
        rvProducts.setLayoutManager(linearLayoutManager);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView)
    {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void getRoutes()
    {
        CommonMethods.showBottomProgressDialoge(mActivity, getResources().getString(R.string.please_wait));
        mApp.getRedmartApiService()
                .getRoutes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RouteSubscriber<RouteResponse>(mActivity, true)
                {
                    @Override
                    public void onComplete()
                    {
                        CommonMethods.hideBottomProgressDialoge();
                    }

                    @Override
                    public void onNext(RouteResponse routeResponse)
                    {
                        super.onNext(routeResponse);
                        if (routeResponse != null)
                            displayRoutes(routeResponse);
                        else
                            CommonMethods.showOkAlertDialog(mActivity.getResources().getText(R.string.no_data_found_try_again)
                                            .toString(), mActivity.getResources().getString(R.string.app_name)
                                    , mActivity, true);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        super.onError(e);
                    }
                });
    }

    private void displayRoutes(RouteResponse routeResponse)
    {
        ArrayList<Route> routeList = routeResponse.mRoutes;
        routes = new ArrayList<>();
        int previousSize = routes.size();
        routes.addAll(routeList);
        if (routesAdapter == null)
        {
            routesAdapter = new RoutesAdapter(mActivity, routes, new ListItemClickListener()
            {
                @Override
                public void onClick(View view, int position)
                {
                    Route route = routes.get(position);
                    RouteDetailsActivity.launchActivity(mActivity, route);
                }
            });
            rvProducts.setAdapter(routesAdapter);
        } else
            routesAdapter.notifyItemRangeChanged(previousSize, routes.size());
        runLayoutAnimation(rvProducts);
    }
}
