package com.emjiayuan.factoryside.mvp.activity.soup;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.event.SoupUpdateEvent;
import com.emjiayuan.nll.model.Soup;
import com.emjiayuan.nll.utils.GlideUtil;
import com.emjiayuan.nll.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class SoupCheckAdapter extends BaseAdapter {
    private Context mContext;
    private static String TAG = "MenuAdapter";

    private List<Soup> grouplists = new ArrayList<>();
    private Map<Integer,String> map=new HashMap<>();
    private LayoutInflater mInflater;
    private int index=-1;
    private ViewHolder holder;

    public List<Soup> getGrouplists() {
        return grouplists;
    }

    public void setGrouplists(List<Soup> grouplists) {
        this.grouplists = grouplists;
        notifyDataSetChanged();
    }

    public SoupCheckAdapter(Context mContext, List<Soup> grouplists) {
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
        if (convertView == null) {// 如果是第一次显示该页面(要记得保存到viewholder中供下次直接从缓存中调用)
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.soup_check_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.setting_tv = (TextView) convertView.findViewById(R.id.setting_tv);
            holder.et_count = convertView.findViewById(R.id.et_count);
            holder.et_count.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    //注意，此处必须使用getTag的方式，不能将position定义为final，写成mTouchItemPosition = position
                    index = (Integer) view.getTag();
                    return false;
                }
            });
            holder.setting_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = (Integer) view.getTag();
//                    map.put(index,"1");
//                    holder.et_count.setTag(index);
//                    holder.et_count.setText("1");
                    grouplists.get(index).setNum("1");
                    notifyDataSetChanged();
                }
            });
            // 让ViewHolder持有一个TextWathcer，动态更新position来防治数据错乱；不能将position定义成final直接使用，必须动态更新
            holder.mTextWatcher = new MyTextWatcher();
            holder.et_count.addTextChangedListener(holder.mTextWatcher);
            holder.updatePosition(position);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
            holder.updatePosition(position);
        }
        holder.setting_tv.setTag(position);
        holder.et_count.setTag(position);
        MyUtils.e("position",position+"");
        Soup item = grouplists.get(position);
        GlideUtil.loadImageView(mContext,item.getImages(),holder.icon);
        holder.name.setText(item.getName());

        if (!"0".equals(grouplists.get(position).getNum())){
//            index=position;
            holder.et_count.setText(grouplists.get(position).getNum());
            holder.et_count.setVisibility(View.VISIBLE);
            holder.setting_tv.setVisibility(View.GONE);
        }else{
            holder.et_count.setText("0");
            holder.et_count.setVisibility(View.GONE);
            holder.setting_tv.setVisibility(View.VISIBLE);
        }
        if (index == position) {
            holder.et_count.requestFocus();
            holder.et_count.setSelection(holder.et_count.getText().length());
        } else {
            holder.et_count.clearFocus();
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView icon;
        public TextView name;
        public TextView setting_tv;
        public EditText et_count;
        MyTextWatcher mTextWatcher;

        //动态更新TextWathcer的position
        public void updatePosition(int position) {
            mTextWatcher.updatePosition(position);
        }
    }
    class MyTextWatcher implements TextWatcher {
        //由于TextWatcher的afterTextChanged中拿不到对应的position值，所以自己创建一个子类
        private int mPosition;

        public void updatePosition(int position) {
            mPosition = position;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if ("".equals(s.toString())){
                return;
            }
//            map.put(mPosition,s.toString());
            grouplists.get(mPosition).setNum(s.toString());
            EventBus.getDefault().post(new SoupUpdateEvent(grouplists));
        }
    }
}
