package com.zhenhaikj.factoryside.mvp.fragment;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.UninvoicedAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.bean.Address;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class UninvoicedFragment extends BaseLazyFragment {
    @BindView(R.id.rv_uninvoiced)
    RecyclerView mRvUninvoiced;
    @BindView(R.id.tv_factory_name)
    TextView mTvFactoryName;
    @BindView(R.id.et_unified_social_credit_code)
    EditText mEtUnifiedSocialCreditCode;
    @BindView(R.id.tv_invoice_content)
    TextView mTvInvoiceContent;
    @BindView(R.id.tv_total_invoice)
    TextView mTvTotalInvoice;
    @BindView(R.id.tv_invoice_type)
    TextView mTvInvoiceType;
    @BindView(R.id.ll_invoice_type)
    LinearLayout mLlInvoiceType;
    @BindView(R.id.et_invoice_mailbox)
    EditText mEtInvoiceMailbox;
    private ArrayList<Address> uninvoicedList=new ArrayList<>();
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_uninvoiced;
    }

    @Override
    protected void initData() {
        for (int i=0;i<10;i++){
            uninvoicedList.add(new Address());
        }
        UninvoicedAdapter uninvoicedAdapter=new UninvoicedAdapter(R.layout.item_uninvoiced,uninvoicedList);
        mRvUninvoiced.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvUninvoiced.setAdapter(uninvoicedAdapter);
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
