package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.PayTheDepositeAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.WithdrawalMarginAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.widget.MaginDialog;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MarginActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_withdrawal_margin)
    TextView mTvWithdrawalMargin;
    @BindView(R.id.tv_pay_the_deposit)
    TextView mTvPayTheDeposit;
    @BindView(R.id.ll_paying_a_deposit_record)
    LinearLayout mLlPayingADepositRecord;
    @BindView(R.id.rv_pay_the_deposit)
    RecyclerView mRvPayTheDeposit;
    @BindView(R.id.ll_withdrawal_of_margin_records)
    LinearLayout mLlWithdrawalOfMarginRecords;
    @BindView(R.id.rv_withdrawal_margin)
    RecyclerView mRvWithdrawalMargin;
    private List<Address> marginList = new ArrayList<>();
    private List<Address> payList = new ArrayList<>();
    private MaginDialog maginDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_margin;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            marginList.add(new Address());
            payList.add(new Address());
        }
        PayTheDepositeAdapter payTheDepositeAdapter=new PayTheDepositeAdapter(R.layout.item_pay_the_deposit,payList);
        mRvPayTheDeposit.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPayTheDeposit.setAdapter(payTheDepositeAdapter);

        WithdrawalMarginAdapter withdrawalMarginAdapter=new WithdrawalMarginAdapter(R.layout.item_withdrawal_margin,marginList);
        mRvWithdrawalMargin.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvWithdrawalMargin.setAdapter(withdrawalMarginAdapter);

    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("保证金");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvPayTheDeposit.setOnClickListener(this);
        mTvWithdrawalMargin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_withdrawal_margin:
                maginDialog = new MaginDialog(this);
                maginDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);

                maginDialog.setNoOnclickListener("取消", new MaginDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        maginDialog.dismiss();
                    }
                });
                maginDialog.show();
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
