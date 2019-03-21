package com.bawei.project.model;

import com.bawei.project.api.ApiService;
import com.bawei.project.api.MyApi;
import com.bawei.project.bean.BannerBean;
import com.bawei.project.bean.HomeBean;
import com.bawei.project.bean.SearchBean;
import com.bawei.project.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class HomeModel {
    public void sendBanner() {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(MyApi.BannerUrl, ApiService.class);
        Flowable<BannerBean> banner = apiService.getBanner();
        banner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BannerBean>() {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        List<BannerBean.ResultBean> result = bannerBean.getResult();
                        if (homeLisenter != null) {
                            homeLisenter.OnBanner(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void sendSearch(String s, int page, int count) {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(MyApi.SearchUrl, ApiService.class);
        Flowable<SearchBean> search1 = apiService.getSearch(s, page, count);
        search1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.ResultBean> result = searchBean.getResult();
                        if (homeLisenter != null) {
                            homeLisenter.OnHome(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sendHome() {
        ApiService apiService = RetrofitUtils.getInstance().getApiService(MyApi.HomeUrl, ApiService.class);
        Flowable<HomeBean> home = apiService.getHome();
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        HomeBean.ResultBean result = homeBean.getResult();
                        if (homeLisenter != null) {
                            homeLisenter.OnHome(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface OnHomeLisenter {
        void OnHome(List<SearchBean.ResultBean> result);

        void OnBanner(List<BannerBean.ResultBean> result);

        void OnHome(HomeBean.ResultBean result);
    }

    private OnHomeLisenter homeLisenter;

    public void setOnHomeLisenter(OnHomeLisenter homeLisenter) {
        this.homeLisenter = homeLisenter;
    }
}
