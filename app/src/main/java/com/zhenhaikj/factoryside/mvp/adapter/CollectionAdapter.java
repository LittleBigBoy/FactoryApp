package com.zhenhaikj.factoryside.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.event.ColUpdateEvent;
import com.zhenhaikj.factoryside.mvp.interfaces.AllCheckListener;
import com.zhenhaikj.factoryside.mvp.bean.Product;
import com.zhenhaikj.factoryside.mvp.widget.MyImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class CollectionAdapter extends BaseAdapter {
    private Context mContext;
    private Map<Integer, Boolean> map = new HashMap();
    private AllCheckListener allCheckListener;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    private boolean flag = false;


    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Boolean> map) {
        this.map = map;
        notifyDataSetChanged();
    }


    private List<Product> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;

    public CollectionAdapter(Context mContext, List<Product> grouplists, AllCheckListener listener) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.allCheckListener = listener;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
//        return 20;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {// 如果是第一次显示该页面(要记得保存到viewholder中供下次直接从缓存中调用)
            convertView = mInflater.inflate(R.layout.collection_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        final Product item = grouplists.get(position);
        Glide.with(mContext).load(item.getImages()).into(holder.mIcon);
        holder.mName.setText(item.getName());
        holder.mPrice.setText("¥" + item.getPrice());
        if (flag) {
            holder.mCheck.setVisibility(View.VISIBLE);
        } else {
            holder.mCheck.setVisibility(View.GONE);
        }

        holder.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    map.put(position, true);
                } else {
                    map.remove(position);
                }
                item.setChecked(b);
                //监听每个item，若所有checkbox都为选中状态则更改main的全选checkbox状态
                EventBus.getDefault().post(new ColUpdateEvent(grouplists));
                for (Product carBean : grouplists) {
                    if (!carBean.isChecked()) {
                        allCheckListener.onCheckedChanged(false);
                        return;
                    }
                }
                allCheckListener.onCheckedChanged(true);

            }
        });
        if (map != null && map.containsKey(position)) {
            holder.mCheck.setChecked(true);
        } else {
            holder.mCheck.setChecked(false);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.icon)
        MyImageView mIcon;
        @BindView(R.id.check)
        CheckBox mCheck;
        @BindView(R.id.fl_item)
        FrameLayout mFlItem;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.price)
        TextView mPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
