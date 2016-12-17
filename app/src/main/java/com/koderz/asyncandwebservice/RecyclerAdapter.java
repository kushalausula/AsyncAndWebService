package com.koderz.asyncandwebservice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Pupa on 12/16/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context mContext ;
    ArrayList<SingleArticle> singleArticles;

    public RecyclerAdapter(Context mContext, ArrayList<SingleArticle> singleArticles) {
        this.singleArticles=singleArticles;
        this.mContext=mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvData.setText(singleArticles.get(position).title);
        Picasso.with(mContext).load("https://i.ytimg.com/vi/"+singleArticles.get(position).link+"/0.jpg").into(holder.imgView);


    }


    @Override
    public int getItemCount() {
        return singleArticles.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvData;
        ImageView imgView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvData= (TextView) itemView.findViewById(R.id.tvData);
            imgView= (ImageView) itemView.findViewById(R.id.imgView);


        }
    }
}
