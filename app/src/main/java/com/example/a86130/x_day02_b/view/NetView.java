package com.example.a86130.x_day02_b.view;

import com.example.a86130.x_day02_b.bean.FuliBean;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/20.
 */

public interface NetView {
    void addDatas(ArrayList<FuliBean.ResultsBean> fb);
    void showToast(String str);
}
