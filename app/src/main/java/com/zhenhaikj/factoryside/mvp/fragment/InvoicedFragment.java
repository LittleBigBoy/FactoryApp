package com.zhenhaikj.factoryside.mvp.fragment;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.OpenedAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.UninvoicedAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class InvoicedFragment extends BaseLazyFragment {
    @BindView(R.id.rv_invoiced)
    RecyclerView mRvInvoiced;

    private ArrayList<Address> invoicedList=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_invoiced;
    }

    @Override
    protected void initData() {
        for (int i=0;i<10;i++){
            invoicedList.add(new Address());
        }

        OpenedAdapter openedAdapter=new OpenedAdapter(R.layout.item_uninvoiced,invoicedList);
        mRvInvoiced.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvInvoiced.setAdapter(openedAdapter);
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
}
