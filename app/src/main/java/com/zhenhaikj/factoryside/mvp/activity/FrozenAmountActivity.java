package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.FrozenAmountAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.SpinnerAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Data2;
import com.zhenhaikj.factoryside.mvp.bean.Freezing;
import com.zhenhaikj.factoryside.mvp.bean.Recharge;
import com.zhenhaikj.factoryside.mvp.bean.SingleQuantity;
import com.zhenhaikj.factoryside.mvp.bean.SpinnerBean;
import com.zhenhaikj.factoryside.mvp.contract.RecordContract;
import com.zhenhaikj.factoryside.mvp.model.RecordModel;
import com.zhenhaikj.factoryside.mvp.presenter.RecordPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

public class FrozenAmountActivity extends BaseActivity<RecordPresenter, RecordModel> implements View.OnClickListener, RecordContract.View {
    @BindView(R.id.view)
    View mView;
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
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_day)
    TextView mTvDay;
    @BindView(R.id.tv_week)
    TextView mTvWeek;
    @BindView(R.id.tv_month)
    TextView mTvMonth;
    @BindView(R.id.tv_year)
    TextView mTvYear;
    @BindView(R.id.rv_frozen)
    RecyclerView mRvFrozen;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private List<Freezing.DataBean> list = new ArrayList<>();//充值记录
    private FrozenAmountAdapter amountAdapter;
    private String userId;
    private int page = 1;
    private String date;
    private String startTime;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_frozen_amount;
    }

    @Override
    protected void initData() {
        mSpinner.setDropDownWidth(300); //下拉宽度
        mSpinner.setDropDownHorizontalOffset(100); //下拉的横向偏移
        mSpinner.setDropDownVerticalOffset(100); //下拉的纵向偏移
        ArrayList<SpinnerBean> cars = new ArrayList<>();
        SpinnerBean car = new SpinnerBean();
        car.setName("筛选");
        cars.add(car);
        car = new SpinnerBean();
        car.setName("上门检测费");
        cars.add(car);
        car = new SpinnerBean();
        car.setName("上门维修费");
        cars.add(car);
        car = new SpinnerBean();
        car.setName("上门安装费");
        cars.add(car);
        car = new SpinnerBean();
        car.setName("远程费");
        cars.add(car);
        car = new SpinnerBean();
        car.setName("邮费");
        cars.add(car);
        final SpinnerAdapter adapter = new SpinnerAdapter(mActivity, cars);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                page = 1;
                list.clear();
                mPresenter.FreezingAmount(userId, startTime,date,String.valueOf(page), "10");
                refreshlayout.finishRefresh();
            }
        });
        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                mPresenter.FreezingAmount(userId, startTime,date,String.valueOf(page), "10");
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("冻结记录");
        mTvDay.setSelected(true);

        amountAdapter = new FrozenAmountAdapter(R.layout.item_frozen, list);
        mRvFrozen.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvFrozen.setAdapter(amountAdapter);
        amountAdapter.setEmptyView(getEmptyRecord());
        amountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent1 = new Intent(mActivity, WarrantyActivity.class);
                intent1.putExtra("OrderID", list.get(position).getOrderID());
                startActivity(intent1);
            }
        });
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));   //修改时区
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //HH:24小时制  hh:12小时制
        date = dateFormat.format(new Date());
        startTime = getStringByFormat(getTimesmorning());
        mTvDay.setSelected(true);
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.FreezingAmount(userId, startTime,date,String.valueOf(page), "10");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvDay.setOnClickListener(this);
        mTvMonth.setOnClickListener(this);
        mTvWeek.setOnClickListener(this);
        mTvYear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_day:
                tabSelected(mTvDay);
                startTime = getStringByFormat(getTimesmorning());
                list.clear();
                mPresenter.FreezingAmount(userId, startTime,date,String.valueOf(page), "10");
                break;
            case R.id.tv_week:
                tabSelected(mTvWeek);
                startTime = getStringByFormat(getTimesWeekmorning());
                list.clear();
                mPresenter.FreezingAmount(userId, startTime,date,String.valueOf(page), "10");
                break;
            case R.id.tv_month:
                tabSelected(mTvMonth);
                startTime = getStringByFormat(getTimesMonthmorning());
                list.clear();
                mPresenter.FreezingAmount(userId, startTime,date,String.valueOf(page), "10");
                break;
            case R.id.tv_year:
                tabSelected(mTvYear);
                startTime = getStringByFormat(getTimesYearmorning());
                list.clear();
                mPresenter.FreezingAmount(userId, startTime,date,String.valueOf(page), "10");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void tabSelected(TextView linearLayout) {
        mTvDay.setSelected(false);
        mTvMonth.setSelected(false);
        mTvWeek.setSelected(false);
        mTvYear.setSelected(false);
        linearLayout.setSelected(true);
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    public static String getStringByFormat(Date date) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    // 获得当天0点时间
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }


    // 获得本年第一天0点时间
    public static Date getTimesYearmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.YEAR), cal.get(Calendar.DAY_OF_YEAR), 0, 0, 0);
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

    @Override
    public void RechargeRecord(BaseResult<Data2<Recharge>> result) {

    }

    @Override
    public void FreezingAmount(BaseResult<Data<Freezing>> result) {
        switch (result.getStatusCode()){
            case 200:
                list.addAll(result.getData().getItem2().getData());
                amountAdapter.setNewData(list);
                mTvMoney.setText(result.getData().getItem2().getTotal()+"");
                break;
        }
    }

    @Override
    public void WorkOrderTime(BaseResult<Data<SingleQuantity>> result) {

    }
}
