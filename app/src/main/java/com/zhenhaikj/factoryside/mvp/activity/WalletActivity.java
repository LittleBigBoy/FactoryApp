package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WalletActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.money_tv)
    TextView mMoneyTv;
    @BindView(R.id.hide_iv)
    ImageView mHideIv;
    @BindView(R.id.available_tv)
    TextView mAvailableTv;
    @BindView(R.id.freeze_tv)
    TextView mFreezeTv;
    @BindView(R.id.gift_tv)
    TextView mGiftTv;
    @BindView(R.id.voucher_ll)
    LinearLayout mVoucherLl;
    @BindView(R.id.margin_ll)
    LinearLayout mMarginLl;
    @BindView(R.id.invoice_ll)
    LinearLayout mInvoiceLl;
    @BindView(R.id.bank_card_ll)
    LinearLayout mBankCardLl;
    @BindView(R.id.exchange_ll)
    LinearLayout mExchangeLl;
    @BindView(R.id.rechargerecord_rv)
    RecyclerView mRechargerecordRv;
    @BindView(R.id.bill_rv)
    RecyclerView mBillRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recharge_tv)
    TextView mRechargeTv;
    @BindView(R.id.pay_the_deposi_tv)
    TextView mPayTheDeposiTv;
    @BindView(R.id.view)
    View mView;
    private List<Address> billList = new ArrayList<>();
    private List<Address> rechargeRecordList = new ArrayList<>();
    private BillAdapter billAdapter;
    private RechargeRecordAdapter rechargeRecordAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wallet;
    }
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的钱包");
        mRefreshLayout.setEnableLoadMore(false);
        for (int i = 0; i < 10; i++) {
            billList.add(new Address());
            rechargeRecordList.add(new Address());
        }
        billAdapter = new BillAdapter(R.layout.bill_item, billList);
        rechargeRecordAdapter = new RechargeRecordAdapter(R.layout.rechargerecord_item, rechargeRecordList);
        mBillRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mRechargerecordRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mBillRv.setAdapter(billAdapter);
        mRechargerecordRv.setAdapter(rechargeRecordAdapter);
    }

    @Override
    protected void setListener() {
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_search:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}