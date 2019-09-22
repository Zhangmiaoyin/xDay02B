package com.example.a86130.x_day02_b;

import com.example.a86130.x_day02_b.bean.FuliBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 86130 on 2019/9/20.
 */

public interface ApiService {
    String url="http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<FuliBean> geturl();
}
