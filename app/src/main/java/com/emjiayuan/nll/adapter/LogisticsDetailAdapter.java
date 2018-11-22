package com.emjiayuan.nll.adapter;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.model.LogisticsBean;
import com.emjiayuan.nll.utils.DensityUtil;
import com.emjiayuan.nll.viewholder.LayoutParamsViewHolder;

import java.util.List;

public class LogisticsDetailAdapter extends BaseQuickAdapter<LogisticsBean,LayoutParamsViewHolder> {
    public LogisticsDetailAdapter(int layoutResId, List<LogisticsBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(LayoutParamsViewHolder helper, LogisticsBean item) {
        helper.setText(R.id.tv_status,item.getContext());
        helper.setText(R.id.tv_date,item.getTime());
        helper.setVisible(R.id.tv_time,false);
        int position=helper.getAdapterPosition();
        if (position == 0) {
            //绿色的圆点
            helper.setImageResource(R.id.iv_status,R.drawable.green_dot);
            RelativeLayout.LayoutParams pointParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            pointParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,pointParams);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);
        } else if(position==getItemCount()-1){
            helper.setImageResource(R.id.iv_status,R.drawable.blue_dot);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 15), DensityUtil.dp2px(mContext, 15));
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_status,lp);
            //灰色的竖线
            RelativeLayout.LayoutParams lineParams = new RelativeLayout.LayoutParams(DensityUtil.dp2px(mContext, 1), ViewGroup.LayoutParams.MATCH_PARENT);
            lineParams.addRule(RelativeLayout.ABOVE, R.id.iv_status);//让直线置于圆点下面
            lineParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            helper.setLayoutParams(R.id.iv_line,lineParams);

        }else{
            helper.setImageResource(R.id.iv_status,R.drawable.blue_dot);
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
