package com.zhenhaikj.factoryside.mvp.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushManager;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.MainActivity;
import com.zhenhaikj.factoryside.mvp.adapter.MyPagerAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.WorkOrdersDetailContract;
import com.zhenhaikj.factoryside.mvp.fragment.MessageFragment;
import com.zhenhaikj.factoryside.mvp.fragment.OrderDetailFragment;
import com.zhenhaikj.factoryside.mvp.fragment.ReturnFragment;
import com.zhenhaikj.factoryside.mvp.fragment.ShippingFragment;
import com.zhenhaikj.factoryside.mvp.fragment.TrackFragment;
import com.zhenhaikj.factoryside.mvp.model.WorkOrdersDetailModel;
import com.zhenhaikj.factoryside.mvp.presenter.WorkOrdersDetailPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WarrantyActivity extends BaseActivity<WorkOrdersDetailPresenter, WorkOrdersDetailModel> implements View.OnClickListener, ViewPager.OnPageChangeListener, WorkOrdersDetailContract.View {

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
    @BindView(R.id.ll_warranty)
    ScrollView mLlWarranty;
    @BindView(R.id.wp_warranty)
    ViewPager mWpWarranty;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.work_order_detail_tv)
    TextView mWorkOrderDetailTv;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    private String OrderId;
    private WorkOrder.DataBean data;

    private ArrayList<Fragment> mFragments;
    private CommonNavigator commonNavigator;
    private String[] mTitleDataList = new String[]{
            "详情","留言", "工单跟踪", "寄件物流", "返件物流"
    };
    private XGPushClickedResult clickedResult;
    private View complaint_view;
    private Button btn_negtive;
    private Button btn_positive;
    private EditText et_content;
    private TextView title;
    private AlertDialog complaint_dialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_warranty;
    }

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
        mTvTitle.setText("工单详情");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("投诉");
        //this必须为点击消息要跳转到页面的上下文。
        clickedResult = XGPushManager.onActivityStarted(this);
        if (clickedResult !=null){
            //获取消息附近参数
            String ster = clickedResult.getCustomContent();
            try {
                JSONObject jsonObject=new JSONObject(ster);
                OrderId=jsonObject.getString("OrderID");
            } catch (JSONException e) {
                e.printStackTrace();
            }
//获取消息标题
//            String set = clickedResult.getTitle();
//获取消息内容
//            String s = clickedResult.getContent();
        }else{
            OrderId = getIntent().getStringExtra("OrderID");
        }
        mFragments = new ArrayList<>();
        mFragments.add(OrderDetailFragment.newInstance(OrderId, ""));
        mFragments.add(MessageFragment.newInstance(OrderId, ""));
        mFragments.add(TrackFragment.newInstance(OrderId, ""));
        mFragments.add(ShippingFragment.newInstance(OrderId, ""));
        mFragments.add(ReturnFragment.newInstance(OrderId, ""));
//        mPresenter.GetOrderInfo(OrderId);
//        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                mPresenter.GetOrderInfo(OrderId);
//                mRefreshLayout.finishRefresh(3000);
//            }
//        });
        mWpWarranty.setOffscreenPageLimit(mTitleDataList.length);
        mWpWarranty.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mTitleDataList,mFragments));
        commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.BLACK);
                colorTransitionPagerTitleView.setSelectedColor(Color.RED);
                colorTransitionPagerTitleView.setText(mTitleDataList[index]);
                colorTransitionPagerTitleView.setTextSize(16);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mWpWarranty.setCurrentItem(index);
