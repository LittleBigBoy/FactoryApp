package com.emjiayuan.factoryside.mvp.activity.soup;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class GoodsInAdapter extends BaseAdapter {
    private Context mContext;

    private List<SoupOrder.ProductListBean> grouplists = new ArrayList<>();

    public List<SoupOrder.ProductListBean> getGrouplists() {
        return grouplists;
    }

    public void setGrouplists(ArrayList<SoupOrder.ProductListBean> grouplists) {
        this.grouplists = grouplists;
    }

    private LayoutInflater mInflater;

    public GoodsInAdapter(Context mContext, List<SoupOrder.ProductListBean> grouplists) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
//        return 4;
        return grouplists.size();
    }

    @Override
    public SoupOrder.ProductListBean getItem(int position) {
        return grouplists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.goods_item_in, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        SoupOrder.ProductListBean item = grouplists.get(position);
        Glide.with(mContext).load(item.getImages()).into(holder.icon);
        holder.name.setText(item.getName());
        holder.num.setText(item.getWeight_jin()+"斤");
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.num)
        TextView num;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
