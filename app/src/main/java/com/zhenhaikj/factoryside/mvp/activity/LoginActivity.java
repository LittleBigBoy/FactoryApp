package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.zhenhaikj.factoryside.mvp.MainActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.contract.LoginContract;
import com.zhenhaikj.factoryside.mvp.model.LoginModel;
import com.zhenhaikj.factoryside.mvp.presenter.LoginPresenter;
import com.zhenhaikj.factoryside.mvp.requestbody.GetUserInfo;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import butterknife.BindView;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity<LoginPresenter,LoginModel> implements View.OnClickListener,LoginContract.View {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @BindView(R.id.tv_get_verification_code)
    TextView mTvGetVerificationCode;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;
    @BindView(R.id.tv_password_login)
    TextView mTvPasswordLogin;
    @BindView(R.id.cb)
    CheckBox mCb;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_weixin)
    ImageView mIvWeixin;
    @BindView(R.id.iv_weibo)
    ImageView mIvWeibo;
    private String userName;
    private String passWord;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void initView() {
        mEtUsername.setText("admin");
        mEtPassword.setText("123");
    }

    @Override
    protected void setListener() {
        mLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvPasswordLogin.setOnClickListener(this);
        mIvQq.setOnClickListener(this);
        mIvWeixin.setOnClickListener(this);
        mIvWeibo.setOnClickListener(this);
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
            case R.id.login:
                userName = mEtUsername.getText().toString();
                passWord = mEtPassword.getText().toString();
                if ("".equals(userName)){
                    ToastUtils.showShort("请输入用户名！");
                    return;
                }
                if ("".equals(passWord)){
                    ToastUtils.showShort("请输入密码！");
                    return;
                }
                mPresenter.Login(userName, passWord);
                break;
            case R.id.tv_agreement:
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_password_login:
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weixin:
                break;
            case R.id.iv_weibo:
                break;
        }
    }

    @Override
    public void Login(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                SPUtils spUtils= SPUtils.getInstance("token");
                spUtils.put("adminToken",baseResult.getData());
                spUtils.put("userName",userName);
//                GetUserInfo getUserInfo=new GetUserInfo(userName,baseResult.getData(),"","");
//                Gson gson=new Gson();
//                RequestBody json=RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),gson.toJson(getUserInfo));
//                mPresenter.GetUserInfo(json);
//                mPresenter.GetUserInfo(userName);
                startActivity(new Intent(mActivity,MainActivity.class));
                finish();
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetUserInfo(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                MyUtils.e("userInfo",baseResult.getData());
                ToastUtils.showShort(baseResult.getData());
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }
}