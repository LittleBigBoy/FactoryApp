package com.zhenhaikj.factoryside.mvp.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.WorkOrderAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.AllWorkOrdersContract;
import com.zhenhaikj.factoryside.mvp.model.AllWorkOrdersModel;
import com.zhenhaikj.factoryside.mvp.presenter.AllWorkOrdersPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class WorkOrderFragment extends BaseLazyFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements AllWorkOrdersContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.rv_work_order)
    RecyclerView mRvWorkOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;

    private int pageIndex = 1;
    private String mParam1;
    private String mParam2;
    private WorkOrder workOrder;
    private WorkOrderAdapter mWorkOrderAdapter;
    private List<WorkOrder.DataBean> workOrderList = new ArrayList<>();
    private String[] mTitleDataList = new String[]{
            "所有工单", "待接单", "退单处理", "已完结", "配件单", "待支付",
            "远程费单", "质保单", "未完成单", "费用变更", "留言工单"
    };

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
        super.initImmersionBar();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_work_order_page;
    }

    //"所有工单","待接单", "退单处理", "已完结", "配件单", "待支付",
//        "远程费单", "质保单", "未完成单", "费用变更", "留言工单"
    @Override
    protected void initData() {
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
    }

    public void getData() {
        switch (mParam1) {
            case "所有工单":
                mPresenter.GetOrderInfoList("", Integer.toString(pageIndex), "3");
                break;
            case "待接单":
                mPresenter.GetOrderInfoList("0", Integer.toString(pageIndex), "3");
                break;
            case "退单处理":
                mPresenter.GetOrderInfoList("-1", Integer.toString(pageIndex), "3");
                break;
            case "已完结":
                mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "3");
                break;
            case "配件单":
                mPresenter.GetOrderInfoList("2", Integer.toString(pageIndex), "3");
                break;
            case "待支付":
                mPresenter.GetOrderInfoList("3", Integer.toString(pageIndex), "3");
                break;
            case "远程费单":
                mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "3");
                break;
            case "质保单":
                mPresenter.GetOrderInfoList("2", Integer.toString(pageIndex), "3");
                break;
            case "未完成单":
                mPresenter.GetOrderInfoList("3", Integer.toString(pageIndex), "3");
                break;
            case "费用变更":
                mPresenter.GetOrderInfoList("1", Integer.toString(pageIndex), "3");
                break;
            case "留言工单":
                mPresenter.GetOrderInfoList("2", Integer.toString(pageIndex), "3");
                break;
        }
    }


    @Override
    protected void initView() {
        mRvWorkOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mWorkOrderAdapter = new WorkOrderAdapter(R.layout.order_item, workOrderList);
        mWorkOrderAdapter.setEmptyView(getEmptyView());
        mRvWorkOrder.setAdapter(mWorkOrderAdapter);
    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                workOrder = baseResult.getData();
                workOrderList.addAll(workOrder.getData());
                mWorkOrderAdapter.setNewData(workOrderList);
                mRefreshLayout.finishRefresh();
                if (pageIndex!=1&&"0".equals(workOrder.getCount())){
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    mRefreshLayout.finishLoadMore();
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
    }
}
