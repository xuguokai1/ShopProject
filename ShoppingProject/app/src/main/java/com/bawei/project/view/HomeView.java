package com.bawei.project.view;

import com.bawei.project.bean.BannerBean;
import com.bawei.project.bean.HomeBean;
import com.bawei.project.bean.SearchBean;

import java.util.List;

public interface HomeView {
    void sendSearch(List<SearchBean.ResultBean> result);

    void sendBanner(List<BannerBean.ResultBean> result);

    void sendHome(HomeBean.ResultBean result);
}
