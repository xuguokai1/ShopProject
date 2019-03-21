package com.bawei.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.project.R;
import com.bawei.project.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHandler> {
    Context context;
    List<SearchBean.ResultBean> result;

    public SearchAdapter(Context context, List<SearchBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_search, null);
        MyViewHandler myViewHandler = new MyViewHandler(view);
        return myViewHandler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHandler myViewHandler, int i) {
        //赋值
        String commodityName = result.get(i).getCommodityName();
        String masterPic = result.get(i).getMasterPic();
        int price = result.get(i).getPrice();
        int saleNum = result.get(i).getSaleNum();

        myViewHandler.search_img.setImageURI(masterPic);
        myViewHandler.search_name.setText(commodityName);
        myViewHandler.search_price.setText("￥" + price);
        myViewHandler.search_num.setText("已售" + saleNum + "件");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MyViewHandler extends RecyclerView.ViewHolder {
        private SimpleDraweeView search_img;
        private TextView search_name, search_price, search_num;

        public MyViewHandler(@NonNull View itemView) {
            super(itemView);
            search_img = itemView.findViewById(R.id.search_img);
            search_name = itemView.findViewById(R.id.search_name);
            search_price = itemView.findViewById(R.id.search_price);
            search_num = itemView.findViewById(R.id.search_num);
        }
    }
}
