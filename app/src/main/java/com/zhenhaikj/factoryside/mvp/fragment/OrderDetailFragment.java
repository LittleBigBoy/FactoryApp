package com.zhenhaikj.factoryside.mvp.fragment;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vondear.rxui.view.dialog.RxDialogScaleView;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.PayPasswordActivity;
import com.zhenhaikj.factoryside.mvp.activity.ScanActivity;
import com.zhenhaikj.factoryside.mvp.activity.ShippingAddressActivity;
import com.zhenhaikj.factoryside.mvp.adapter.AccessoryDetailAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ServiceAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.GAccessory;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.WorkOrdersDetailContract;
import com.zhenhaikj.factoryside.mvp.model.WorkOrdersDetailModel;
import com.zhenhaikj.factoryside.mvp.presenter.WorkOrdersDetailPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.zhenhaikj.factoryside.mvp.utils.SingleClick;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;
import com.zhenhaikj.factoryside.mvp.widget.OrderFreezing;
import com.zhenhaikj.factoryside.mvp.widget.PasswordEditText;
import com.zhenhaikj.factoryside.mvp.widget.PayPasswordView;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderDetailFragment extends BaseLazyFragment<WorkOrdersDetailPresenter, WorkOrdersDetailModel> implements View.OnClickListener, WorkOrdersDetailContract.View, PasswordEditText.PasswordFullListener {

    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.tv_beyond_money)
    TextView mTvBeyondMoney;
    @BindView(R.id.tv_accessory_money)
    TextView mTvAccessoryMoney;
    @BindView(R.id.tv_service_money)
    TextView mTvServiceMoney;
    @BindView(R.id.tv_order_money)
    TextView mTvOrderMoney;
    @BindView(R.id.tv_post_money)
    TextView mTvPostMoney;
    @BindView(R.id.ll_post_money)
    LinearLayout mLlPostMoney;
    @BindView(R.id.tv_send_accessory)
    TextView mTvSendAccessory;
    @BindView(R.id.ll_send_accessory)
    LinearLayout mLlSendAccessory;
    @BindView(R.id.iv_host)
    ImageView mIvHost;
    @BindView(R.id.ll_host)
    LinearLayout mLlHost;
    @BindView(R.id.iv_accessories)
    ImageView mIvAccessories;
    @BindView(R.id.ll_accessories)
    LinearLayout mLlAccessories;
    @BindView(R.id.tv_amount_of_accessories)
    TextView mTvAmountOfAccessories;
    @BindView(R.id.ll_amount_of_accessories)
    LinearLayout mLlAmountOfAccessories;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.tv_send_address)
    TextView mTvSendAddress;
    @BindView(R.id.ll_select_time)
    LinearLayout mLlSelectTime;
    @BindView(R.id.tv_modify_beyond)
    TextView mTvModifyBeyond;
    @BindView(R.id.iv_copy)
    ImageView mIvCopy;
    @BindView(R.id.ll_accessory_sequency)
    LinearLayout mLlAccessorySequency;
    @BindView(R.id.tv_y)
    TextView mTvY;
    @BindView(R.id.tv_post)
    TextView mTvPost;
    @BindView(R.id.tv_addressback2)
    TextView mTvAddressback2;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.ll_post)
    LinearLayout mLlPost;
    @BindView(R.id.ll_service_item)
    LinearLayout mLlServiceItem;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.ll_beyond_money)
    LinearLayout mLlBeyondMoney;
    @BindView(R.id.tv_postage)
    TextView mTvPostage;
    @BindView(R.id.ll_postage)
    LinearLayout mLlPostage;
    @BindView(R.id.tv_expedited_fee)
    TextView mTvExpeditedFee;
    @BindView(R.id.ll_expedited_fee)
    LinearLayout mLlExpeditedFee;
    @BindView(R.id.ll_service_money)
    LinearLayout mLlServiceMoney;
    @BindView(R.id.tv_order_name)
    TextView mTvOrderName;
    @BindView(R.id.tv_abolition)
    TextView mTvAbolition;
    @BindView(R.id.tv_close)
    TextView mTvClose;

    private String mParam1;
    private String mParam2;
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
    @BindView(R.id.tv_reject)
    TextView mTvReject;
    @BindView(R.id.tv_pass)
    TextView mTvPass;
    @BindView(R.id.rv_accessories)
    RecyclerView mRvAccessories;
    @BindView(R.id.ll_approve_beyond_money)
    LinearLayout mLlApproveBeyondMoney;
    @BindView(R.id.tv_review_accessories)
    TextView mTvReviewAccessories;
    @BindView(R.id.tv_audit_service)
    TextView mTvAuditService;
    @BindView(R.id.tv_reject_service)
    TextView mTvRejectService;
    @BindView(R.id.tv_pass_service)
    TextView mTvPassService;
    @BindView(R.id.rv_service)
    RecyclerView mRvService;
    @BindView(R.id.ll_audit_service)
    LinearLayout mLlAuditService;
    @BindView(R.id.tv_status_accessory)
    TextView mTvStatusAccessory;
    @BindView(R.id.tv_status_service)
    TextView mTvStatusService;
    @BindView(R.id.tv_order_state)
    TextView mTvOrderState;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_accessory_memo)
    TextView mTvAccessoryMemo;
    @BindView(R.id.tv_accessory_sequency)
    TextView mTvAccessorySequency;
    @BindView(R.id.ll_approve_accessory)
    LinearLayout mLlApproveAccessory;
    @BindView(R.id.tv_reject_beyond)
    TextView mTvRejectBeyond;
    @BindView(R.id.tv_pass_beyond)
    TextView mTvPassBeyond;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_range)
    TextView mTvRange;
    @BindView(R.id.iv_range_one)
    ImageView mIvRangeOne;
    @BindView(R.id.iv_range_two)
    ImageView mIvRangeTwo;
    @BindView(R.id.iv_n)
    ImageView mIvN;
    @BindView(R.id.ll_n)
    LinearLayout mLlN;
    @BindView(R.id.iv_y)
    ImageView mIvY;
    @BindView(R.id.ll_y)
    LinearLayout mLlY;
    @BindView(R.id.iv_pay)
    ImageView mIvPay;
    @BindView(R.id.ll_pay)
    LinearLayout mLlPay;
    @BindView(R.id.iv_pay2)
    ImageView mIvPay2;
    @BindView(R.id.ll_pay2)
    LinearLayout mLlPay2;
    @BindView(R.id.ll_address_info)
    LinearLayout mLlAddressInfo;
    @BindView(R.id.tv_modify)
    TextView mTvModify;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.ll_old_accessory)
    LinearLayout mLlOldAccessory;
    @BindView(R.id.tv_addressback)
    TextView mTvAddressback;
    @BindView(R.id.tv_select_time)
    TextView mTvSelectTime;
    @BindView(R.id.tv_apply)
    TextView mTvApply;
    @BindView(R.id.ll_apply_custom_service)
    LinearLayout mLlApplyCustomService;
    @BindView(R.id.iv_bar_code)
    ImageView mIvBarCode;
    @BindView(R.id.ll_bar_code)
    LinearLayout mLlBarCode;
    @BindView(R.id.iv_machine)
    ImageView mIvMachine;
    @BindView(R.id.ll_machine)
    LinearLayout mLlMachine;
    @BindView(R.id.iv_fault_location)
    ImageView mIvFaultLocation;
    @BindView(R.id.ll_fault_location)
    LinearLayout mLlFaultLocation;
    @BindView(R.id.iv_new_and_old_accessories)
    ImageView mIvNewAndOldAccessories;
    @BindView(R.id.ll_new_and_old_accessories)
    LinearLayout mLlNewAndOldAccessories;
    @BindView(R.id.ll_return_information)
    LinearLayout mLlReturnInformation;
    @BindView(R.id.negtive)
    Button mNegtive;
    @BindView(R.id.ll_confirm)
    LinearLayout mLlConfirm;
    private String OrderID;
    private WorkOrder.DataBean data;
    private AccessoryDetailAdapter accessoryDetailAdapter;
    private ServiceAdapter serviceAdapter;
    private CommonDialog_Home reject;
    private CommonDialog_Home pass;
    private Data<String> result;
    private View expressno_view;
    private AlertDialog expressno_dialog;
    private Button btn_negtive;
    private Button btn_positive;
    private TextView tv_title;
    private EditText et_expressno;
    private LinearLayout ll_scan;
    private TextView tv_message;
    private String expressno;
    private SimpleTarget<Bitmap> simpleTarget;
    private String IsReturn = "";
    private String PostPayType = "";
    private String AddressBack = "";
    private List<Address> addressList;
    private String userId;
    private Address address;
    private double Service_range = 15;//正常距离
    private Double distance;
    private Double beyond;
    private EditText et_new_money;
    private String newmoney;
    private AlertDialog editDialog;
    private AlertDialog beyondDialog;
    private LinearLayout ll_new_money;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private UserInfo.UserInfoDean userInfo;
    private BottomSheetDialog bottomSheetDialog;
    private int state;//-1拒绝，1厂家寄件，2师傅寄件同意
    private Double freezingMoney;
    private ZLoadingDialog dialog ;

    public static OrderDetailFragment newInstance(String param1, String param2) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_accessories_list;
    }

