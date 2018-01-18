package com.biswas.route.view.viewHolders;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.biswas.route.R;
import com.biswas.route.presenter.ListItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.tv_route_name)
    public TextView tvRouteName;

    @BindView(R.id.tv_description)
    public TextView tvDescription;

    @BindView(R.id.iv_wheel)
    public AppCompatImageView ivWheel;

    @BindView(R.id.iv_accesibility)
    public AppCompatImageView ivAccesibility;

    private ListItemClickListener mListener;

    public RouteViewHolder(View itemView, ListItemClickListener listener) {
        super(itemView);
        mListener=listener;
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view, getAdapterPosition());
    }
}
