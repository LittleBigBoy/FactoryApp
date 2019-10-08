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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.FrozenMoneyAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.MonthBillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.FrozenMoney;
import com.zhenhaikj.factoryside.mvp.bean.MonthBill;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.WalletContract;
import com.zhenhaikj.factoryside.mvp.model.WalletModel;
import com.zhenhaikj.factoryside.mvp.presenter.WalletPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
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
    private List<MonthBill.DataBean> MonthBillList = new ArrayList<>();
    private List<FrozenMoney.DataBean> FrozenMoneyList = new ArrayList<>();
    private MonthBillAdapter monthBillAdapter;
    private FrozenMoneyAdapter frozenMoneyAdapter;
    private boolean flag;
    private SPUtils spUtils;

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
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        flag = spUtils.getBoolean("flag", false);
        mHideIv.setSelected(flag);
        mPresenter.GetUserInfoList(userId, "1");

        mPresenter.AccountBill(userId, "1","1","999");//充值
//        mPresenter.AccountBill(userId, "3");//提现
//        mPresenter.AccountBill(userId, "2,5");//收入和支出
        mPresenter.MonthBill(userId,"1,2");
        mPresenter.GetFrozenMoney(userId);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的钱包");
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.GetUserInfoList(userId, "1");

                mRefreshLayout.finishRefresh(1000);
            }
        });
        for (int i = 0; i < 10; i++) {

//            rechargeRecordList.add(new Address());
        }


        mBillRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mRechargerecordRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mBillRv.setAdapter(billAdapter);
        mRechargerecordRv.setAdapter(rechargeRecordAdapter);

        monthBillAdapter = new MonthBillAdapter(R.layout.bill_item,MonthBillList);
        mBillRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mBillRv.setAdapter(monthBillAdapter);

        frozenMoneyAdapter = new FrozenMoneyAdapter(R.layout.item_frozen_amount,FrozenMoneyList);
        mFrozenAmountRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mFrozenAmountRv.setAdapter(frozenMoneyAdapter);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mPayTheDeposiTv.setOnClickListener(this);
        mLlInvoice.setOnClickListener(this);
        mRechargeTv.setOnClickListener(this);
        mLlRechargeRecord.setOnClickListener(this);
        mLlMonthlyBill.setOnClickListener(this);
        mLlFrozenAmount.setOnClickListener(this);
        mHideIv.setOnClickListener(this);
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
            case R.id.ll_recharge_record:
                Intent intent = new Intent(this, DetailRecordActivity.class);
                intent.putExtra("openwhich", "1");
                startActivity(intent);
                break;
            case R.id.ll_monthly_bill:
                Intent intent1 = new Intent(this, DetailRecordActivity.class);
                intent1.putExtra("openwhich", "2");
                startActivity(intent1);
                break;
            case R.id.ll_frozen_amount:
                Intent intent2 = new Intent(this, DetailRecordActivity.class);
                intent2.putExtra("openwhich", "3");
                startActivity(intent2);
                break;
            case R.id.hide_iv:
                if (!flag){
                    flag=true;
                    mAvailableTv.setText("****");//钱包余额
//                    mTvWatermelonBalance.setText("****");//西瓜币
                }else{
                    flag=false;
                    String format = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                    mAvailableTv.setText( format+ "");//钱包余额
//                    mTvWatermelonBalance.setText("¥" + userInfo.getCon() + "");//西瓜币
                }
                mHideIv.setSelected(flag);
                spUtils.put("flag",flag);
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
                String TotalMoney = String.format("%.2f", userInfo.getTotalMoney());
                mMoneyTv.setText(TotalMoney);
                String format = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                mAvailableTv.setText(format);
                String FrozenMoney = String.format("%.2f", userInfo.getFrozenMoney());
                mFreezeTv.setText(FrozenMoney);
                String DepositMoney = String.format("%.2f", userInfo.getDepositMoney());
                mTvMargin.setText(DepositMoney);
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
                                break;
                            case "3"://提现
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

    @Override
    public void MonthBill(BaseResult<Data<MonthBill>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getItem2().getData()!=null){
                    MonthBillList.addAll(baseResult.getData().getItem2().getData());
                    if (MonthBillList.size()<=4){
                        monthBillAdapter.setNewData(MonthBillList);
                    }else {
                        List<MonthBill.DataBean> List = new ArrayList<>();
                        for (int i=0;i<5;i++){
                            List.add(MonthBillList.get(i));
                        }
                        monthBillAdapter.setNewData(List);
                    }

                }

                break;
        }
    }

    @Override
    public void GetFrozenMoney(BaseResult<Data<FrozenMoney>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getItem2().getData()!=null){
                    FrozenMoneyList.addAll(baseResult.getData().getItem2().getData());
                    if (FrozenMoneyList.size()<=4){
                        frozenMoneyAdapter.setNewData(FrozenMoneyList);
                    }else {
                        List<FrozenMoney.DataBean> List = new ArrayList<>();
                        for (int i = 0; i <5 ; i++) {
                            List.add(FrozenMoneyList.get(i));
                        }
                        frozenMoneyAdapter.setNewData(List);
                    }
                }

                break;
        }
    }
}