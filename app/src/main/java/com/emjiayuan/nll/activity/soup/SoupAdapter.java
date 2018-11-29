package com.emjiayuan.nll.activity.soup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Soup;
import com.emjiayuan.nll.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class SoupAdapter extends BaseAdapter {
    private Context mContext;

    private List<Soup> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;

    public SoupAdapter(Context mContext, List<Soup> grouplists) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
//        return 6;
        return grouplists.size();
    }

    @Override
    public Soup getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.soup_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.check = (ImageView) convertView.findViewById(R.id.check);
            holder.soup_ll = (LinearLayout) convertView.findViewById(R.id.soup_ll);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        Soup item = grouplists.get(position);
        GlideUtil.loadImageView(mContext,item.getImages(),holder.icon);
        holder.name.setText(item.getName());
        if (item.isCheck()){
            holder.check.setVisibility(View.VISIBLE);
            holder.soup_ll.setBackgroundResource(R.drawable.soup_shape);
        }else{
            holder.check.setVisibility(View.INVISIBLE);
            holder.soup_ll.setBackgroundResource(R.drawable.edit_shape);
        }
        return convertView;
    }

    public class ViewHolder {
        public ImageView icon;
        public ImageView check;
        public TextView name;
        public LinearLayout soup_ll;
    }
}
