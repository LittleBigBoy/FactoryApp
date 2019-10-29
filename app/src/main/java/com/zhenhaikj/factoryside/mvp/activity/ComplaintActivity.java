package com.zhenhaikj.factoryside.mvp.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_orderid)
    TextView mTvOrderid;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.spinner_object)
    Spinner mSpinnerObject;
    @BindView(R.id.spinner_type)
    Spinner mSpinnerType;
    @BindView(R.id.ll_type)
    LinearLayout mLlType;
    @BindView(R.id.et_information)
    EditText mEtInformation;
    @BindView(R.id.ll_information)
    LinearLayout mLlInformation;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    @BindView(R.id.et_fault_description)
    EditText mEtFaultDescription;
    @BindView(R.id.iv_microphone_one)
    ImageView mIvMicrophoneOne;
    @BindView(R.id.ll_microphone_one)
    LinearLayout mLlMicrophoneOne;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    private String orderId;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_complaint;
    }

    @Override
    protected void initData() {
        mSpinnerObject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos){
                    case 0:
                        mLlType.setVisibility(View.GONE);
                        mLlInformation.setVisibility(View.GONE);
                        break;
                    case 1:
                        mLlType.setVisibility(View.VISIBLE);
                        mLlInformation.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        mLlType.setVisibility(View.GONE);
                        mLlInformation.setVisibility(View.GONE);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    @Override
    protected void initView() {
        mTvTitle.setText("投诉");
        mTvTitle.setVisibility(View.VISIBLE);
        orderId = getIntent().getStringExtra("orderId");
        mTvOrderid.setText(orderId);
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIvCopy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.iv_copy:
                myClip = ClipData.newPlainText("", orderId);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
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
