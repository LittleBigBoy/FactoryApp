package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    boolean isFirstIn = false;

    protected ImmersionBar mImmersionBar;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final long SPLASH_DELAY_MILLIS = 2000;


    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.img)
    ImageView mImg;
    private SPUtils spUtils;
    private String userName;
    private String passWord;
    private boolean isLogin;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        if (isImmersionBarEnabled())
            initImmersionBar();
        init();
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private Handler mHandler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
//                    goGuide();
                    break;
                case GO_GUIDE:
//                    goGuide();
                    goHome();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void init() {
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);


        isFirstIn = preferences.getBoolean("isFirstIn", true);
//        isFirstIn = true;
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }

    }


    private void goHome() {
        spUtils = SPUtils.getInstance("token");
        userName = spUtils.getString("userName");
        passWord = spUtils.getString("passWord");
        isLogin = spUtils.getBoolean("isLogin");
        if (userName != null && isLogin) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }
        finish();
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.red2);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    private void goGuide() {
//        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
//        SplashActivity.this.startActivity(intent);
//        SplashActivity.this.finish();
    }
}


