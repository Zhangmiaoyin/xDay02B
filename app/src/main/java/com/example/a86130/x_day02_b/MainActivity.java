package com.example.a86130.x_day02_b;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a86130.x_day02_b.bean.FuliBean;
import com.example.a86130.x_day02_b.presenter.NetPresenter;
import com.example.a86130.x_day02_b.view.NetView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetView {

    private RecyclerView mRecycler;
    private ArrayList<FuliBean.ResultsBean> fuliBeans;
    private MyAdapter myAdapter;
    private NetPresenter netPresenter;
    private ViewPager mViewPager;
    private String TAG = "MainActivity";
    private MyVpAdapter myVpAdapter;
    private ArrayList<View> views;
    private int mPosition;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        netPresenter = new NetPresenter(this);
        //p层调用加载数据的方法
        netPresenter.loadData();
        initView();
    }

    //初始化recycler
    private void initView() {
        //找控件
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTv = (TextView) findViewById(R.id.tv);
        //管理器
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //数据源
        fuliBeans = new ArrayList<>();
        //创建适配器
        myAdapter = new MyAdapter(fuliBeans, this);
        //设置适配器
        mRecycler.setAdapter(myAdapter);
        //接口回调
        myAdapter.setOnclick(new MyAdapter.Onclick() {
            @Override
            public void onclick(int position) {
                mPosition = position;
                //当点击条目时recyclerview不可见   viewpager和textview可见
                mRecycler.setVisibility(View.GONE);
                mViewPager.setVisibility(View.VISIBLE);
                mTv.setVisibility(View.VISIBLE);
                initvp(position);
                //移动到点击的条目对应的图片
                mViewPager.setCurrentItem(mPosition);
            }
        });
    }

    private void initvp(int position) {
        views = new ArrayList<>();
        //因为要获取条目的小标 所以要遍历集合的长度
        for (int i = 0; i <fuliBeans.size() ; i++) {
            //加载布局
            View view= LayoutInflater.from(this).inflate(R.layout.vp,null);
            //给控件赋值
            ImageView iv = view.findViewById(R.id.iv);
            Glide.with(this).load(fuliBeans.get(i).getUrl()).into(iv);
            //将遍历出来的小标添加到viewpager的集合中
            views.add(view);
        }
        myVpAdapter = new MyVpAdapter(fuliBeans,views);
        mViewPager.setAdapter(myVpAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTv.setText("第"+(position+1)+"/"+"共"+views.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void addDatas(ArrayList<FuliBean.ResultsBean> fb) {
        fuliBeans.addAll(fb);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: " + "VVVVVVVVVVVV");
    }

    @Override
    public void onBackPressed() {
       //如果recyclerview页面不可见  GONE--不可见
        if (mRecycler.getVisibility() == View.GONE) {
            //那么返回时如果recyclerview页面可见   VISIBLE--可见
            mRecycler.setVisibility(View.VISIBLE);
            //返回时viewpager和textview不可见
            mViewPager.setVisibility(View.GONE);
            mTv.setVisibility(View.GONE);
            //否则
        } else {
            //就关闭
            super.onBackPressed();
        }
    }
}
