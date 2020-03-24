package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.adapter.ComplaintDetailAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.ComplaintList;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.ComplaintContract;
import com.zhenhaikj.factoryside.mvp.model.ComplaintModel;
import com.zhenhaikj.factoryside.mvp.presenter.ComplaintPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintDetailActivity extends BaseActivity<ComplaintPresenter, ComplaintModel> implements View.OnClickListener, ComplaintContract.View {
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
    @BindView(R.id.rv_complaint_detail)
    RecyclerView mRvComplaintDetail;
    private String orderId;
    private List<ComplaintList> lists = new ArrayList<>();
    private ComplaintDetailAdapter adapter;
    private String UserId;

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
        return R.layout.activity_complaint_detail;
    }

    @Override
    protected void initData() {
        adapter = new ComplaintDetailAdapter(R.layout.item_complaint_detail, lists);
        mRvComplaintDetail.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvComplaintDetail.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_image:
                        Intent intent = new Intent(mActivity, PhotoViewActivity.class);
                        intent.putExtra("PhotoUrl", Config.ComPlaint_URL +lists.get(position).getPhoto());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setText("投诉详情");
        mTvTitle.setVisibility(View.VISIBLE);
        SPUtils spUtils=SPUtils.getInstance("token");
        UserId=spUtils.getString("userName");
        orderId = getIntent().getStringExtra("orderId");
        mPresenter.GetComplaintListByOrderId(orderId,UserId);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
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
    public void FactoryComplaint(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ComPlaintImg(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetComplaintListByOrderId(BaseResult<List<ComplaintList>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData()!=null){
                    lists.addAll(baseResult.getData());
                    adapter.setNewData(lists);
                }
                break;
        }
    }
}
