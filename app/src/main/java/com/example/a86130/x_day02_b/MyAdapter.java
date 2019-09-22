package com.example.a86130.x_day02_b;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a86130.x_day02_b.bean.FuliBean;

import java.util.ArrayList;

/**
 * Created by 86130 on 2019/9/20.
 */

class MyAdapter extends RecyclerView.Adapter{
    private ArrayList<FuliBean.ResultsBean> fuliBeans;
    private Context context;

    public MyAdapter(ArrayList<FuliBean.ResultsBean> fuliBeans, Context context) {
        this.fuliBeans = fuliBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Glide.with(context).load(fuliBeans.get(position).getUrl()).into(((MyViewHolder)holder).iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fuliBeans.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        public MyViewHolder(View view) {
            super(view);
              iv = view.findViewById(R.id.iv);
        }
    }
    interface Onclick{
        void onclick(int position);
    }
    private Onclick onclick;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

}
