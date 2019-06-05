package com.zhenhaikj.factoryside.mvp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.CanInvoice;

import java.util.List;
import java.util.logging.SimpleFormatter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

//public class UninvoicedAdapter extends BaseQuickAdapter<CanInvoice, BaseViewHolder> {
//    public UninvoicedAdapter(int layoutResId, @Nullable List<CanInvoice> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(BaseViewHolder helper, CanInvoice item) {
//        StringBuilder stringBuilder = new StringBuilder(item.getCreateTime());
//        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
//        helper.setText(R.id.time_tv,time)
//                .setText(R.id.money_tv,item.getPayMoney());
//
//    }
//}
/*购物车二级列表adapter*/
public class UninvoicedAdapter extends RecyclerView.Adapter<UninvoicedAdapter.MyHolder> {
    private List<CanInvoice> list;
    private Context mContext;

    public UninvoicedAdapter(List<CanInvoice> list,Context context) {
        this.list = list;
        this.mContext=context;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_choose;
        private TextView time;
        private TextView money;


        public CheckBox getCheckBox() {
            return cb_choose;
        }




        public MyHolder(View itemView) {
            super(itemView);
            cb_choose = (CheckBox) itemView.findViewById(R.id.checkbox);
            time = itemView.findViewById(R.id.time_tv);
            money = itemView.findViewById(R.id.money_tv);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_type, parent,false);//解决显示不全
        MyHolder holder = new MyHolder(view);
        return holder;*/
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_uninvoiced,parent,false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        StringBuilder stringBuilder = new StringBuilder(list.get(position).getCreateTime());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        holder.time.setText(time);
        holder.money.setText(list.get(position).getPayMoney());

        holder.getCheckBox().setChecked(list.get(position).isIscheck());

        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将商品的checkbox的点击变化事件进行回调给第一个Recyclerview
                if (mCallBack != null) {
                    mCallBack.OnCheckListener(isChecked, position);
                }
            }
        });
        holder.itemView.setId(position);



    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    public interface allCheck {
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        public void OnCheckListener(boolean isChecked, int childpostion);

        public void OnAddReduceListner(int value,int childposition);

        public void OnItemClickDetailListner(View view,int childposition);
    }


}
