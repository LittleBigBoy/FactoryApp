package com.emjiayuan.nll.activity.soup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.SoupUpdateEvent;
import com.emjiayuan.nll.model.Soup;
import com.emjiayuan.nll.model.SoupCategory;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class TldzActivity extends BaseActivity {

    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.lv_content)
    ListView lvContent;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tv_soupwiki)
    TextView tv_soupwiki;
    @BindView(R.id.up_soup)
    ImageView upSoup;
    @BindView(R.id.soupwiki_ll)
    LinearLayout soupwikiLl;
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
    //    private SoupAdapter adapter;
    private SoupRightAdapter adapter;
    private SoupCheckAdapter checkAdapter;
    private SoupCategoryAdapter categoryAdapter;
    private List<SoupCategory> soupCategoryList = new ArrayList<>();
    private List<Soup> list = new ArrayList<>();
    private List<Soup> okList = new ArrayList<>();
    private List<Soup> selectedList = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private TextView countin;
    private String mass = "斤";
    private String val = "500";
    private double total = 0;
    private double num = 0;
    //记录滑动的ListView 滑动的位置   
    private int scrollPosition = -1;
    DecimalFormat df = new DecimalFormat("######0.00");


    @Override
    protected int setLayoutId() {
        return R.layout.activity_soup;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        //部分文字改变颜色
//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan greenSpan = new ForegroundColorSpan(Color.parseColor("#2BB835"));
        count.setText("已选" + selectedList.size() + "类");
//这里注意一定要先给textview赋值

        SpannableStringBuilder builder = new SpannableStringBuilder(count.getText().toString());
//为不同位置字符串设置不同颜色
//   Spanned.SPAN_INCLUSIVE_EXCLUSIVE 从起始下标到终了下标，包括起始下标
//   Spanned.SPAN_INCLUSIVE_INCLUSIVE 从起始下标到终了下标，同时包括起始下标和终了下标
//   Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 从起始下标到终了下标，但都不包括起始下标和终了下标
//   Spanned.SPAN_EXCLUSIVE_INCLUSIVE 从起始下标到终了下标，包括终了下标
        builder.setSpan(greenSpan, 2, count.getText().toString().length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//最后为textview赋值
        count.setText(builder);
        mTvTitle.setText("汤料定制");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("我的料单");
        tv_soupwiki.setText("香料\n百科");
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
        soupwikiLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                startActivity(new Intent(mActivity, SoupwikiActivity.class));
            }
        });
        getSoupCategoryList();
//        getSoupProductList();
//        gvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (list.get(i).isCheck()) {
//                    list.get(i).setCheck(false);
//                    selectedList.remove(list.get(i));
//                } else {
//                    list.get(i).setCheck(true);
//                    selectedList.add(list.get(i));
//                }
//                adapter.notifyDataSetChanged();
//                count.setText("已选"+selectedList.size()+"类");
//            }
//        });
    }

    public void getSoupProductList() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        Log.d("------参数------", formBody.build().toString());
        formBody.add("pageindex", "1");
        formBody.add("pagesize", "100");
//new call
        Call call = MyOkHttp.GetCall("soupProduct.getSoupProductList", formBody);
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
                MyUtils.e("------获取汤料列表结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void getSoupCategoryList() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        Log.d("------参数------", formBody.build().toString());
//        formBody.add("pageindex", "1");
//        formBody.add("pagesize", "100");
//new call
        Call call = MyOkHttp.GetCall("soupProduct.getSoupCategoryList", formBody);
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
                MyUtils.e("------获取汤料分类列表结果------", result);
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
//                case 0:
//                    try {
//                        JSONObject jsonObject = new JSONObject(result);
//                        String code = jsonObject.getString("code");
//                        String message = jsonObject.getString("message");
//                        String data = jsonObject.getString("data");
//                        Gson gson = new Gson();
//                        if ("200".equals(code)) {
//                            stateLayout.changeState(StateFrameLayout.SUCCESS);
//                            JSONArray jsonArray = new JSONArray(data);
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                list.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Soup.class));
//                            }
//                            categoryAdapter = new SoupCategoryAdapter(mActivity, list);
//                            listview.setAdapter(categoryAdapter);
//                            adapter = new SoupAdapter(mActivity, list);
//                            gvContent.setAdapter(adapter);
//                        } else {
//                            MyUtils.showToast(mActivity, message);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONArray jsonArray = new JSONArray(data);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                soupCategoryList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), SoupCategory.class));
                            }
                            categoryAdapter = new SoupCategoryAdapter(mActivity, soupCategoryList);
                            listview.setAdapter(categoryAdapter);
                            adapter = new SoupRightAdapter(mActivity, soupCategoryList);
                            lvContent.setAdapter(adapter);
