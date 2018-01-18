package com.biswas.route.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.biswas.route.R;
import com.biswas.route.model.Stop;
import com.biswas.route.view.viewHolders.StopViewHolder;

import java.util.ArrayList;


public class StopsAdapter extends RecyclerView.Adapter
{
    private ArrayList<Stop> mStops;
    private Context mContext;

    public StopsAdapter(Context context, ArrayList<Stop> stops)
    {
        mContext = context;
        mStops = stops;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return (new StopViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_stop, parent, false)));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        StopViewHolder svh = (StopViewHolder) holder;
        if (mStops.size() - 1 == position)
            svh.ivVline.setVisibility(View.GONE);
        else
            svh.ivVline.setVisibility(View.VISIBLE);
        svh.tvRouteName.setText(mStops.get(position).mName);
    }

    @Override
    public int getItemCount()
    {
        return mStops.size();
    }
}
