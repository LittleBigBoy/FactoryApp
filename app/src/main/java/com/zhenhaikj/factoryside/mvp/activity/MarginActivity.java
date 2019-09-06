package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.PayTheDepositeAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.WithdrawalMarginAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.PayResult;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;
import com.zhenhaikj.factoryside.mvp.contract.MarginContract;
import com.zhenhaikj.factoryside.mvp.model.MarginModel;
import com.zhenhaikj.factoryside.mvp.presenter.MarginPresenter;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;
import com.zhenhaikj.factoryside.mvp.widget.MaginDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MarginActivity extends BaseActivity<MarginPresenter, MarginModel> implements View.OnClickListener, MarginContract.View {
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
    @BindView(R.id.tv_withdrawal_margin)
    TextView mTvWithdrawalMargin;
    @BindView(R.id.tv_pay_the_deposit)
    TextView mTvPayTheDeposit;
    @BindView(R.id.ll_paying_a_deposit_record)
    LinearLayout mLlPayingADepositRecord;
    @BindView(R.id.rv_pay_the_deposit)
    RecyclerView mRvPayTheDeposit;
    @BindView(R.id.ll_withdrawal_of_margin_records)
    LinearLayout mLlWithdrawalOfMarginRecords;
    @BindView(R.id.rv_withdrawal_margin)
    RecyclerView mRvWithdrawalMargin;
    private List<Address> marginList = new ArrayList<>();
    private List<Address> payList = new ArrayList<>();
    private MaginDialog maginDialog;
    private View under_review;
    private TextView tv_cancel;
    private RadioGroup radiogroup;
    private TextView tv_confirm;
    private AlertDialog underReviewDialog;
    private Window window;
    private TextView tv_margin;
    private String value;
    private LinearLayout ll_alipay;
    private LinearLayout ll_wxpay;
    private ImageView iv_aplipay;
    private ImageView iv_wechat;
    private int payway=1;
    private RadioButton rb1;
    private SPUtils spUtils;
    private String userId;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private LinearLayout ll_choose1;
    private LinearLayout ll_choose2;
    private LinearLayout ll_choose3;
    private LinearLayout ll_choose4;
    private ImageView cb1;
    private ImageView cb2;
    private ImageView cb3;
    private ImageView cb4;
    private String orderinfo;
    private WXpayInfo wXpayInfo;
    private IWXAPI api;
    private LinearLayout ll_choose5;
    private LinearLayout ll_choose6;
    private ImageView cb5;
    private ImageView cb6;
    private AlertDialog dialog;
    private UserInfo.UserInfoDean userInfo;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_margin;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId,"1");
        api = WXAPIFactory.createWXAPI(this, "wxd6509c9c912f0015");
        // 将该app注册到微信
        api.registerApp("wxd6509c9c912f0015");

        for (int i = 0; i < 10; i++) {
            marginList.add(new Address());
            payList.add(new Address());
        }
        PayTheDepositeAdapter payTheDepositeAdapter=new PayTheDepositeAdapter(R.layout.item_pay_the_deposit,payList);
        mRvPayTheDeposit.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPayTheDeposit.setAdapter(payTheDepositeAdapter);

        WithdrawalMarginAdapter withdrawalMarginAdapter=new WithdrawalMarginAdapter(R.layout.item_withdrawal_margin,marginList);
        mRvWithdrawalMargin.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvWithdrawalMargin.setAdapter(withdrawalMarginAdapter);

    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("保证金");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvPayTheDeposit.setOnClickListener(this);
        mTvWithdrawalMargin.setOnClickListener(this);
       mTvPayTheDeposit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_withdrawal_margin:
