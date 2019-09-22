package com.example.a86130.x_day02_b.model;

import android.util.Log;

import com.example.a86130.x_day02_b.ApiService;
import com.example.a86130.x_day02_b.bean.FuliBean;
import com.example.a86130.x_day02_b.presenter.NetPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 86130 on 2019/9/20.
 */

public class NetModel {
    public void loadData(final NetCallBack netCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FuliBean> geturl = apiService.geturl();
        geturl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuliBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuliBean fuliBean) {
                      if(fuliBean!=null&&fuliBean.getResults()!=null){
                          ArrayList<FuliBean.ResultsBean> results = (ArrayList<FuliBean.ResultsBean>) fuliBean.getResults();
                          Log.i("111",results.toString());
                          netCallBack.onSucceed(results);
                      }else{
                          netCallBack.onFali("请求的数据为空");
                      }
                    }

                    @Override
                    public void onError(Throwable e) {
                           netCallBack.onFali("请求数据异常");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface NetCallBack{
        void onSucceed(ArrayList<FuliBean.ResultsBean> fb);
        void onFali(String str);

    }
}
