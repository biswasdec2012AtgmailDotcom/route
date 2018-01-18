package com.biswas.route.view.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.biswas.route.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StopViewHolder extends RecyclerView.ViewHolder
{

    @BindView(R.id.tv_route_name)
    public TextView tvRouteName;

    @BindView(R.id.iv_vline)
    public View ivVline;

    public StopViewHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
