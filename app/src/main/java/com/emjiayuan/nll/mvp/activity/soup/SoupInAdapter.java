package com.emjiayuan.nll.mvp.activity.soup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Soup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class SoupInAdapter extends BaseAdapter {
    private Context mContext;

    private List<Soup> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;
    private String mass="斤";

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public SoupInAdapter(Context mContext, List<Soup> grouplists) {
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
            convertView = mInflater.inflate(R.layout.soup_in_item, null);
            holder.level = convertView.findViewById(R.id.level);
            holder.name = convertView.findViewById(R.id.name);
            holder.mass = convertView.findViewById(R.id.mass);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        Soup item = grouplists.get(position);
        holder.name.setText(item.getName());
        holder.level.setText(item.getClassX());
        holder.mass.setText(item.getNum()+""+mass);

        return convertView;
    }

    public class ViewHolder {
        public TextView mass;
        public TextView level;
        public TextView name;
    }
}
