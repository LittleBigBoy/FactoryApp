package com.zhenhaikj.factoryside.mvp.fragment;

import com.blankj.utilcode.util.SPUtils;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.OpenedAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.UninvoicedAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.CanInvoice;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.InvoicedContract;
import com.zhenhaikj.factoryside.mvp.model.InvoicedModel;
import com.zhenhaikj.factoryside.mvp.presenter.InvoicedPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

public class InvoicedFragment extends BaseLazyFragment<InvoicedPresenter, InvoicedModel> implements InvoicedContract.View {
    @BindView(R.id.rv_invoiced)
    RecyclerView mRvInvoiced;

    private ArrayList<CanInvoice> invoicedList = new ArrayList<>();
    private String userId;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_invoiced;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
//        mPresenter.GetInvoiceByUserid(userId);
        mPresenter.GetCanInvoiceByUserid(userId, "1");

//        for (int i=0;i<10;i++){
//            invoicedList.add(new Address());
//        }


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
    public void GetCanInvoiceByUserid(BaseResult<Data<List<CanInvoice>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    if (baseResult.getData().getItem2().size() != 0) {
                        invoicedList.addAll(baseResult.getData().getItem2());
                        OpenedAdapter openedAdapter = new OpenedAdapter(R.layout.item_uninvoiced, invoicedList);
                        mRvInvoiced.setLayoutManager(new LinearLayoutManager(mActivity));
                        mRvInvoiced.setAdapter(openedAdapter);
                    }
                }
                break;
        }
    }

    @Override
    public void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void AddInvoice(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetInvoiceByUserid(BaseResult<Data<List<CanInvoice>>> baseResult) {

    }
}
