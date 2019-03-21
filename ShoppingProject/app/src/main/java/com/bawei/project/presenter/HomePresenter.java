package com.bawei.project.presenter;

import com.bawei.project.bean.BannerBean;
import com.bawei.project.bean.HomeBean;
import com.bawei.project.bean.SearchBean;
import com.bawei.project.model.HomeModel;
import com.bawei.project.view.HomeView;

import java.util.List;

public class HomePresenter {
    private HomeModel homeModel;
    private HomeView homeView;

    public HomePresenter(HomeView view) {
        homeModel = new HomeModel();
        homeView = view;
    }

    public void sendSearch(String s, int page, int count) {
        homeModel.sendSearch(s, page, count);
        homeModel.setOnHomeLisenter(new HomeModel.OnHomeLisenter() {
            @Override
            public void OnHome(List<SearchBean.ResultBean> result) {
                homeView.sendSearch(result);
            }

            @Override
            public void OnBanner(List<BannerBean.ResultBean> result) {

            }

            @Override
            public void OnHome(HomeBean.ResultBean result) {

            }
        });
    }

    public void sendBanner() {
        homeModel.sendBanner();
        homeModel.setOnHomeLisenter(new HomeModel.OnHomeLisenter() {
            @Override
            public void OnHome(List<SearchBean.ResultBean> result) {

            }

            @Override
            public void OnBanner(List<BannerBean.ResultBean> result) {
                homeView.sendBanner(result);
            }

            @Override
            public void OnHome(HomeBean.ResultBean result) {

            }
        });
    }

    public void sendHome() {
        homeModel.sendHome();
        homeModel.setOnHomeLisenter(new HomeModel.OnHomeLisenter() {
            @Override
            public void OnHome(List<SearchBean.ResultBean> result) {

            }

            @Override
            public void OnBanner(List<BannerBean.ResultBean> result) {

            }

            @Override
            public void OnHome(HomeBean.ResultBean result) {
                homeView.sendHome(result);
            }
        });
    }
}