//                maginDialog = new MaginDialog(this);
//                maginDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
//
//                maginDialog.setNoOnclickListener("取消", new MaginDialog.onNoOnclickListener() {
//                    @Override
//                    public void onNoClick() {
//                        maginDialog.dismiss();
//                    }
//                });
//                maginDialog.show();
                View view=LayoutInflater.from(mActivity).inflate(R.layout.dialog_pay_the_deposit,null);
                TextView et_margin=view.findViewById(R.id.et_margin);
                TextView tv_confirm=view.findViewById(R.id.tv_confirm);
                TextView tv_cancel=view.findViewById(R.id.tv_cancel);
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userInfo.getFrozenMoney()>0){
                            final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                            dialog.setMessage("必须工单全部完结才能提取保证金")
                                    //.setImageResId(R.mipmap.ic_launcher)
                                    .setTitle("提示")
                                    .setPositive("确定")
                                    .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                @Override
                                public void onPositiveClick() {//拨打电话
                                    dialog.dismiss();
                                }

                                @Override
                                public void onNegtiveClick() {//取消
                                    dialog.dismiss();
                                    // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                        }else if (userInfo.getDepositMoney()<1000){
                            final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                            dialog.setMessage("可提现保证金小于需提现的保证金")
                                    //.setImageResId(R.mipmap.ic_launcher)
                                    .setTitle("提示")
                                    .setPositive("确定")
                                    .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                @Override
                                public void onPositiveClick() {//拨打电话
                                    dialog.dismiss();
                                }

                                @Override
                                public void onNegtiveClick() {//取消
                                    dialog.dismiss();
                                    // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                        }else {
                            final CommonDialog_Home home_dialog = new CommonDialog_Home(mActivity);
                            home_dialog.setMessage("确认提取1000保证金")
                                    //.setImageResId(R.mipmap.ic_launcher)
                                    .setTitle("提示")
                                    .setPositive("确定")
                                    .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                @Override
                                public void onPositiveClick() {//拨打电话
                                    startActivity(new Intent(mActivity, WithdrawActivity.class));
                                    home_dialog.dismiss();
                                    dialog.dismiss();
                                }

                                @Override
                                public void onNegtiveClick() {//取消
                                    home_dialog.dismiss();
                                    // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                        }
                    }
                });
                dialog = new AlertDialog.Builder(mActivity).setView(view).create();
                dialog.show();
                Window window= dialog.getWindow();
                WindowManager.LayoutParams lp=window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(lp);
                break;

            case R.id.tv_pay_the_deposit:
                deposit();
                break;
        }
    }

    public void deposit(){
        under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_deposit, null);
        tv_cancel = under_review.findViewById(R.id.tv_cancel);
        radiogroup = under_review.findViewById(R.id.radiogroup);
        tv_confirm = under_review.findViewById(R.id.tv_confirm);
        tv_margin = under_review.findViewById(R.id.tv_margin);
        ll_alipay = under_review.findViewById(R.id.ll_alipay);
        ll_wxpay = under_review.findViewById(R.id.ll_wxpay);
        iv_aplipay = under_review.findViewById(R.id.iv_aplipay);
        iv_wechat = under_review.findViewById(R.id.iv_wechat);
        iv_aplipay.setSelected(true);
        ll_choose1 = under_review.findViewById(R.id.ll_choose1);
        ll_choose2 = under_review.findViewById(R.id.ll_choose2);
        ll_choose3 = under_review.findViewById(R.id.ll_choose3);
        ll_choose4 = under_review.findViewById(R.id.ll_choose4);
        ll_choose5 = under_review.findViewById(R.id.ll_choose5);
        ll_choose6 = under_review.findViewById(R.id.ll_choose6);
        cb1 = under_review.findViewById(R.id.cb1);
        cb2 = under_review.findViewById(R.id.cb2);
        cb3 = under_review.findViewById(R.id.cb3);
        cb4 = under_review.findViewById(R.id.cb4);
        cb5 = under_review.findViewById(R.id.cb5);
        cb6 = under_review.findViewById(R.id.cb6);
        cb2.setSelected(true);
        tv_margin.setText("1000");
        ll_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payway = 1;
                iv_aplipay.setSelected(true);
                iv_wechat.setSelected(false);
            }
        });

        ll_wxpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payway=2;
                iv_aplipay.setSelected(false);
                iv_wechat.setSelected(true);
            }
        });

        ll_choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(true);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("500");
            }
        });
        ll_choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(true);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("1000");
            }
        });
        ll_choose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(true);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("1500");
            }
        });
        ll_choose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(true);
                cb5.setSelected(false);
                cb6.setSelected(false);
                tv_margin.setText("3000");
            }
        });
        ll_choose5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(true);
                cb6.setSelected(false);
                tv_margin.setText("5000");
            }
        });
        ll_choose6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb1.setSelected(false);
                cb2.setSelected(false);
                cb3.setSelected(false);
                cb4.setSelected(false);
                cb5.setSelected(false);
                cb6.setSelected(true);
                tv_margin.setText("10000");
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                underReviewDialog.dismiss();
            }
        });


        value = tv_margin.getText().toString();
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (value ==null||"0".equals(value)){
//                    ToastUtils.showShort("请选择或输入充值金额");
//                    return;
//                }
                switch (payway){
                    case 1:
                        mPresenter.GetOrderStr(userId,tv_margin.getText().toString());
//                        alipay();
                        break;
                    case 2:
                        mPresenter.GetWXOrderStr(userId,tv_margin.getText().toString());
//                        WXpay();
                        break;
                }
            }
        });
        underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review).create();
        underReviewDialog.show();
        window = underReviewDialog.getWindow();
        WindowManager.LayoutParams lp=window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetOrderStr(BaseResult<Data<String>> baseResult) {
        switch(baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    orderinfo = baseResult.getData().getItem2();
                    if (!"".equals(orderinfo)){
                        alipay();
                    }
                }else{
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult) {
        switch(baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    wXpayInfo = baseResult.getData().getItem2();
                    if (wXpayInfo!=null){
                        WXpay();
                    }
                }else{
                    ToastUtils.showShort("获取支付信息失败！");
                }
                break;
            default:
                ToastUtils.showShort("获取支付信息失败！");
                break;
        }
    }

    @Override
    public void WXNotifyManual(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                break;
        }
    }


    /**
     * 支付宝支付业务
     *
     */
    public void alipay() {

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
//        orderinfo = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22IQJZSRC1YMQB5HU%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fdomain.merchant.com%2Fpayment_notify&sign_type=RSA2&timestamp=2016-08-25%2020%3A26%3A31&version=1.0&sign=cYmuUnKi5QdBsoZEAbMXVMmRWjsuUj%2By48A2DvWAVVBuYkiBj13CFDHu2vZQvmOfkjE0YqCUQE04kqm9Xg3tIX8tPeIGIFtsIyp%2FM45w1ZsDOiduBbduGfRo1XRsvAyVAv2hCrBLLrDI5Vi7uZZ77Lo5J0PpUUWwyQGt0M4cj8g%3D";

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MarginActivity.this);
                Map<String, String> result = alipay.payV2(orderinfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 微信支付
     */
    public void WXpay(){
        PayReq req = new PayReq();
        req.appId			= wXpayInfo.getAppid();
        req.partnerId		= wXpayInfo.getPartnerid();
        req.prepayId		= wXpayInfo.getPrepayid();
        req.nonceStr		= wXpayInfo.getNoncestr();
        req.timeStamp		= wXpayInfo.getTimestamp();
        req.packageValue	=  wXpayInfo.getPackageX();
        req.sign			= wXpayInfo.getSign();
        //req.extData			= "app data"; // optional
        api.sendReq(req);
    }

    /**
     * 支付宝支付结果回调
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShort("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 微信支付结果
     * @param resp
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseResp resp) {
        switch (resp.errCode){
            case 0:
                mPresenter.WXNotifyManual(wXpayInfo.getOut_trade_no());
                ToastUtils.showShort("支付成功");
                break;
            case -1:
                ToastUtils.showShort("支付出错");
                break;
            case -2:
                ToastUtils.showShort("支付取消");
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"GetUserInfoList".equals(message)) {
            return;
        }

    }

}
