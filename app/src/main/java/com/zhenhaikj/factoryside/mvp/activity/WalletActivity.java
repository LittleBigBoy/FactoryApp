package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WalletActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.Recharge_tv)
    TextView mRechargeTv;
    @BindView(R.id.PayTheDeposi_tv)
    TextView mPayTheDeposiTv;
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

    @Override
    protected int setLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

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

}