package com.zhenhaikj.factoryside.mvp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.YSFOptions;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import androidx.multidex.MultiDexApplication;

/**
 * 描述：
 * 作者：HMY
 * 时间：2016/5/13
 */
public class MyApplication extends MultiDexApplication {
    static {//static 代码段可以防止内存泄露
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                MaterialHeader header=new MaterialHeader(context);
                header.setPrimaryColors(Color.parseColor("#00000000"));
                header.setShowBezierWave(true);
                layout.setEnableHeaderTranslationContent(false);
                return header;//指定为经典Header，默认是 贝塞尔雷达Header
//                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        // appKey 可以在七鱼管理系统->设置->APP接入 页面找到
//        Unicorn.init(this, "1b5d0202117baa53c3c796075d043590", options(), new GlideImageLoader2(getApplicationContext()));
        XGPushConfig.enableDebug(this,true);
        XGPushConfig.enableOtherPush(getApplicationContext(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplicationContext(), "3dda854c8a0b1");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "143c2f4a39b0224cc8568280e3688a30");
        XGPushConfig.setMzPushAppId(this, "3dda854c8a0b1");
        XGPushConfig.setMzPushAppKey(this, "143c2f4a39b0224cc8568280e3688a30");
        XGPushManager.registerPush(this, new XGIOperateCallback() {
            @Override
            public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                Log.d("TPush", "注册成功，设备token为：" + data);
            }
            @Override
            public void onFail(Object data, int errCode, String msg) {
                Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
            }
        });
        XGPushManager.bindAccount(getApplicationContext(), "XINGE");
        XGPushManager.setTag(this,"XINGE");
    }
    // 如果返回值为null，则全部使用默认参数。
    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        return options;
    }
}
