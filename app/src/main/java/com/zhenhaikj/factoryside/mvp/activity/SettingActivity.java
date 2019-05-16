package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.bugly.beta.Beta;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.LoginContract;
import com.zhenhaikj.factoryside.mvp.model.LoginModel;
import com.zhenhaikj.factoryside.mvp.presenter.LoginPresenter;
import com.zhenhaikj.factoryside.mvp.utils.DataCleanManager;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;
import com.zhenhaikj.factoryside.mvp.widget.CustomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingActivity extends BaseActivity <LoginPresenter, LoginModel> implements View.OnClickListener, LoginContract.View {


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
    @BindView(R.id.ll_clean)
    LinearLayout mLlClean;
    @BindView(R.id.ll_update)
    LinearLayout mLlUpdate;
    @BindView(R.id.tv_cache)
    TextView mTvCache;
    @BindView(R.id.btn_sign_out_of_your_account)
    Button mBtnSignOutOfYourAccount;
    private List<Address> billList = new ArrayList<>();
    private List<Address> rechargeRecordList = new ArrayList<>();
    private BillAdapter billAdapter;
    private RechargeRecordAdapter rechargeRecordAdapter;
    private View shareView;
    private AlertDialog shareDialog;

    private SPUtils spUtils;
    private String userId;
    private CustomDialog serviceDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
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
        String resultCache = null;
        try {
            resultCache = DataCleanManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTvCache.setText(resultCache);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("设置");
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlClean.setOnClickListener(this);
        mBtnSignOutOfYourAccount.setOnClickListener(this);
        mLlUpdate.setOnClickListener(this);
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
            case R.id.ll_clean:
                String resultCache = null;
                try {
                    resultCache = DataCleanManager.getTotalCacheSize(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mTvCache.setText(resultCache);
                DataCleanManager.clearAllCache(this);
                break;
            case R.id.btn_sign_out_of_your_account:
//                spUtils.put("isLogin", false);
                startActivity(new Intent(mActivity, LoginActivity.class));
                ActivityUtils.finishAllActivities();
                mPresenter.LoginOut(userId);
                break;
            case R.id.ll_update:
//                showService();
//                shareView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_update, null);
//                shareDialog = new AlertDialog.Builder(mActivity).setView(shareView).create();
//                shareDialog.show();
//                Window window = shareDialog.getWindow();
//                WindowManager.LayoutParams lp = window.getAttributes();
////                Display display = mActivity.getWindowManager().getDefaultDisplay();
////                lp.width = (int) (display.getWidth() * 0.6);
//                window.setAttributes(lp);
//                window.setBackgroundDrawable(new ColorDrawable());
                Beta.checkUpgrade();
                break;
        }
    }

    private void showService() {
        serviceDialog = new CustomDialog(getBaseContext());
        serviceDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        serviceDialog.show();
        /*serviceDialog.setNoOnclickListener("取消", new CustomDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                serviceDialog.dismiss();
            }
        });

        rv_service = serviceDialog.findViewById(R.id.rv_service);
        for (int i = 0; i < 10; i++) {
            ServiceList.add(new Product());
        }
        ServiceAdapter serviceAdapter = new ServiceAdapter(R.layout.item_service, ServiceList);
        rv_service.setLayoutManager(new LinearLayoutManager(mActivity));
        rv_service.setAdapter(serviceAdapter);*/
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }


    @Override
    public void Login(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {

    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {

    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void LoginOnMessage(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void LoginOut(BaseResult<Data<String>> baseResult) {
        switch(baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    spUtils.put("isLogin", false);
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    ActivityUtils.finishAllActivities();
                }else{
                    ToastUtils.showShort("退出失败");
                }
                break;

            default:
                break;
        }
    }
}