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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.factoryside.mvp.activity.address.AddressActivity;
import com.emjiayuan.nll.Constants;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.ConfirmOrderProductAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.WXpaySuccessEvent;
import com.emjiayuan.nll.model.Address;
import com.emjiayuan.nll.model.OrderConfirm;
import com.emjiayuan.nll.model.PayResult;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.shr_tv)
    TextView mShrTv;
    @BindView(R.id.tele_tv)
    TextView mTeleTv;
    @BindView(R.id.address_tv)
    TextView mAddressTv;
    @BindView(R.id.all_ll)
    RelativeLayout mAllLl;
    @BindView(R.id.address_ll)
    LinearLayout mAddressLl;
    @BindView(R.id.address_ll_no)
    LinearLayout mAddressLlNo;
    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    @BindView(R.id.bz_et)
    EditText mBzEt;
    @BindView(R.id.total_good)
    TextView mTotalGood;
    @BindView(R.id.freight_tv)
    TextView mFreightTv;
    @BindView(R.id.money_ll)
    LinearLayout mMoneyLl;
    @BindView(R.id.total_tv)
    TextView mTotalTv;
    @BindView(R.id.total_ll)
    LinearLayout mTotalLl;
    @BindView(R.id.limit_tv)
    TextView mLimitTv;
    @BindView(R.id.total_pay)
    TextView mTotalPay;
    @BindView(R.id.pay_btn)
    TextView mPayBtn;
    private OrderConfirm orderConfirm;
    private String productids="";
    private ConfirmOrderProductAdapter mConfirmOrderProductAdapter;
    private String remark;
    private String orderid;
    private String total;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_confirm;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mTvTitle.setText("确认订单");
        mTvTitle.setVisibility(View.VISIBLE);
        reqDefaultAddress();
        Intent intent = getIntent();
        if (intent != null) {
            orderConfirm = (OrderConfirm) intent.getSerializableExtra("orderConfirm");
            for (int i = 0; i < orderConfirm.getProducts().size(); i++) {
                productids += orderConfirm.getProducts().get(i).getId() + "|" + orderConfirm.getProducts().get(i).getBuynum() + ",";
            }
        }

        setdata();

        mConfirmOrderProductAdapter = new ConfirmOrderProductAdapter(R.layout.order_item_in, orderConfirm.getProducts());
        mRvGoods.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvGoods.setAdapter(mConfirmOrderProductAdapter);
        mConfirmOrderProductAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(OrderConfirmActivity.this, ProductDetailActivity.class);
                intent.putExtra("productid", orderConfirm.getProducts().get(position).getId());
                startActivity(intent);
            }
        });
    }

    public void setdata() {
        mTotalGood.setText("¥" + orderConfirm.getProductprice());
        mFreightTv.setText("¥" + orderConfirm.getExpressprice());
        mTotalTv.setText("¥" + orderConfirm.getPayprice());
        total = orderConfirm.getPayprice() + "";
        mTotalPay.setText("¥" + orderConfirm.getPayprice());
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mAddressLl.setOnClickListener(this);
        mAddressLlNo.setOnClickListener(this);
        mPayBtn.setOnClickListener(this);
    }

    public void confirmOrder() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("addressprovince", address.getShengfen());
        formBody.add("address", address.getAddress().substring(0, address.getAddress().lastIndexOf(" ")));
        formBody.add("productids", productids);
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("order.confirmOrder", formBody);
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
                MyUtils.e("------提交订单结果------", result);
                Message message = new Message();
                message.what = 6;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void reqDefaultAddress() {

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("userAddress.getDefaultAddress", formBody);
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
                MyUtils.e("------获取默认地址结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void addOrder() {

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("addressname", address.getUsername());
        formBody.add("addressphone", address.getTelphone());
        formBody.add("address", address.getAddress());
        formBody.add("addressprovince", address.getShengfen());
        formBody.add("remark", remark);
        formBody.add("productids", productids.substring(0, productids.lastIndexOf(",")));
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("order.addOrder", formBody);
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
                MyUtils.e("------添加订单结果------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    private Address address;

    public void setAddress() {
        if (address == null) {
            mAddressLlNo.setVisibility(View.VISIBLE);
            mAddressLl.setVisibility(View.GONE);
        } else {
            mAddressLlNo.setVisibility(View.GONE);
            mAddressLl.setVisibility(View.VISIBLE);
            mShrTv.setText("收货人：" + address.getUsername());
            mTeleTv.setText(address.getTelphone());
            mAddressTv.setText("地址：" + address.getAddress());
            confirmOrder();
        }

    }


    public void alipayrequest() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", "COMMON");//传递键值对参数
        formBody.add("orderid", orderid);//传递键值对参数
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
        formBody.add("paytype", "COMMON");//传递键值对参数
        formBody.add("orderid", orderid);//传递键值对参数
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

    private String orderInfo;
    private PopupWindow mPopupWindow;
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
                            address = gson.fromJson(jsonObject1.toString(), Address.class);
                        } else {
//                            MyUtils.showToast(mActivity, message);
                        }
                        setAddress();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONObject jsonObject1 = new JSONObject(data);
                            orderid = jsonObject1.getString("orderid");
                            showPopupWindow();
//                            Intent intent = new Intent(mActivity, OrderDetailActivity.class);
//                            intent.putExtra("orderid", orderid);
//                            startActivity(intent);
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case Constants.ALIPAY://支付宝支付
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
                case Constants.ALIPAY_RESULT://支付宝结果
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
                        Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                        intent.putExtra("orderid", orderid);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyUtils.showToast(mActivity, "支付失败");
                        mPopupWindow.dismiss();
                        Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                        intent.putExtra("orderid", orderid);
                        startActivity(intent);
                        finish();
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

                case 6:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONObject jsonObject1 = new JSONObject(data);
                            orderConfirm = gson.fromJson(jsonObject1.toString(), OrderConfirm.class);
                            setdata();
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

    @Override
    public void onClick(View view) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        Intent intent;
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.address_ll:
                intent = new Intent(mActivity, AddressActivity.class);
                intent.putExtra("flag", true);
                startActivityForResult(intent, 100);
                break;
            case R.id.address_ll_no:
                intent = new Intent(mActivity, AddressActivity.class);
                intent.putExtra("flag", true);
                startActivityForResult(intent, 100);
                break;
            case R.id.pay_btn:
                remark = mBzEt.getText().toString();
                if (address == null) {
                    MyUtils.showToast(mActivity, "请添加收货地址！");
                    return;
                }
                if (orderid == null) {
                    addOrder();
                } else {
                    showPopupWindow();
                }
                break;
        }
    }

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
//        yepay_ll.setVisibility(View.VISIBLE);
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
//                Intent intent=new Intent(mActivity,OrderNormalActivity2.class);
//                intent.putExtra("type",0);
//                startActivity(intent);
//                finish();
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
                PayTask alipay = new PayTask(OrderConfirmActivity.this);
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
//        api.registerApp( Constants.APP_ID);
//        List<NameValuePair> list=new ArrayList<>();
//        NameValuePair nameValuePair1=new NameValuePair();
//        nameValuePair1.setName("appid");
//        nameValuePair1.setValue(payInfo.getAppid());
//        NameValuePair nameValuePair2=new NameValuePair();
//        nameValuePair2.setName("partnerid");
//        nameValuePair2.setValue(payInfo.getAppid());
//        NameValuePair nameValuePair3=new NameValuePair();
//        nameValuePair3.setName("prepayid");
//        nameValuePair3.setValue(payInfo.getPrepayid());
//        NameValuePair nameValuePair4=new NameValuePair();
//        nameValuePair4.setName("package");
//        nameValuePair4.setValue(payInfo.getPackageX());
//        NameValuePair nameValuePair5=new NameValuePair();
//        nameValuePair5.setName("noncestr");
//        nameValuePair5.setValue(payInfo.getNoncestr());
//        NameValuePair nameValuePair6=new NameValuePair();
//        nameValuePair6.setName("timestamp");
//        nameValuePair6.setValue(payInfo.getTimestamp());
//        list.add(nameValuePair1);
//        list.add(nameValuePair5);
//        list.add(nameValuePair4);
//        list.add(nameValuePair2);
//        list.add(nameValuePair3);
//        list.add(nameValuePair6);

        PayReq request = new PayReq();
        request.appId = payInfo.getAppid();
        request.partnerId = payInfo.getPartnerid();
        request.prepayId = payInfo.getPrepayid();
        request.packageValue = payInfo.getPackageX();
        request.nonceStr = payInfo.getNoncestr();
        request.timeStamp = payInfo.getTimestamp();
        request.sign = payInfo.getSign();
//        request.sign = MyUtils.genPackageSign2(list);
//        MyUtils.e("sign",payInfo.getSign()+"|"+MyUtils.genPackageSign2(list));
        api.sendReq(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (data != null) {
                    address = (Address) data.getSerializableExtra("address");
                    setAddress();
                }
                break;
        }

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
                Intent intent = new Intent(mActivity, SuccessActivity.class);
                intent.putExtra("orderid", orderid);
                intent.putExtra("product", orderConfirm.getProducts().get(0));
                startActivity(intent);
                finish();
            } else if (resp.errCode == -1) {  // -1 错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
                MyUtils.showToast(mActivity, "支付出错");
                mPopupWindow.dismiss();
                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                intent.putExtra("orderid", orderid);
                startActivity(intent);
                finish();
            } else if (resp.errCode == -2) {  // -2 用户取消    无需处理。发生场景：用户不支付了，点击取消，返回APP。
                MyUtils.showToast(mActivity, "取消支付");
                mPopupWindow.dismiss();
                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                intent.putExtra("orderid", orderid);
                startActivity(intent);
                finish();
            }
        }
    }
}
