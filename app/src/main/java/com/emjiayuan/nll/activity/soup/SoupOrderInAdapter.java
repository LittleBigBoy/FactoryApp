package com.emjiayuan.nll.activity.soup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.SoupOrder;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class SoupOrderInAdapter extends BaseAdapter {
    private Context mContext;
    private SoupOrder soupOrder;
    private LayoutInflater mInflater;
    public SoupOrderInAdapter(Context mContext, SoupOrder soupOrder) {
        super();
        this.mContext = mContext;
        this.soupOrder = soupOrder;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public SoupOrder getItem(int position) {
        return soupOrder;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        final  ViewHolder holder= new ViewHolder();
        ViewHolder holder= null;
        if (convertView == null) {// 如果是第一次显示该页面(要记得保存到holder中供下次直接从缓存中调用)
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.order_item_in, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.num = (TextView) convertView.findViewById(R.id.num);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用holder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        SoupOrder.ProductListBean item = soupOrder.getProduct_list().get(position);
        Glide.with(mContext).load(item.getImages()).into(holder.icon);
        holder.name.setText(soupOrder.getOrder_name());
        holder.price.setText("¥"+soupOrder.getTotalmoney());
        holder.num.setText("类型："+soupOrder.getLeixing());

        return convertView;
    }
    public class ViewHolder {
        public ImageView icon;
        public TextView name;
        public TextView price;
        public TextView num;
    }
}
