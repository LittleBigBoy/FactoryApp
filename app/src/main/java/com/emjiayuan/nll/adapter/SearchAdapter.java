package com.emjiayuan.nll.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Product;
import com.emjiayuan.nll.utils.GlideUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class SearchAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<Product> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;

    public SearchAdapter(Context mContext, ArrayList<Product> grouplists) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return grouplists.size();
    }

    @Override
    public Product getItem(int position) {
        return grouplists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {// 如果是第一次显示该页面(要记得保存到viewholder中供下次直接从缓存中调用)
            convertView = mInflater.inflate(R.layout.search_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        Product item = grouplists.get(position);
        GlideUtil.loadImageView(mContext, item.getImages(), holder.mIcon);
        holder.mTxtName.setText(item.getName());
        holder.mTxtPrice.setText("¥" + item.getPrice());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.txt_name)
        TextView mTxtName;
        @BindView(R.id.txt_price)
        TextView mTxtPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
