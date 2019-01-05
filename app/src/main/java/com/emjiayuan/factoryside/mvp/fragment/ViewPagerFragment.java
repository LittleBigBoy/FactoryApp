package com.emjiayuan.factoryside.mvp.fragment;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.factoryside.mvp.activity.JudgeActivity;
import com.emjiayuan.factoryside.mvp.activity.LogisticsDetailActivity;
import com.emjiayuan.factoryside.mvp.activity.OrderDetailActivity;
import com.emjiayuan.factoryside.mvp.activity.soup.SoupOrderDetailActivity;
import com.emjiayuan.nll.Constants;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.OrderAdapter;
import com.emjiayuan.nll.adapter.SoupOrderAdapter;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.event.UpdateEvent;
import com.emjiayuan.nll.event.WXpaySuccessEvent;
import com.emjiayuan.nll.model.Order;
import com.emjiayuan.nll.model.PayResult;
import com.emjiayuan.nll.model.SoupOrder;
import com.emjiayuan.nll.model.WXpayInfo;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.google.gson.Gson;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class ViewPagerFragment extends BaseLazyFragment {
    private static final String ARG_PARAM1 = "param1";//""全部 1待付款 2待发货 3待收货
    private static final String ARG_PARAM2 = "param2";//0普通订单 1汤料订单
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    private String mParam1;
    private String mParam2;

    private ArrayList<Order> mOrderArrayList=new ArrayList<>();
    private ArrayList<SoupOrder> mSoupOrderArrayList=new ArrayList<>();
    private OrderAdapter mOrderAdapter;
    private SoupOrderAdapter mSoupOrderAdapter;
    private int pageindex = 1;
    private Order mItem;
    private PopupWindow mPopupWindow;
    private SoupOrder mSoupOrder;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPagerFragment newInstance(String param1, String param2) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_order_page;
    }
    @Override
    protected void initData() {
        if ("0".equals(mParam2)){
            mOrderAdapter = new OrderAdapter(R.layout.order_item, mOrderArrayList);
            mOrderAdapter.setEmptyView(getEmptyView());
//            mOrderAdapter.openLoadAnimation();
//            mOrderAdapter.isFirstOnly(false);
//            mOrderAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRvOrder.setAdapter(mOrderAdapter);
            mOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                    intent.putExtra("orderid", mOrderArrayList.get(position).getId());
                    startActivity(intent);
                }
            });
            mOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    mItem = mOrderArrayList.get(position);
                    switch (view.getId()){
                        case R.id.btn1:
                            switch (mItem.getOrdertype()){
                                /*1.取消订单3.查看物流*/
                                case "1":
                                    updateOrder("0",mItem.getId(),"order.updateOrderStatus");
                                    break;
                                case "3":
                                    Intent intent=new Intent(mActivity, LogisticsDetailActivity.class);
                                    intent.putExtra("postid", mItem.getExpressno());
                                    intent.putExtra("postcom", mItem.getExpresscom());
                                    intent.putExtra("order_no", mItem.getOrder_no());
                                    intent.putExtra("date", mItem.getCreatedate());
                                    intent.putExtra("address", mItem.getAddress());
                                    startActivity(intent);
                                    break;
                            }
                            break;
                        case R.id.btn2:
                            switch (mItem.getOrdertype()){
                                /*1.去付款2.提醒发货3.确认收货4.去评价*/
                                case "1":
                                    showPopupWindow("COMMON",mItem.getId());
                                    break;
                                case "2":
                                    String title = "伊穆家园客服";
                                    /**
                                     * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
                                     * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
                                     * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                                     */
                                    ConsultSource source = new ConsultSource("app", "app", "custom information string");
                                    /**
                                     * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                                     * 如果返回为false，该接口不会有任何动作
                                     *
                                     * @param context 上下文
                                     * @param title   聊天窗口的标题
                                     * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                                     */
                                    Unicorn.openServiceActivity(mActivity, title, source);
                                    break;
                                case "3":
                                    updateOrder("1",mItem.getId(),"order.updateOrderStatus");
                                    break;
                                case "4":
                                    Intent intent=new Intent(mActivity, JudgeActivity.class);
                                    intent.putExtra("order",mItem);
                                    startActivity(intent);
                                    break;
                            }
                            break;
                    }
                }
            });
            getOrderList();
            mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    pageindex = 1;
                    mOrderArrayList.clear();
                    mRefreshLayout.setNoMoreData(false);
                    getOrderList();
                }
            });
            mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pageindex++;
                    getOrderList();
                }
            });
        }else{
            mSoupOrderAdapter = new SoupOrderAdapter(R.layout.order_item, mSoupOrderArrayList);
            mSoupOrderAdapter.setEmptyView(getEmptyView());
//            mSoupOrderAdapter.openLoadAnimation();
//            mSoupOrderAdapter.isFirstOnly(false);
//            mSoupOrderAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mRvOrder.setAdapter(mSoupOrderAdapter);
            mSoupOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(mActivity, SoupOrderDetailActivity.class);
                    intent.putExtra("orderid", mSoupOrderArrayList.get(position).getId());
                    startActivity(intent);
                }
            });
            mSoupOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    mSoupOrder = mSoupOrderArrayList.get(position);
                    switch (view.getId()){
                        case R.id.btn1:
                            switch (mSoupOrder.getOrdertype()){
                                /*1.取消订单3.查看物流*/
                                case "1":
                                    updateOrder("0",mSoupOrder.getId(),"soupOrder.updateSoupOrderStatus");
                                    break;
                                case "3":
                                    Intent intent=new Intent(mActivity, LogisticsDetailActivity.class);
                                    intent.putExtra("postid", mSoupOrder.getExpressno());
                                    intent.putExtra("postcom", mSoupOrder.getExpresscom());
                                    intent.putExtra("order_no", mSoupOrder.getOrder_no());
                                    intent.putExtra("date", mSoupOrder.getCreatedate());
                                    intent.putExtra("address", mSoupOrder.getAddress());
                                    startActivity(intent);
                                    break;
                            }
                            break;
                        case R.id.btn2:
                            switch (mSoupOrder.getOrdertype()){
                                /*1.去付款2.提醒发货3.确认收货*/
                                case "1":
                                    showPopupWindow("SOUP",mSoupOrder.getId());
                                    break;
                                case "2":
                                    String title = "伊穆家园客服";
                                    /**
                                     * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
                                     * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
                                     * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                                     */
                                    ConsultSource source = new ConsultSource("app", "app", "custom information string");
                                    /**
                                     * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                                     * 如果返回为false，该接口不会有任何动作
                                     *
                                     * @param context 上下文
                                     * @param title   聊天窗口的标题
                                     * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                                     */
                                    Unicorn.openServiceActivity(mActivity, title, source);
                                    break;
                                case "3":
                                    updateOrder("1",mSoupOrder.getId(),"soupOrder.updateSoupOrderStatus");
                                    break;
                            }
                            break;
//                        case R.id.up_down:
//                            View view1=adapter.getViewByPosition(R.id.rv_goods_in,position);
//                            view1.setVisibility(view1.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
//                            break;
                    }
                }
            });
            getSoupOrder();
            mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    pageindex = 1;
                    mSoupOrderArrayList.clear();
                    mRefreshLayout.setNoMoreData(false);
                    getSoupOrder();
                }
            });
            mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    pageindex++;
                    getSoupOrder();
                }
            });
        }

    }


    @Override
    protected void initView() {
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 20, Color.parseColor("#EEEEEE")));
    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }
    /*普通订单*/
    public void getOrderList() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("status", mParam1);
        formBody.add("compute", "");
        formBody.add("isProduct", "1");
        formBody.add("type", "0");
        formBody.add("pageindex", Integer.toString(pageindex));
        formBody.add("pagesize", "1");
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("order.getOrderList", formBody);
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
                MyUtils.e("------获取普通订单列表结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }
    /*汤料订单*/
    public void getSoupOrder() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("type", mParam1);
        formBody.add("compute", "");
        formBody.add("isProduct", "1");
        formBody.add("pageindex", Integer.toString(pageindex));
        formBody.add("pagesize", "1");
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("soupOrder.getSoupOrderList", formBody);
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
                MyUtils.e("------获取汤料订单列表结果------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }
    /*取消订单or确认收货*/
    public void updateOrder(String option,String orderid,String method){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("orderid", orderid);//传递键值对参数
        /*option  0.取消订单1.确认收货*/
        formBody.add("option", option);//传递键值对参数
        formBody.add("userid", Global.loginResult.getId());//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall(method,formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------",e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                Log.d("------取消订单或确认收货结果------",result);
                Message message=new Message();
                message.what=2;
                Bundle bundle=new Bundle();
                bundle.putString("result",result);
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
                            JSONArray dataArray = new JSONArray(data);
                            for (int i = 0; i < dataArray.length(); i++) {
                                mOrderArrayList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), Order.class));
                            }
                            mOrderAdapter.setNewData(mOrderArrayList);
                        } else {
                            mOrderAdapter.setNewData(mOrderArrayList);
                            if (pageindex!=1){
                                mRefreshLayout.setNoMoreData(true);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.finishLoadMore();
                    break;
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
//                            stateLayout.changeState(StateFrameLayout.SUCCESS);
                            JSONArray dataArray = new JSONArray(data);
                            for (int i = 0; i < dataArray.length(); i++) {
                                mSoupOrderArrayList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), SoupOrder.class));
                            }
                            mSoupOrderAdapter.setNewData(mSoupOrderArrayList);
                        } else {
                            mSoupOrderAdapter.setNewData(mSoupOrderArrayList);
                            if (pageindex!=1){
                                mRefreshLayout.setNoMoreData(true);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.finishLoadMore();
                    break;
                case 2:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        MyUtils.showToast(mActivity,message);
                        if ("200".equals(code)) {
                            EventBus.getDefault().post(new UpdateEvent(""));
                        } else {

                        }
                    } catch (Exception e) {
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
                            MyUtils.showToast(mActivity,message);
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
                        EventBus.getDefault().post(new UpdateEvent(""));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        MyUtils.showToast(mActivity, "支付失败");
                        mPopupWindow.dismiss();
                    }
                    break;
                case Constants.WXPAY://微信支付
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            WXpayInfo payInfo = gson.fromJson(data,WXpayInfo.class);
                            WXPay(payInfo);
                        } else{
                            MyUtils.showToast(mActivity,message);
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
    public void showPopupWindow(final String paytype, final String orderid) {
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
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        cz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()){
                    return;
                }
                if (alipay_check.isChecked() == true) {
                    alipayrequest(paytype,orderid);
                } else if (weixin_check.isChecked() == true) {
                    WXpayrequest(paytype,orderid);
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
                PayTask alipay = new PayTask((Activity) mActivity);
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
        IWXAPI api = WXAPIFactory.createWXAPI(mActivity, Constants.APP_ID);
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
    public void alipayrequest(String paytype,String orderid) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", paytype);//传递键值对参数
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
    public void WXpayrequest(String paytype,String orderid) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("paytype", paytype);//传递键值对参数
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(WXpaySuccessEvent event) {
        BaseResp resp=event.getResp();
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {   // 0 成功  展示成功页面
                MyUtils.showToast(mActivity, "支付成功");
                mPopupWindow.dismiss();
                EventBus.getDefault().post(new UpdateEvent(""));
            } else if (resp.errCode == -1) {  // -1 错误  可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
                MyUtils.showToast(mActivity, "支付出错");
                mPopupWindow.dismiss();
            } else if (resp.errCode == -2) {  // -2 用户取消    无需处理。发生场景：用户不支付了，点击取消，返回APP。
                MyUtils.showToast(mActivity, "取消支付");
                mPopupWindow.dismiss();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UpdateEvent event) {
        pageindex=1;
        if ("0".equals(mParam2)){
            mOrderArrayList.clear();
            getOrderList();
        }else{
            mSoupOrderArrayList.clear();
            getSoupOrder();
        }
    }
}
