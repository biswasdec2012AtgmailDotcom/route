package com.biswas.route.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.biswas.route.R;
import com.biswas.route.model.Route;
import com.biswas.route.presenter.ListItemClickListener;
import com.biswas.route.view.viewHolders.RouteViewHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class RoutesAdapter extends RecyclerView.Adapter
{
    private ArrayList<Route> mRoutes;
    private Context mContext;
    private ListItemClickListener mListener;

    public RoutesAdapter(Context context, ArrayList<Route> routes, ListItemClickListener listener)
    {
        mContext = context;
        mRoutes = routes;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return (new RouteViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_route, parent, false), mListener));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        RouteViewHolder rhvh = (RouteViewHolder) holder;

        rhvh.ivAccesibility.setVisibility(View.GONE);
        rhvh.tvRouteName.setText("" + mRoutes.get(position).mTitle);
        rhvh.tvDescription.setText("" + mRoutes.get(position).mDesc);
        Glide.with(mContext)
                .load(mRoutes.get(position).mImage)
                .placeholder(mContext.getResources().getDrawable(R.drawable.place_holder))
                .error(mContext.getResources().getDrawable(R.drawable.place_holder))
                .into(rhvh.ivWheel);
    }

    @Override
    public int getItemCount()
    {
        return mRoutes.size();
    }
}
