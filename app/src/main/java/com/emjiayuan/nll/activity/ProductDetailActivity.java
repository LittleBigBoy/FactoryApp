package com.emjiayuan.nll.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.labels.LabelsView;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.OrderConfirm;
import com.emjiayuan.nll.model.Product;
import com.emjiayuan.nll.utils.GlideImageLoader;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
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
    @BindView(R.id.shopping_cart_ll)
    LinearLayout mShoppingCarLl;
    @BindView(R.id.add)
    TextView mAdd;
    @BindView(R.id.buy)
    TextView mBuy;
    private String productid;
    private Product mProduct;
    private int num;
    private PopupWindow popupWindow;

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
        mKfLl.setOnClickListener(this);
        mShoucangLl.setOnClickListener(this);
        mShoppingCarLl.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mBuy.setOnClickListener(this);
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
            case R.id.kf_ll:
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
            case R.id.shoucang_ll:
                if (mColImg.isSelected()) {
                    collection("user.removeCollection");
                } else {
                    collection("user.addCollection");
                }
                break;
            case R.id.shopping_cart_ll:
                startActivity(new Intent(mActivity,ShoppingCartActivity.class));
                break;
            case R.id.add:
                showPopWindow(0);
                break;
            case R.id.buy:
                showPopWindow(1);
                break;
        }
    }
    public void addCar(String num) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("productid", mProduct.getId());
        formBody.add("option", "0");
        formBody.add("num", num);
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
    public void collection(String method) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("productid", mProduct.getId());

        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall(method, formBody);
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
                MyUtils.e("------收藏------", result);
                Message message = new Message();
                message.what = 3;
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
        formBody.add("productids", mProduct.getId() + "|" + num);
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

    private OrderConfirm orderComfirm;
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
                            mProduct = gson.fromJson(data,Product.class);
                            setData();
                        } else {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mRefreshLayout.finishRefresh();
                    break;
                case 1://加购物车
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            MyUtils.showToast(mActivity, "已加入购物车！");
                            popupWindow.dismiss();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2://立即购买
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONObject jsonObject1 = new JSONObject(data);
                            orderComfirm = gson.fromJson(jsonObject1.toString(), OrderConfirm.class);
                            Intent intent = new Intent(mActivity, OrderConfirmActivity.class);
                            intent.putExtra("orderComfirm", orderComfirm);
                            startActivity(intent);
                            popupWindow.dismiss();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3://收藏
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            MyUtils.showToast(mActivity, message);
                            if (mColImg.isSelected()) {
                                mColImg.setSelected(false);
                            } else {
                                mColImg.setSelected(true);
                            }
                        } else {
                            MyUtils.showToast(mActivity, message);
                            if (mColImg.isSelected()) {
                                mColImg.setSelected(true);
                            } else {
                                mColImg.setSelected(false);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
    public void showPopWindow(int type) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.choose_num, null);
        final ImageView imageView = view.findViewById(R.id.icon);
        final TextView name = view.findViewById(R.id.name);
        final TextView price = view.findViewById(R.id.price);
        final TextView preprice = view.findViewById(R.id.preprice);
        TextView up = view.findViewById(R.id.up);
        TextView down = view.findViewById(R.id.down);
        final TextView kc = view.findViewById(R.id.kc);
        LabelsView labelsView = view.findViewById(R.id.labels);
        LinearLayout gg_ll = view.findViewById(R.id.gg_ll);
        View gg_line = view.findViewById(R.id.gg_line);
        gg_line.setVisibility(mProduct.getStyle_list() == null ? View.GONE : View.VISIBLE);
        gg_ll.setVisibility(mProduct.getStyle_list() == null ? View.GONE : View.VISIBLE);
        TextView next = view.findViewById(R.id.next);
        final EditText et_count = view.findViewById(R.id.et_count);
        if (mProduct.getStyle_list() != null) {
            labelsView.setLabels(mProduct.getStyle_list(), new LabelsView.LabelTextProvider<Product>() {
                @Override
                public CharSequence getLabelText(TextView label, int position, Product data) {
                    return data.getGuige();
                }
            });
            for (int i = 0; i < mProduct.getStyle_list().size(); i++) {
                if (mProduct.getStyle_list().get(i).getGuige().equals(mProduct.getGuige())) {
                    labelsView.setSelects(i);
                }
            }
            //标签的选中监听
            labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
                @Override
                public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                    //label是被选中的标签，data是标签所对应的数据，isSelect是是否选中，position是标签的位置。
                    Product product_gg = mProduct.getStyle_list().get(position);
                    product_gg.setStyle_list(mProduct.getStyle_list());
                    mProduct = product_gg;
                    Glide.with(mActivity).load(mProduct.getImages()).into(imageView);
                    name.setText(mProduct.getName());
                    price.setText("¥" + mProduct.getPrice());
                    preprice.setText("原价¥" + mProduct.getPreprice());
                    kc.setText("库存" + mProduct.getTotalnum() + "件");
                }
            });
        }
        Glide.with(mActivity).load(mProduct.getImages()).into(imageView);
        name.setText(mProduct.getName());
        price.setText("¥" + mProduct.getPrice());
        preprice.setText("原价¥" + mProduct.getPreprice());
        kc.setText("库存" + mProduct.getTotalnum() + "件");
        preprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        preprice.getPaint().setAntiAlias(true);
        num = 1;
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num < Integer.parseInt(mProduct.getTotalnum())) {
                    et_count.setText("" + (++num));
                } else {
                    et_count.setText("" + num);
                    MyUtils.showToast(mActivity, "不能大于库存！");
                }

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num > 1) {
                    et_count.setText("" + (--num));
                } else {
                    MyUtils.showToast(mActivity, "不能小于1！");
                }
            }
        });
        switch (type) {
            case 0:
                next.setText("加入购物车");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!MyUtils.isFastClick()) {
                            return;
                        }
                        addCar(Integer.toString(num));
                    }
                });
                break;
            case 1:
                next.setText("立即购买");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!MyUtils.isFastClick()) {
                            return;
                        }
                        confirmOrder();
                    }
                });
                break;
        }

        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }
}