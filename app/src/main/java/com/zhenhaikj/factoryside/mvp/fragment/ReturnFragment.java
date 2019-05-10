package com.zhenhaikj.factoryside.mvp.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.LogisticsAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Logistics;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.contract.ExpressInfoContract;
import com.zhenhaikj.factoryside.mvp.model.ExpressInfoModel;
import com.zhenhaikj.factoryside.mvp.presenter.ExpressInfoPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ReturnFragment extends BaseLazyFragment<ExpressInfoPresenter, ExpressInfoModel> implements ExpressInfoContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.courier_company_tv)
    TextView mCourierCompanyTv;
    @BindView(R.id.return_rv)
    RecyclerView mReturnRv;
    @BindView(R.id.tv_number)
    TextView mTvNumber;

    private WorkOrder.DataBean data;
    private List<Logistics> list = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private String orderId;
    private LogisticsAdapter adapter;

    public static ReturnFragment newInstance(String param1, String param2) {
        ReturnFragment fragment = new ReturnFragment();
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
        return R.layout.fragment_return;
    }

    @Override
    protected void initData() {
        orderId = mParam1;
        mPresenter.GetOrderInfo(orderId);
    }

    @Override
    protected void initView() {
        adapter = new LogisticsAdapter(R.layout.logistics_recycle_item, list);
        mReturnRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mReturnRv.setAdapter(adapter);
        adapter.setEmptyView(getEmptyView());
    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                list.addAll(baseResult.getData().getItem2());
                adapter.setNewData(list);
                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                mPresenter.GetExpressInfo(data.getReturnAccessoryMsg());
                mTvNumber.setText(data.getReturnAccessoryMsg());
                break;
        }
    }
}
