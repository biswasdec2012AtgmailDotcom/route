package com.biswas.route.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.biswas.route.R;
import com.biswas.route.model.Route;
import com.biswas.route.model.Stop;
import com.biswas.route.view.adapters.StopsAdapter;
import com.bumptech.glide.Glide;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RouteDetailsActivity extends RoutesBaseActivity
{

    @BindView(R.id.tv_route_name)
    public TextView tvRouteName;

    @BindView(R.id.tv_description)
    public TextView tvDescription;

    @BindView(R.id.iv_wheel)
    public AppCompatImageView ivWheel;

    @BindView(R.id.iv_accesibility)
    public AppCompatImageView ivAccesibility;

    @BindView(R.id.rv_routes)
    RecyclerView rvRoutes;


    private Route mRoute;

    public static void launchActivity(Activity activity, Route route)
    {
        activity.startActivity(new Intent(activity, RouteDetailsActivity.class).putExtra("route", route));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_route);
        ButterKnife.bind(this);
    }

    @Override
    public void handleActionBar()
    {
        showToolbar(false);
    }

    @Override
    public void loadValuesFromIntent()
    {
        Intent intent = getIntent();
        mRoute = (Route) intent.getSerializableExtra("route");
        displayRouteDetail(mRoute);
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
        rvRoutes.setLayoutManager(linearLayoutManager);
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

    private void displayRouteDetail(Route route)
    {
        Glide.with(mActivity)
                .load(route.mImage)
                .placeholder(getResources().getDrawable(R.drawable.place_holder))
                .error(getResources().getDrawable(R.drawable.place_holder))
                .into(ivWheel);
        tvRouteName.setText(route.mTitle);
        tvDescription.setMaxLines(Integer.MAX_VALUE);
        tvDescription.setText(route.mDesc);
        if (route.mAccessible)
            ivAccesibility.setVisibility(View.VISIBLE);
        else
            ivAccesibility.setVisibility(View.GONE);
        ArrayList<Stop> mStops = route.mStops;
        if (mStops != null && mStops.size() > 0)
        {
            rvRoutes.setVisibility(View.VISIBLE);
            StopsAdapter productImageAdapter = new StopsAdapter(mActivity, mStops);
            rvRoutes.setAdapter(productImageAdapter);
            runLayoutAnimation(rvRoutes);
        } else
        {
            rvRoutes.setVisibility(View.GONE);
        }
    }
}