//    @Override
//    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this);
////        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
//        mImmersionBar.statusBarView(mView);
//        mImmersionBar.keyboardEnable(true);
//        mImmersionBar.init();
//    }

    @Override
    protected void initData() {
        mToolbar.setVisibility(View.GONE);
        mView.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("订单详情");
        OrderID = mParam1;
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
//        mPresenter.GetOrderInfo(OrderID);

        mPresenter.GetAccountAddress(userId);
        mPresenter.GetOrderAccessoryMoney(OrderID);
        mPresenter.getOrderFreezing(OrderID);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                mPresenter.GetOrderInfo(OrderID);
                mPresenter.GetOrderAccessoryMoney(OrderID);
                mPresenter.getOrderFreezing(OrderID);
                mRefreshLayout.finishRefresh(3000);
            }
        });

        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void initView() {
        dialog = new ZLoadingDialog(mActivity); //loading
        showLoading();
//        mIvY.setSelected(true);
//        mIvN.setSelected(false);
//        mIvPay.setSelected(true);
//        mIvPay2.setSelected(false);
//        IsReturn = "1";
//        PostPayType = "1";
    }

    public void scaleview(String url) {
        simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                    super Bitmap> transition) {
                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                rxDialogScaleView.setImage(resource);
                rxDialogScaleView.show();
            }
        };

        Glide.with(mActivity)
                .asBitmap()
                .load(url)
                .into(simpleTarget);
    }

    @Override
    protected void setListener() {
        mTvSendAccessory.setOnClickListener(this);

        mIconBack.setOnClickListener(this);
//        mLlContactCustomerService.setOnClickListener(this);
        mTvReject.setOnClickListener(this);
        mTvPass.setOnClickListener(this);
        mTvPassService.setOnClickListener(this);
        mTvRejectService.setOnClickListener(this);

        mTvRejectBeyond.setOnClickListener(this);
        mTvPassBeyond.setOnClickListener(this);
        mIvRangeOne.setOnClickListener(this);

        mLlY.setOnClickListener(this);
        mLlN.setOnClickListener(this);
        mLlPay.setOnClickListener(this);
        mLlPay2.setOnClickListener(this);
        mTvModify.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);

        mTvApply.setOnClickListener(this);
        mIvBarCode.setOnClickListener(this);
        mIvMachine.setOnClickListener(this);
        mIvFaultLocation.setOnClickListener(this);
        mIvNewAndOldAccessories.setOnClickListener(this);
        mNegtive.setOnClickListener(this);

        mIvHost.setOnClickListener(this);
        mIvAccessories.setOnClickListener(this);

        mTvModifyBeyond.setOnClickListener(this);
        mIvCopy.setOnClickListener(this);
        mTvAbolition.setOnClickListener(this);
    }


    @SingleClick
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_copy:
                String id = mTvWorkOrderNumber.getText().toString();
                myClip = ClipData.newPlainText("", id);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
            case R.id.tv_send_accessory:
                expressno_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno2, null);
                btn_negtive = expressno_view.findViewById(R.id.negtive);
                btn_positive = expressno_view.findViewById(R.id.positive);
                tv_title = expressno_view.findViewById(R.id.title);
                tv_message = expressno_view.findViewById(R.id.message);
                et_expressno = expressno_view.findViewById(R.id.et_expressno);
                ll_scan = expressno_view.findViewById(R.id.ll_scan);
                expressno_dialog = new AlertDialog.Builder(mActivity)
                        .setView(expressno_view)
                        .create();
                expressno_dialog.show();
                tv_title.setText("提示");
                tv_message.setText("是否发送配件");
                ll_scan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scan();
                    }
                });
                btn_negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expressno_dialog.dismiss();
                    }
                });
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expressno = et_expressno.getText().toString();
                        if ("".equals(expressno)) {
                            expressno = "123";
                        }
                        mPresenter.AddOrUpdateExpressNo(OrderID, expressno);
                    }
                });
                break;
            case R.id.tv_apply:
                final CommonDialog_Home apply = new CommonDialog_Home(mActivity);
                apply.setMessage("是否要发起质保")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//发起质保
                        apply.dismiss();
                        mPresenter.ApplyCustomService(OrderID);
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        apply.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.negtive:
//                mPresenter.FactoryEnsureOrder(OrderID, "888888");
//                mPresenter.NowEnSureOrder(OrderID);
                mPresenter.GetUserInfoList(userId, "1");
                break;
            case R.id.iv_bar_code:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img1".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.iv_machine:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img2".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.iv_fault_location:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img3".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.iv_new_and_old_accessories:
                for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                    if ("img4".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                        scaleview("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl());
                    }
                }
                break;
            case R.id.ll_y:
                mIvY.setSelected(true);
                mIvN.setSelected(false);
                mLlAddressInfo.setVisibility(View.VISIBLE);
                IsReturn = "1";
                break;
            case R.id.ll_n:
                mIvY.setSelected(false);
                mIvN.setSelected(true);
                mLlAddressInfo.setVisibility(View.GONE);
                IsReturn = "2";
                break;
            case R.id.ll_pay:
                mIvPay.setSelected(true);
                mIvPay2.setSelected(false);
                PostPayType = "1";
                break;
            case R.id.ll_pay2:
                mIvPay.setSelected(false);
                mIvPay2.setSelected(true);
                PostPayType = "2";
                break;
            case R.id.tv_modify:
                Intent intent = new Intent(mActivity, ShippingAddressActivity.class);
                intent.putExtra("type", "0");
                startActivityForResult(intent, 1000);
                break;
            case R.id.tv_submit:
                mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
                break;
            case R.id.iv_range_one:
                if (data.getOrderBeyondImg() == null) {
                    return;
                }
                if (data.getOrderBeyondImg().size() == 0) {
                    return;
                }
                simpleTarget = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                            super Bitmap> transition) {
                        RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                        rxDialogScaleView.setImage(resource);
                        rxDialogScaleView.show();
                    }
                };

                Glide.with(mActivity)
                        .asBitmap()
                        .load("https://img.xigyu.com/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(0).getUrl())
                        .into(simpleTarget);
                break;
            case R.id.iv_host:
                if (data.getOrderAccessroyDetail() == null) {
                    return;
                }
                if (data.getOrderAccessroyDetail().size() == 0) {
                    return;
                }
                simpleTarget = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                            super Bitmap> transition) {
                        RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                        rxDialogScaleView.setImage(resource);
                        rxDialogScaleView.show();
                    }
                };

                Glide.with(mActivity)
                        .asBitmap()
                        .load("https://img.xigyu.com/Pics/Accessory/" + data.getOrderAccessroyDetail().get(0).getPhoto1())
                        .into(simpleTarget);
                break;
            case R.id.iv_accessories:
                if (data.getOrderAccessroyDetail() == null) {
                    return;
                }
                if (data.getOrderAccessroyDetail().size() == 0) {
                    return;
                }
                simpleTarget = new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                            super Bitmap> transition) {
                        RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                        rxDialogScaleView.setImage(resource);
                        rxDialogScaleView.show();
                    }
                };

                Glide.with(mActivity)
                        .asBitmap()
                        .load("https://img.xigyu.com/Pics/Accessory/" + data.getOrderAccessroyDetail().get(0).getPhoto2())
                        .into(simpleTarget);
                break;
            case R.id.ll_contact_customer_service:
                final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                dialog.setMessage("是否拨打电话给客服")
                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {//拨打电话
                        dialog.dismiss();
                        call("tel:" + "4006262365");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        dialog.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_reject:
                if ("厂家寄件".equals(data.getAccessorySequencyStr())) {
                    reject = new CommonDialog_Home(mActivity);
                    reject.setMessage("是否拒绝申请")

                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            reject.dismiss();
                            state = -1;
//                        mPresenter.ApproveOrderAccessory(OrderID, "-1", "0",data.getOrderAccessroyDetail().get(position).getId());
                            mPresenter.ApproveOrderAccessoryAndService(OrderID, "-1", "", "");
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            reject.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                } else if ("师傅自购件".equals(data.getAccessorySequencyStr())) {

                    reject = new CommonDialog_Home(mActivity);
                    reject.setMessage("如您拒绝师傅自购件，请点击厂家寄件")

                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setPositive("厂家寄件")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {
                            reject.dismiss();
                            if (data.getOrderAccessroyDetail().size() > 0) {
                                if (!"2".equals(data.getTypeID())) {
                                    if ("1".equals(IsReturn)) {
                                        if ("".equals(AddressBack)) {
                                            MyUtils.showToast(mActivity, "请添加旧件寄送地址");
                                            return;
                                        } else {
                                            if ("".equals(PostPayType)) {
                                                MyUtils.showToast(mActivity, "请选择邮费方式");
                                                return;

                                            } else {
                                                state = 1;
                                                mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
//                                                mPresenter.ApproveOrderAccessoryAndService(OrderID, "2", PostPayType, IsReturn);
                                            }

                                        }
                                    } else if ("".equals(IsReturn)) {
                                        MyUtils.showToast(mActivity, "请选择是否需要返件");
                                        return;
                                    } else {
//                                        mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
                                        state = 1;
                                        mPresenter.ApproveOrderAccessoryAndService(OrderID, "2", PostPayType, IsReturn);
                                    }
                                }
                            }

//                        mPresenter.ApproveOrderAccessory(OrderID, "-1", "0",data.getOrderAccessroyDetail().get(position).getId());

                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            reject.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }

                break;
            case R.id.tv_pass:

                if ("0".equals(data.getAccessoryAndServiceApplyState())) {
                    expressno_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno, null);
                    btn_negtive = expressno_view.findViewById(R.id.negtive);
                    btn_positive = expressno_view.findViewById(R.id.positive);
                    tv_title = expressno_view.findViewById(R.id.title);
                    tv_message = expressno_view.findViewById(R.id.message);
                    et_expressno = expressno_view.findViewById(R.id.et_expressno);
                    et_new_money = expressno_view.findViewById(R.id.et_new_money);
                    ll_scan = expressno_view.findViewById(R.id.ll_scan);
                    ll_new_money = expressno_view.findViewById(R.id.ll_new_money);
                    ll_new_money.setVisibility(View.GONE);
                    expressno_dialog = new AlertDialog.Builder(mActivity)
                            .setView(expressno_view)
                            .create();
                    expressno_dialog.show();
                    tv_title.setText("提示");
                    tv_message.setText("是否同意申请");
                    ll_scan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            scan();
                        }
                    });
                    btn_negtive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            expressno_dialog.dismiss();
                        }
                    });
                    btn_positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            expressno = et_expressno.getText().toString();
