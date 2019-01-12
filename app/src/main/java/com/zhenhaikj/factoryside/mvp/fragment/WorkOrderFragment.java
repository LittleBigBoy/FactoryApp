package com.zhenhaikj.factoryside.mvp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.WorkOrderAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.AllWorkOrdersContract;
import com.zhenhaikj.factoryside.mvp.model.AllWorkOrdersModel;
import com.zhenhaikj.factoryside.mvp.presenter.AllWorkOrdersPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class WorkOrderFragment extends BaseLazyFragment<AllWorkOrdersPresenter,AllWorkOrdersModel> implements AllWorkOrdersContract.View {
    private static final String ARG_PARAM1 = "param1";//""全部 1待付款 2待发货 3待收货
    private static final String ARG_PARAM2 = "param2";//0普通订单 1汤料订单
    @BindView(R.id.rv_work_order)
    RecyclerView mRvWorkOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    private String mParam1;
    private String mParam2;
    private WorkOrderAdapter mWorkOrderAdapter;
    private List<WorkOrder> workOrderList=new ArrayList<>();


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

    @Override
    protected void initData() {
        mPresenter.GetOrderInfoList("0","1","40");
        for (int i = 0; i < 30; i++) {
            workOrderList.add(new WorkOrder());
        }
        mRvWorkOrder.setLayoutManager(new LinearLayoutManager(mActivity));
//        mRvWorkOrder.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 20, Color.parseColor("#EEEEEE")));
        mWorkOrderAdapter = new WorkOrderAdapter(R.layout.order_item, workOrderList);
        mRvWorkOrder.setAdapter(mWorkOrderAdapter);
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void GetOrderInfoList(BaseResult<String> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                MyUtils.e("GetOrderInfoList",baseResult.getData());
                ToastUtils.showShort(baseResult.getData());
                break;
            case 401:
                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }
}
