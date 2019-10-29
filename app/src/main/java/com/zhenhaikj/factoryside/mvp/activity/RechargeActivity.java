package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.FaceValueAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.FaceValue;
import com.zhenhaikj.factoryside.mvp.bean.PayResult;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;
import com.zhenhaikj.factoryside.mvp.contract.RechargeContract;
import com.zhenhaikj.factoryside.mvp.model.RechargeModel;
import com.zhenhaikj.factoryside.mvp.presenter.RechargePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RechargeActivity extends BaseActivity<RechargePresenter, RechargeModel> implements View.OnClickListener, RechargeContract.View {

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
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.rl_recharge_amount)
    RecyclerView mRlRechargeAmount;
    @BindView(R.id.tv_actual_arrival)
    TextView mTvActualArrival;
    @BindView(R.id.iv_aplipay)
    ImageView mIvAplipay;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.tv_recharge_agreement)
    TextView mTvRechargeAgreement;
    @BindView(R.id.bt_recharge)
    Button mBtRecharge;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_alipay)
    LinearLayout mLlAlipay;
    @BindView(R.id.ll_wxpay)
    LinearLayout mLlWxpay;
    @BindView(R.id.et_any_amount)
    EditText mEtAnyAmount;
    @BindView(R.id.tv_frozen_amount)
    TextView mTvFrozenAmount;
    @BindView(R.id.tv_available)
    TextView mTvAvailable;
    @BindView(R.id.ll_margin)
    LinearLayout mLlMargin;
    @BindView(R.id.tv_margin)
    TextView mTvMargin;
    private List<FaceValue> faceValueList = new ArrayList<>();
    private FaceValueAdapter faceValueAdapter;
    private String[] faceValues = new String[]{"100", "300", "500", "1000", "2000", "3000"};
    private String value;
    private IWXAPI api;
    private int payway = 1;
    private SPUtils spUtils;
    private String userID;
    private String orderinfo;
    private WXpayInfo wXpayInfo;
    private UserInfo.UserInfoDean userInfo = new UserInfo.UserInfoDean();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_recharge;
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
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        api = WXAPIFactory.createWXAPI(this, "wxd6509c9c912f0015");

        mPresenter.GetUserInfoList(userID, "1");

        mIvAplipay.setSelected(true);//默认选中支付宝
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("在线充值");
        for (int i = 0; i < faceValues.length; i++) {
            faceValueList.add(new FaceValue(faceValues[i], false));
        }
        faceValueList.get(0).setSelect(true);
        value = faceValueList.get(0).getValue();
        mTvActualArrival.setText(value);
        faceValueAdapter = new FaceValueAdapter(R.layout.face_value_item, faceValueList);
        mRlRechargeAmount.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRlRechargeAmount.setAdapter(faceValueAdapter);
        faceValueAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mEtAnyAmount.clearFocus();
                mEtAnyAmount.setText("");
                for (int i = 0; i < faceValueList.size(); i++) {
                    if (i == position) {
                        faceValueList.get(i).setSelect(true);
                    } else {
                        faceValueList.get(i).setSelect(false);
                    }
                }
                faceValueAdapter.notifyDataSetChanged();
                value = faceValueList.get(position).getValue();
                mTvActualArrival.setText(value);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlAlipay.setOnClickListener(this);
        mLlWxpay.setOnClickListener(this);
        mBtRecharge.setOnClickListener(this);
        mTvRechargeAgreement.setOnClickListener(this);
        mEtAnyAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    for (int i = 0; i < faceValueList.size(); i++) {
                        faceValueList.get(i).setSelect(false);
                    }
                    faceValueAdapter.notifyDataSetChanged();
                    value = "0";
                    mTvActualArrival.setText(value);
                }
            }
        });
        mEtAnyAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                int len = s.toString().length();
                if (len > 1 && text.startsWith("0")) {
                    value = s.replace(0, 1, "").toString();
                } else {
                    if ("".equals(text)) {
                        value = "0";
                    } else {
                        value = text;
                    }
                }
                mTvActualArrival.setText(value);
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
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_alipay:
                payway = 1;
                mIvAplipay.setSelected(true);
                mIvWechat.setSelected(false);
                break;
            case R.id.ll_wxpay:
                payway = 2;
                mIvAplipay.setSelected(false);
                mIvWechat.setSelected(true);
                break;
            case R.id.tv_recharge_agreement:
                Intent intent=new Intent(mActivity,WebActivity.class);
                intent.putExtra("Url","http://admin.xigyu.com/message/recharge");
                intent.putExtra("title","充值协议");
                startActivity(intent);
                break;
            case R.id.bt_recharge:
                if (value == null || "0".equals(value)) {
                    ToastUtils.showShort("请选择或输入充值金额");
                    return;
                }
                switch (payway) {
                    case 1:
                        mPresenter.GetOrderStr(userID, value);
//                        alipay();
                        break;
                    case 2:
                        mPresenter.GetWXOrderStr(userID, value);
//                        WXpay();
                        break;
                }
                break;
        }
    }

    /**
     * 支付宝支付业务
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
                PayTask alipay = new PayTask(RechargeActivity.this);
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
    public void WXpay() {
        // 将该app注册到微信
        api.registerApp("wxd6509c9c912f0015");
        PayReq req = new PayReq();
        req.appId = wXpayInfo.getAppid();
        req.partnerId = wXpayInfo.getPartnerid();
        req.prepayId = wXpayInfo.getPrepayid();
        req.nonceStr = wXpayInfo.getNoncestr();
        req.timeStamp = wXpayInfo.getTimestamp();
        req.packageValue = wXpayInfo.getPackageX();
        req.sign = wXpayInfo.getSign();
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
                        EventBus.getDefault().post("money");
                        finish();
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
     *
     * @param resp
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(BaseResp resp) {
        switch (resp.errCode) {
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

    @Override
    public void GetOrderStr(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    orderinfo = baseResult.getData().getItem2();
                    if (!"".equals(orderinfo)) {
                        alipay();
                    }
                } else {
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
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    mPresenter.GetUserInfoList(userID, "1");
                    EventBus.getDefault().post("money");
                    wXpayInfo = baseResult.getData().getItem2();
                    if (wXpayInfo != null) {
                        WXpay();
                    }
                } else {
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
        switch (baseResult.getStatusCode()){
            case 200:
                finish();
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                String TotalMoney = String.format("%.2f", userInfo.getTotalMoney());
                mTvMoney.setText(TotalMoney);
                String format = String.format("%.2f", userInfo.getTotalMoney() - userInfo.getFrozenMoney());
                mTvAvailable.setText(format);
                String FrozenMoney = String.format("%.2f", userInfo.getFrozenMoney());
                mTvFrozenAmount.setText(FrozenMoney);
                String DepositMoney = String.format("%.2f", userInfo.getDepositMoney());
                mTvMargin.setText(DepositMoney);
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