//                            MyUtils.showToast(mActivity, message);
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

    @Override
    protected void setListener() {
        upSoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                if (selectedList.size() > 0) {
                    showPopupWindow();
                } else {
                    MyUtils.showToast(mActivity, "请先选择配料！");
                }
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                if (selectedList.size() > 0) {
                    showPopupWindow();
                } else {
                    MyUtils.showToast(mActivity, "请先选择配料！");
                }
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                lvContent.post(new Runnable() {
                    @Override
                    public void run() {
                        categoryAdapter.setSelectItem(position);
                        lvContent.setSelection(position);
//                        lvContent.setSelectionFromTop(position,0);
//                        lv_content.smoothScrollToPositionFromTop(position,0);

                    }
                });
            }
        });
        lvContent.setOnScrollListener(listener);
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, MySoupOrderActivity.class));
            }
        });
    }

    AbsListView.OnScrollListener listener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (scrollPosition != firstVisibleItem) {
                if (adapter != null) {
                    categoryAdapter.setSelectItem(firstVisibleItem);
                    listview.setSelectionFromTop(firstVisibleItem, 40);
                    scrollPosition = firstVisibleItem;
                }
            }
        }
    };

    @SuppressLint("ResourceAsColor")
    public void setChecked(TextView t1, TextView t2, TextView t3, TextView t4) {
        t1.setBackgroundResource(R.drawable.btn_shape);
        t1.setTextColor(Color.RED);
        t2.setBackgroundResource(R.drawable.edit_shape);
        t2.setTextColor(R.color.black);
        t3.setBackgroundResource(R.drawable.edit_shape);
        t3.setTextColor(R.color.black);
        t4.setBackgroundResource(R.drawable.edit_shape);
        t4.setTextColor(R.color.black);
    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow() {
//        okList.clear();
//        MyUtils.subZeroAndDot(total)=0;
        View popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.soup_check, null);
        ImageView down_soup = popupWindow_view.findViewById(R.id.down_soup);
        final SwipeMenuListView swipe_lv = popupWindow_view.findViewById(R.id.swipe_lv);
        countin = popupWindow_view.findViewById(R.id.count);
        TextView ok = popupWindow_view.findViewById(R.id.ok);
        final TextView jin = popupWindow_view.findViewById(R.id.jin);
        final TextView liang = popupWindow_view.findViewById(R.id.liang);
        final TextView ke = popupWindow_view.findViewById(R.id.ke);
        final TextView kg = popupWindow_view.findViewById(R.id.kg);
//        if (checkAdapter==null){
        checkAdapter = new SoupCheckAdapter(mActivity, selectedList);
        swipe_lv.setAdapter(checkAdapter);
//        }else{
//            checkAdapter.setGrouplists(selectedList);
//            checkAdapter.notifyDataSetChanged();
//        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                double totalweight = 0;
                if ("斤".equals(mass)) {
                    totalweight = total / 2;
                } else if ("两".equals(mass)) {
                    totalweight = total / 20;
                } else if ("克".equals(mass)) {
                    totalweight = total / 1000;
                } else if ("千克".equals(mass)) {
                    totalweight = total;
                }
                if (okList.size() != selectedList.size()) {
                    MyUtils.showToast(mActivity, "请配置完再提交！");
                    for (int i = 0; i < selectedList.size(); i++) {
                        if ("0".equals(selectedList.get(i).getNum())) {
                            swipe_lv.setSelection(i);
                            return;
                        }
                    }
                    return;
                }
                if (totalweight < 2.5) {
                    MyUtils.showToast(mActivity, "汤料总重量不能小于‘2.5’千克！");
                    return;
                }

                /*if (Global.loginResult == null) {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                    return;
                }*/
                Intent intent = new Intent(mActivity, SoupOrderConfirmActivity.class);
                intent.putExtra("oklist", (Serializable) okList);
                intent.putExtra("val", val);
                startActivity(intent);
            }
        });
