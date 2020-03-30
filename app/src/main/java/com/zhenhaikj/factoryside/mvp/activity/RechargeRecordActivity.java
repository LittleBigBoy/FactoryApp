package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter2;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Data2;
import com.zhenhaikj.factoryside.mvp.bean.Freezing;
import com.zhenhaikj.factoryside.mvp.bean.Recharge;
import com.zhenhaikj.factoryside.mvp.bean.SingleQuantity;
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

public class RechargeRecordActivity extends BaseActivity<RecordPresenter, RecordModel> implements View.OnClickListener, RecordContract.View {
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
    @BindView(R.id.rv_recharge)
    RecyclerView mRvRecharge;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String startTime;
    private String stateName;
    private int pageIndex=1;

    private List<Recharge.Data1Bean> recharge_list = new ArrayList<>();//充值记录
    private String userId;
    private String date;
    private RechargeRecordAdapter2 adapter2;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_recharge_record;
    }

    @Override
    protected void initData() {
        adapter2 = new RechargeRecordAdapter2(R.layout.item_record, recharge_list);
        mRvRecharge.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvRecharge.setAdapter(adapter2);
        adapter2.setEmptyView(getEmptyRecord());

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {


            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                recharge_list.clear();
                mPresenter.RechargeRecord(userId, startTime, date, "全部", "1",String.valueOf(pageIndex),"10");
                refreshlayout.finishRefresh();
            }
        });
        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                mPresenter.RechargeRecord(userId, startTime, date, "全部", "1",String.valueOf(pageIndex),"10");
                refreshLayout.finishLoadMore();
            }
        });

    }

    @Override
    protected void initView() {
        mTvTitle.setText("充值记录");
        mTvTitle.setVisibility(View.VISIBLE);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));   //修改时区
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //HH:24小时制  hh:12小时制
        date = dateFormat.format(new Date());
        startTime = getStringByFormat(getTimesmorning());
        mTvDay.setSelected(true);
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        showProgress();
        mPresenter.RechargeRecord(userId, startTime, date, "全部", "1",String.valueOf(pageIndex),"10");
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
                recharge_list.clear();
                showProgress();
                mPresenter.RechargeRecord(userId, startTime, date, "全部", "1",String.valueOf(pageIndex),"10");
                break;
            case R.id.tv_week:
                tabSelected(mTvWeek);
                startTime = getStringByFormat(getTimesWeekmorning());
                recharge_list.clear();
                showProgress();
                mPresenter.RechargeRecord(userId, startTime, date, "全部", "1",String.valueOf(pageIndex),"10");
                break;
            case R.id.tv_month:
                tabSelected(mTvMonth);
                startTime = getStringByFormat(getTimesMonthmorning());
                recharge_list.clear();
                showProgress();
                mPresenter.RechargeRecord(userId, startTime, date, "全部", "1",String.valueOf(pageIndex),"10");
                break;
            case R.id.tv_year:
                tabSelected(mTvYear);
                startTime = getStringByFormat(getTimesYearmorning());
                recharge_list.clear();
                showProgress();
                mPresenter.RechargeRecord(userId, startTime, date, "全部", "1",String.valueOf(pageIndex),"10");
                break;
        }
    }

    private void tabSelected(TextView linearLayout) {
        mTvDay.setSelected(false);
        mTvMonth.setSelected(false);
        mTvWeek.setSelected(false);
        mTvYear.setSelected(false);
        linearLayout.setSelected(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
        switch (result.getStatusCode()) {
            case 200:
                mTvMoney.setText(result.getData().getItem3() + "");
                recharge_list.addAll(result.getData().getItem2().getData1());
                adapter2.setNewData(recharge_list);
                adapter2.notifyDataSetChanged();
                hideProgress();
                break;
        }

    }

    @Override
    public void FreezingAmount(BaseResult<Data<Freezing>> result) {

    }

    @Override
    public void WorkOrderTime(BaseResult<Data<SingleQuantity>> result) {

    }
}
