package com.zhenhaikj.factoryside.mvp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BillAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.RechargeRecordAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.contract.OpinionContract;
import com.zhenhaikj.factoryside.mvp.model.OpinionModel;
import com.zhenhaikj.factoryside.mvp.presenter.OpinionPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;


public class OpinionActivity extends BaseActivity<OpinionPresenter, OpinionModel> implements View.OnClickListener , OpinionContract.View {


    @BindView(R.id.view)
    View mView;
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
    @BindView(R.id.tv_account_problem)
    TextView mTvAccountProblem;
    @BindView(R.id.tv_payment_issues)
    TextView mTvPaymentIssues;
    @BindView(R.id.tv_other_questions)
    TextView mTvOtherQuestions;
    @BindView(R.id.et_opinion)
    EditText mEtOpinion;
    @BindView(R.id.tv_word_count)
    TextView mTvWordCount;
    @BindView(R.id.btn_opinion)
    Button mBtnOpinion;
    private List<Address> billList = new ArrayList<>();
    private List<Address> rechargeRecordList = new ArrayList<>();
    private BillAdapter billAdapter;
    private RechargeRecordAdapter rechargeRecordAdapter;
    private String userId;
    private String type="";
    private String content;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_opinion;
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
        SPUtils spUtils=SPUtils.getInstance("token");
        userId = spUtils.getString("userName");

        type="1";

        mEtOpinion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTvWordCount.setText(s.length()+"/200");
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("意见反馈");
        mTvAccountProblem.setSelected(true);
        mTvPaymentIssues.setSelected(false);
        mTvOtherQuestions.setSelected(false);


    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvAccountProblem.setOnClickListener(this);
        mTvPaymentIssues.setOnClickListener(this);
        mTvOtherQuestions.setOnClickListener(this);
        mBtnOpinion.setOnClickListener(this);
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
            case R.id.tv_account_problem:
                type="1";
                mTvAccountProblem.setSelected(true);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_payment_issues:
                type="2";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(true);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_other_questions:
                type="3";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(true);
                break;
            case R.id.btn_opinion:
                content=mEtOpinion.getText().toString();
                if ("".equals(type)){
                    MyUtils.showToast(mActivity,"请选择问题类型");
                    return;
                }
                if ("".equals(content)){
                    MyUtils.showToast(mActivity,"请输入反馈内容");
                    return;
                }
                mPresenter.AddOpinion(userId,type,content);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void AddOpinion(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                Data<String> data=baseResult.getData();
                if (data.isItem1()){
                    ToastUtils.showShort("反馈成功");
                    mEtOpinion.setText("");
                    type="1";
                    mTvAccountProblem.setSelected(true);
                    mTvPaymentIssues.setSelected(false);
                    mTvOtherQuestions.setSelected(false);
                }else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            case 401:
                break;
        }
    }
}