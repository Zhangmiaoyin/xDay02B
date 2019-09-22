package com.example.a86130.x_day02_b.presenter;

import com.example.a86130.x_day02_b.bean.FuliBean;
import com.example.a86130.x_day02_b.model.NetModel;
import com.example.a86130.x_day02_b.view.NetView;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/20.
 */

public class NetPresenter implements NetModel.NetCallBack{
    private NetView netView;
    private NetModel netModel;
    public NetPresenter(NetView netView) {
        this.netView = netView;
        this.netModel=new NetModel();
    }
    //加载数据
    public void loadData() {
         netModel.loadData(this);
    }

    @Override
    public void onSucceed(ArrayList<FuliBean.ResultsBean> fb) {
        netView.addDatas(fb);
    }

    @Override
    public void onFali(String str) {
       netView.showToast(str);
    }
}
