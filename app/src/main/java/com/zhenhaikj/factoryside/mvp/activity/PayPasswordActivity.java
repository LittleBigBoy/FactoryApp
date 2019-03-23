package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.widget.OnPasswordInputFinish;
import com.zhenhaikj.factoryside.mvp.widget.PasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayPasswordActivity extends BaseActivity {


    @BindView(R.id.pwd_view)
    PasswordView mPwdView;
    @BindView(R.id.view)
    View mView;

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
        mPwdView.setOnFinishInput(new OnPasswordInputFinish() {
            @Override
            public void inputFinish() {
                // 输入完成后我们简单显示一下输入的密码
                // 也就是说——>实现你的交易逻辑什么的在这里写
                Toast.makeText(PayPasswordActivity.this, mPwdView.getStrPassword(),
                        Toast.LENGTH_SHORT).show();
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
}
