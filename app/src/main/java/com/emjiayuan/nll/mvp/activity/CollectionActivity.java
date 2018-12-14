package com.emjiayuan.nll.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.CollectionAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.ColUpdateEvent;
import com.emjiayuan.nll.event.UpdateEvent;
import com.emjiayuan.nll.interfaces.AllCheckListener;
import com.emjiayuan.nll.model.Product;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
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

public class CollectionActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


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
    @BindView(R.id.gv_cellection)
    GridView mGvCellection;
    @BindView(R.id.checkAll)
    CheckBox mCheckAll;
    @BindView(R.id.check)
    TextView mCheck;
    @BindView(R.id.delete)
    TextView mDelete;
    @BindView(R.id.manage_ll)
    LinearLayout mManageLl;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    private CollectionAdapter adapter;
    private ArrayList<Product> list = new ArrayList<>();
    private boolean mIsFromItem = false;
    private ArrayList<Product> selectList = new ArrayList<>();
    private String productid = "";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("管理");
        mTvSave.setOnClickListener(this);
        mTvTitle.setText("我的收藏");
        getCollectionList();

    }

    public void getCollectionList() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());

        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("user.getCollectionList", formBody);
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
                MyUtils.e("------收藏列表------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void delete() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("productid", productid.substring(0, productid.lastIndexOf(",")));

        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("user.removeCollection", formBody);
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
                MyUtils.e("------删除收藏列表------", result);
                Message message = new Message();
                message.what = 1;
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
                        list.clear();
                        if ("200".equals(code)) {
                            JSONArray jsonArray = new JSONArray(data);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                list.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Product.class));
                            }
                            adapter = new CollectionAdapter(mActivity, list, new AllCheckListener() {
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
                            mGvCellection.setEmptyView(mEmptyView);
                            mGvCellection.setAdapter(adapter);
                        } else {
                            mGvCellection.setEmptyView(mEmptyView);
                            mGvCellection.setAdapter(new CollectionAdapter(mActivity, new ArrayList<Product>(), new AllCheckListener() {
                                @Override
                                public void onCheckedChanged(boolean b) {

                                }
                            }));
                        }
                    } catch (Exception e) {
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
                            getCollectionList();
                            mManageLl.setVisibility(View.GONE);
                            mTvSave.setText("管理");
                            if (adapter != null) {
                                adapter.setFlag(false);
                                adapter.notifyDataSetChanged();
                            }
                            selectList.clear();
                            mCheckAll.setChecked(false);
                            MyUtils.showToast(mActivity, message);
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UpdateEvent event) {
        getCollectionList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ColUpdateEvent event) {
        setSelectList(event.getProductList());
    }

    public void setSelectList(List<Product> productList) {
        selectList.clear();
        for (Product product : productList) {
            if (product.isChecked()) {
                selectList.add(product);
            }
        }
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mGvCellection.setOnItemClickListener(this);
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
                for (Product carBean : list) {
                    carBean.setChecked(b);
                }
                Map<Integer, Boolean> map = new HashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    if (b) {
                        map.put(i, true);
                    } else {
                        map.remove(i);
                    }
                }
                if (adapter != null) {
                    adapter.setMap(map);
                    //刷新listview
                    adapter.notifyDataSetChanged();
                }

            }
        });
        mDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.delete:
                if (selectList.size() == 0) {
                    MyUtils.showToast(mActivity, "请选择要删除的收藏！");
                    return;
                }
                for (int i = 0; i < selectList.size(); i++) {
                    productid += selectList.get(i).getId() + ",";
                }
                delete();
                break;

            case R.id.tv_save:
                if ("管理".equals(mTvSave.getText().toString())) {
                    mManageLl.setVisibility(View.VISIBLE);
                    mTvSave.setText("完成");
                    if (adapter != null) {
                        adapter.setFlag(true);
                        adapter.notifyDataSetChanged();
                    }

                } else {
                    mManageLl.setVisibility(View.GONE);
                    mTvSave.setText("管理");
                    if (adapter != null) {
                        adapter.setFlag(false);
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        Intent intent = new Intent(CollectionActivity.this, ProductDetailActivity.class);
        intent.putExtra("productid", list.get(i).getId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
