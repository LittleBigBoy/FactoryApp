package com.emjiayuan.nll.activity.address;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.AddressAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.AddressEvent;
import com.emjiayuan.nll.model.Address;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class AddressActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.rv)
    RecyclerView mRv;
    private List<Address> mAddressList=new ArrayList<>();
    private AddressAdapter mAddressAdapter;
    private boolean flag;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initData() {
        flag = getIntent().getBooleanExtra("flag", false);
        EventBus.getDefault().register(this);
        mTvTitle.setText("地址管理");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("添加地址");

        mAddressAdapter = new AddressAdapter(R.layout.address_item,mAddressList);
        mRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mRv.addItemDecoration(new RecyclerViewDivider(mActivity,LinearLayoutManager.HORIZONTAL,20,Color.parseColor("#EEEEEE")));
        mAddressAdapter.setEmptyView(getEmptyView());
        mRv.setAdapter(mAddressAdapter);
        mAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.default_ll:
                        setDefault(mAddressList.get(position).getId());
                        break;
                    case R.id.edit_ll:
                        Intent intent=new Intent(mActivity,ModifyAddressActivity.class);
                        intent.putExtra("address",mAddressList.get(position));
                        startActivity(intent);
                        break;
                    case R.id.delete_ll:
                        delete(mAddressList.get(position).getId());
                        break;
                }
            }
        });
        mAddressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!MyUtils.isFastClick()){
                    return;
                }
                if (flag) {
                    Intent intent = new Intent();
                    intent.putExtra("address", mAddressList.get(position));
                    setResult(100, intent);
                    finish();
                }
            }
        });
        request();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(AddressEvent event) {
        request();
    }

    public void request() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall("userAddress.getAddressList", formBody);
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
                Log.d("------地址列表结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void delete(String addresssid){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("addressid", addresssid);//传递键值对参数
        formBody.add("userid", Global.loginResult.getId());//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall("userAddress.removeAddress",formBody);
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
                Log.d("------删除地址结果------",result);
                Message message=new Message();
                message.what=1;
                Bundle bundle=new Bundle();
                bundle.putString("result",result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void setDefault(String addresssid){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("addressid", addresssid);//传递键值对参数
        formBody.add("userid", Global.loginResult.getId());//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall("userAddress.setDefaultAddress",formBody);
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
                Log.d("------设置默认地址结果------",result);
                Message message=new Message();
                message.what=1;
                Bundle bundle=new Bundle();
                bundle.putString("result",result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.icon_back:
                setResult(100, new Intent());
                finish();
                break;
            case R.id.tv_save:
                startActivity(new Intent(this, AddAddressActivity.class));
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                        if ("200".equals(code)) {
                            mAddressList = new ArrayList<>();
                            JSONArray jsonArray = new JSONArray(data);
                            Gson gson = new Gson();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                mAddressList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Address.class));
                            }
                            mAddressAdapter.setNewData(mAddressList);
                        }else{
                            mAddressList=new ArrayList<>();
                            mAddressAdapter.setNewData(mAddressList);
//                            MyUtils.showToast(mActivity,message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        JSONObject jsonObject=new JSONObject(result);
                        String code=jsonObject.getString("code");
                        String message=jsonObject.getString("message");
                        String data=jsonObject.getString("data");
                        if ("200".equals(code)){
                            request();
                        }else {
                            MyUtils.showToast(mActivity,message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
}
