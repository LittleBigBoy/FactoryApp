package com.zhenhaikj.factoryside.mvp.adapter;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.bean.Logistics;
import com.zhenhaikj.factoryside.mvp.utils.DensityUtil;
import com.zhenhaikj.factoryside.mvp.viewholder.LayoutParamsViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

public class LogisticsAdapter extends BaseQuickAdapter<Logistics, LayoutParamsViewHolder> {
    public LogisticsAdapter(int layoutResId, @Nullable List<Logistics> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(LayoutParamsViewHolder helper, Logistics item) {
        StringBuilder stringBuilder = new StringBuilder(item.getTime());
        String time = "" + stringBuilder.replace(10, 11, " "); //去掉T
        helper.setText(R.id.tv_status,item.getContent())
                .setText(R.id.tv_date,time);

        int position=helper.getAdapterPosition();
        if (position==0){
            helper.setImageResource(R.id.iv_status,R.drawable.red_bot);
            RelativeLayout.LayoutParams pointParams=new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext,15),DensityUtil.dp2px(mContext, 15));
            pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,pointParams);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);
        }else if(position==getItemCount()-1){
            helper.setImageResource(R.id.iv_status,R.drawable.gray_dot);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,lp);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.ABOVE, R.id.iv_status);//让直线置于圆点上面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);

        }else{
            helper.setImageResource(R.id.iv_status,R.drawable.gray_dot);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,lp);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);
        }

    }
}
