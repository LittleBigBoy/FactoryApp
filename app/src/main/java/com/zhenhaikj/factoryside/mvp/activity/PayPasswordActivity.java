package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.ChangePayPasswordContract;
import com.zhenhaikj.factoryside.mvp.model.ChangePayPasswordModel;
import com.zhenhaikj.factoryside.mvp.presenter.ChangePayPasswordPresenter;
import com.zhenhaikj.factoryside.mvp.widget.OnPasswordInputFinish;
import com.zhenhaikj.factoryside.mvp.widget.PasswordView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayPasswordActivity extends BaseActivity<ChangePayPasswordPresenter, ChangePayPasswordModel> implements ChangePayPasswordContract.View {


    @BindView(R.id.pwd_view)
    PasswordView mPwdView;
    @BindView(R.id.view)
    View mView;
    private String userId;
    private UserInfo.UserInfoDean userInfo;
    private int Type;  //1设置 2重置

    private String paypassword; //支付密码
    private List<String> paylist=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pay_password;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        SPUtils spUtils=SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId,"1");

        mPwdView.setOnFinishInput(new OnPasswordInputFinish() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void inputFinish() {
                // 输入完成后我们简单显示一下输入的密码
                // 也就是说——>实现你的交易逻辑什么的在这里写
                if (Type==1){
                    mPresenter.ChangePayPassword(userId,"",mPwdView.getStrPassword());
                }else if (Type==2){
                    if (paylist.size()==0){
                        if (!paypassword.equals(mPwdView.getStrPassword())){
                            ToastUtils.showShort("旧密码错误");
                        }else {
                            paylist.add(mPwdView.getStrPassword());
                            mPwdView.cleanPassword();
                            mPwdView.setTitle("请输入新密码");
                        }
                    }else {
                        mPresenter.ChangePayPassword(userId,paypassword,mPwdView.getStrPassword());
                    }
                }
//                Toast.makeText(PayPasswordActivity.this, mPwdView.getStrPassword(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void outfo() {
                //关闭支付页面
                finish();
            }

            @Override
            public void forgetPwd() {
                Toast.makeText(PayPasswordActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                if (baseResult.getData().getData()!=null){
                    /*判断是否有支付密码*/
                    if ("".equals(baseResult.getData().getData().get(0).getPayPassWord())){
                        Type=1;
                        mPwdView.setTitle("设置密码");
                    }else {
                        paypassword=baseResult.getData().getData().get(0).getPayPassWord();
                        Type=2;
                        mPwdView.setTitle("请输入旧密码");
                    }

                }

                break;
        }
    }

    @Override
    public void ChangePayPassword(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort("设置成功");
                finish();
                break;
        }
    }
}
