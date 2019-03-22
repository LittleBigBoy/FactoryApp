package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.InvoiceAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.fragment.InvoicedFragment;
import com.zhenhaikj.factoryside.mvp.fragment.UninvoicedFragment;
import com.zhenhaikj.factoryside.mvp.widget.CustomViewPager;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.tv_unissued_ticket)
    TextView mTvUnissuedTicket;
    @BindView(R.id.tv_ticket_has_been_issued)
    TextView mTvTicketHasBeenIssued;
    @BindView(R.id.viewpager_invoice)
    CustomViewPager mViewpagerInvoice;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private UninvoicedFragment uninvoicedFragment;
    private InvoicedFragment invoicedFragmentnew;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_invoice;
    }

    /**
     * 初始化沉浸式
     */
//    @Override
//    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//        mImmersionBar.statusBarView(mView);
//        mImmersionBar.keyboardEnable(true);
//        mImmersionBar.init();
//    }
    @Override
    protected void initData() {
        uninvoicedFragment = new UninvoicedFragment();
        invoicedFragmentnew = new InvoicedFragment();
        mFragmentList.add(uninvoicedFragment);
        mFragmentList.add(invoicedFragmentnew);
        InvoiceAdapter invoiceAdapter = new InvoiceAdapter(getSupportFragmentManager(), mFragmentList);
        mViewpagerInvoice.setAdapter(invoiceAdapter);
        mViewpagerInvoice.setCurrentItem(0);
        mTvUnissuedTicket.setSelected(true);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("发票");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvUnissuedTicket.setOnClickListener(this);
        mTvTicketHasBeenIssued.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_unissued_ticket:
                mViewpagerInvoice.setCurrentItem(0);
                mTvTicketHasBeenIssued.setSelected(false);
                mTvUnissuedTicket.setSelected(true);
                break;
            case R.id.tv_ticket_has_been_issued:
                mViewpagerInvoice.setCurrentItem(1);
                mTvTicketHasBeenIssued.setSelected(true);
                mTvUnissuedTicket.setSelected(false);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
