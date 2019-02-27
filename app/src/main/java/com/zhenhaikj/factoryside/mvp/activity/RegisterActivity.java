package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.MainActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.RegisterContract;
import com.zhenhaikj.factoryside.mvp.model.RegisterModel;
import com.zhenhaikj.factoryside.mvp.presenter.RegisterPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.zhenhaikj.factoryside.mvp.widget.ClearEditText;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity<RegisterPresenter, RegisterModel> implements View.OnClickListener, RegisterContract.View {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.et_username)
    ClearEditText mEtUsername;
    @BindView(R.id.et_password)
    ClearEditText mEtPassword;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.et_verification_code)
    ClearEditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;

    @BindView(R.id.tv_can_not_receive)
    TextView mTvCanNotReceive;
    @BindView(R.id.cb)
    CheckBox mCb;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    private String userName;
    private String passWord;
    private String code;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void initView() {
//        mEtUsername.setText("admin");
//        mEtPassword.setText("123");
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.white);
        mImmersionBar.fitsSystemWindows(false);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    protected void setListener() {
        mTvGetVerificationCode.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mTvCanNotReceive.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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
            case R.id.btn_register:
                userName = mEtUsername.getText().toString();
                code = mEtVerificationCode.getText().toString();
                if ("".equals(userName)) {
                    ToastUtils.showShort("请输入手机号！");
                    return;
                }
                if ("".equals(code)) {
                    ToastUtils.showShort("请输入验证码！");
                    return;
                }
                mPresenter.Reg(userName, code);
                break;
            case R.id.tv_get_verification_code:
                userName = mEtUsername.getText().toString();
                if ("".equals(userName)) {
                    ToastUtils.showShort("请输入手机号！");
                    return;
                }
                if (!RegexUtils.isMobileExact(userName)){
                    ToastUtils.showShort("手机号格式不正确！");
                    return;
                }
                mPresenter.ValidateUserName(userName);

                break;
            case R.id.tv_agreement:
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_can_not_receive:
                final CommonDialog_Home dialog = new CommonDialog_Home(this);
                dialog.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog.dismiss();
                        call("tel:"+"18074208209");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }

    @Override
    public void Reg(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data data=baseResult.getData();
                if (data.isItem1()){
                    mPresenter.Login(userName,"888888");
                }
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetCode(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data data=baseResult.getData();
                if (data.isItem1()){
                    MyUtils.showToast(mActivity,"验证码已发送，请注意查收！");
                }else{
                    
                }
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void Login(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                SPUtils spUtils = SPUtils.getInstance("token");
                spUtils.put("adminToken", baseResult.getData());
                spUtils.put("userName", userName);
//                GetUserInfo getUserInfo=new GetUserInfo(userName,baseResult.getData(),"","");
//                Gson gson=new Gson();
//                RequestBody json=RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),gson.toJson(getUserInfo));
//                mPresenter.GetUserInfo(json);
//                mPresenter.GetUserInfo(userName);
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void ValidateUserName(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                MyUtils.e(TAG, baseResult.getData());
                if ("true".equals(baseResult.getData())){
                    TimeCount time = new TimeCount(60000, 1000);
                    time.start();
                    mPresenter.GetCode(userName);
                }else{
                    ToastUtils.showShort("手机号已经注册！");
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            get_yzm.setBackgroundColor(Color.parseColor("#B6B6D8"));
            if (mTvGetVerificationCode==null){
                return;
            }
            mTvGetVerificationCode.setClickable(false);
            mTvGetVerificationCode.setText(millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            if (mTvGetVerificationCode==null){
                return;
            }
            mTvGetVerificationCode.setText("重新获取验证码");
            mTvGetVerificationCode.setClickable(true);
//            get_yzm.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }
}