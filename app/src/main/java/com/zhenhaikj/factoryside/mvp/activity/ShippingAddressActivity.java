package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.AddressAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ShippingAddressAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.contract.AddressContract;
import com.zhenhaikj.factoryside.mvp.model.AddressModel;
import com.zhenhaikj.factoryside.mvp.presenter.AddressPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingAddressActivity extends BaseActivity<AddressPresenter, AddressModel> implements View.OnClickListener, AddressContract.View {


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
    private List<Address> addressList = new ArrayList<>();
    private SPUtils spUtils;
    private String userId;
    private ShippingAddressAdapter addressAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shipping_address;
    }

    @Override
    protected void initData() {
//        for (int i = 0; i < 10; i++) {
//            addressList.add(new Address());
//        }
        addressAdapter = new ShippingAddressAdapter(R.layout.item_address, addressList);
        mRvAddress.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvAddress.setAdapter(addressAdapter);
        addressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch(view.getId()){
                    case R.id.tv_edit:
                        Intent intent=new Intent(mActivity,AddAddressActivity.class);
                        intent.putExtra("address",addressList.get(position));
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetAccountAddress(userId);
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

    @Override
    public void AddAccountAddress(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateAccountAddress(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void DeleteAccountAddress(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetAccountAddress(BaseResult<List<Address>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                addressList = baseResult.getData();
                addressAdapter.setNewData(addressList);
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }

    @Override
    public void GetProvince(BaseResult<List<Province>> baseResult) {

    }

    @Override
    public void GetCity(BaseResult<Data<List<City>>> baseResult) {

    }

    @Override
    public void GetArea(BaseResult<Data<List<Area>>> baseResult) {

    }

    @Override
    public void GetDistrict(BaseResult<Data<List<District>>> baseResult) {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String message) {
        if (!"address".equals(message)){
            return;
        }
        mPresenter.GetAccountAddress(userId);
    }
}
