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

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final long SPLASH_DELAY_MILLIS = 2000;


    private static final String SHAREDPREFERENCES_NAME = "first_pref";
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
        init();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void goGuide() {
//        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
//        SplashActivity.this.startActivity(intent);
//        SplashActivity.this.finish();
    }
}


