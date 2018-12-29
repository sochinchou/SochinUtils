package com.sochin.code.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sochin.R;

import java.util.List;

/**
 * Created by Administrator on 2018/11/5.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private List<String> mList;

    public MyRecyclerAdapter(List<String> arr) {
        this.mList = arr;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_recycler, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtTitle.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
