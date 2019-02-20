package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.FaceValueAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.bean.FaceValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
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
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_alipay)
    LinearLayout mLlAlipay;
    @BindView(R.id.ll_wxpay)
    LinearLayout mLlWxpay;
    @BindView(R.id.et_any_amount)
    EditText mEtAnyAmount;
    private List<FaceValue> faceValueList = new ArrayList<>();
    private FaceValueAdapter faceValueAdapter;
    private String[] faceValues = new String[]{"100", "300", "500", "1000", "2000", "3000"};
    private String value;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_recharge;
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
        mIvAplipay.setSelected(true);//默认选中支付宝
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("充值");
        for (int i = 0; i < faceValues.length; i++) {
            faceValueList.add(new FaceValue(faceValues[i],false));
        }
        faceValueAdapter = new FaceValueAdapter(R.layout.face_value_item, faceValueList);
        mRlRechargeAmount.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRlRechargeAmount.setAdapter(faceValueAdapter);
        faceValueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mEtAnyAmount.clearFocus();
                mEtAnyAmount.setText("");
                for (int i = 0; i <faceValueList.size() ; i++) {
                    if (i==position){
                        faceValueList.get(i).setSelect(true);
                    }else{
                        faceValueList.get(i).setSelect(false);
                    }
                }
                faceValueAdapter.notifyDataSetChanged();
                value = faceValueList.get(position).getValue();
                mTvActualArrival.setText(value);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlAlipay.setOnClickListener(this);
        mLlWxpay.setOnClickListener(this);
        mBtRecharge.setOnClickListener(this);
        mTvRechargeAgreement.setOnClickListener(this);
        mEtAnyAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    for (int i = 0; i <faceValueList.size() ; i++) {
                        faceValueList.get(i).setSelect(false);
                    }
                    faceValueAdapter.notifyDataSetChanged();
                    value="0";
                    mTvActualArrival.setText(value);
                }
            }
        });
        mEtAnyAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int len = s.toString().length();
                if (len > 1 && text.startsWith("0")) {
                    value=s.replace(0,1,"").toString();
                }else{
                    value=s.toString();
                }
                mTvActualArrival.setText(value);
            }
        });
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
            case R.id.ll_alipay:
                mIvAplipay.setSelected(true);
                mIvWechat.setSelected(false);
                break;
            case R.id.ll_wxpay:
                mIvAplipay.setSelected(false);
                mIvWechat.setSelected(true);
                break;
            case R.id.tv_recharge_agreement:

                break;
            case R.id.bt_recharge:
                ToastUtils.showShort("充值"+value+"元！");
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