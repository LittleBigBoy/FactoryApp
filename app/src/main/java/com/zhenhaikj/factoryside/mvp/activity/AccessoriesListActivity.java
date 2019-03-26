package com.zhenhaikj.factoryside.mvp.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.AccessoryDetailAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.ServiceAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.WorkOrdersDetailContract;
import com.zhenhaikj.factoryside.mvp.model.WorkOrdersDetailModel;
import com.zhenhaikj.factoryside.mvp.presenter.WorkOrdersDetailPresenter;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/*配件单详情*/
public class AccessoriesListActivity extends BaseActivity<WorkOrdersDetailPresenter, WorkOrdersDetailModel> implements View.OnClickListener, WorkOrdersDetailContract.View {

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

    @Override
    protected int setLayoutId() {
        return R.layout.activity_accessories_list;
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
        OrderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetOrderInfo(OrderID);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetOrderInfo(OrderID);
                mRefreshLayout.finishRefresh(3000);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlContactCustomerService.setOnClickListener(this);
        mTvReject.setOnClickListener(this);
        mTvPass.setOnClickListener(this);
        mTvPassService.setOnClickListener(this);
        mTvRejectService.setOnClickListener(this);
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
            case R.id.ll_contact_customer_service:
                final CommonDialog_Home dialog = new CommonDialog_Home(AccessoriesListActivity.this);
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
                reject = new CommonDialog_Home(AccessoriesListActivity.this);
                reject.setMessage("是否拒绝申请的配件")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        reject.dismiss();
                        mPresenter.ApproveOrderAccessory(OrderID, "-1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        reject.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_pass:
                expressno_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_add_expressno, null);
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
                tv_message.setText("是否同意申请的配件");
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
                        expressno = et_expressno.getText().toString().trim();
//                        if ("".equals(expressno)){
//                            ToastUtils.showShort("请输入快递单号");
//                        }else{
                        mPresenter.AddOrUpdateExpressNo(OrderID, expressno);
//                        }
                    }
                });
                break;

            case R.id.tv_reject_service:
                reject = new CommonDialog_Home(AccessoriesListActivity.this);
                reject.setMessage("是否拒绝申请的服务")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        reject.dismiss();
                        mPresenter.ApproveOrderService(OrderID, "-1");
                    }

                    @Override
                    public void onNegtiveClick() {//取消
                        reject.dismiss();
                        // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.tv_pass_service:
                pass = new CommonDialog_Home(AccessoriesListActivity.this);
                pass.setMessage("是否同意申请的服务")

                        //.setImageResId(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        pass.dismiss();
                        mPresenter.ApproveOrderService(OrderID, "1");
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

    /**
     * 扫描二维码
     */
    public void scan() {
        IntentIntegrator integrator = new IntentIntegrator(AccessoriesListActivity.this);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        mRefreshLayout.finishRefresh();
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                mTvOrderState.setText(data.getState());
                mTvName.setText(data.getUserName());
                mTvAccessoryMemo.setText("备注：" + data.getAccessoryMemo());
                mTvAccessorySequency.setText("寄件类型：" + data.getAccessorySequencyStr());
                mTvPhone.setText(data.getPhone());
                mTvAddress.setText(data.getAddress());
                mTvTime.setText(data.getCreateDate());
                mTvWorkOrderStatus.setText(data.getState());
                mTvWorkOrderNumber.setText(data.getOrderID());
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

                if ("1".equals(data.getAccessoryApplyState())) {
                    mTvPass.setVisibility(View.GONE);
                    mTvReject.setVisibility(View.GONE);
                    mTvStatusAccessory.setVisibility(View.VISIBLE);
                    mTvStatusAccessory.setText("已审核通过");
                } else if ("-1".equals(data.getAccessoryApplyState())) {
                    mTvPass.setVisibility(View.GONE);
                    mTvReject.setVisibility(View.GONE);
                    mTvStatusAccessory.setVisibility(View.VISIBLE);
                    mTvStatusAccessory.setText("已拒绝");
                } else {
                    mTvPass.setVisibility(View.VISIBLE);
                    mTvReject.setVisibility(View.VISIBLE);
                    mTvStatusAccessory.setVisibility(View.GONE);
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
                        mLlAuditService.setVisibility(View.VISIBLE);
                        serviceAdapter = new ServiceAdapter(R.layout.item_accessories, data.getOrderServiceDetail());
                        mRvService.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvService.setAdapter(serviceAdapter);
                    }

                }
                if (data.getOrderAccessroyDetail() == null) {
                    mLlApproveBeyondMoney.setVisibility(View.GONE);
                } else {
                    if (data.getOrderAccessroyDetail().size() == 0) {
                        mLlApproveBeyondMoney.setVisibility(View.GONE);
                    } else {
                        mLlApproveBeyondMoney.setVisibility(View.VISIBLE);
                        accessoryDetailAdapter = new AccessoryDetailAdapter(R.layout.item_accessories, data.getOrderAccessroyDetail());
                        mRvAccessories.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvAccessories.setAdapter(accessoryDetailAdapter);
                    }
                }

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
    public void ApproveBeyondMoney(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void AddOrUpdateExpressNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                result = baseResult.getData();
                if (result.isItem1()) {
                    ToastUtils.showShort("添加成功！");
                    expressno_dialog.dismiss();
                    mPresenter.ApproveOrderAccessory(OrderID, "1");
                } else {
                    ToastUtils.showShort("添加失败！" + result.getItem2());
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanResult != null) {
            expressno = scanResult.getContents();
            et_expressno.setText(expressno);
        }
    }
}