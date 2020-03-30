package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.LogisticsAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Logistics;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.ExpressInfoContract;
import com.zhenhaikj.factoryside.mvp.model.ExpressInfoModel;
import com.zhenhaikj.factoryside.mvp.presenter.ExpressInfoPresenter;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ReturnFragment extends BaseLazyFragment<ExpressInfoPresenter, ExpressInfoModel> implements ExpressInfoContract.View, View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.courier_company_tv)
    TextView mCourierCompanyTv;
    @BindView(R.id.return_rv)
    RecyclerView mReturnRv;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.tv_no_logistics)
    TextView mTvNoLogistics;
    @BindView(R.id.sv_logistics)
    ScrollView mSvLogistics;
    @BindView(R.id.ll_empty)
    LinearLayout mLlEmpty;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    private WorkOrder.DataBean data;
    private List<Logistics.ExpressDetailListBean.DataBean> list = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private String orderId;
    private LogisticsAdapter adapter;
    private ZLoadingDialog dialog;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.white);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    public static ReturnFragment newInstance(String param1, String param2) {
        ReturnFragment fragment = new ReturnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_return;
    }

    @Override
    protected void initData() {
        orderId = mParam1;
        mPresenter.GetOrderInfo(orderId);
    }

    @Override
    protected void initView() {
        //loading
        dialog = new ZLoadingDialog(mActivity);
//        showLoading();
        adapter = new LogisticsAdapter(R.layout.logistics_recycle_item, list);
        mReturnRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mReturnRv.setAdapter(adapter);
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mIvCopy.setOnClickListener(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void GetExpressInfo(BaseResult<Data<Logistics>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getItem2().getExpressDetailList() != null) {
                    list.addAll(baseResult.getData().getItem2().getExpressDetailList().getData());
                    adapter.setNewData(list);
                    cancleLoading();
                }

                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                if ("".equals(data.getReturnAccessoryMsg()) || data.getReturnAccessoryMsg() == null) {
                    mTvNoLogistics.setVisibility(View.VISIBLE);
                    mLlEmpty.setVisibility(View.VISIBLE);
                    mSvLogistics.setVisibility(View.GONE);
                    return;

                } else {
                    mTvNoLogistics.setVisibility(View.GONE);
                    mLlEmpty.setVisibility(View.GONE);
                    mSvLogistics.setVisibility(View.VISIBLE);
                    mPresenter.GetExpressInfo(data.getReturnAccessoryMsg());
                    mTvNumber.setText(data.getReturnAccessoryMsg());
                }

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_copy:
                String id = mTvNumber.getText().toString();
                myClip = ClipData.newPlainText("", id);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
        }
    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading() {
        dialog.dismiss();
    }
}