//                            newmoney = et_new_money.getText().toString();
//                            if ("".equals(expressno)) {
//                                expressno = "123";
//                            }
//                            if ("".equals(newmoney)) {
//                                newmoney = "0";
//                            }
                            if ("师傅自购件".equals(data.getAccessorySequencyStr())) {
                                state = 2;
                            } else {
                                state = 1;
                            }

                            expressno_dialog.dismiss();
                            if (data.getOrderAccessroyDetail().size() > 0) {
                                if (!"2".equals(data.getTypeID())) {
                                    if ("1".equals(IsReturn)) {
                                        if ("".equals(AddressBack)) {
                                            MyUtils.showToast(mActivity, "请添加旧件寄送地址");
                                            return;
                                        } else {
                                            if ("".equals(PostPayType)) {
                                                MyUtils.showToast(mActivity, "请选择邮费方式");
                                                return;

                                            } else {
                                                mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
//                                                mPresenter.ApproveOrderAccessoryAndService(OrderID, "1", PostPayType, IsReturn);
                                            }

                                        }
                                    } else if ("".equals(IsReturn)) {
                                        MyUtils.showToast(mActivity, "请选择是否需要返件");
                                        return;
                                    } else {
                                        mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
//                                        mPresenter.ApproveOrderAccessoryAndService(OrderID, "1", PostPayType, IsReturn);
                                    }
                                }
                            } else {
                                mPresenter.ApproveOrderAccessoryAndService(OrderID, "1", PostPayType, IsReturn);

                            }

//                            mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney,data.getOrderAccessroyDetail().get(position).getId());
//                            mPresenter.AddOrUpdateExpressNo(OrderID, expressno);
                        }
                    });
                } else if ("1".equals(data.getAccessoryAndServiceApplyState())) {
                    expressno_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_newmoney, null);
                    btn_negtive = expressno_view.findViewById(R.id.negtive);
                    btn_positive = expressno_view.findViewById(R.id.positive);
                    tv_title = expressno_view.findViewById(R.id.title);
                    tv_message = expressno_view.findViewById(R.id.message);
                    et_new_money = expressno_view.findViewById(R.id.et_new_money);
//                    et_expressno = expressno_view.findViewById(R.id.et_expressno);
//                    ll_scan = expressno_view.findViewById(R.id.ll_scan);
                    expressno_dialog = new AlertDialog.Builder(mActivity)
                            .setView(expressno_view)
                            .create();
                    expressno_dialog.show();
                    tv_title.setText("提示");
                    tv_message.setText("是否同意申请的配件");
//                    ll_scan.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            scan();
//                        }
//                    });
                    btn_negtive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            expressno_dialog.dismiss();
                        }
                    });
                    btn_positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            newmoney = et_new_money.getText().toString();
                            if ("".equals(newmoney)) {
                                newmoney = "0";
                            }
                            expressno_dialog.dismiss();
//                            mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney,data.getOrderAccessroyDetail().get(position).getId());
                        }
                    });
                } else {
//                    mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney,data.getOrderAccessroyDetail().get(position).getId());
                }

                break;

            case R.id.tv_reject_service:
                reject = new CommonDialog_Home(mActivity);
                reject.setMessage("是否拒绝申请的服务")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        reject.dismiss();
//                        mPresenter.ApproveOrderService(OrderID, "-1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        reject.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_pass_service:
                pass = new CommonDialog_Home(mActivity);
                pass.setMessage("是否同意申请的服务")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        pass.dismiss();
