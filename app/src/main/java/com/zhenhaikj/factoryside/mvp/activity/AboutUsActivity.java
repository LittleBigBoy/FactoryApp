package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutUsActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.tv_version_number)
    TextView mTvVersionNumber;
    @BindView(R.id.tv_user_Agreement)
    TextView mTvUserAgreement;
    @BindView(R.id.tv_about_app)
    TextView mTvAboutApp;
    @BindView(R.id.tv_opinion)
    TextView mTvOpinion;
    @BindView(R.id.tv_net)
    TextView mTvNet;
    @BindView(R.id.tv_phone_number)
    TextView mTvPhoneNumber;
    @BindView(R.id.tv_company_english)
    TextView mTvCompanyEnglish;
    private List<Address> billList = new ArrayList<>();
    private List<Address> rechargeRecordList = new ArrayList<>();
    private BillAdapter billAdapter;
    private RechargeRecordAdapter rechargeRecordAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about_us;
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
        mTvTitle.setText("关于我们");

        PackageManager manager = mActivity.getPackageManager();
//        int code = 0;
//        try {
//            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
//            code = info.versionCode;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mTvVersionNumber.setText("V "+name);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvOpinion.setOnClickListener(this);
        mTvUserAgreement.setOnClickListener(this);
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
            case R.id.tv_opinion:
                startActivity(new Intent(mActivity,OpinionActivity.class));
                break;
            case R.id.tv_user_Agreement:
                Intent intent=new Intent(mActivity,WebActivity.class);
                intent.putExtra("Url","http://admin.xigyu.com/Agreement");
                intent.putExtra("title","用户协议");
                startActivity(intent);
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