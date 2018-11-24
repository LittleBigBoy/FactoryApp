package com.emjiayuan.nll.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.Comment;
import com.emjiayuan.nll.utils.GlideUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class CommentAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<Comment> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;

    public CommentAdapter(Context mContext, ArrayList<Comment> grouplists) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
//        return 10;
        return grouplists.size();
    }

    @Override
    public Comment getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.pl_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        Comment item = grouplists.get(position);
        GlideUtil.loadImageViewLoding(mContext, item.getHeadimg(), holder.mIcon, R.drawable.default_tx, R.drawable.default_tx);
        holder.mName.setText(item.getNickname());
        holder.mTime.setText(item.getCreatetime());
        holder.mContent.setText(item.getComment());
        holder.mHf.setVisibility(null == item.getReply_comment() ? View.GONE : View.VISIBLE);
        holder.mHf.setText(Html.fromHtml("<font color='#333333'>客服回复：<font/><font color='#757575'>" + item.getReply_comment() + "<font/>"));
        return convertView;
    }

    static

    class ViewHolder {
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.time)
        TextView mTime;
        @BindView(R.id.content)
        TextView mContent;
        @BindView(R.id.hf)
        TextView mHf;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