//                        mPresenter.ApproveOrderService(OrderID, "1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        pass.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_reject_beyond:
                reject = new CommonDialog_Home(mActivity);
                reject.setMessage("是否拒绝申请的远程费")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        reject.dismiss();
                        mPresenter.ApproveBeyondMoney(OrderID, "-1", "");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        reject.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_pass_beyond:
                pass = new CommonDialog_Home(mActivity);
                pass.setMessage("是否同意申请的远程费")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        pass.dismiss();
                        mPresenter.ApproveBeyondMoney(OrderID, "1", "");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        pass.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_modify_beyond:
                View BeyondView = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_newmoney, null);
                TextView message = BeyondView.findViewById(R.id.message);
                TextView title = BeyondView.findViewById(R.id.title);
                EditText et_new_money = BeyondView.findViewById(R.id.et_new_money);
                Button negtive = BeyondView.findViewById(R.id.negtive);
                Button positive = BeyondView.findViewById(R.id.positive);
                title.setText("提示");
                message.setText("若您对师傅申请的远程费价格不满意，请您修改您满意的远程费价格");

                negtive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        beyondDialog.dismiss();
                    }
                });

                positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String money = et_new_money.getText().toString();
                        if (money.isEmpty()) {
                            ToastUtils.showShort("请输入修改的金额");
                        } else {
                            mPresenter.ApproveBeyondMoney(OrderID, "2", money);
                            beyondDialog.dismiss();
                        }
                    }
                });

                beyondDialog = new AlertDialog.Builder(mActivity).setView(BeyondView).create();
                beyondDialog.show();
                Window window = beyondDialog.getWindow();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(lp);
                break;
            case R.id.tv_abolition:
                mPresenter.ApplyCancelOrder(OrderID);
                break;
        }
    }

    /**
     * 扫描二维码
     */
    public void scan() {
//        IntentIntegrator integrator = new IntentIntegrator(mActivity);
        IntentIntegrator integrator = IntentIntegrator.forSupportFragment(this);
        // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setCaptureActivity(ScanActivity.class); //设置打开摄像头的Activity
        integrator.setPrompt("请扫描快递码"); //底部的提示文字，设为""可以置空
        integrator.setCameraId(0); //前置或者后置摄像头
        integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        mRefreshLayout.finishRefresh();
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                mTvOrderState.setText(data.getState());
                mTvName.setText(data.getUserName());

//                mTvBeyondMoney.setText("¥" + data.getBeyondMoney() + "");
//                mTvAccessoryMoney.setText("¥" + data.getAccessoryMoney());
//                mTvServiceMoney.setText("¥" + data.getServiceMoney());
//                mTvBeyondMoney.setVisibility(View.GONE);
//                mTvAccessoryMoney.setVisibility(View.GONE);
//                mTvServiceMoney.setVisibility(View.GONE);

                if (data.getOrderAccessroyDetail().size() > 0) {
                    Glide.with(mActivity).load("https://img.xigyu.com/Pics/Accessory/" + data.getOrderAccessroyDetail().get(0).getPhoto1()).into(mIvHost);
                    Glide.with(mActivity).load("https://img.xigyu.com/Pics/Accessory/" + data.getOrderAccessroyDetail().get(0).getPhoto2()).into(mIvAccessories);

                } else {
                    mLlHost.setVisibility(View.GONE);
                    mLlAccessories.setVisibility(View.GONE);
                }

                Double money = Double.parseDouble(data.getOrderMoney()) - Double.parseDouble(data.getBeyondMoney()) - Double.parseDouble(data.getExtraFee()) - Double.parseDouble(data.getPostMoney());
                if ("0.00".equals(data.getBeyondMoney())) {
                    mLlBeyondMoney.setVisibility(View.GONE);
                } else {
                    mLlBeyondMoney.setVisibility(View.VISIBLE);
                    mTvBeyondMoney.setText("¥" + data.getBeyondMoney());
                }

                if ("0.00".equals(data.getPostMoney())) {
                    mLlPostage.setVisibility(View.GONE);
                } else {
                    mLlPostage.setVisibility(View.VISIBLE);
                    mTvPostage.setText("¥" + data.getPostMoney());
                }

                if ("0.0".equals(data.getExtraFee())) {
                    mLlExpeditedFee.setVisibility(View.GONE);
                } else {
                    mLlExpeditedFee.setVisibility(View.VISIBLE);
                    mTvExpeditedFee.setText("¥" + data.getExtraFee());
                }

                if ("3".equals(data.getTypeID())) {
                    mTvOrderMoney.setText("¥" + data.getQuaMoney() + "");
                } else if ("2".equals(data.getTypeID())) {
                    mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
                } else {
//                    if (data.getAccessoryMoney() != null && !"0.00".equals(data.getAccessoryMoney())) {
//                    if ("1".equals(data.getAccessoryApplyState())) {
//                        mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
////                        mTvOrderMoney.setText("¥" + (Double.parseDouble(data.getAccessoryMoney()) + Double.parseDouble(data.getBeyondMoney()) + Double.parseDouble(data.getPostMoney())) + "");
//                    } else {
//                        mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
//                    }

//                    if ("待评价".equals(data.getState())||"服务完成".equals(data.getState())||"已完成".equals(data.getState())){
//                        mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
//
//                        mTvServiceMoney.setText("¥" +money+"");
//                        mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
//                    }else {
//                        if ("1".equals(data.getAccessoryAndServiceApplyState())||"2".equals(data.getAccessoryAndServiceApplyState())){
//                            mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
//                        }
//                    }
                    if ("待接单".equals(data.getState())||"已接单待联系客户".equals(data.getState())){
                        mLlContactCustomerService.setVisibility(View.VISIBLE);
                    }else {
                        mLlContactCustomerService.setVisibility(View.GONE);
                    }

                    if ("待接单".equals(data.getState()) || "已接单待联系客户".equals(data.getState()) || "已联系客户待服务".equals(data.getState()) || "远程费审核".equals(data.getState())) {
                        Double Free = freezingMoney + Double.parseDouble(data.getBeyondMoney()) + Double.parseDouble(data.getExtraFee());
                        mTvOrderMoney.setText("¥" + Free);
                        mTvOrderName.setText("维修单预冻结费：");
                        mLlServiceMoney.setVisibility(View.GONE);
                    } else if ("待审核".equals(data.getState())) {
                        mTvOrderMoney.setText("¥" + data.getExamineMoney() + "");
                        mLlServiceMoney.setVisibility(View.VISIBLE);
                        Double servicemoney = Double.parseDouble(data.getExamineMoney()) - Double.parseDouble(data.getBeyondMoney()) - Double.parseDouble(data.getExtraFee()) - Double.parseDouble(data.getPostMoney());
                        mTvServiceMoney.setText("¥" + servicemoney);
                    } else {
                        mTvOrderMoney.setText("¥" + data.getOrderMoney() + "");
                        mLlServiceMoney.setVisibility(View.VISIBLE);
                        mTvServiceMoney.setText("¥" + money);
                    }
                }

                if (!"0.00".equals(data.getPostMoney()) && data.getPostMoney() != null) {
                    mLlPostMoney.setVisibility(View.VISIBLE);
                    mTvPostMoney.setText("¥" + data.getPostMoney());
                } else {
                    mLlPostMoney.setVisibility(View.GONE);
                }

//                if ("保内".equals(data.getGuarantee())) {
//                    mIvY.setSelected(true);
//                    mIvN.setSelected(false);
//                    mLlAddressInfo.setVisibility(View.VISIBLE);
//                    IsReturn = "1";
//                } else {
//                    mIvY.setSelected(false);
//                    mIvN.setSelected(true);
//                    mLlAddressInfo.setVisibility(View.GONE);
//                    IsReturn = "2";
//                }

                if (!"".equals(data.getAccessorySequencyStr())) {
                    mTvAccessorySequency.setText(data.getAccessorySequencyStr());
                } else {
                    mLlAccessorySequency.setVisibility(View.GONE);
                }

                mTvAccessoryMemo.setText("备注：" + data.getAccessoryMemo());

                mTvSendAddress.setText("寄件地址：" + data.getSendAddress());
                mTvPhone.setText(data.getPhone());
                mTvAddress.setText(data.getAddress());
                mTvTime.setText(data.getCreateDate());
                mTvWorkOrderStatus.setText(data.getState());
                mTvWorkOrderNumber.setText(data.getOrderID());
                mTvWarrantyType.setText(data.getGuarantee() + data.getTypeName());
                mTvWorkOrderType.setText(data.getTypeName());
                mTvRecoveryTime.setText(data.getRecycleOrderHour());
                mTvSentOutAccessories.setText(data.getIsRecevieGoods());
                mTvBrand.setText(data.getBrandName());
                mTvCategory.setText(data.getSubCategoryName());
                mTvModel.setText(data.getProductType());
                mTvFaultDescription.setText(data.getMemo());

                mTvSpecifyDoorToDoorTime.setText(data.getExtraTime());
                mTvOrderSource.setText(data.getPartyNo());
                mTvThirdParty.setText(data.getThirdPartyNo());

                if ("安装".equals(data.getTypeName())) {
                    mTvDescription.setText("安装备注");
                } else {
                    mTvDescription.setText("故障描述");
                }

                if ("1".equals(data.getServiceApplyState())) {
                    mTvPassService.setVisibility(View.GONE);
                    mTvRejectService.setVisibility(View.GONE);
                    mTvStatusService.setVisibility(View.VISIBLE);
                    mTvStatusService.setText("已审核通过");
                } else if ("-1".equals(data.getServiceApplyState())) {
                    mTvPassService.setVisibility(View.GONE);
                    mTvRejectService.setVisibility(View.GONE);
                    mTvStatusService.setVisibility(View.VISIBLE);
                    mTvStatusService.setText("已拒绝");
                } else {
                    mTvPassService.setVisibility(View.VISIBLE);
                    mTvRejectService.setVisibility(View.VISIBLE);
                    mTvStatusService.setVisibility(View.GONE);
                }
                if (data.getOrderServiceDetail() == null) {
                    mLlAuditService.setVisibility(View.GONE);
                } else {
                    if (data.getOrderServiceDetail().size() == 0) {
                        mLlAuditService.setVisibility(View.GONE);
                    } else {
                        mLlAuditService.setVisibility(View.GONE);
                        serviceAdapter = new ServiceAdapter(R.layout.item_accessories, data.getOrderServiceDetail());
                        mRvService.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvService.setAdapter(serviceAdapter);
                        serviceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.tv_reject:
                                        reject = new CommonDialog_Home(mActivity);
                                        reject.setMessage("是否拒绝申请的服务")

                                                //.setImageResId(R.mipmap.ic_launcher)
                                                .setTitle("提示")
                                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                reject.dismiss();
                                                mPresenter.ApproveOrderService(OrderID, "-1", data.getOrderServiceDetail().get(position).getOrderServiceID());
                                            }

                                            @Override
                                            public void onNegtiveClick() {//取消
                                                reject.dismiss();
                                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                                            }
                                        }).show();
                                        break;
                                    case R.id.tv_pass:
                                        pass = new CommonDialog_Home(mActivity);
                                        pass.setMessage("是否同意申请的服务")

                                                //.setImageResId(R.mipmap.ic_launcher)
                                                .setTitle("提示")
                                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                pass.dismiss();
                                                mPresenter.ApproveOrderService(OrderID, "1", data.getOrderServiceDetail().get(position).getOrderServiceID());
                                            }

                                            @Override
                                            public void onNegtiveClick() {//取消
                                                pass.dismiss();
                                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                                            }
                                        }).show();
                                        break;
                                }
                            }
                        });
                    }

                }
                if ("关闭工单".equals(data.getState())) {
                    mLlApproveAccessory.setVisibility(View.GONE);
                    mLlServiceItem.setVisibility(View.GONE);
                    mLlOldAccessory.setVisibility(View.GONE);
                    mLlSendAccessory.setVisibility(View.GONE);
                    mTvSendAddress.setVisibility(View.GONE);
                    mTvClose.setVisibility(View.VISIBLE);
                } else {
                    if (data.getAccessoryAndServiceApplyState() == null) {
                        mLlApproveAccessory.setVisibility(View.GONE);
                        mLlServiceItem.setVisibility(View.GONE);
                        mLlOldAccessory.setVisibility(View.GONE);
                        mLlSendAccessory.setVisibility(View.GONE);
                        mTvSendAddress.setVisibility(View.GONE);
                        mTvClose.setVisibility(View.GONE);
                    } else {
//                    if (data.getOrderAccessroyDetail().size() == 0) {
//                        mLlApproveAccessory.setVisibility(View.GONE);
//                        mLlOldAccessory.setVisibility(View.GONE);
//                        mLlSendAccessory.setVisibility(View.GONE);
//                    } else {


                        if ("1".equals(data.getAccessoryAndServiceApplyState())) {
                            mTvPass.setVisibility(View.GONE);
                            mTvReject.setVisibility(View.GONE);
                            mLlServiceItem.setVisibility(View.GONE);
                            mTvStatusAccessory.setVisibility(View.VISIBLE);
                            mTvClose.setVisibility(View.GONE);
                            mTvStatusAccessory.setText("已审核通过");
                            if ("0".equals(data.getAccessoryState())) {

                                if (data.getOrderAccessroyDetail().size() > 0) {
                                    for (int i = 0; i < data.getOrderAccessroyDetail().size(); i++) {
                                        if ("".equals(data.getOrderAccessroyDetail().get(i).getExpressNo())) {
                                            mLlSendAccessory.setVisibility(View.VISIBLE);
                                            mLlOldAccessory.setVisibility(View.VISIBLE);
                                        } else {
                                            mLlSendAccessory.setVisibility(View.GONE);
                                            mLlOldAccessory.setVisibility(View.GONE);
                                        }
                                    }
                                } else {
                                    mLlSendAccessory.setVisibility(View.GONE);
                                    mLlOldAccessory.setVisibility(View.GONE);
                                }
                            } else {
                                mLlSendAccessory.setVisibility(View.GONE);
                                mLlOldAccessory.setVisibility(View.GONE);
                            }

                            if (data.getOrderAccessroyDetail().size() > 0) {
                                mLlOldAccessory.setVisibility(View.GONE);
                                mLlReturn.setVisibility(View.VISIBLE);
                            } else {
                                mLlOldAccessory.setVisibility(View.GONE);
                                mLlReturn.setVisibility(View.GONE);
                            }

                            if (data.getIsReturn() != null) {
                                if ("1".equals(data.getIsReturn())) {
                                    mTvAddressback2.setText(data.getAddressBack());
                                    mLlAddressInfo.setVisibility(View.VISIBLE);
                                    mLlY.setVisibility(View.GONE);
                                    mIvY.setVisibility(View.GONE);
                                    mLlN.setVisibility(View.GONE);
                                    mTvModify.setVisibility(View.GONE);
                                    mTvY.setText("是");
                                    mTvY.setVisibility(View.VISIBLE);
                                    if ("1".equals(data.getPostPayType())) {
                                        mLlPay.setVisibility(View.GONE);
                                        mIvPay.setVisibility(View.GONE);
                                        mLlPay2.setVisibility(View.GONE);
                                        mTvPost.setText("厂商到付");
                                        mTvPost.setVisibility(View.VISIBLE);
                                    } else {
                                        mLlPay.setVisibility(View.GONE);
                                        mIvPay2.setVisibility(View.GONE);
                                        mLlPay2.setVisibility(View.GONE);
                                        mTvPost.setVisibility(View.VISIBLE);
                                        mTvPost.setText("师傅现付");
                                    }
                                } else {
                                    mLlAddressInfo.setVisibility(View.GONE);
                                    mLlY.setVisibility(View.GONE);
                                    mIvN.setVisibility(View.GONE);
                                    mLlN.setVisibility(View.GONE);
                                    mTvY.setVisibility(View.VISIBLE);
                                    mTvY.setText("否");
                                    mLlPost.setVisibility(View.GONE);
                                    mLlAddress.setVisibility(View.GONE);
                                }
                            }
                        } else if ("-1".equals(data.getAccessoryAndServiceApplyState())) {
                            mTvPass.setVisibility(View.GONE);
                            mTvReject.setVisibility(View.GONE);
                            mLlServiceItem.setVisibility(View.GONE);
                            mTvStatusAccessory.setVisibility(View.VISIBLE);
                            mTvStatusAccessory.setText("已拒绝");
                            mLlOldAccessory.setVisibility(View.GONE);
                            mLlSendAccessory.setVisibility(View.GONE);
                            mTvClose.setVisibility(View.GONE);
                        } else if ("2".equals(data.getAccessoryAndServiceApplyState())) {
                            mTvPass.setVisibility(View.GONE);
                            mTvReject.setVisibility(View.GONE);
                            mLlServiceItem.setVisibility(View.GONE);
                            mTvStatusAccessory.setVisibility(View.VISIBLE);
                            mTvClose.setVisibility(View.GONE);
                            mTvStatusAccessory.setText("厂家寄件");
                            if (data.getOrderAccessroyDetail().size() > 0) {
                                for (int i = 0; i < data.getOrderAccessroyDetail().size(); i++) {
                                    if ("".equals(data.getOrderAccessroyDetail().get(i).getExpressNo())) {
                                        mLlSendAccessory.setVisibility(View.VISIBLE);
                                        mLlOldAccessory.setVisibility(View.VISIBLE);
                                    } else {
                                        mLlSendAccessory.setVisibility(View.GONE);
                                        mLlOldAccessory.setVisibility(View.GONE);
                                    }
                                }
                            } else {
                                mLlSendAccessory.setVisibility(View.GONE);
                                mLlOldAccessory.setVisibility(View.GONE);
                            }

                            if (data.getOrderAccessroyDetail().size() > 0) {
                                mLlOldAccessory.setVisibility(View.GONE);
                                mLlReturn.setVisibility(View.VISIBLE);
                            } else {
                                mLlOldAccessory.setVisibility(View.GONE);
                                mLlReturn.setVisibility(View.GONE);
                            }

                            if (data.getIsReturn() != null) {
                                if ("1".equals(data.getIsReturn())) {
                                    mTvAddressback2.setText(data.getAddressBack());
                                    mLlAddressInfo.setVisibility(View.VISIBLE);
                                    mLlY.setVisibility(View.GONE);
                                    mIvY.setVisibility(View.GONE);
                                    mLlN.setVisibility(View.GONE);
                                    mTvModify.setVisibility(View.GONE);
                                    mTvY.setText("是");
                                    mTvY.setVisibility(View.VISIBLE);
                                    if ("1".equals(data.getPostPayType())) {
                                        mLlPay.setVisibility(View.GONE);
                                        mIvPay.setVisibility(View.GONE);
                                        mLlPay2.setVisibility(View.GONE);
                                        mTvPost.setText("厂商到付");
                                        mTvPost.setVisibility(View.VISIBLE);
                                    } else {
                                        mLlPay.setVisibility(View.GONE);
                                        mIvPay2.setVisibility(View.GONE);
                                        mLlPay2.setVisibility(View.GONE);
                                        mTvPost.setVisibility(View.VISIBLE);
                                        mTvPost.setText("师傅现付");
                                    }
                                } else {
                                    mLlAddressInfo.setVisibility(View.GONE);
                                    mLlY.setVisibility(View.GONE);
                                    mIvN.setVisibility(View.GONE);
                                    mLlN.setVisibility(View.GONE);
                                    mTvY.setVisibility(View.VISIBLE);
                                    mTvY.setText("否");
                                    mLlPost.setVisibility(View.GONE);
                                    mLlAddress.setVisibility(View.GONE);
                                }
                            }
                        } else {
                            mTvPass.setVisibility(View.VISIBLE);
                            mTvReject.setVisibility(View.VISIBLE);
                            mLlServiceItem.setVisibility(View.VISIBLE);
                            mTvStatusAccessory.setVisibility(View.GONE);
                            mLlSendAccessory.setVisibility(View.GONE);
                            mTvClose.setVisibility(View.GONE);
                        }

//                        if ("4".equals(data.getOrderAccessroyDetail().get(0).getSizeID())) {
//                            mLlApproveAccessory.setVisibility(View.GONE);
//                            mLlSendAccessory.setVisibility(View.GONE);
//                            mLlOldAccessory.setVisibility(View.GONE);
//                        } else {
//                            mLlApproveAccessory.setVisibility(View.VISIBLE);
//                        }
//                    mLlOldAccessory.setVisibility(View.VISIBLE);
                        for (int i = 0; i < data.getOrderAccessroyDetail().size(); i++) {
                            if ("2".equals(data.getOrderAccessroyDetail().get(i).getState())) {
                                data.getOrderAccessroyDetail().remove(i);
                            }
                        }
                        accessoryDetailAdapter = new AccessoryDetailAdapter(R.layout.item_accessories, data.getOrderAccessroyDetail(), data.getAccessoryState());
                        mRvAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvAccessories.setAdapter(accessoryDetailAdapter);
                        accessoryDetailAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.tv_pass:
                                        if (!"2".equals(data.getTypeID())) {
                                            if ("1".equals(IsReturn)) {
                                                if ("".equals(AddressBack)) {
                                                    MyUtils.showToast(mActivity, "请添加旧件寄送地址");
                                                    return;
                                                } else {
                                                    mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
                                                }
                                            } else {
                                                mPresenter.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType);
                                            }
                                        }
//                                        showEdit(data.getOrderAccessroyDetail(),position);
                                        reject = new CommonDialog_Home(mActivity);
                                        reject.setMessage("是否同意申请的配件")

                                                //.setImageResId(R.mipmap.ic_launcher)
                                                .setTitle("提示")
                                                .setPositive("确定")
                                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                reject.dismiss();
                                                mPresenter.ApproveOrderAccessory(OrderID, "1", "0", data.getOrderAccessroyDetail().get(position).getId());
                                            }

                                            @Override
                                            public void onNegtiveClick() {//取消
                                                reject.dismiss();
                                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                                            }
                                        }).show();
                                        break;
                                    case R.id.tv_reject:
                                        reject = new CommonDialog_Home(mActivity);
                                        reject.setMessage("是否拒绝申请的配件")

                                                //.setImageResId(R.mipmap.ic_launcher)
                                                .setTitle("提示")
                                                .setPositive("确定")
                                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                reject.dismiss();
                                                mPresenter.ApproveOrderAccessory(OrderID, "-1", "0", data.getOrderAccessroyDetail().get(position).getId());
                                            }

                                            @Override
                                            public void onNegtiveClick() {//取消
                                                reject.dismiss();
                                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                                            }
                                        }).show();
                                        break;
                                    case R.id.iv_host:
                                        if (data.getOrderAccessroyDetail() == null) {
                                            return;
                                        }
                                        if (data.getOrderAccessroyDetail().size() == 0) {
                                            return;
                                        }
                                        simpleTarget = new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                                                    super Bitmap> transition) {
                                                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                                                rxDialogScaleView.setImage(resource);
                                                rxDialogScaleView.show();
                                            }
                                        };

                                        Glide.with(mActivity)
                                                .asBitmap()
                                                .load("https://img.xigyu.com/Pics/Accessory/" + data.getOrderAccessroyDetail().get(position).getPhoto1())
                                                .into(simpleTarget);
                                        break;
                                    case R.id.iv_accessories:
                                        if (data.getOrderAccessroyDetail() == null) {
                                            return;
                                        }
                                        if (data.getOrderAccessroyDetail().size() == 0) {
                                            return;
                                        }
                                        simpleTarget = new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<?
                                                    super Bitmap> transition) {
                                                RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mActivity);
                                                rxDialogScaleView.setImage(resource);
                                                rxDialogScaleView.show();
                                            }
                                        };

                                        Glide.with(mActivity)
                                                .asBitmap()
                                                .load("https://img.xigyu.com/Pics/Accessory/" + data.getOrderAccessroyDetail().get(position).getPhoto2())
                                                .into(simpleTarget);
                                        break;
                                }
                            }
                        });
