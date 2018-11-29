package com.emjiayuan.nll.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.activity.ProductActivity;
import com.emjiayuan.nll.activity.ShoppingCartActivity;
import com.emjiayuan.nll.activity.soup.TldzActivity;
import com.emjiayuan.nll.adapter.CategoryAdapter;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.model.BannerItem;
import com.emjiayuan.nll.model.Category;
import com.emjiayuan.nll.utils.GlideImageLoader;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class PurchaseFragment extends BaseLazyFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Banner bannerTop;
    @BindView(R.id.rv_category)
    RecyclerView mRvCategory;
    @BindView(R.id.iv_car)
    ImageView mIvCar;
    @BindView(R.id.shopping_cart_fab)
    FloatingActionButton mShoppingCartFab;

    private String mParam1;
    private String mParam2;
    private CategoryAdapter categoryAdapter;
    private String[] images = new String[]{
            "http://qiniu.emjiayuan.com/upload_file/ems/2018092111329525354",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100815514348272",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018101113731538120",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100911071275167",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018071817991346559",
    };

    private List<Category> categoryList = new ArrayList<>();

    public PurchaseFragment() {
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
    public static PurchaseFragment newInstance(String param1, String param2) {
        PurchaseFragment fragment = new PurchaseFragment();
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
        return R.layout.fragment_purchase;
    }

    @Override
    protected void initData() {
        reqCategory();
    }

    @Override
    protected void initView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                reqCategory();
            }
        });
        mRvCategory.setLayoutManager(new GridLayoutManager(mActivity, 2));
    }

    @Override
    protected void setListener() {
        mIvCar.setOnClickListener(this);
        mShoppingCartFab.setOnClickListener(this);
    }

    public void reqCategory() {
        /*if (!checkNetwork()) {
            stateLayout.changeState(StateFrameLayout.NET_ERROR);
            return;
        }
        if (flags) {
            stateLayout.changeState(StateFrameLayout.LOADING);
        }*/

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
//        formBody.add("provinceid", Global.provinceid);
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("product.getCategoryList", formBody);
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
                MyUtils.e("------获取分类结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }


    private ArrayList mImagelist;
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
                            categoryList = new ArrayList<>();
                            for (int i = 0; i < dataArray.length(); i++) {
                                categoryList.add(gson.fromJson(dataArray.getJSONObject(i).toString(), Category.class));
                            }
                            categoryAdapter = new CategoryAdapter(R.layout.category_item, categoryList);
                            View top = LayoutInflater.from(mActivity).inflate(R.layout.banner_top_purchase, null);
                            bannerTop = top.findViewById(R.id.banner_top);
                            categoryAdapter.addHeaderView(top);
                            categoryAdapter.openLoadAnimation();
                            categoryAdapter.isFirstOnly(false);
                            categoryAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                            mRvCategory.setAdapter(categoryAdapter);
                            categoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(mActivity, ProductActivity.class);
                                    intent.putExtra("categoryid", categoryList.get(position).getId());
                                    intent.putExtra("topimage", categoryList.get(position).getTopimage());
                                    startActivity(intent);
                                }
                            });
                            if (jsonObject.has("bannerimage")){
                                mImagelist = new ArrayList();
                                BannerItem bannerItem=gson.fromJson(jsonObject.getJSONObject("bannerimage").toString(),BannerItem.class);
                                mImagelist.add(bannerItem.getImage());
                                bannerTop.setImageLoader(new GlideImageLoader());
                                bannerTop.setImages(mImagelist);
                                bannerTop.start();
                                bannerTop.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        startActivity(new Intent(mActivity,TldzActivity.class));
                                    }
                                });
                            }
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    refreshLayout.finishRefresh();
                    break;
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_car:
                startActivity(new Intent(mActivity, ShoppingCartActivity.class));
                break;
            case R.id.shopping_cart_fab:
                startActivity(new Intent(mActivity, ShoppingCartActivity.class));
                break;
        }
    }
}
