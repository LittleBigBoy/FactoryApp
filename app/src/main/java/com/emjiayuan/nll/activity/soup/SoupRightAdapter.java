package com.emjiayuan.nll.activity.soup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Soup;
import com.emjiayuan.nll.model.SoupCategory;
import com.emjiayuan.nll.widget.MyGridView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class SoupRightAdapter extends BaseAdapter {
    private Context mContext;

    private List<SoupCategory> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;

    public SoupRightAdapter(Context mContext, List<SoupCategory> grouplists) {
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
            convertView = mInflater.inflate(R.layout.soup_right_item, null);
            holder.gv_content = convertView.findViewById(R.id.gv_content);
            holder.content = convertView.findViewById(R.id.txt_content);

            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }
        final List<Soup> list=grouplists.get(position).getProducts();
        final SoupAdapter adapter=new SoupAdapter(mContext,list);
        holder.content.setText(grouplists.get(position).getName());
        holder.gv_content.setAdapter(adapter);
        holder.gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (list.get(i).isCheck()) {
                    list.get(i).setCheck(false);
                } else {
                    list.get(i).setCheck(true);
                }
                EventBus.getDefault().post(list.get(i));
                adapter.notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public class ViewHolder {
        public MyGridView gv_content;
        public TextView content;
    }
}
