package com.emjiayuan.factoryside.mvp.activity.soup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.emjiayuan.factoryside.mvp.activity.address.AddressActivity;
import com.emjiayuan.nll.Constants;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.WXpaySuccessEvent;
import com.emjiayuan.nll.model.Address;
import com.emjiayuan.nll.model.PayResult;
import com.emjiayuan.nll.model.Soup;
import com.emjiayuan.nll.model.SoupOrderConfirm;
import com.emjiayuan.nll.model.WXpayInfo;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.MyListView;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class SoupOrderConfirmActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.shr_tv)
    TextView shrTv;
    @BindView(R.id.tele_tv)
    TextView teleTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.all_ll)
    RelativeLayout allLl;
    @BindView(R.id.address_ll)
    LinearLayout addressLl;
    @BindView(R.id.address_ll_no)
    LinearLayout addressLlNo;
    @BindView(R.id.soup_name_et)
    EditText soupNameEt;
    @BindView(R.id.bz_et)
    EditText bzEt;
    @BindView(R.id.lv_goods)
    MyListView lvGoods;
    @BindView(R.id.total_mass)
    TextView totalMass;
    @BindView(R.id.make_money)
    TextView makeMoney;
    @BindView(R.id.cost)
    TextView cost;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.limit_tv)
    TextView limitTv;
    @BindView(R.id.total_pay)
    TextView totalPay;
    @BindView(R.id.pay_btn)
    TextView payBtn;
    @BindView(R.id.leixing1)
    TextView leixing1;
    @BindView(R.id.leixing2)
    TextView leixing2;
    @BindView(R.id.leixing0)
    TextView leixing0;
    @BindView(R.id.discount)
    TextView discount;
    @BindView(R.id.discount_tv)
    TextView discountTv;
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

    private PopupWindow mPopupWindow;
    private String remark = "";
    private String productids = "";
    private String val = "500";
    private String mass = "斤";
    private Address address;
    private SoupOrderConfirm soupOrderConfirm;
    private List<Soup> list = new ArrayList<>();
    private SoupInAdapter adapter;
    private String orderInfo;
    private String orderid;
    private String soupName = "";
    private String leixing = "";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_confirm_soup;
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("ResourceAsColor")
    public void setChecked(TextView t1, TextView t2, TextView t3) {
        t1.setBackgroundResource(R.drawable.btn_shape);
        t1.setTextColor(Color.RED);
        t2.setBackgroundResource(R.drawable.edit_shape);
        t2.setTextColor(R.color.black);
        t3.setBackgroundResource(R.drawable.edit_shape);
        t3.setTextColor(R.color.black);
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mTvTitle.setText("确认订单");
        mTvTitle.setVisibility(View.VISIBLE);
        reqDefaultAddress();
        Intent intent = getIntent();
        if (intent != null) {
            val = intent.getStringExtra("val");
            list = (List<Soup>) intent.getSerializableExtra("oklist");
            for (int i = 0; i < list.size(); i++) {
                productids += list.get(i).getId() + "|" + list.get(i).getNum() + ",";
            }
        }

        confirmOrder();

        adapter = new SoupInAdapter(mActivity, list);
        if ("500".equals(val)) {
            mass = "斤";
        } else if ("50".equals(val)) {
            mass = "两";
        } else if ("1000".equals(val)) {
            mass = "千克";
        } else if ("1".equals(val)) {
            mass = "克";
        }
        adapter.setMass(mass);
        lvGoods.setAdapter(adapter);
//        setChecked(leixing1,leixing2,leixing0);
    }

    public void setdata() {
        totalMass.setText("总重量" + soupOrderConfirm.getTotalweight() + mass);
        makeMoney.setText("¥" + soupOrderConfirm.getCostmoney());
        cost.setText("¥" + soupOrderConfirm.getProductprice());
        totalTv.setText("¥" + soupOrderConfirm.getTotalmoney());
        totalPay.setText("¥" + soupOrderConfirm.getTotalmoney());
        discount.setText(soupOrderConfirm.getDiscount() + "折");
        discountTv.setText("-¥" + soupOrderConfirm.getDiscountprice());
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        addressLl.setOnClickListener(this);
        addressLlNo.setOnClickListener(this);
        payBtn.setOnClickListener(this);
        leixing0.setOnClickListener(this);
        leixing1.setOnClickListener(this);
        leixing2.setOnClickListener(this);
    }

    public void confirmOrder() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("val", val);
        formBody.add("productids", productids.substring(0, productids.lastIndexOf(",")));
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("soupOrder.confirmSoupOrder", formBody);
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
        formBody.add("val", val);
        formBody.add("danwei", mass);
        formBody.add("soupname", soupName);
        formBody.add("remark", remark);
        formBody.add("leixing", leixing);
        formBody.add("productids", productids.substring(0, productids.lastIndexOf(",")));
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("soupOrder.addSoupOrder", formBody);
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
                MyUtils.e("------添加汤料订单结果------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }


    public void setAddress() {
        if (address == null) {
            addressLlNo.setVisibility(View.VISIBLE);
            addressLl.setVisibility(View.GONE);
        } else {
            addressLlNo.setVisibility(View.GONE);
            addressLl.setVisibility(View.VISIBLE);
            shrTv.setText("收货人：" + address.getUsername());
            teleTv.setText(address.getTelphone());
            addressTv.setText("地址：" + address.getAddress());
        }

    }

    public void Walletpayrequest() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", "SOUP");//传递键值对参数
        formBody.add("orderid", orderid);//传递键值对参数
        formBody.add("userid", Global.loginResult.getId());
//new call
        Call call = MyOkHttp.GetCall("pay.wallet", formBody);
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
                MyUtils.e("------余额支付------", result);
                Message message = new Message();
                message.what = 5;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void alipayrequest() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", "SOUP");//传递键值对参数
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
                message.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void WXpayrequest() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", "SOUP");//传递键值对参数
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
                message.what = 4;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

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
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
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
                case 3:
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
                        Intent intent = new Intent(mActivity, SoupSuccessActivity.class);
                        intent.putExtra("orderid", orderid);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyUtils.showToast(mActivity, "支付失败");
                        Intent intent = new Intent(mActivity, SoupOrderDetailActivity.class);
                        intent.putExtra("orderid", orderid);
                        mActivity.startActivity(intent);
                        finish();
                    }
                    break;
                case 4:
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
                case 5:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            MyUtils.showToast(mActivity, message);
                            Intent intent = new Intent(mActivity, SoupSuccessActivity.class);
                            intent.putExtra("orderid", orderid);