//                    }
                    }

                }
                if (data.getBeyondState() != null) {
                    mLlApproveBeyondMoney.setVisibility(View.VISIBLE);
                } else {
                    mLlApproveBeyondMoney.setVisibility(View.GONE);
                }
                distance = Double.parseDouble(data.getDistance());
                beyond = Double.parseDouble(data.getBeyondDistance());
                if (distance.equals(beyond)) {
                    mTvRange.setText(String.format("%.2f", beyond - Service_range));
                } else {
                    mTvRange.setText(String.format("%.2f", beyond));
                }

                if ("关闭工单".equals(data.getState())) {
                    mTvPassBeyond.setVisibility(View.GONE);
                    mTvRejectBeyond.setVisibility(View.GONE);
                    mTvModifyBeyond.setVisibility(View.GONE);
                    mTvStatus.setVisibility(View.GONE);
                } else {

                    if ("1".equals(data.getBeyondState())) {
                        mTvPassBeyond.setVisibility(View.GONE);
                        mTvRejectBeyond.setVisibility(View.GONE);
                        mTvModifyBeyond.setVisibility(View.GONE);
                        mTvStatus.setVisibility(View.VISIBLE);
                        mTvStatus.setText("已审核通过");
                    } else if ("-1".equals(data.getBeyondState())) {
                        mTvPassBeyond.setVisibility(View.GONE);
                        mTvRejectBeyond.setVisibility(View.GONE);
                        mTvModifyBeyond.setVisibility(View.GONE);
                        mTvStatus.setVisibility(View.VISIBLE);
                        mTvStatus.setText("已拒绝");
                    } else if ("2".equals(data.getBeyondState())) {
                        mTvPassBeyond.setVisibility(View.GONE);
                        mTvRejectBeyond.setVisibility(View.GONE);
                        mTvModifyBeyond.setVisibility(View.GONE);
                        mTvStatus.setVisibility(View.VISIBLE);
                        mTvStatus.setText("已修改");
                    } else {
                        mTvPassBeyond.setVisibility(View.VISIBLE);
                        mTvRejectBeyond.setVisibility(View.VISIBLE);
                        mTvModifyBeyond.setVisibility(View.VISIBLE);
                        mTvStatus.setVisibility(View.GONE);
                    }

                }
                if (data.getOrderBeyondImg() == null) {
                    return;
                }
                if (data.getOrderBeyondImg().size() == 1) {
                    Glide.with(mActivity).load("https://img.xigyu.com/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(0).getUrl()).into(mIvRangeOne);
//                    Glide.with(mActivity).load("https://img.xigyu.com/Pics/OrderByondImg/" + data.getOrderBeyondImg().get(1).getUrl()).into(mIvRangeTwo);
                    mIvRangeOne.setVisibility(View.VISIBLE);
                    mIvRangeTwo.setVisibility(View.GONE);
                } else {
                    mIvRangeOne.setVisibility(View.GONE);
                    mIvRangeTwo.setVisibility(View.GONE);
                }
                if ("服务完成".equals(data.getState()) || "待评价".equals(data.getState()) || "已完成".equals(data.getState())) {
                    if ("已完成".equals(data.getState())) {
                        mLlApplyCustomService.setVisibility(View.VISIBLE);
                    } else {
                        mLlApplyCustomService.setVisibility(View.GONE);
                    }
                    if ("服务完成".equals(data.getState())) {
                        if ("1".equals(data.getIsReturn())) {
                            if ("".equals(data.getReturnAccessoryMsg()) || data.getReturnAccessoryMsg() == null) {
                                mLlConfirm.setVisibility(View.GONE);
                            } else {
                                mLlConfirm.setVisibility(View.VISIBLE);
                            }
                        } else {
                            mLlConfirm.setVisibility(View.VISIBLE);
                        }
                    } else {
                        mLlConfirm.setVisibility(View.GONE);
                    }
                    mLlReturnInformation.setVisibility(View.VISIBLE);
                    if ("".equals(data.getEndRemark())) {
                        mLlMessage.setVisibility(View.GONE);
                    } else {
                        mLlMessage.setVisibility(View.VISIBLE);
                        mTvMessage.setText(data.getEndRemark());
                    }
                    if ("1".equals(data.getTypeID()) || "3".equals(data.getTypeID())) {//维修
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                            if ("img1".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvBarCode);
                            }
                            if ("img2".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvMachine);
                            }
                            if ("img3".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvFaultLocation);
                            }
                            if ("img4".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(i).getUrl()).into(mIvNewAndOldAccessories);
                            }
                            list.add(data.getReturnaccessoryImg().get(i).getRelation());
                        }
                        if (!list.contains("img1")) {
                            mLlBarCode.setVisibility(View.GONE);
                        }
                        if (!list.contains("img2")) {
                            mLlMachine.setVisibility(View.GONE);
                        }
                        if (!list.contains("img3")) {
                            mLlFaultLocation.setVisibility(View.GONE);
                        }
                        if (!list.contains("img4")) {
                            mLlNewAndOldAccessories.setVisibility(View.GONE);
                        }
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(0).getUrl()).into(mIvBarCode);
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(1).getUrl()).into(mIvMachine);
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(2).getUrl()).into(mIvFaultLocation);
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(3).getUrl()).into(mIvNewAndOldAccessories);
                    } else {
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/FinishOrder/" + data.getOrderImg().get(0).getUrl()).into(mIvBarCode);
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/FinishOrder/" + data.getOrderImg().get(1).getUrl()).into(mIvMachine);
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/FinishOrder/" + data.getOrderImg().get(2).getUrl()).into(mIvFaultLocation);
//                        Glide.with(mActivity).load("https://img.xigyu.com/Pics/FinishOrder/" + data.getOrderImg().get(3).getUrl()).into(mIvNewAndOldAccessories);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < data.getReturnaccessoryImg().size(); i++) {
                            if ("img1".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(0).getUrl()).into(mIvBarCode);
                            }
                            if ("img2".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(1).getUrl()).into(mIvMachine);

                            }
                            if ("img3".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(2).getUrl()).into(mIvFaultLocation);

                            }
                            if ("img4".equals(data.getReturnaccessoryImg().get(i).getRelation())) {
                                Glide.with(mActivity).load("https://img.xigyu.com/Pics/OldAccessory/" + data.getReturnaccessoryImg().get(3).getUrl()).into(mIvNewAndOldAccessories);

                            }
                            list.add(data.getReturnaccessoryImg().get(i).getRelation());
                        }
