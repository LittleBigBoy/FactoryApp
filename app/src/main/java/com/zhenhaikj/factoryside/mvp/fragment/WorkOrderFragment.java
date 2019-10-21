package com.zhenhaikj.factoryside.mvp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.activity.AccessoriesListActivity;
import com.zhenhaikj.factoryside.mvp.activity.CompletionOrderActivity;
import com.zhenhaikj.factoryside.mvp.activity.RemoteBillActivity;
import com.zhenhaikj.factoryside.mvp.activity.WarrantyActivity;
import com.zhenhaikj.factoryside.mvp.activity.WorkOrderDetailsActivity;
import com.zhenhaikj.factoryside.mvp.adapter.WorkOrderAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.AllWorkOrdersContract;
import com.zhenhaikj.factoryside.mvp.event.UpdateEvent;
import com.zhenhaikj.factoryside.mvp.model.AllWorkOrdersModel;
import com.zhenhaikj.factoryside.mvp.presenter.AllWorkOrdersPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;
import com.zhenhaikj.factoryside.mvp.widget.LinearLayoutManagerWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.TextView;

public class WorkOrderFragment extends BaseLazyFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements AllWorkOrdersContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.rv_work_order)
    RecyclerView mRvWorkOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    private int pageIndex = 1;
    private String mParam1;
    private String mParam2;
    private WorkOrder workOrder;
    private WorkOrderAdapter mWorkOrderAdapter;
    private List<WorkOrder.DataBean> workOrderList = new ArrayList<>();
    private static SPUtils spUtils;
    private String UserID;
    private View complaint_view;
    private Button btn_negtive;
    private Button btn_positive;
    private EditText et_content;
    private AlertDialog complaint_dialog;
    private String content;
    private TextView title;

    public WorkOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkOrderFragment newInstance(String param1, String param2) {
        WorkOrderFragment fragment = new WorkOrderFragment();
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
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.red);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_work_order_page;
    }

    //"所有工单","待接单", "退单处理", "已完结", "配件单", "待支付",
//        "远程费单", "质保单", "未完成单", "费用变更", "留言工单"
    @Override
    protected void initData() {
       /* /this必须为点击消息要跳转到页面的上下文。
        XGPushClickedResult clickedResult = XGPushManager.onActivityStarted(this);
        if (clickedResult!=null){
            //获取消息附近参数
            String ster = clickedResult.getCustomContent();
            try {
                JSONObject jsonObject=new JSONObject(ster);
                id=jsonObject.getString("event_val");
                getWeiboLetterList();
            }catch (Exception e){
                e.printStackTrace();
            }
//获取消息标题
            String set = clickedResult.getTitle();
//获取消息内容
            String s = clickedResult.getContent();
            MyUtils.e("推送",ster+"|"+set+"|"+s);*/
        spUtils = SPUtils.getInstance("token");
        UserID = spUtils.getString("userName");
        getData();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                workOrderList.clear();
                pageIndex = 1;
                getData();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                getData();
            }
        });

        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /*    工厂端state

    0、所有工单
    1、待接单
    2、已接待预约
    3、退单处理
    4、已完结
    5、配件单
    6、待支付
    7、远程费单
    8、质保单
    9、未完成单
    10、费用变更
    11、留言工单*/
    public void getData() {
        switch (mParam1) {
            case "待接单":
                mPresenter.GetOrderInfoList(UserID, "0", Integer.toString(pageIndex), "3");
                break;
            case "待审核":
                mPresenter.GetOrderInfoList(UserID, "1", Integer.toString(pageIndex), "3");
                break;
            case "已接单":
                mPresenter.GetOrderInfoList(UserID, "7", Integer.toString(pageIndex), "3");
                break;
            case "待支付":
                mPresenter.GetOrderInfoList(UserID, "2", Integer.toString(pageIndex), "3");
                break;
            case "已完成":
                mPresenter.GetOrderInfoList(UserID, "3", Integer.toString(pageIndex), "3");
                break;
            case "质保单":
                mPresenter.GetOrderInfoList(UserID, "4", Integer.toString(pageIndex), "3");
                break;
            case "所有工单":
                mPresenter.GetOrderInfoList(UserID, "5", Integer.toString(pageIndex), "3");
                break;
            case "退单处理":
                mPresenter.GetOrderInfoList(UserID, "6", Integer.toString(pageIndex), "3");
                break;
            case "远程费审核":
                mPresenter.GetOrderInfoList(UserID, "9", Integer.toString(pageIndex), "3");
                break;
            case "待寄件":
                mPresenter.GetOrderInfoList(UserID, "10", Integer.toString(pageIndex), "3");
                break;
            case "星标工单":
                mPresenter.GetOrderInfoList(UserID, "11", Integer.toString(pageIndex), "3");
                break;

        }
    }


    @Override
    protected void initView() {
        mRvWorkOrder.setLayoutManager(new LinearLayoutManagerWrapper(mActivity));
        mWorkOrderAdapter = new WorkOrderAdapter(R.layout.order_item, workOrderList, mParam1);
        mWorkOrderAdapter.setEmptyView(getEmptyView());
        mRvWorkOrder.setAdapter(mWorkOrderAdapter);
        mWorkOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_complaint:
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
                                content = et_content.getText().toString().trim();
                                if ("".equals(content)) {
                                    MyUtils.showToast(mActivity, "请输入投诉原因");
                                } else {
                                    mPresenter.FactoryComplaint(workOrderList.get(position).getOrderID(), content);
                                }
                            }
                        });
                        break;
                    case R.id.tv_leave_message:

                        break;
                    case R.id.tv_see_detail:
