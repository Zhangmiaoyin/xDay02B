package com.example.a86130.x_day02_b;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.a86130.x_day02_b.bean.FuliBean;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/22.
 */

class MyVpAdapter extends PagerAdapter{
    private ArrayList<FuliBean.ResultsBean> fuliBeans;
    private ArrayList<View> views;

    public MyVpAdapter(ArrayList<FuliBean.ResultsBean> fuliBeans, ArrayList<View> views) {
        this.fuliBeans = fuliBeans;
        this.views = views;
    }

    @Override
    public int getCount() {
        return fuliBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = views.get(position);
        container.removeView(view);
    }
}
