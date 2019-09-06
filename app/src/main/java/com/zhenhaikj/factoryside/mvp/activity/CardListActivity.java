package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.MyCardAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.BankCard;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.WithdrawContract;
import com.zhenhaikj.factoryside.mvp.model.WithdrawModel;
import com.zhenhaikj.factoryside.mvp.presenter.WithdrawPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardListActivity extends BaseActivity<WithdrawPresenter, WithdrawModel> implements View.OnClickListener, WithdrawContract.View {


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
    @BindView(R.id.rv_card_list)
    RecyclerView mRvCardList;
    @BindView(R.id.ll_add_card)
    LinearLayout mLlAddCard;
    @BindView(R.id.tv_tips)
    TextView mTvTips;
    private String userId;
    private MyCardAdapter myCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_cardlist;
    }

    @Override
    protected void initData() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    protected void initView() {
        mTvSave.setText("添加银行卡");
        mTvSave.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("我的银行卡");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mLlAddCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_add_card:
            case R.id.tv_save:
                startActivityForResult(new Intent(this, AddCardActivity.class), 2002);
                //startActivity(new Intent(this,Add_Card_Activity.class));
                break;


        }

    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {

    }



    /*获取银行卡*/
    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    mRvCardList.setLayoutManager(new LinearLayoutManager(mActivity));
                    myCardAdapter = new MyCardAdapter(R.layout.item_mycard, baseResult.getData(), mActivity);
                    mRvCardList.setAdapter(myCardAdapter);
                    myCardAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            switch (view.getId()){
                                case R.id.cardview:
                                    int length = baseResult.getData().get(position).getPayNo().length();
                                    if (length > 4) {
                                        String endNum = baseResult.getData().get(position).getPayNo().substring(length - 4, length);
                                    }
                                    Intent intent = new Intent();
                                    intent.putExtra("bankName", baseResult.getData().get(position).getPayInfoName());
                                    intent.putExtra("bankNo", baseResult.getData().get(position).getPayNo());
                                    intent.putExtra("payName",baseResult.getData().get(position).getPayName());
                                    setResult(2000, intent);
                                    finish();
                                    break;
                            }
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetBankNameByCardNo(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void WithDrawDeposit(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2000) {
            if (requestCode == 2002) {//添加卡的请求
                mPresenter.GetAccountPayInfoList(userId);
            }
        }
    }

}