//                        if (mParam1=="质保单"){
//                            Intent intent1=new Intent(mActivity, WarrantyActivity.class);
//                            intent1.putExtra("OrderID",workOrderList.get(position).getOrderID());
//                            startActivity(intent1);
//                        }else if (mParam1=="待审核"){
//                            Intent intent1=new Intent(mActivity, AccessoriesListActivity.class);
//                            intent1.putExtra("OrderID",workOrderList.get(position).getOrderID());
//                            startActivity(intent1);
//                        }else if (mParam1=="已完成"){
//                            Intent intent1=new Intent(mActivity, CompletionOrderActivity.class);
//                            intent1.putExtra("OrderID",workOrderList.get(position).getOrderID());
//                            startActivity(intent1);
//                        } else {
//                            Intent intent=new Intent(mActivity,WorkOrderDetailsActivity.class);
//                            intent.putExtra("OrderID",workOrderList.get(position).getOrderID());
//                            startActivity(intent);
//                        }
//                        if (mParam1=="质保单"){
                        mPresenter.UpdateOrderFIsLook(workOrderList.get(position).getOrderID(), "2", "2");
                        Intent intent1 = new Intent(mActivity, WarrantyActivity.class);
                        intent1.putExtra("OrderID", workOrderList.get(position).getOrderID());
                        startActivity(intent1);
//                        }else{
//                            Intent intent1=new Intent(mActivity, AccessoriesListActivity.class);
//                            intent1.putExtra("OrderID",workOrderList.get(position).getOrderID());
//                            startActivity(intent1);
//                        }
                        break;
                    case R.id.iv_copy:
                        String id = workOrderList.get(position).getOrderID();
                        myClip = ClipData.newPlainText("", id);
                        myClipboard.setPrimaryClip(myClip);
                        ToastUtils.showShort("复制成功");
                        break;
                    case R.id.tv_obsolete:
                        String orderId = workOrderList.get(position).getOrderID();
//                        mPresenter.UpdateOrderState(orderId,"-2");
                        mPresenter.ApplyCancelOrder(orderId);
                        workOrderList.clear();
                        getData();
                        mRefreshLayout.finishRefresh();
                        break;
                    case R.id.iv_star:
                        if (view.isSelected()){
                            view.setSelected(false);
                            mPresenter.GetFStarOrder(workOrderList.get(position).getOrderID(),"N");
                        }else {
                            view.setSelected(true);
                            mPresenter.GetFStarOrder(workOrderList.get(position).getOrderID(),"Y");
                        }
                        break;
                }
            }
        });

        mWorkOrderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.UpdateOrderFIsLook(workOrderList.get(position).getOrderID(), "2", "2");
                Intent intent1 = new Intent(mActivity, WarrantyActivity.class);
                intent1.putExtra("OrderID", workOrderList.get(position).getOrderID());
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
//        getData();
        switch (name){
            case "7":
                getData();
                break;
            case "10":
                getData();
                break;
            case "post":
                getData();
                break;
        }
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
    public void UpdateOrderFIsLook(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    EventBus.getDefault().post(Config.ORDER_READ);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void ApplyCancelOrder(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetFStarOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
//                getData();
                break;
        }
    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                workOrder = baseResult.getData();
                if (workOrder.getData() != null) {
                    workOrderList.addAll(workOrder.getData());
                    mWorkOrderAdapter.setNewData(workOrderList);
//                    mWorkOrderAdapter.notifyDataSetChanged();
                }
                mRefreshLayout.finishRefresh();
                if (pageIndex != 1 && "0".equals(workOrder.getCount())) {
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    mRefreshLayout.finishLoadMore();
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort("取消成功");
                break;
            case 401:
                break;
        }
    }

}