//                        mTitle.setText(mTitleDataList[index]);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setColors(Color.RED);
                return indicator;
            }
        });
        mMagicIndicator.setBackgroundColor(Color.WHITE);
        mMagicIndicator.setNavigator(commonNavigator);

        ViewPagerHelper.bind(mMagicIndicator, mWpWarranty);
    }

    @Override
    protected void initView() {
        mWpWarranty.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mWpWarranty.setOffscreenPageLimit(mFragments.size());

        mWpWarranty.setCurrentItem(0);
        tabSelected(mWorkOrderDetailTv);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mWorkOrderDetailTv.setOnClickListener(this);
        mMessageTv.setOnClickListener(this);
        mWorkOrderTrackingTv.setOnClickListener(this);
        mShippingLogisticsTv.setOnClickListener(this);
        mReturnLogisticsTv.setOnClickListener(this);
        mWpWarranty.addOnPageChangeListener(this);
        mTvSave.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        if (clickedResult!=null){
            startActivity(new Intent(mActivity, MainActivity.class));
            finish();
        }else{
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                if (clickedResult!=null){
                    startActivity(new Intent(mActivity, MainActivity.class));
                    finish();
                }else{
                    finish();
                }
                break;
            case R.id.work_order_detail_tv:
                mWpWarranty.setCurrentItem(0);
                tabSelected(mWorkOrderDetailTv);
                break;
            case R.id.message_tv:
                mWpWarranty.setCurrentItem(1);
                tabSelected(mMessageTv);
                break;
            case R.id.work_order_tracking_tv:
                mWpWarranty.setCurrentItem(2);
                tabSelected(mWorkOrderTrackingTv);
                break;
            case R.id.shipping_logistics_tv:
                mWpWarranty.setCurrentItem(3);
                tabSelected(mShippingLogisticsTv);
                break;
            case R.id.return_logistics_tv:
                mWpWarranty.setCurrentItem(4);
                tabSelected(mReturnLogisticsTv);
                break;
            case R.id.tv_save:
                complaint_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_complaint, null);
                title = complaint_view.findViewById(R.id.title);
                btn_negtive = complaint_view.findViewById(R.id.negtive);
                btn_positive = complaint_view.findViewById(R.id.positive);
                et_content = complaint_view.findViewById(R.id.et_content);
                title.setText("投诉");
                complaint_dialog = new AlertDialog.Builder(mActivity)
                        .setView(complaint_view)
                        .create();
                complaint_dialog.show();
                btn_negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        complaint_dialog.dismiss();
                    }
                });
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String content = et_content.getText().toString().trim();
                        if ("".equals(content)) {
                            MyUtils.showToast(mActivity, "请输入投诉原因");
                        } else {
                            mPresenter.FactoryComplaint(OrderId, content);
                        }
                    }
                });
                break;
        }
    }


    private void tabSelected(TextView textView) {
        mWorkOrderDetailTv.setSelected(false);
        mMessageTv.setSelected(false);
        mWorkOrderTrackingTv.setSelected(false);
        mShippingLogisticsTv.setSelected(false);
        mReturnLogisticsTv.setSelected(false);
        textView.setSelected(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                tabSelected(mWorkOrderDetailTv);
                break;
            case 1:
                tabSelected(mMessageTv);
                break;
            case 2:
                tabSelected(mWorkOrderTrackingTv);
                break;
            case 3:
                tabSelected(mShippingLogisticsTv);
                break;
            case 4:
                tabSelected(mReturnLogisticsTv);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        mRefreshLayout.finishRefresh();
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                mWorkOrderStatusTv.setText(data.getState());
                mNameTv.setText(data.getUserName());
                mPhoneTv.setText(data.getPhone());
                mAddressTv.setText(data.getAddress());
                mBillingTimeTv.setText(data.getCreateDate());
                mWorkOrderNumberTv.setText(data.getId());
                mWarrantyTypeTv.setText(data.getGuarantee());
                mWorkOrderTypeTv.setText(data.getTypeName());
//                mTvRecoveryTime.setText(data.getRecycleOrderHour());
//                mTvSentOutAccessories.setText(data.getAccessorySendState());
                mBrandTv.setText(data.getBrandName());
                mCategoryTv.setText(data.getSubCategoryName());
                mModelTv.setText(data.getProductType());
                mRemarksTv.setText(data.getMemo());
                mOrderQuantityTv.setText(data.getNum());

//                mTvSpecifyDoorToDoorTime.setText(data.getExtraTime());
//                mTvOrderSource.setText(data.getExpressNo());
//                mTvThirdParty.setText(data.getThirdPartyNo());
                break;
            case 401:
                break;
        }
    }

    @Override
    public void ApplyCustomService(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApproveOrderAccessory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApproveBeyondMoney(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApproveOrderService(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApproveOrderAccessoryAndService(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void AddOrUpdateExpressNo(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void EnSureOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void FactoryEnsureOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void UpdateIsReturnByOrderID(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetAccountAddress(BaseResult<List<Address>> baseResult) {

    }

    @Override
    public void GetOrderAccessoryMoney(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateFactoryAccessorybyFactory(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void ApproveOrderAccessoryByModifyPrice(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void NowEnSureOrder(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void FactoryComplaint(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                    complaint_dialog.dismiss();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}