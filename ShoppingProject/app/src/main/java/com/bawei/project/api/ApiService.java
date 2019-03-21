package com.bawei.project.api;

import com.bawei.project.bean.BannerBean;
import com.bawei.project.bean.HomeBean;
import com.bawei.project.bean.SearchBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //findCommodityByKeyword
    @GET("findCommodityByKeyword")
    Flowable<SearchBean> getSearch(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);

    //bannerShow
    @GET("bannerShow")
    Flowable<BannerBean> getBanner();

    //commodityList
    @GET("commodityList")
    Flowable<HomeBean> getHome();
}