//                        Log.d(TAG,"data.getReturnaccessoryImg().size()"+data.getReturnaccessoryImg().size());
                        if (!list.contains("img1")) {
                            mLlBarCode.setVisibility(View.GONE);
                        }
                        if (!list.contains("img2")) {
                            mLlMachine.setVisibility(View.GONE);
                        }
                        if (!list.contains("img3")) {
                            mLlFaultLocation.setVisibility(View.GONE);
                        }
                        if (!list.contains("img4")) {
                            mLlNewAndOldAccessories.setVisibility(View.GONE);
                        }
                        mLlOldAccessory.setVisibility(View.GONE);
                    }
                } else {
                    mLlReturnInformation.setVisibility(View.GONE);
                    mLlApplyCustomService.setVisibility(View.GONE);
                    mLlConfirm.setVisibility(View.GONE);
                    mLlMessage.setVisibility(View.GONE);
                }
                if (data.getSendOrderList().size() != 0) {
                    if ("".equals(data.getSendOrderList().get(0).getServiceDate())) {
                        mLlSelectTime.setVisibility(View.GONE);
                    } else {
                        mTvSelectTime.setText(data.getSendOrderList().get(0).getServiceDate());
                        mLlSelectTime.setVisibility(View.GONE);
                    }
                }
                if ("2".equals(data.getTypeID())) {
                    mLlOldAccessory.setVisibility(View.GONE);
                }
                cancleLoading();
                break;
            case 401:
                break;
        }
    }

    private void showEdit(List<GAccessory> orderAccessroyDetail, int position) {
        GAccessory orderAccessroy = orderAccessroyDetail.get(position);
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_edit_price, null);
        EditText et_accessories_name = view.findViewById(R.id.et_accessories_name);
        EditText et_accessories_price = view.findViewById(R.id.et_accessories_price);
        Button btn_sure = view.findViewById(R.id.btn_sure);
        LinearLayout ll_accessories_price = view.findViewById(R.id.ll_accessories_price);

