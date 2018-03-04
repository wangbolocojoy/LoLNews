package com.blog.news.adapter.tabhomeitem;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blog.news.R;
import com.blog.news.adapter.tabhomeitem.home.Home_Fragment_Item2_Adapter;
import com.blog.news.http.result.home.Home_Json_Bean;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author MacBookPor
 * @date 2018/1/5
 */
public class Bean2ViewBinder extends ItemViewBinder<Home_Json_Bean.DatasBean, Bean2ViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_beannav, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Home_Json_Bean.DatasBean bean2) {
        holder.setposts(bean2.getNav());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private GridLayoutManager gridLayoutManager;
        private RecyclerView recyclerView;
        private Home_Fragment_Item2_Adapter adapter;

        ViewHolder(View itemView) {
            super(itemView);
            adapter = new Home_Fragment_Item2_Adapter(null);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.home_dahang_item_naitem);
            gridLayoutManager = new GridLayoutManager(recyclerView.getRootView().getContext(), 5);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapter);

        }
        private void setposts(List<Home_Json_Bean.DatasBean.NavBean>list){
            adapter.setNewData(list);
            adapter.notifyDataSetChanged();

        }
    }
}
