package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.AddressAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ShippingAddressAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingAddressActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.rv_address)
    RecyclerView mRvAddress;
    private ArrayList<Address> addressList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shipping_address;
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            addressList.add(new Address());
        }
        ShippingAddressAdapter addressAdapter = new ShippingAddressAdapter(R.layout.item_address, addressList);
        mRvAddress.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAddress.setAdapter(addressAdapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的收货地址");
        mTvSave.setText("添加新地址");
        mTvSave.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_save:
                startActivity(new Intent(mActivity, AddAddressActivity.class));
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
