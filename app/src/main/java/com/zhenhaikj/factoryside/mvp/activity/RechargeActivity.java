package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.FaceValueAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RechargeActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.rl_recharge_amount)
    RecyclerView mRlRechargeAmount;
    @BindView(R.id.tv_actual_arrival)
    TextView mTvActualArrival;
    @BindView(R.id.iv_aplipay)
    ImageView mIvAplipay;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.tv_recharge_agreement)
    TextView mTvRechargeAgreement;
    @BindView(R.id.bt_recharge)
    Button mBtRecharge;
    private List<Address> faceValueList = new ArrayList<>();
    private FaceValueAdapter faceValueAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("充值");
        for (int i = 0; i < 6; i++) {
            faceValueList.add(new Address());
        }
        faceValueAdapter = new FaceValueAdapter(R.layout.face_value_item, faceValueList);
        mRlRechargeAmount.setLayoutManager(new GridLayoutManager(mActivity,3));
        mRlRechargeAmount.setAdapter(faceValueAdapter);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}