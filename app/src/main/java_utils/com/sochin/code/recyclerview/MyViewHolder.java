package com.sochin.code.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sochin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/11/5.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
