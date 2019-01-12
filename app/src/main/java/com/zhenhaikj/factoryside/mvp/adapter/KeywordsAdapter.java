package com.zhenhaikj.factoryside.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class KeywordsAdapter extends BaseAdapter {
    private Context mContext;

    private List<String> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;

    public KeywordsAdapter(Context mContext, List<String> grouplists) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return grouplists.size() > 8 ? 8 : grouplists.size();
    }

    @Override
    public String getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.keyword_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        String item = grouplists.get(position);
        holder.keyword.setText(item);

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.keyword)
        TextView keyword;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
