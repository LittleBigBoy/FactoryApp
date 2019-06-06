package com.zhenhaikj.factoryside.mvp.fragment;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.UninvoicedAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
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

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class UninvoicedFragment extends BaseLazyFragment<InvoicedPresenter, InvoicedModel> implements InvoicedContract.View, AdapterView.OnItemSelectedListener,View.OnClickListener{
    private static final String TAG = "UninvoicedFragment";
    @BindView(R.id.rv_uninvoiced)
    RecyclerView mRvUninvoiced;
    @BindView(R.id.tv_factory_name)
    TextView mTvFactoryName;
    @BindView(R.id.tv_invoice_content)
    TextView mTvInvoiceContent;
    @BindView(R.id.tv_total_invoice)
    TextView mTvTotalInvoice;
    //    @BindView(R.id.tv_invoice_type)
//    TextView mTvInvoiceType;
    @BindView(R.id.ll_invoice_type)
    LinearLayout mLlInvoiceType;
    @BindView(R.id.et_invoice_mailbox)
    EditText mEtInvoiceMailbox;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.tv_unified_social_credit_code)
    TextView mTvUnifiedSocialCreditCode;
    @BindView(R.id.spinner)
    AppCompatSpinner mSpinner;
    @BindView(R.id.btn_sure)
    Button mBtnSure;

    private List<CanInvoice> uninvoicedList = new ArrayList<>();
    private String userId;
    private UninvoicedAdapter uninvoicedAdapter;
    private CompanyInfo companyDean;
    private String message;
    double Money = 0;
    private UserInfo.UserInfoDean userInfo;
    private String invoicedId;
    private int count;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_uninvoiced;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");

        mPresenter.GetUserInfoList(userId, "1");
        mPresenter.GetmessageBytype(userId);

        mCheckbox.isChecked();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mSpinner.setOnItemSelectedListener(this);
        mBtnSure.setOnClickListener(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void GetCanInvoiceByUserid(BaseResult<Data<List<CanInvoice>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    uninvoicedList.addAll(baseResult.getData().getItem2());
                    uninvoicedAdapter = new UninvoicedAdapter(uninvoicedList, mActivity);
                    mRvUninvoiced.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvUninvoiced.setAdapter(uninvoicedAdapter);
                    mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                for (int i = 0; i < uninvoicedList.size(); i++) {
                                    //选择店铺
                                    if (!uninvoicedList.get(i).isIscheck()) {
                                        uninvoicedList.get(i).setIscheck(true);
                                    }
                                }


                            } else {
                                //只有当点击全不选时才执行
                                // 解决点击取消选择店铺或商品时，
                                // 全选按钮取消选择状态，不会不变成全不选
                                if (allSelect() == uninvoicedList.size()) {
                                    for (int i = 0; i < uninvoicedList.size(); i++) {
                                        if (uninvoicedList.get(i).isIscheck()) {
                                            uninvoicedList.get(i).setIscheck(false);
                                        }

                                    }

                                }
                            }
                            getTotalMoneyAndCloseCount(uninvoicedList);
//                //更新
                            UpdateRecyclerView();
                        }
                    });

                    uninvoicedAdapter.setCallBack(new UninvoicedAdapter.allCheck() {
                        @Override
                        public void OnCheckListener(boolean isChecked, int position) {
                            //保存店铺点击状态
                            uninvoicedList.get(position).setIscheck(isChecked);
                            //通知全选CheckBox的选择状态
                            if (allSelect() == uninvoicedList.size()) {
                                mCheckbox.setChecked(true);

                            } else {
                                mCheckbox.setChecked(false);
                            }

                            getTotalMoneyAndCloseCount(uninvoicedList);
                            //更新
                            UpdateRecyclerView();
                        }

                        @Override
                        public void OnAddReduceListner(int value, int childposition) {

                        }

                        @Override
                        public void OnItemClickDetailListner(View view, int childposition) {

                        }
                    });

                }
        }
    }

    @Override
    public void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    companyDean = baseResult.getData().getItem2();
                    if ("1".equals(companyDean.getIfAuth())) {
                        mTvFactoryName.setText(companyDean.getCompanyName());
                        mTvUnifiedSocialCreditCode.setText(companyDean.getCompanyNum());
                    }
                }
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfo = baseResult.getData().getData().get(0);
                if ("1".equals(userInfo.getIfAuth())) {
                    mPresenter.GetCanInvoiceByUserid(userId,"0");
                } else {
                    mTvFactoryName.setText("");
                }
                break;
        }
    }

    @Override
    public void AddInvoice(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    ToastUtils.showShort(baseResult.getData().getItem2());
                }
                break;
        }
    }

    @Override
    public void GetInvoiceByUserid(BaseResult<Data<List<CanInvoice>>> baseResult) {

    }

    private int allSelect() {
        int sum = 0;
        for (int i = 0; i < uninvoicedList.size(); i++) {
            if (uninvoicedList.get(i).isIscheck()) {
                sum++;
            }
        }
        System.out.println(sum);
        return sum;
    }

    /*
     *解决Recycleyview刷新报错问题
     */
    private void UpdateRecyclerView() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                uninvoicedAdapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner:
                String[] letter = getResources().getStringArray(R.array.letter);
//                Log.i("spinner1点击------",letter[position]);
                switch (position) {
                    case 0:
                        message = "增值税普通发票";
                        break;
                    case 1:
                        message = "增值税专用发票（一般纳税人）先审核";
                        break;
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        message = "增值税普通发票";
    }

    private void getTotalMoneyAndCloseCount(List<CanInvoice> uninvoicedList) {
        Money=0;
        invoicedId="";
        count=0;
        for (int i = 0; i < uninvoicedList.size(); i++) {
            if (uninvoicedList.get(i).isIscheck()) {
                double price = Double.parseDouble(uninvoicedList.get(i).getPayMoney());
                invoicedId += uninvoicedList.get(i).getId()+"|";
                Money += price;
                count++;
            }
        }
        if (invoicedId!=null){
            if (invoicedId.contains("|")){
                invoicedId=invoicedId.substring(0,invoicedId.lastIndexOf("|"));
            }
        }


        mTvTotalInvoice.setText("¥" + String.format("%.2f", Money)); //保留两位小数
//        mTvSettlement.setText("结算" + "(" + CloseCount + ")");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sure:
                String email=mEtInvoiceMailbox.getText().toString();
                Log.d(TAG,"id" +invoicedId);
//                Log.d(TAG,"发票抬头"+companyDean.getCompanyName()+"信用代码"+companyDean.getCompanyNum()+"发票金额"+Money+"发票类型"+message);
                if (!"1".equals(userInfo.getIfAuth())){
                    ToastUtils.showShort("请实名认证");
                }else if (Money==0){
                    ToastUtils.showShort("请选择需要开取发票的记录！");
                }else if ("".equals(email)){
                    ToastUtils.showShort("请输入发送电子发票的邮箱");
                }
                else{
                    mPresenter.AddInvoice(userId,companyDean.getCompanyName(),companyDean.getCompanyNum(),"技术服务费",String.valueOf(Money),message,email,"3",invoicedId,String.valueOf(count));
                }
                break;
        }
    }
}