//                            intent.putExtra("type", 0);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(mActivity, SoupOrderDetailActivity.class);
                            intent.putExtra("orderid", orderid);
                            mActivity.startActivity(intent);
                            finish();
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
                            soupOrderConfirm = gson.fromJson(jsonObject1.toString(), SoupOrderConfirm.class);
                            setdata();
                        } else {
                            MyUtils.showToast(mActivity, message);
                            finish();
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
            case R.id.back:
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

                remark = bzEt.getText().toString();
                soupName = soupNameEt.getText().toString();
                if (address == null) {
                    MyUtils.showToast(mActivity, "请添加收货地址！");
                    return;
                }
                if ("".equals(soupName)) {
                    MyUtils.showToast(mActivity, "请填写汤料名称！");
                    return;
                }
                if ("".equals(leixing)) {
                    MyUtils.showToast(mActivity, "请选择汤料类型！");
                    return;
                }
                if (orderid == null) {
                    addOrder();
                } else {
                    showPopupWindow();
                }
                break;
            case R.id.leixing0:
                leixing = "0";
                setChecked(leixing0, leixing2, leixing1);
                break;
            case R.id.leixing1:
                leixing = "1";
                setChecked(leixing1, leixing2, leixing0);
                break;
            case R.id.leixing2:
                leixing = "2";
                setChecked(leixing2, leixing0, leixing1);
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
//                Intent intent = new Intent(mActivity, OrderNormalActivity2.class);
//                intent.putExtra("type", 3);
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
//                    Toast.makeText(mActivity, "微信支付",
//                            Toast.LENGTH_LONG).show();

                    WXpayrequest();
                } else if (yepay_check.isChecked() == true) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setTitle("温馨提示");
                    builder.setMessage("余额支付" + soupOrderConfirm.getTotalmoney() + "元，确定要继续吗");
                    builder.setCancelable(true);

                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Walletpayrequest();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //在这里实现你自己的逻辑

                        }
                    });
                    builder.create().show();

                } else {
                    Toast.makeText(mActivity, "请选择支付方式",
                            Toast.LENGTH_LONG).show();
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
 *//*
         *//*boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;*/
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(SoupOrderConfirmActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = 3;
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
				/*AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("微信支付结果");
				builder.setMessage("支付订单成功！");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						finish();
					}
				});
				builder.show();*/
                Intent intent = new Intent(mActivity, SoupSuccessActivity.class);
                intent.putExtra("orderid", orderid);
//                            intent.putExtra("type", 0);
                startActivity(intent);
                finish();
            } else if (resp.errCode == -1) {  // -1 错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
                MyUtils.showToast(mActivity, "支付出错");
                Intent intent = new Intent(mActivity, SoupOrderDetailActivity.class);
                intent.putExtra("orderid", orderid);
                mActivity.startActivity(intent);
                finish();
            } else if (resp.errCode == -2) {  // -2 用户取消    无需处理。发生场景：用户不支付了，点击取消，返回APP。
                MyUtils.showToast(mActivity, "取消支付");
                Intent intent = new Intent(mActivity, SoupOrderDetailActivity.class);
                intent.putExtra("orderid", orderid);
                mActivity.startActivity(intent);
                finish();
            }
        }
    }
}
