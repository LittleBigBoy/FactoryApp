package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.WalletContract;
import com.zhenhaikj.factoryside.mvp.model.WalletModel;
import com.zhenhaikj.factoryside.mvp.presenter.WalletPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WalletActivity extends BaseActivity<WalletPresenter, WalletModel> implements View.OnClickListener, WalletContract.View {


    @BindView(R.id.view)
    View mView;
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
    @BindView(R.id.money_tv)
    TextView mMoneyTv;
    @BindView(R.id.hide_iv)
    ImageView mHideIv;
    @BindView(R.id.ll_margin)
    LinearLayout mLlMargin;
    @BindView(R.id.available_tv)
    TextView mAvailableTv;
    @BindView(R.id.freeze_tv)
    TextView mFreezeTv;
    @BindView(R.id.gift_tv)
    TextView mGiftTv;
    @BindView(R.id.recharge_tv)
    TextView mRechargeTv;
    @BindView(R.id.pay_the_deposi_tv)
    TextView mPayTheDeposiTv;
    @BindView(R.id.ll_invoice)
    LinearLayout mLlInvoice;
    @BindView(R.id.ll_recharge_record)
    LinearLayout mLlRechargeRecord;
    @BindView(R.id.rechargerecord_rv)
    RecyclerView mRechargerecordRv;
    @BindView(R.id.ll_monthly_bill)
    LinearLayout mLlMonthlyBill;
    @BindView(R.id.bill_rv)
    RecyclerView mBillRv;
    @BindView(R.id.ll_frozen_amount)
    LinearLayout mLlFrozenAmount;
    @BindView(R.id.frozen_amount_rv)
    RecyclerView mFrozenAmountRv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_margin)
    TextView mTvMargin;
    private List<Bill.DataBean> billList = new ArrayList<>();
    private List<Bill.DataBean> rechargeRecordList = new ArrayList<>();
    private BillAdapter billAdapter;
    private RechargeRecordAdapter rechargeRecordAdapter;
    private String userId;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();

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
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");

        mPresenter.AccountBill(userId, "1");//充值
        mPresenter.AccountBill(userId, "3");//提现
        mPresenter.AccountBill(userId, "2,5");//收入和支出
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的钱包");
        mRefreshLayout.setEnableLoadMore(false);
        for (int i = 0; i < 10; i++) {

//            rechargeRecordList.add(new Address());
        }


        mBillRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mRechargerecordRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mBillRv.setAdapter(billAdapter);
        mRechargerecordRv.setAdapter(rechargeRecordAdapter);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mPayTheDeposiTv.setOnClickListener(this);
        mLlInvoice.setOnClickListener(this);
        mRechargeTv.setOnClickListener(this);
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
            case R.id.pay_the_deposi_tv:
                startActivity(new Intent(mActivity, MarginActivity.class));
                break;
            case R.id.ll_invoice:
                startActivity(new Intent(mActivity, InvoiceActivity.class));
                break;
            case R.id.recharge_tv:
                startActivity(new Intent(mActivity, RechargeActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                mMoneyTv.setText(userInfo.getTotalMoney().toString());
                String format = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                mAvailableTv.setText(format);
                mFreezeTv.setText(userInfo.getFrozenMoney().toString());
                mTvMargin.setText("保证金："+userInfo.getRemainMoney()+"元");
                break;
        }
    }

    @Override
    public void AccountBill(BaseResult<Data<Bill>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    if (baseResult.getData().getItem2().getData() != null) {
                        switch (baseResult.getData().getItem2().getData().get(0).getState()) {
                            case "1"://充值
                                rechargeRecordList.addAll(baseResult.getData().getItem2().getData());
                                mRechargerecordRv.setLayoutManager(new LinearLayoutManager(mActivity));
                                mRechargerecordRv.setHasFixedSize(true);
                                mRechargerecordRv.setNestedScrollingEnabled(false);

                                if (rechargeRecordList.size() <= 4) {
//
                                    rechargeRecordAdapter = new RechargeRecordAdapter(R.layout.rechargerecord_item, rechargeRecordList);
                                } else {
                                    List<Bill.DataBean> list = new ArrayList<>();//充值记录

                                    for (int i = 0; i < 4; i++) {
                                        list.add(rechargeRecordList.get(i));
                                    }
//                                    billAdapter = new BillAdapter(R.layout.bill_item, list);
                                    rechargeRecordAdapter = new RechargeRecordAdapter(R.layout.rechargerecord_item, rechargeRecordList);

                                }

                                mRechargerecordRv.setAdapter(rechargeRecordAdapter);

                                break;
                            case "2"://支出
                            case "5"://收入
                                billList.addAll(baseResult.getData().getItem2().getData());
                                mBillRv.setLayoutManager(new LinearLayoutManager(mActivity));
                                mBillRv.setHasFixedSize(true);
                                mBillRv.setNestedScrollingEnabled(false);


                                if (billList.size() <= 4) {
                                    billAdapter = new BillAdapter(R.layout.bill_item, billList);
                                } else {
                                    List<Bill.DataBean> list = new ArrayList<>();//提现记录

                                    for (int i = 0; i < 4; i++) {
                                        list.add(billList.get(i));
                                    }
                                    billAdapter = new BillAdapter(R.layout.bill_item, billList);

                                }

                                mBillRv.setAdapter(billAdapter);


                                break;
                            case "3"://提现
//                                withdraw_list.addAll(baseResult.getData().getItem2().getData());
//                                mRvWithdrawalsRecord.setLayoutManager(new LinearLayoutManager(mActivity));
//                                mRvWithdrawalsRecord.setHasFixedSize(true);
//                                mRvWithdrawalsRecord.setNestedScrollingEnabled(false);
//
//                                if (withdraw_list.size()<=4){
//                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, withdraw_list);
//                                }else {
//                                    List<Bill.DataBean> list = new ArrayList<>();//提现记录
//
//                                    for (int i=0;i<4;i++){
//                                        list.add(withdraw_list.get(i));
//                                    }
//                                    wallet_record_adapter = new Wallet_record_Adapter(R.layout.item_withdrawals_record, list);
//                                }
//                                mRvWithdrawalsRecord.setAdapter(wallet_record_adapter);
                                break;
                            case "4"://待支付
                                break;
                            default:
                                break;
                        }
                    }
                }
                break;
        }
    }
}