package com.shop.newshop_application.adapter.tabhomeitem;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shop.newshop_application.R;
import com.shop.newshop_application.adapter.tabhomeitem.home.Home_Fragment_Item7_Adapter;
import com.shop.newshop_application.application.MyApplication;
import com.shop.newshop_application.http.result.home.Home_Json_Bean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by MacBookPor on 2018/1/8.
 */
public class Bean7ViewBinder extends ItemViewBinder<Home_Json_Bean.DatasBean.bean6.GoodsBean3, Bean7ViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_beanxxx, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Home_Json_Bean.DatasBean.bean6.GoodsBean3 bean6) {
        holder.textView.setText(bean6.getTitle()+"");
        holder.adapter.setNewData(bean6.getItem());
        holder.adapter.notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        private TextView textView;
        private Home_Fragment_Item7_Adapter adapter;

        ViewHolder(View itemView) {

            super(itemView);
            adapter=new Home_Fragment_Item7_Adapter(null);
            GridLayoutManager manager = new GridLayoutManager(MyApplication.getContext(), 2);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_beanxxx_recycleviewv);
            textView= (TextView) itemView.findViewById(R.id.item_beanxxx_title_name);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    }
}
