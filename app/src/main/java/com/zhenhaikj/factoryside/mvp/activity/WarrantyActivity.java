package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WarrantyActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.message_tv)
    TextView mMessageTv;
    @BindView(R.id.message_number_tv)
    TextView mMessageNumberTv;
    @BindView(R.id.work_order_tracking_tv)
    TextView mWorkOrderTrackingTv;
    @BindView(R.id.shipping_logistics_tv)
    TextView mShippingLogisticsTv;
    @BindView(R.id.return_logistics_tv)
    TextView mReturnLogisticsTv;
    @BindView(R.id.work_order_number_tv)
    TextView mWorkOrderNumberTv;
    @BindView(R.id.work_order_status_tv)
    TextView mWorkOrderStatusTv;
    @BindView(R.id.master_name_tv)
    TextView mMasterNameTv;
    @BindView(R.id.warranty_type_tv)
    TextView mWarrantyTypeTv;
    @BindView(R.id.work_order_type_tv)
    TextView mWorkOrderTypeTv;
    @BindView(R.id.billing_time_tv)
    TextView mBillingTimeTv;
    @BindView(R.id.original_work_order_tv)
    TextView mOriginalWorkOrderTv;
    @BindView(R.id.remarks_tv)
    TextView mRemarksTv;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.address_tv)
    TextView mAddressTv;
    @BindView(R.id.service_center_name_tv)
    TextView mServiceCenterNameTv;
    @BindView(R.id.responsible_person_phone_tv)
    TextView mResponsiblePersonPhoneTv;
    @BindView(R.id.information_officer_phone_tv)
    TextView mInformationOfficerPhoneTv;
    @BindView(R.id.brand_tv)
    TextView mBrandTv;
    @BindView(R.id.category_tv)
    TextView mCategoryTv;
    @BindView(R.id.model_tv)
    TextView mModelTv;
    @BindView(R.id.order_quantity_tv)
    TextView mOrderQuantityTv;
    @BindView(R.id.pre_freeze_number_tv)
    TextView mPreFreezeNumberTv;
    @BindView(R.id.pending_effect_one_tv)
    TextView mPendingEffectOneTv;
    @BindView(R.id.maintenance_fees_tv)
    TextView mMaintenanceFeesTv;
    @BindView(R.id.pending_effect_two_tv)
    TextView mPendingEffectTwoTv;
    @BindView(R.id.total_tv)
    TextView mTotalTv;
    @BindView(R.id.pending_effect_tv)
    TextView mPendingEffectTv;
    @BindView(R.id.order_source_tv)
    TextView mOrderSourceTv;
    @BindView(R.id.accessories_rv)
    RecyclerView mAccessoriesRv;
    @BindView(R.id.shipping_address_tv)
    TextView mShippingAddressTv;
    @BindView(R.id.receiver_tv)
    TextView mReceiverTv;
    @BindView(R.id.receiving_call_tv)
    TextView mReceivingCallTv;
    @BindView(R.id.plant_tv)
    TextView mPlantTv;
    @BindView(R.id.send_tv)
    TextView mSendTv;
    @BindView(R.id.by_cash_tv)
    TextView mByCashTv;
    @BindView(R.id.pay_tv)
    TextView mPayTv;
    @BindView(R.id.application_time_tv)
    TextView mApplicationTimeTv;
    @BindView(R.id.delivery_time_tv)
    TextView mDeliveryTimeTv;
    @BindView(R.id.choose_time_iv)
    ImageView mChooseTimeIv;
    @BindView(R.id.tracking_number_tv)
    EditText mTrackingNumberTv;
    @BindView(R.id.logistics_company_tv)
    TextView mLogisticsCompanyTv;
    @BindView(R.id.confirm_mailing_accessories_tv)
    TextView mConfirmMailingAccessoriesTv;
    @BindView(R.id.delayed_delivery_tv)
    TextView mDelayedDeliveryTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_warranty;
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
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
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
}