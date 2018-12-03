package com.emjiayuan.nll.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.ProductAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Product;
import com.emjiayuan.nll.utils.GlideImageLoader;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.json.JSONArray;
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

public class ProductActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.iv_car)
    ImageView mIvCar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.shopping_cart_fab)
    FloatingActionButton mShoppingCartFab;

    private Banner bannerTop;
    private String[] images = new String[]{
            "http://qiniu.emjiayuan.com/upload_file/ems/2018092111329525354",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100815514348272",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018101113731538120",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100911071275167",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018071817991346559",
    };
    private String categoryid = "";
    private String topimage = "";
    private int pageindex = 1;
    private List<Product> mProductList=new ArrayList<>();
    private ArrayList<String> mImagelist;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected void initData() {
        categoryid = getIntent().getStringExtra("categoryid");
        topimage = getIntent().getStringExtra("topimage");
        getProductList(pageindex);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageindex = 1;
                mProductList.clear();
                refreshLayout.setNoMoreData(false);
                getProductList(pageindex);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageindex++;
                getProductList(pageindex);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mShoppingCartFab.setOnClickListener(this);
        mEtSearch.setOnClickListener(this);
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
            case R.id.shopping_cart_fab:
                startActivity(new Intent(mActivity, ShoppingCartActivity.class));
                break;
            case R.id.et_search:
                startActivity(new Intent(mActivity, SearchActivity.class));
                break;
        }
    }
    public void addCar(String id) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("productid", id);
        formBody.add("option", "0");
        formBody.add("num", "1");
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("cart.addOrUpdateCart", formBody);
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
                MyUtils.e("------加入购物车结果------", result);
                Message message = new Message();
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void getProductList(int pageindex) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("categoryid", categoryid);//传递键值对参数
        formBody.add("pageindex", Integer.toString(pageindex));//传递键值对参数
        formBody.add("pagesize", "4");//传递键值对参数

//new call
        Call call = MyOkHttp.GetCall("product.getProductList", formBody);
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
                MyUtils.e("-----产品列表-------", result);
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
//                            stateLayout.changeState(StateFrameLayout.SUCCESS);
                            JSONArray dataArray = new JSONArray(data);
                            for (int i = 0; i < dataArray.length(); i++) {
                                mProductList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), Product.class));
                            }
                            ProductAdapter productAdapter = new ProductAdapter(R.layout.product_item, mProductList);
                            View top = LayoutInflater.from(mActivity).inflate(R.layout.banner_top_product, null);
                            bannerTop = top.findViewById(R.id.banner_top);
                            productAdapter.addHeaderView(top);
                            mRvProduct.setLayoutManager(new LinearLayoutManager(mActivity));
                            mRvProduct.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 1, R.color.gray));
//                            productAdapter.openLoadAnimation();
//                            productAdapter.isFirstOnly(true);
//                            productAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                            mRvProduct.setAdapter(productAdapter);
                            productAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                                    intent.putExtra("productid", mProductList.get(position).getId());
                                    startActivity(intent);
                                }
                            });
                            productAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    addCar(mProductList.get(position).getId());
                                }
                            });
                            bannerTop.setImageLoader(new GlideImageLoader());
                            mImagelist = new ArrayList();
                            mImagelist.add(topimage);
                            bannerTop.setImages(mImagelist);
                            bannerTop.start();
                        } else {
                            if (pageindex!=1){
                                refreshLayout.setNoMoreData(true);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                    break;
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            MyUtils.showToast(mActivity, "已加入购物车！");
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
}