package com.emjiayuan.nll.mvp.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.TurnoverAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Turnover;
import com.emjiayuan.nll.model.UserInfo;
import com.emjiayuan.nll.utils.AddressPickTask;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class ShopInfoActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.tv_addr)
    TextView mTvAddr;
    @BindView(R.id.et_addr_detail)
    EditText mEtAddrDetail;
    @BindView(R.id.tv_turnover)
    TextView mTvTurnover;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    private String mName;
    private String mAddrDetail;
    private String mAddr;
    private String mTurnover;
    private String mProvinceName;
    private String mCityName;
    private String mDistrictName;
    private List<Turnover> mTurnoverList;
    private String mTurnoverId="";
    private PopupWindow mPopupWindow;
    private UserInfo mUserInfo;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_information;
    }

    @Override
    protected void initData() {
        mUserInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");
        if (mUserInfo != null) {
            mProvinceName=mUserInfo.getShop_address_province();
            mCityName=mUserInfo.getShop_address_city();
            mDistrictName=mUserInfo.getShop_address_area();
            mEtName.setText(mUserInfo.getShop_name());
            mTvAddr.setText(mProvinceName+" "+mCityName+" "+mDistrictName);
            mEtAddrDetail.setText(mUserInfo.getShop_address_detail());
            mTurnoverId=mUserInfo.getShop_sale();
            mTvTurnover.setText(mUserInfo.getShop_sale_str());
        }
    }

    @Override
    protected void initView() {

    }

    public void showAddressPicker() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                MyUtils.showToast(mActivity,"数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    mAddr=province.getAreaName() +" "+ city.getAreaName();
                } else {
                    mAddr=province.getAreaName() +" "+ city.getAreaName() +" "+ county.getAreaName();
                }
                mTvAddr.setText(mAddr);
                mProvinceName =province.getAreaName();
                mCityName =city.getAreaName();
                mDistrictName =county.getAreaName();
            }
        });
        if (mProvinceName !=null){
            task.execute(mProvinceName, mCityName, mDistrictName);
        }else{
            task.execute("浙江", "宁波市", "镇海区");
        }

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);
        mTvTurnover.setOnClickListener(this);
        mTvAddr.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
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
            case R.id.icon_search:
                finish();
                break;
            case R.id.tv_turnover:
                getShopBusinessList();
                break;
            case R.id.tv_addr:
                showAddressPicker();
                break;
            case R.id.btn_next:
                mName = mEtName.getText().toString().trim();
                mAddrDetail = mEtAddrDetail.getText().toString().trim();
                mAddr = mTvAddr.getText().toString().trim();
                mTurnover = mTvTurnover.getText().toString().trim();
                if ("".equals(mName)){
                    MyUtils.showToast(mActivity,"请输入您的店铺名称！");
                    return;
                }
                if ("".equals(mAddr)){
                    MyUtils.showToast(mActivity,"请选择您的店铺地址！");
                    return;
                }
                if ("".equals(mAddrDetail)){
                    MyUtils.showToast(mActivity,"请输入您的店铺详细地址！");
                    return;
                }
                if ("".equals(mTurnover)){
                    MyUtils.showToast(mActivity,"请选择您的营业额！");
                    return;
                }
                addOrUpdateUserShop();
                break;
        }
    }

    public void addOrUpdateUserShop(){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid",Global.loginResult.getId());
        formBody.add("shopname",mName);
        formBody.add("addressprovince",mProvinceName);
        formBody.add("addresscity",mCityName);
        formBody.add("addressarea",mDistrictName);
        formBody.add("addressdetail",mAddrDetail);
        formBody.add("shopsale",mTurnoverId);
        formBody.add("status","1");
//new call
        Call call = MyOkHttp.GetCall("User.addOrUpdateUserShop", formBody);
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
                MyUtils.e("-----提交店铺信息-------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }
    public void getShopBusinessList(){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
//new call
        Call call = MyOkHttp.GetCall("User.getShopBusinessList", formBody);
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
                MyUtils.e("-----营业额列表-------", result);
                Message message = new Message();
                message.what = 0;
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
                            JSONArray jsonArray = new JSONArray(data);
                            mTurnoverList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                mTurnoverList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(),Turnover.class));
                            }
                            showPopupWindow();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
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
                            startActivity(new Intent(mActivity,VerifyActivity.class));
                            finish();
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
        View popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.pop_turnover, null);
        RecyclerView rv = popupWindow_view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.addItemDecoration(new RecyclerViewDivider(mActivity,LinearLayoutManager.HORIZONTAL));
        TurnoverAdapter turnoverAdapter=new TurnoverAdapter(R.layout.turnover_item,mTurnoverList);
        rv.setAdapter(turnoverAdapter);
        turnoverAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTurnoverId = mTurnoverList.get(position).getId();
                mTvTurnover.setText(mTurnoverList.get(position).getName());
                mPopupWindow.dismiss();
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
}