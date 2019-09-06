package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.BankCard;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.WithdrawContract;
import com.zhenhaikj.factoryside.mvp.model.WithdrawModel;
import com.zhenhaikj.factoryside.mvp.presenter.WithdrawPresenter;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCardActivity extends BaseActivity<WithdrawPresenter, WithdrawModel> implements View.OnClickListener, WithdrawContract.View {
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
    @BindView(R.id.et_add_card_name)
    EditText mEtAddCardName;
    @BindView(R.id.tv_add_card_bankname)
    TextView mTvAddCardBankname;
    @BindView(R.id.ll_choose_bank)
    LinearLayout mLlChooseBank;
    @BindView(R.id.et_banknumber)
    EditText mEtBanknumber;
    @BindView(R.id.tv_bind_card)
    TextView mTvBindCard;
    private String userId;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("添加银行卡");
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvBindCard.setOnClickListener(this);
        mEtBanknumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length()==6){

                    mPresenter.GetBankNameByCardNo(s.toString());
                }else if (s.toString().length()>=16){

                    String num=s.toString();
                    String substring = num.substring(0, 6);

                    mPresenter.GetBankNameByCardNo(substring);
                }

                else {
                    mTvAddCardBankname.setText("");
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_bind_card:
                String name=mEtAddCardName.getText().toString();
                String number=mEtBanknumber.getText().toString();
                String cardName=mTvAddCardBankname.getText().toString();
                if (name==null||"".equals(name)){
                    ToastUtils.showShort("请输入姓名");
                }else if (number==null||"".equals(number)){
                    ToastUtils.showShort("请输入银行卡号");
                }else {
                    mPresenter.AddorUpdateAccountPayInfo(userId, "Bank", mTvAddCardBankname.getText().toString(), number,name);
                }
                break;
        }
    }

    @Override
    public void GetBankNameByCardNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()&&!baseResult.getData().getItem2().equals("")){

                    mTvAddCardBankname.setText(baseResult.getData().getItem2()); //绑定银行名


                }else {//不支持的银行
                    mEtBanknumber.setText("");
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("暂时不支持绑定该银行")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
                            dialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void WithDrawDeposit(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    Toast.makeText(this, baseResult.getData().getItem2(), Toast.LENGTH_SHORT).show();
                    EventBus.getDefault().post("GetUserInfoList");
                    finish();
                } else {
                    Toast.makeText(this, baseResult.getData().getItem2(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    setResult(2000);
                    EventBus.getDefault().post("GetAccountPayInfoList");
                    finish();

                } else {
                    Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }
}
