package com.emjiayuan.nll.activity.soup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Soup;
import com.emjiayuan.nll.model.SoupOrder;
import com.emjiayuan.nll.utils.MyUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class MySoupOrderAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<SoupOrder> grouplists = new ArrayList<>();
    private ArrayList<Soup> list = new ArrayList<>();

    public ArrayList<SoupOrder> getGrouplists() {
        return grouplists;
    }

    public void setGrouplists(ArrayList<SoupOrder> grouplists) {
        this.grouplists = grouplists;
    }

    private LayoutInflater mInflater;

    public MySoupOrderAdapter(Context mContext, ArrayList<SoupOrder> grouplists) {
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
    public SoupOrder getItem(int position) {
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
            holder = new ViewHolder();
//        final ViewHolder holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.mysouporder_item, null);
            holder.order_num = convertView.findViewById(R.id.order_num);
            holder.order_name = convertView.findViewById(R.id.order_name);
            holder.time = convertView.findViewById(R.id.time);
            holder.again = convertView.findViewById(R.id.again);

            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        final SoupOrder item = grouplists.get(position);
        holder.order_num.setText("订单号："+item.getOrder_no());
        holder.order_name.setText(item.getOrder_name());
        holder.time.setText("下单时间："+item.getCreatedate());
        final List<SoupOrder.ProductListBean> listBeans=item.getProduct_list();
        holder.again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()){
                    return;
                }
                list.clear();
                for (int i = 0; i < listBeans.size(); i++) {
                    SoupOrder.ProductListBean bean=listBeans.get(i);
                    Soup soup=new Soup();
                    soup.setArea(bean.getArea());
                    soup.setClassX(bean.getClassX());
                    soup.setDelflag(bean.getDelflag());
                    soup.setId(bean.getSoup_product_id());
                    soup.setImages(bean.getImages());
                    soup.setName(bean.getName());
                    soup.setNum(bean.getWeight_jin());
                    soup.setPrice(bean.getPrice());
                    soup.setStatus(bean.getStatus());
                    soup.setTop(bean.getTop());
                    list.add(soup);
                }
                Intent intent=new Intent(mContext,SoupOrderConfirmActivity.class);
                intent.putExtra("oklist", (Serializable) list);
                intent.putExtra("val", "500");
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


    public class ViewHolder {
        public TextView order_num;
        public TextView order_name;
        public TextView time;
        public TextView again;
    }
}
