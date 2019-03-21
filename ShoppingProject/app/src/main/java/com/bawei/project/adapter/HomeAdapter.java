package com.bawei.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.project.R;
import com.bawei.project.bean.HomeBean;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    HomeBean.ResultBean result;

    public HomeAdapter(Context context, HomeBean.ResultBean result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return 0;
        } else if (position % 3 == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_home1, null);
            MyViewHandler myViewHandler = new MyViewHandler(view);
            return myViewHandler;
        } else if (i == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_home2, null);
            MyViewHandler1 myViewHandler1 = new MyViewHandler1(view);
            return myViewHandler1;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_home3, null);
            MyViewHandler2 myViewHandler2 = new MyViewHandler2(view);
            return myViewHandler2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    private class MyViewHandler extends RecyclerView.ViewHolder {
        public MyViewHandler(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class MyViewHandler1 extends RecyclerView.ViewHolder {
        public MyViewHandler1(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class MyViewHandler2 extends RecyclerView.ViewHolder {
        public MyViewHandler2(@NonNull View itemView) {
            super(itemView);
        }
    }
}
