package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.WorkOrdersDetailContract;
import com.zhenhaikj.factoryside.mvp.model.WorkOrdersDetailModel;
import com.zhenhaikj.factoryside.mvp.presenter.WorkOrdersDetailPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WorkOrderDetailsActivity extends BaseActivity<WorkOrdersDetailPresenter,WorkOrdersDetailModel> implements View.OnClickListener,WorkOrdersDetailContract.View {

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
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_contact_customer_service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.tv_work_order_status)
    TextView mTvWorkOrderStatus;
    @BindView(R.id.tv_work_order_number)
    TextView mTvWorkOrderNumber;
    @BindView(R.id.tv_warranty_type)
    TextView mTvWarrantyType;
    @BindView(R.id.tv_work_order_type)
    TextView mTvWorkOrderType;
    @BindView(R.id.tv_recovery_time)
    TextView mTvRecoveryTime;
    @BindView(R.id.tv_estimated_recycling_time)
    TextView mTvEstimatedRecyclingTime;
    @BindView(R.id.tv_sent_out_accessories)
    TextView mTvSentOutAccessories;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.tv_model)
    TextView mTvModel;
    @BindView(R.id.tv_specify_door_to_door_time)
    TextView mTvSpecifyDoorToDoorTime;
    @BindView(R.id.tv_order_source)
    TextView mTvOrderSource;
    @BindView(R.id.tv_third_party)
    TextView mTvThirdParty;
    @BindView(R.id.tv_fault_description)
    TextView mTvFaultDescription;
    @BindView(R.id.view)
    View mView;
    private String OrderID;
    private WorkOrder.DataBean data;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_work_order_details;
    }
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }
    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("订单详情");
        OrderID =getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(OrderID);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data =baseResult.getData();
                mTvName.setText(data.getUserName());
                mTvPhone.setText(data.getPhone());
                mTvAddress.setText(data.getAddress());
                mTvTime.setText(data.getCreateDate());
                mTvWorkOrderStatus.setText(data.getState());
                mTvWorkOrderNumber.setText(data.getId());
                mTvWarrantyType.setText(data.getGuarantee());
                mTvWorkOrderType.setText(data.getTypeName());
                mTvRecoveryTime.setText(data.getRecycleOrderHour());
                mTvSentOutAccessories.setText(data.getAccessorySendState());
                mTvBrand.setText(data.getBrandName());
                mTvCategory.setText(data.getSubCategoryName());
                mTvModel.setText(data.getProductType());
                mTvFaultDescription.setText(data.getMemo());

                mTvSpecifyDoorToDoorTime.setText(data.getExtraTime());
                mTvOrderSource.setText(data.getExpressNo());
                mTvThirdParty.setText(data.getThirdPartyNo());
                break;
            case 401:
                break;
        }
    }
}