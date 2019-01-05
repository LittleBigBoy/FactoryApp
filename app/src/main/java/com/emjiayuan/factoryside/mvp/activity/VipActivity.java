package com.emjiayuan.factoryside.mvp.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.emjiayuan.nll.Constants;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.PrivilegeAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.WXpaySuccessEvent;
import com.emjiayuan.nll.model.PayResult;
import com.emjiayuan.nll.model.Privilege;
import com.emjiayuan.nll.model.WXpayInfo;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class VipActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "VipActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.icon_search)
    ImageView iconSearch;
    @BindView(R.id.rv_privilege)
    RecyclerView mRvPrivilege;
    @BindView(R.id.cbox)
    CheckBox mCbox;
    @BindView(R.id.ll_check)
    LinearLayout mLlCheck;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.pay_btn_ll)
    LinearLayout mPayBtnLl;
    private PrivilegeAdapter mPrivilegeAdapter;
    private ArrayList<Privilege> mPrivilegeList;
    private String mLevelid;
    private String mLevelcost;
    private PopupWindow mPopupWindow;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("开通会员");
        getVipPowerExplan();

    }

    @Override
    protected void setListener() {
        iconBack.setOnClickListener(this);
        iconSearch.setOnClickListener(this);
        mLlCheck.setOnClickListener(this);
        mDescription.setOnClickListener(this);
        mPayBtnLl.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.pay_btn_ll:
                if (mCbox.isChecked()){
                    showPopupWindow();
                }else{
                    MyUtils.showToast(mActivity,"请阅读并同意伊穆家园会员服务协议！");
                }
                break;
            case R.id.ll_check:
                if (mCbox.isChecked()) {
                    mCbox.setChecked(false);
                } else {
                    mCbox.setChecked(true);
                }
                break;
            case R.id.description:
                startActivity(new Intent(mActivity, VipAgreementActivity.class));
                break;
        }
    }

    public void getVipPowerExplan() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("user.getVipPowerExplan", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyUtils.e("------获取会员权益结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    private String orderInfo;
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            switch (msg.what) {
                case 0:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONObject jsonObject1 = new JSONObject(data);
                            JSONArray jsonArray = jsonObject1.getJSONArray("powerlist");
                            mLevelid = jsonObject1.getString("levelid");
                            mLevelcost = jsonObject1.getString("levelcost");
                            mPrivilegeList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                mPrivilegeList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Privilege.class));
                            }
                            mRvPrivilege.setLayoutManager(new GridLayoutManager(mActivity, 3));
                            mPrivilegeAdapter = new PrivilegeAdapter(R.layout.privilege_item, mPrivilegeList);
                            mRvPrivilege.setAdapter(mPrivilegeAdapter);
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.ALIPAY_RESULT://支付宝支付结果
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
                        MyUtils.showToast(mActivity, "支付成功");
                        mPopupWindow.dismiss();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyUtils.showToast(mActivity, "支付失败");
                        mPopupWindow.dismiss();
                    }
                    break;
                case Constants.ALIPAY:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            orderInfo = data;
                            alipay();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.WXPAY:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            WXpayInfo payInfo = gson.fromJson(data, WXpayInfo.class);
                            WXPay(payInfo);
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow() {
        View popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.payment_layout, null);
        LinearLayout yepay_ll = popupWindow_view.findViewById(R.id.yepay_ll);
        LinearLayout weixin_ll = popupWindow_view.findViewById(R.id.weixin_ll);
        final LinearLayout alipay_ll = popupWindow_view.findViewById(R.id.alipay_ll);
        Button cz_btn = popupWindow_view.findViewById(R.id.cz_btn);
        cz_btn.setText("立即支付");
        Button cancel_btn = popupWindow_view.findViewById(R.id.cancel_btn);
        final CheckBox weixin_check = popupWindow_view.findViewById(R.id.weixin_check);
        final CheckBox alipay_check = popupWindow_view.findViewById(R.id.alipay_check);
        final CheckBox yepay_check = popupWindow_view.findViewById(R.id.yepay_check);
        yepay_ll.setVisibility(View.GONE);
        weixin_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weixin_check.isChecked()) {
                    weixin_check.setChecked(false);
                } else {
                    weixin_check.setChecked(true);
                    alipay_check.setChecked(false);
                    yepay_check.setChecked(false);
                }
            }
        });
        alipay_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alipay_check.isChecked()) {
                    alipay_check.setChecked(false);
                } else {
                    alipay_check.setChecked(true);
                    weixin_check.setChecked(false);
                    yepay_check.setChecked(false);
                }
            }
        });
        yepay_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yepay_check.isChecked()) {
                    yepay_check.setChecked(false);
                } else {
                    alipay_check.setChecked(false);
                    weixin_check.setChecked(false);
                    yepay_check.setChecked(true);
                }
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        cz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                if (alipay_check.isChecked() == true) {
                    alipayrequest();
                } else if (weixin_check.isChecked() == true) {
                    WXpayrequest();
                } else {
                    MyUtils.showToast(mActivity, "请选择支付方式");
                }
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    //    支付宝支付
    private void alipay() {
/**
 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
 *
 * orderInfo的获取必须来自服务端；
 */
        /*boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;*/
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(VipActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = Constants.ALIPAY_RESULT;
                msg.obj = result;
                myHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    //微信支付

    private void WXPay(WXpayInfo payInfo) {
        IWXAPI api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        PayReq request = new PayReq();
        request.appId = payInfo.getAppid();
        request.partnerId = payInfo.getPartnerid();
        request.prepayId = payInfo.getPrepayid();
        request.packageValue = payInfo.getPackageX();
        request.nonceStr = payInfo.getNoncestr();
        request.timeStamp = payInfo.getTimestamp();
        request.sign = payInfo.getSign();
        api.sendReq(request);
    }

    public void alipayrequest() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", "BUY_LEVEL");//传递键值对参数
        formBody.add("levelid", mLevelid);//传递键值对参数
        formBody.add("userid", Global.loginResult.getId());
//new call
        Call call = MyOkHttp.GetCall("pay.alipay", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                MyUtils.e("------支付宝支付------", result);
                Message message = new Message();
                message.what = Constants.ALIPAY;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void WXpayrequest() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", "BUY_LEVEL");//传递键值对参数
        formBody.add("levelid", mLevelid);//传递键值对参数
        formBody.add("userid", Global.loginResult.getId());
//new call
        Call call = MyOkHttp.GetCall("pay.wxpay", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                MyUtils.e("------微信支付------", result);
                Message message = new Message();
                message.what = Constants.WXPAY;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(WXpaySuccessEvent event) {
        BaseResp resp = event.getResp();
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {   // 0 成功  展示成功页面
                MyUtils.showToast(mActivity, "支付成功");
                mPopupWindow.dismiss();
            } else if (resp.errCode == -1) {  // -1 错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
                MyUtils.showToast(mActivity, "支付出错");
                mPopupWindow.dismiss();

            } else if (resp.errCode == -2) {  // -2 用户取消    无需处理。发生场景：用户不支付了，点击取消，返回APP。
                MyUtils.showToast(mActivity, "取消支付");
                mPopupWindow.dismiss();
            }
        }
    }
}