//        ToastUtils.showShort(orderAccessroyDetail.get(position).getPrice());
        if ("0.00".equals(orderAccessroyDetail.get(position).getPrice())) {
            ll_accessories_price.setVisibility(View.VISIBLE);
        } else {
            ll_accessories_price.setVisibility(View.GONE);
        }
        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_accessories_name.getText().toString();
                String price = et_accessories_price.getText().toString();
                if ("0.00".equals(orderAccessroy.getPrice())) {
                    if ("".equals(price)) {
                        ToastUtils.showShort("请输入配件价格");
                    } else {
                        mPresenter.UpdateFactoryAccessorybyFactory(orderAccessroy.getFAccessoryID(), name, price, orderAccessroy.getId());
                        mPresenter.ApproveOrderAccessoryByModifyPrice(OrderID, "1", "0", orderAccessroy.getId());
                    }
                } else {
                    mPresenter.UpdateFactoryAccessorybyFactory(orderAccessroy.getFAccessoryID(), name, "0", orderAccessroy.getId());
//                    mPresenter.ApproveOrderAccessoryByModifyPrice(OrderID,"1","0",orderAccessroy.getId());
                    mPresenter.ApproveOrderAccessory(OrderID, "1", "0", orderAccessroy.getId());
                }
            }
        });
        editDialog = new AlertDialog.Builder(mActivity).setView(view).create();
        editDialog.show();
        Window window = editDialog.getWindow();
