package com.bawei.project.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.project.MainActivity;
import com.bawei.project.R;
import com.bawei.project.WebActivity;
import com.bawei.project.adapter.HomeAdapter;
import com.bawei.project.adapter.SearchAdapter;
import com.bawei.project.base.BaseFragment;
import com.bawei.project.bean.BannerBean;
import com.bawei.project.bean.HomeBean;
import com.bawei.project.bean.SearchBean;
import com.bawei.project.custom.SearchView;
import com.bawei.project.presenter.HomePresenter;
import com.bawei.project.view.HomeView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener {

    private ImageView img_classify, img_glass;
    private SearchView view_search;
    private SwipeRefreshLayout s_search;
    private RecyclerView r_search;
    private EditText edit_search;
    private TextView text_search, text_cancel;
    private LinearLayout lay;
    private RelativeLayout rel;
    private RecyclerView r_home;
    private Banner banner;

    private HomePresenter homePresenter;
    private int page = 1;
    private int count = 10;
    private Handler handler = new Handler();


    @Override
    protected int layoutRID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        //找控件
        img_classify = view.findViewById(R.id.img_classify);
        img_glass = view.findViewById(R.id.img_glass);
        view_search = view.findViewById(R.id.view_search);
        s_search = view.findViewById(R.id.s_search);
        r_search = view.findViewById(R.id.r_search);
        text_cancel = view.findViewById(R.id.text_cancel);
        edit_search = view.findViewById(R.id.edit_search);
        text_search = view.findViewById(R.id.text_search);
        text_cancel = view.findViewById(R.id.text_cancel);
        lay = view.findViewById(R.id.lay);
        rel = view.findViewById(R.id.rel);
        banner = view.findViewById(R.id.banner);
        r_home = view.findViewById(R.id.r_home);
        //点击事件
        img_classify.setOnClickListener(this);
        img_glass.setOnClickListener(this);
        //presenter
        homePresenter = new HomePresenter(this);
    }

    @Override
    protected void initData() {
        //搜索框
        getSearch();
        //轮播图
        getBanner();
        //首页商品列表
        getHomeList();
    }

    private void getHomeList() {
        r_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line2_shape));
        //添加分割线
        r_home.addItemDecoration(dividerItemDecoration);
        homePresenter.sendHome();
    }

    private void getBanner() {
        homePresenter.sendBanner();
    }

    private void getSearch() {
        text_cancel.setOnClickListener(this);
        view_search.setOnSearchLisenter(new SearchView.OnSearchLisenter() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void OnSearch(final String s) {
                rel.setVisibility(View.VISIBLE);
                //设置布局管理器
                r_search.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                //设置分割线
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.line_shape));
                //添加分割线
                r_search.addItemDecoration(dividerItemDecoration);
                //设置刷新颜色
                s_search.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
                s_search.setProgressBackgroundColorSchemeColor(android.R.color.white);
                //下拉加载
                s_search.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        homePresenter.sendSearch(s, page, count);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                                s_search.setRefreshing(false);
                            }
                        }, 2000);
                    }
                });
                homePresenter.sendSearch(s, page, count);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_classify:

                break;
            case R.id.img_glass:
                view_search.setVisibility(View.VISIBLE);
                img_glass.setVisibility(View.GONE);
                break;
            case R.id.text_cancel:
                text_cancel.setVisibility(View.GONE);
                text_search.setVisibility(View.VISIBLE);
                edit_search.setText("");
                rel.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void sendSearch(List<SearchBean.ResultBean> result) {
        if (result.size() == 0) {
            lay.setVisibility(View.VISIBLE);
            r_search.setVisibility(View.GONE);
        } else {
            SearchAdapter searchAdapter = new SearchAdapter(getActivity(), result);
            r_search.setAdapter(searchAdapter);
        }
    }

    @Override
    public void sendBanner(final List<BannerBean.ResultBean> result) {
        ArrayList<String> img = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            img.add(result.get(i).getImageUrl());
        }
        Log.i("xxxx", img.toString());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(3000);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context)
                        .load(path)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(60)))
                        .into(imageView);
            }
        });
        banner.setImages(img);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String jumpUrl = result.get(position).getJumpUrl();
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", jumpUrl);
                startActivity(intent);
            }
        });
        banner.start();
    }

    @Override
    public void sendHome(HomeBean.ResultBean result) {
        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), result);
        r_home.setAdapter(homeAdapter);
    }

}
