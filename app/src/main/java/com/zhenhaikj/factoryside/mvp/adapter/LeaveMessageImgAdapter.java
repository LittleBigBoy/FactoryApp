package com.zhenhaikj.factoryside.mvp.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.utils.GlideUtil;

import java.util.List;

public class LeaveMessageImgAdapter extends BaseQuickAdapter<WorkOrder.LeavemessageimgListBean, BaseViewHolder> {
    public LeaveMessageImgAdapter(int layoutResId, @Nullable List<WorkOrder.LeavemessageimgListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.LeavemessageimgListBean item) {
        ImageView icon=helper.getView(R.id.iv_image);
        GlideUtil.loadImageViewLoding(mContext, Config.Leave_Message_URL +item.getUrl(),icon, R.drawable.image_loading,R.drawable.image_loading);
        helper.addOnClickListener(R.id.iv_image);
    }
}
