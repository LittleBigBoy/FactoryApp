package com.emjiayuan.nll.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.ShoppingCartAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.ShoppingCartUpdateEvent;
import com.emjiayuan.nll.interfaces.AllCheckListener;
import com.emjiayuan.nll.model.OrderConfirm;
import com.emjiayuan.nll.model.ShoppingCart;
import com.emjiayuan.nll.utils.DensityUtil;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.lv_goods)
    SwipeMenuListView mLvGoods;
    @BindView(R.id.checkAll)
    CheckBox mCheckAll;
    @BindView(R.id.check)
    TextView mCheck;
    @BindView(R.id.check_ll)
    LinearLayout mCheckLl;
    @BindView(R.id.pay)
    TextView mPay;
    @BindView(R.id.order)
    TextView mOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    private ShoppingCartAdapter adapter;
    private ArrayList<ShoppingCart> shoppingCartList;
    public List<ShoppingCart> selectList = new ArrayList<>();
    //监听来源
    public boolean mIsFromItem = false;
    public Double total = 0.0;
    private String cartids;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mTvTitle.setText("购物车");
        mTvTitle.setVisibility(View.VISIBLE);
        getCartList();
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getCartList();
            }
        });
    }

    @Override
    protected void initData() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        mActivity);
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
//                openItem.setBackground(Color.RED);
                // set item width
                openItem.setWidth(DensityUtil.dp2px(mActivity, 90));
                // set item title
                openItem.setTitle("删除");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        mActivity);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(DensityUtil.dp2px(mActivity, 90));
                // set a icon
                deleteItem.setIcon(R.drawable.delete);
                // add to menu
//                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        mLvGoods.setMenuCreator(creator);
        mLvGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productid", shoppingCartList.get(i).getProductid());
                startActivity(intent);
            }
        });
        mLvGoods.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
//                        Global.list.remove(position);
//                        adapter.notifyDataSetChanged();
                        deleteCar(shoppingCartList.get(position).getCartid(), position);
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        mLvGoods.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mCheckLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckAll.isChecked()) {
                    mCheckAll.setChecked(false);
                } else {
                    mCheckAll.setChecked(true);
                }
            }
        });
        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //当监听来源为点击item改变maincbk状态时不在监听改变，防止死循环
                if (mIsFromItem) {
                    mIsFromItem = false;
                    Log.e("mainCheckBox", "此时我不可以触发");
                    return;
                }

                //改变数据
                for (ShoppingCart ShoppingCart : shoppingCartList) {
                    ShoppingCart.setChecked(b);
                }
                Map<Integer, Boolean> map = new HashMap<>();
                for (int i = 0; i < shoppingCartList.size(); i++) {
                    if (b) {
                        map.put(i, true);
                    } else {
                        map.remove(i);
                    }
                }
                adapter.setMap(map);
                total = 0.00;
                DecimalFormat df = new DecimalFormat("#0.00");
                if (b) {
                    for (ShoppingCart ShoppingCart : shoppingCartList) {
                        total = total + Integer.parseInt(ShoppingCart.getCartnum()) * Double.parseDouble(ShoppingCart.getPrice());
                    }
                    mPay.setText("¥" + df.format(total));
                } else {
                    mPay.setText("¥" + total);
                }
                //刷新listview
                adapter.notifyDataSetChanged();
            }
        });
        mOrder.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ShoppingCartUpdateEvent event) {
        setPay(event.getshoppingCartList());
    }

    public void setPay(List<ShoppingCart> carBeanList) {
        total = 0.00;
        selectList.clear();
        cartids = "";
        DecimalFormat df = new DecimalFormat("#0.00");
        for (ShoppingCart ShoppingCart : carBeanList) {
            if (ShoppingCart.isChecked()) {
                cartids += ShoppingCart.getCartid() + ",";
                selectList.add(ShoppingCart);
                total = total + Integer.parseInt(ShoppingCart.getCartnum()) * Double.parseDouble(ShoppingCart.getPrice());
            }
        }
        mPay.setText("¥" + df.format(total));
    }

    @Override
    public void onClick(View view) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.order:
                if (selectList.size() == 0) {
                    MyUtils.showToast(mActivity, "请选择商品！");
                    return;
                }
                confirmOrder();
                break;
        }
    }

    public void getProductCartList() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("cart.getProductCartList", formBody);
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
                MyUtils.e("------获取推荐产品结果------", result);
                Message message = new Message();
                message.what = 3;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void getCartList() {
        mCheckAll.setChecked(false);
        selectList.clear();
        total = 0.0;
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体

        formBody.add("userid", Global.loginResult.getId());
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("cart.getCartList", formBody);
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
                MyUtils.e("------获取购物车结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void confirmOrder() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("cartids", cartids.substring(0, cartids.lastIndexOf(",")));
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
                message.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void deleteCar(String cartid, final int position) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        if (Global.loginResult == null) {
            startActivity(new Intent(mActivity, LoginActivity.class));
            return;
        }
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("cartid", cartid);
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("cart.removeCart", formBody);
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
                MyUtils.e("------删除购物车结果------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                bundle.putInt("position", position);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    private OrderConfirm orderConfirm;
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            int position = bundle.getInt("position");
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
                            shoppingCartList = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                shoppingCartList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), ShoppingCart.class));
                            }
                            adapter = new ShoppingCartAdapter(mActivity, shoppingCartList, mLvGoods, new AllCheckListener() {
                                @Override
                                public void onCheckedChanged(boolean b) {
                                    //根据不同的情况对maincheckbox做处理
                                    if (!b && !mCheckAll.isChecked()) {
                                        return;
                                    } else if (!b && mCheckAll.isChecked()) {
                                        mIsFromItem = true;
                                        mCheckAll.setChecked(false);
                                    } else if (b) {
                                        mIsFromItem = false;
                                        mCheckAll.setChecked(true);
                                    }
                                }
                            });
                            mLvGoods.setEmptyView(mEmptyView);
                            mLvGoods.setAdapter(adapter);
                        } else {
                            mLvGoods.setEmptyView(mEmptyView);
                            mLvGoods.setAdapter(new ShoppingCartAdapter(mActivity, new ArrayList<ShoppingCart>(), mLvGoods, new AllCheckListener() {
                                @Override
                                public void onCheckedChanged(boolean b) {
                                }
                            }));
                            MyUtils.showToast(mActivity, message);
                        }
                        mRefreshLayout.finishRefresh();
                    } catch (Exception e) {
                        mRefreshLayout.finishRefresh();
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
                            MyUtils.showToast(mActivity, message);
                            getCartList();
                        } else {

                        }
                    } catch (Exception e) {
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
                            JSONObject jsonObject1 = new JSONObject(data);
                            orderConfirm = gson.fromJson(jsonObject1.toString(), OrderConfirm.class);
                            Intent intent = new Intent(mActivity, OrderConfirmActivity.class);
                            intent.putExtra("orderConfirm", orderConfirm);
                            startActivity(intent);
                        } else {
                            MyUtils.showToast(mActivity, result);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            getCartList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