//                window.setContentView(under_review);
        WindowManager.LayoutParams lp = window.getAttributes();
//                lp.alpha = 0.5f;
        // 也可按屏幕宽高比例进行设置宽高
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        lp.width = (int) (display.getWidth() * 0.9);
//                lp.height = under_review.getHeight();
//                lp.width = 300;
//                lp.height = (int) (display.getHeight() * 0.5);

        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
        window.setBackgroundDrawable(new ColorDrawable());
    }

    @Override
    public void ApplyCustomService(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()){
                    ToastUtils.showShort("发起质保成功！");
                    EventBus.getDefault().post("质保单");
                    EventBus.getDefault().post("已完成");
                    EventBus.getDefault().post(9);
                    mActivity.finish();
                }else {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void ApproveOrderAccessory(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("审核成功！");
                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    ToastUtils.showShort("审核失败！" + result.getItem2());
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void ApproveOrderService(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("审核成功！");
                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    ToastUtils.showShort("审核失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void ApproveOrderAccessoryAndService(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("审核成功！");
                    mPresenter.GetOrderInfo(OrderID);
                    if (state == -1) {
                        EventBus.getDefault().post(3);
                        EventBus.getDefault().post("已接单");
                    } else if (state == 1) {
                        EventBus.getDefault().post(6);
                        EventBus.getDefault().post("待寄件");
                    } else {
                        EventBus.getDefault().post(3);
                        EventBus.getDefault().post("已接单");
                    }
                    EventBus.getDefault().post("待审核");
                    mActivity.finish();
                } else {
                    ToastUtils.showShort("审核失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void ApproveBeyondMoney(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("审核成功！");
//                    mPresenter.GetOrderInfo(OrderID);
                    EventBus.getDefault().post(1);
                    EventBus.getDefault().post("所有工单");
                    EventBus.getDefault().post("远程费审核");
                    mActivity.finish();
                } else {
                    ToastUtils.showShort("审核失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void AddOrUpdateExpressNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("添加成功！");
                    expressno_dialog.dismiss();
//                    mPresenter.ApproveOrderAccessory(OrderID, "1", newmoney);
                    mPresenter.GetOrderInfo(OrderID);
                    EventBus.getDefault().post("post");
                    EventBus.getDefault().post("已接单");
                    EventBus.getDefault().post("待寄件");
                    EventBus.getDefault().post(3);
                    mActivity.finish();
                } else {
                    ToastUtils.showShort("添加失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void EnSureOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                    mPresenter.GetOrderInfo(OrderID);
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
                    mPresenter.GetOrderInfo(OrderID);
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
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data1 = baseResult.getData();
                if (data1.isItem1()) {
//                    ToastUtils.showShort(data.getItem2());

                    if ("师傅自购件".equals(data.getAccessorySequencyStr())) {
                        if (state == 2) {
                            mPresenter.ApproveOrderAccessoryAndService(OrderID, "1", PostPayType, IsReturn);
                        } else {
                            mPresenter.ApproveOrderAccessoryAndService(OrderID, "2", PostPayType, IsReturn);
                        }

                    } else {
                        mPresenter.ApproveOrderAccessoryAndService(OrderID, "1", PostPayType, IsReturn);

                    }
                } else {
                    ToastUtils.showShort(data1.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetAccountAddress(BaseResult<List<Address>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                addressList = baseResult.getData();
                if (addressList.size() != 0) {
                    for (int i = 0; i < addressList.size(); i++) {
                        if ("1".equals(addressList.get(i).getIsDefault())) {
                            AddressBack = addressList.get(0).getProvince() + addressList.get(0).getCity() + addressList.get(0).getArea() + addressList.get(0).getDistrict() + addressList.get(0).getAddress() + "(" + addressList.get(i).getUserName() + "收)" + addressList.get(i).getPhone();
                            mTvAddressback.setText(AddressBack);
                            mTvModify.setText("修改地址");
                        } else {
                            AddressBack = addressList.get(0).getProvince() + addressList.get(0).getCity() + addressList.get(0).getArea() + addressList.get(0).getDistrict() + addressList.get(0).getAddress() + "(" + addressList.get(0).getUserName() + "收)" + addressList.get(0).getPhone();
                            mTvAddressback.setText(AddressBack);
                            mTvModify.setText("添加地址");
                        }
                    }
                } else {
                    AddressBack = "";
                    mTvAddressback.setText(AddressBack);
                    mTvModify.setText("添加地址");
                }
                break;
            default:
                ToastUtils.showShort("获取失败");
                break;
        }
    }

    @Override
    public void GetOrderAccessoryMoney(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if ("0".equals(baseResult.getData().getItem2())) {
                    mLlAmountOfAccessories.setVisibility(View.GONE);
                } else {
                    mTvAmountOfAccessories.setText(baseResult.getData().getItem2());
                }
        }
    }

    @Override
    public void UpdateFactoryAccessorybyFactory(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                    editDialog.dismiss();
                } else {
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }
                break;
        }
    }

    @Override
    public void ApproveOrderAccessoryByModifyPrice(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void NowEnSureOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                    mPresenter.GetOrderInfo(OrderID);
                    EventBus.getDefault().post("7");
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void FactoryComplaint(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void NowPayEnSureOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                EventBus.getDefault().post(8);
                EventBus.getDefault().post("已完成");
                EventBus.getDefault().post("待支付");
                mActivity.finish();
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {

                } else {
                    userInfo = baseResult.getData().getData().get(0);
                    if ("".equals(userInfo.getPayPassWord())) {
                        Toast.makeText(mActivity, "请设置支付密码", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mActivity, PayPasswordActivity.class));
                    } else {
                        openPayPasswordDialog();
                    }
                }
                break;

            default:
                break;

        }
    }

    @Override
    public void getOrderFreezing(BaseResult<Data<List<OrderFreezing>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    if (baseResult.getData().getItem2() != null) {
                        freezingMoney = baseResult.getData().getItem2().get(0).getMoney();
                        mPresenter.GetOrderInfo(OrderID);
                    } else {
                        mPresenter.GetOrderInfo(OrderID);
                    }
                } else {
                    mPresenter.GetOrderInfo(OrderID);
                }


                break;
        }
    }

    @Override
    public void ApplyCancelOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("废除成功");
                    mPresenter.GetOrderInfo(OrderID);
                } else {
                    MyUtils.showToast(mActivity, "该工单不能废除，如需关闭工单，请联系客服");
//                    ToastUtils.showShort(baseResult.getData().getItem2());
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult != null) {
                String result = scanResult.getContents();
                if (result == null) {
                    return;
                } else {
                    et_expressno.setText(result);
                }
            }
        }
        if (requestCode == 1000) {
            if (data != null) {
                address = (Address) data.getSerializableExtra("address");
                if (address != null) {
                    AddressBack = address.getProvince() + address.getCity() + address.getArea() + address.getDistrict() + address.getAddress() + "(" + address.getUserName() + "收)" + address.getPhone();
                    mTvAddressback.setText(AddressBack);
                }
            }
        }
    }


    /*支付密码*/
    private void openPayPasswordDialog() {
        PayPasswordView payPasswordView = new PayPasswordView(mActivity);
        bottomSheetDialog = new BottomSheetDialog(mActivity);
        bottomSheetDialog.setContentView(payPasswordView);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
        /*注册监听*/
        payPasswordView.getmPasswordEditText().setPasswordFullListener(this);
        /*关闭*/
        payPasswordView.getImg_back().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public void passwordFull(String password) {
        if (userInfo.getPayPassWord().equals(password)) {
            bottomSheetDialog.dismiss();
            mPresenter.NowPayEnSureOrder(OrderID, password);

        } else {
            Toast.makeText(mActivity, "支付密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    public void showLoading(){
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancleLoading(){
        dialog.dismiss();
    }
}