//        mass="斤";
        if ("斤".equals(mass)) {
            setChecked(jin, liang, ke, kg);
        } else if ("两".equals(mass)) {
            setChecked(liang, jin, ke, kg);
        } else if ("克".equals(mass)) {
            setChecked(ke, liang, jin, kg);
        } else if ("千克".equals(mass)) {
            setChecked(kg, liang, ke, jin);
        }

        countin.setText(Html.fromHtml("已配<font color='#2BB835'>" + okList.size() + "<font/>/<font color='#2BB835'>" + selectedList.size() + "<font/>类 共<font color='#2BB835'>" + MyUtils.subZeroAndDot(df.format((total))) + "<font/>" + mass));
        //千克1000斤500两50克1
        jin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < selectedList.size(); i++) {
                    num = Double.parseDouble(selectedList.get(i).getNum());
                    if (!"0".equals(selectedList.get(i).getNum())) {
                        if ("两".equals(mass)) {
                            selectedList.get(i).setNum(num / 10 + "");
                        } else if ("克".equals(mass)) {
                            selectedList.get(i).setNum(num / 500 + "");
                        } else if ("千克".equals(mass)) {
                            selectedList.get(i).setNum(num * 2 + "");
                        }
                    }
                }
                checkAdapter.notifyDataSetChanged();
                mass = "斤";
                val = "500";
                setChecked(jin, liang, ke, kg);
                countin.setText(Html.fromHtml("已配<font color='#2BB835'>" + okList.size() + "<font/>/<font color='#2BB835'>" + selectedList.size() + "<font/>类 共<font color='#2BB835'>" + MyUtils.subZeroAndDot(df.format(total)) + "<font/>" + mass));
            }
        });
        liang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < selectedList.size(); i++) {
                    num = Double.parseDouble(selectedList.get(i).getNum());
                    if (!"0".equals(selectedList.get(i).getNum())) {
                        if ("斤".equals(mass)) {
                            selectedList.get(i).setNum(num * 10 + "");
                        } else if ("克".equals(mass)) {
                            selectedList.get(i).setNum(num / 50 + "");
                        } else if ("千克".equals(mass)) {
                            selectedList.get(i).setNum(num * 20 + "");
                        }
                    }
                }
                checkAdapter.notifyDataSetChanged();
                mass = "两";
                val = "50";
                setChecked(liang, jin, ke, kg);
                countin.setText(Html.fromHtml("已配<font color='#2BB835'>" + okList.size() + "<font/>/<font color='#2BB835'>" + selectedList.size() + "<font/>类 共<font color='#2BB835'>" + MyUtils.subZeroAndDot(df.format(total)) + "<font/>" + mass));
            }
        });
        ke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < selectedList.size(); i++) {
                    num = Double.parseDouble(selectedList.get(i).getNum());
                    if (!"0".equals(selectedList.get(i).getNum())) {
                        if ("斤".equals(mass)) {
                            selectedList.get(i).setNum(num * 500 + "");
                        } else if ("两".equals(mass)) {
                            selectedList.get(i).setNum(num * 50 + "");
                        } else if ("千克".equals(mass)) {
                            selectedList.get(i).setNum(num * 1000 + "");
                        }
                    }
                }
                checkAdapter.notifyDataSetChanged();
                mass = "克";
                val = "1";
                setChecked(ke, liang, jin, kg);
                countin.setText(Html.fromHtml("已配<font color='#2BB835'>" + okList.size() + "<font/>/<font color='#2BB835'>" + selectedList.size() + "<font/>类 共<font color='#2BB835'>" + MyUtils.subZeroAndDot(df.format(total)) + "<font/>" + mass));
            }
        });
        kg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < selectedList.size(); i++) {
                    num = Double.parseDouble(selectedList.get(i).getNum());
                    if (!"0".equals(selectedList.get(i).getNum())) {
                        if ("斤".equals(mass)) {
                            selectedList.get(i).setNum(num / 2 + "");
                        } else if ("两".equals(mass)) {
                            selectedList.get(i).setNum(num / 20 + "");
                        } else if ("克".equals(mass)) {
                            selectedList.get(i).setNum(num / 1000 + "");
                        }
                    }
                }
                checkAdapter.notifyDataSetChanged();
                mass = "千克";
                val = "1000";
                setChecked(kg, liang, ke, jin);
                countin.setText(Html.fromHtml("已配<font color='#2BB835'>" + okList.size() + "<font/>/<font color='#2BB835'>" + selectedList.size() + "<font/>类 共<font color='#2BB835'>" + MyUtils.subZeroAndDot(df.format(total)) + "<font/>" + mass));
            }
        });
        /*SwipeMenuCreator creator = new SwipeMenuCreator() {

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
                openItem.setWidth(dp2px(mActivity, 90));
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
                deleteItem.setWidth(dp2px(mActivity, 90));
                // set a icon
                deleteItem.setIcon(R.drawable.delete);
                // add to menu
//                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        swipe_lv.setMenuCreator(creator);
        swipe_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        for (int i = 0; i < list.size(); i++) {
                            if (selectedList.get(position)==list.get(i)){
                                list.get(i).setCheck(false);
                            }
                        }
                        for (int i = 0; i < okList.size(); i++) {
                            if (selectedList.get(position).getName().equals(okList.get(i).getName())){
                                total-=Double.parseDouble(okList.get(i).getNum());
                                okList.remove(i);
                            }
                        }
                        *//*MyUtils.subZeroAndDot(total)=0;
                        for (int i = 0; i < okList.size(); i++) {
                            MyUtils.subZeroAndDot(total)+=okList.get(i).getNum();
                        }*//*
                        selectedList.remove(position);
                        count.setText("已选"+selectedList.size()+"类");
                        countin.setText("已配"+okList.size()+"/"+selectedList.size()+"类 共"+MyUtils.subZeroAndDot(Double.toString(total))+mass);
                       checkAdapter.notifyDataSetChanged();
                       adapter.notifyDataSetChanged();
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        swipe_lv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);*/
        countin.setText(Html.fromHtml("已配<font color='#2BB835'>" + okList.size() + "<font/>/<font color='#2BB835'>" + selectedList.size() + "<font/>类 共<font color='#2BB835'>" + MyUtils.subZeroAndDot(df.format(total)) + "<font/>" + mass));
        down_soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Soup soup) {
        okList.clear();
        total = 0;
        if (!soup.isCheck()) {
            selectedList.remove(soup);
        } else {
            selectedList.add(soup);
        }
        //部分文字改变颜色
//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan greenSpan = new ForegroundColorSpan(Color.parseColor("#2BB835"));
        count.setText("已选" + selectedList.size() + "类");
//这里注意一定要先给textview赋值

        SpannableStringBuilder builder = new SpannableStringBuilder(count.getText().toString());
//为不同位置字符串设置不同颜色
//   Spanned.SPAN_INCLUSIVE_EXCLUSIVE 从起始下标到终了下标，包括起始下标
//   Spanned.SPAN_INCLUSIVE_INCLUSIVE 从起始下标到终了下标，同时包括起始下标和终了下标
//   Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 从起始下标到终了下标，但都不包括起始下标和终了下标
//   Spanned.SPAN_EXCLUSIVE_INCLUSIVE 从起始下标到终了下标，包括终了下标
        builder.setSpan(greenSpan, 2, count.getText().toString().length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//最后为textview赋值
        count.setText(builder);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(SoupUpdateEvent event) {
        selectedList = event.getSoupList();
        okList.clear();
        total = 0;
        for (int i = 0; i < selectedList.size(); i++) {
            if (!"0".equals(selectedList.get(i).getNum())) {
                okList.add(selectedList.get(i));
                total += Double.parseDouble(selectedList.get(i).getNum());
            }
        }
        //部分文字改变颜色
//        ForegroundColorSpan greenSpan = new ForegroundColorSpan(Color.parseColor("#2BB835"));
        countin.setText(Html.fromHtml("已配<font color='#2BB835'>" + okList.size() + "<font/>/<font color='#2BB835'>" + selectedList.size() + "<font/>类 共<font color='#2BB835'>" + MyUtils.subZeroAndDot(df.format(total)) + "<font/>" + mass));

    }

}
