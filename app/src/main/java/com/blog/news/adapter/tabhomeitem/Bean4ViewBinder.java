package com.blog.news.adapter.tabhomeitem;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blog.news.R;
import com.blog.news.adapter.tabhomeitem.home.Home_Fragment_Item4_Adapter;
import com.blog.news.application.MyApplication;
import com.blog.news.http.result.home.Home_Json_Bean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author MacBookPor
 * @date 2018/1/5
 */
public class Bean4ViewBinder extends ItemViewBinder<Home_Json_Bean.DatasBean.bean3.GoodsBean, Bean4ViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_beanxxx, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Home_Json_Bean.DatasBean.bean3.GoodsBean bean3) {
        holder.adapter.setNewData(bean3.getItem());
        holder.adapter.notifyDataSetChanged();
        holder.textView.setText(bean3.getTitle());


    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private Home_Fragment_Item4_Adapter adapter;
        private TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_beanxxx_title_name);
            adapter = new Home_Fragment_Item4_Adapter(null);
            GridLayoutManager manager = new GridLayoutManager(MyApplication.getContext(), 2);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_beanxxx_recycleviewv);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    }
}
