package com.zhenhaikj.factoryside.mvp.fragment;


import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.FrozenMoneyAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.MonthBillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class RecordFragment extends BaseLazyFragment<WalletPresenter, WalletModel> implements WalletContract.View {
    @BindView(R.id.rv_record)
    RecyclerView mRvRecord;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    private View view;
    private String title;
    private String userId;


    private RechargeRecordAdapter wallet_record_adapter;
    private List<Bill.DataBean> recharge_list = new ArrayList<>();//充值记录
    private MonthBillAdapter monthBillAdapter;
    private List<MonthBill.DataBean> MonthBillList = new ArrayList<>();
    private List<FrozenMoney.DataBean> frozenMoneyList = new ArrayList<>();
    private FrozenMoneyAdapter frozenMoneyAdapter;


    public static RecordFragment newInstance(String title) {
        RecordFragment fragment = new RecordFragment();
        fragment.title = title;
        return fragment;
    }

    @Override
    public void MonthBill(BaseResult<Data<MonthBill>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getItem2().getData() != null) {
                    MonthBillList.addAll(baseResult.getData().getItem2().getData());
                    monthBillAdapter = new MonthBillAdapter(R.layout.bill_item, MonthBillList);
                    mRvRecord.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvRecord.setAdapter(monthBillAdapter);
                }

                break;
        }
    }

    @Override
    public void GetFrozenMoney(BaseResult<Data<FrozenMoney>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getItem2().getData() != null) {
                    frozenMoneyList.addAll(baseResult.getData().getItem2().getData());
                    frozenMoneyAdapter = new FrozenMoneyAdapter(R.layout.item_frozen_amount, frozenMoneyList);
                    mRvRecord.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvRecord.setAdapter(frozenMoneyAdapter);
                }

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
                                recharge_list.clear();
                                recharge_list.addAll(baseResult.getData().getItem2().getData());
                                mRvRecord.setLayoutManager(new LinearLayoutManager(mActivity));
//                                mRvRecord.setHasFixedSize(true);
//                                mRvRecord.setNestedScrollingEnabled(false);
                                wallet_record_adapter = new RechargeRecordAdapter(R.layout.rechargerecord_item, recharge_list);
                                mRvRecord.setAdapter(wallet_record_adapter);
                                Double money=0.0;
                                for (int i = 0; i <recharge_list.size() ; i++) {
                                    money=recharge_list.get(i).getPayMoney()+money;
                                }
                                mTvMoney.setText("总金额:"+money+"元");
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
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");

        if (title.equals("每月账单")) {
            mPresenter.MonthBill(userId, "1,2");

        }
        if (title.equals("冻结金额")) {
            mPresenter.GetFrozenMoney(userId);
        }
        if (title.equals("充值")) {
            recharge_list.clear();
            mPresenter.AccountBill(userId, "1");//充值
            mTvMoney.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }
}
