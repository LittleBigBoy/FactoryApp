package com.emjiayuan.nll.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Product;
import com.emjiayuan.nll.utils.GlideImageLoader;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.old_price)
    TextView mOldPrice;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.kc)
    TextView mKc;
    @BindView(R.id.ys)
    TextView mYs;
    @BindView(R.id.cd)
    TextView mCd;
    @BindView(R.id.bottom_ll)
    LinearLayout mBottomLl;
    @BindView(R.id.tv_detail)
    TextView mTvDetail;
    @BindView(R.id.detail_ll)
    LinearLayout mDetailLl;
    @BindView(R.id.tv_pl)
    TextView mTvPl;
    @BindView(R.id.pl_ll)
    LinearLayout mPlLl;
    @BindView(R.id.detail_icon)
    ImageView mDetailIcon;
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.kf_ll)
    LinearLayout mKfLl;
    @BindView(R.id.col_img)
    ImageView mColImg;
    @BindView(R.id.shoucang_ll)
    LinearLayout mShoucangLl;
    @BindView(R.id.shopping_car_ll)
    LinearLayout mShoppingCarLl;
    @BindView(R.id.add)
    TextView mAdd;
    @BindView(R.id.buy)
    TextView mBuy;
    private String productid;
    private Product mProduct;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("商品详情");
        productid=getIntent().getStringExtra("productid");
        getProductDetail();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getProductDetail();
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
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
        }
    }

    public void getProductDetail() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("productid", productid);
        if (Global.loginResult != null) {
            formBody.add("userid", Global.loginResult.getId());
        }
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("product.getProductDetail", formBody);
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
                MyUtils.e("-----产品详情-------", result);
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
                            mProduct = gson.fromJson(data,Product.class);
                            setData();
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mRefreshLayout.finishRefresh();
                    break;
            }
        }
    };

    public void setData() {
        mName.setText(mProduct.getName());
        mPrice.setText("￥"+mProduct.getPrice());
        mOldPrice.setText("￥"+mProduct.getPreprice());
        mOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mOldPrice.getPaint().setAntiAlias(true);
        mKc.setText("库存："+mProduct.getTotalnum());
        mYs.setText("已售："+mProduct.getSellnum());
        mCd.setText("产地："+mProduct.getSource());
        mBanner.setImageLoader(new GlideImageLoader());
        ArrayList<String> imagelist = new ArrayList();
        if (!"".equals(mProduct.getImages())){
            imagelist.add(mProduct.getImages());
        }
        if (!"".equals(mProduct.getImages2())){
            imagelist.add(mProduct.getImages2());
        }
        if (!"".equals(mProduct.getImages3())){
            imagelist.add(mProduct.getImages3());
        }
        if (!"".equals(mProduct.getImages4())){
            imagelist.add(mProduct.getImages4());
        }
        mBanner.setImages(imagelist);
        mBanner.start();
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\t\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "\n" +
                "\t<title>detail</title>\n" +
                "\n" +
                "\t<style>body{border:0;padding:0;margin:0;}img{border:0;display:block;vertical-align: middle;padding:0;margin:0;}p{border:0;padding:0;margin:0;}div{border:0;padding:0;margin:0;}</style>\n" +
                "</head>"
                + "<body>"
                + mProduct.getContent() + "</body>" + "</html>";

        mWebview.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(new WebChromeClient());
    }
}