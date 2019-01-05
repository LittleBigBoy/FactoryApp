package com.emjiayuan.factoryside.mvp.activity.soup;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.SoupCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class SoupCategoryAdapter extends BaseAdapter {
    private Context mContext;

    private List<SoupCategory> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;
    private int selectItem=0;

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }

    public SoupCategoryAdapter(Context mContext, List<SoupCategory> grouplists) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
//        return 3;
        return grouplists.size();
    }

    @Override
    public SoupCategory getItem(int position) {
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
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.classify_item, null);
            holder.content = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        SoupCategory item = grouplists.get(position);
        holder.content.setText(item.getName());
        if (selectItem==position) {
            holder.content.setTextColor(Color.parseColor("#00FF99"));
            holder.content.setBackgroundResource(R.drawable.layout_line_left);
        } else {
            holder.content.setTextColor(Color.parseColor("#000000"));
            holder.content.setBackgroundResource(R.drawable.layout_line);
        }
        return convertView;
    }

    public class ViewHolder {
        public TextView content;
    }
}
