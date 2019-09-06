package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
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

public class WithdrawActivity extends BaseActivity<WithdrawPresenter, WithdrawModel> implements View.OnClickListener, WithdrawContract.View {


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
    @BindView(R.id.ll_bank_card_one)
    LinearLayout mLlBankCardOne;
    @BindView(R.id.iv_brank_logo)
    ImageView mIvBrankLogo;
    @BindView(R.id.tv_bank_name)
    TextView mTvBankName;
    @BindView(R.id.tv_tail_number)
    TextView mTvTailNumber;
    @BindView(R.id.tv_card_type)
    TextView mTvCardType;
    @BindView(R.id.ll_bank_card)
    LinearLayout mLlBankCard;
    @BindView(R.id.tv_withdrawal_amount)
    TextView mTvWithdrawalAmount;
    @BindView(R.id.tv_available_balance)
    TextView mTvAvailableBalance;
    @BindView(R.id.btn_confirm_withdrawal)
    Button mBtnConfirmWithdrawal;
    private String endNum;
    private String bankNo;
    private UserInfo.UserInfoDean userInfoDeanrInfo;
    private String payName;
    private BottomSheetDialog bottomSheetDialog;
    private String money;
    private String userId;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
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
    protected void initData() {

    }


    @Override
    protected void initView() {
        mTvTitle.setText("提取保证金");
        mTvTitle.setVisibility(View.VISIBLE);
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetUserInfoList(userId, "1");
        mPresenter.GetAccountPayInfoList(userId);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvWithdrawalAmount.setOnClickListener(this);
        mBtnConfirmWithdrawal.setOnClickListener(this);
        mLlBankCard.setOnClickListener(this);
        mLlBankCardOne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_confirm_withdrawal:
                if (bankNo == null || "".equals(bankNo)) {
                    ToastUtils.showShort("请选择银行卡");
                } else {
                    mPresenter.WithDrawDeposit("1000", bankNo, userId, payName);
                }
                break;
            case R.id.ll_bank_card_one:
            case R.id.ll_bank_card:
                startActivityForResult(new Intent(mActivity, CardListActivity.class), 2000);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2000:
                if (data == null) {
                    return;
                }
                String bankName = data.getStringExtra("bankName");
                bankNo = data.getStringExtra("bankNo");
                payName = data.getStringExtra("payName");
//                ToastUtils.showShort(bankNo);
                int length = bankNo.length();
                if (length > 4) {
                    endNum = bankNo.substring(length - 4, length);
                }
                mLlBankCard.setVisibility(View.VISIBLE);
                mLlBankCardOne.setVisibility(View.GONE);
                mTvBankName.setText(bankName);
                mTvTailNumber.setText(endNum);
                switch (bankName) {
                    case "光大银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gaungda)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "广发银行股份有限公司":
                        Glide.with(mActivity)
                                .load(R.mipmap.gaungfa)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "工商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gongshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国工商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.gongshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "华夏银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.huaxia)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                    case "中国建设银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jianshe)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "建设银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jianshe)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国交通银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.jiaotong)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "民生银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.minsheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "宁波银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.ningbo)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "农业银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.nongye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国农业银行贷记卡":
                        Glide.with(mActivity)
                                .load(R.mipmap.nongye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "浦发银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.pufa)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "兴业银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.xinye)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "邮政储蓄银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.youzheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "邮储银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.youzheng)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "招商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zhaoshan)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);

                        break;
                    case "浙商银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zheshang)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中国银行":

                        Glide.with(mActivity)
                                .load(R.mipmap.zhongguo)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;
                    case "中信银行":
                        Glide.with(mActivity)
                                .load(R.mipmap.zhongxin)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                    default:
                        Glide.with(mActivity)
                                .load(R.drawable.avatar)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(mIvBrankLogo);
                        break;

                }
        }
    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                userInfoDeanrInfo = Result.getData().getData().get(0);
                String money = String.format("%.2f", userInfoDeanrInfo.getTotalMoney() - userInfoDeanrInfo.getFrozenMoney());
                mTvAvailableBalance.setText("可用余额" + money + "元");
                break;
        }
    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    mLlBankCardOne.setVisibility(View.VISIBLE);
                    mLlBankCard.setVisibility(View.GONE);
                    return;
                } else {
                    mLlBankCard.setVisibility(View.VISIBLE);
                    mLlBankCardOne.setVisibility(View.GONE);
                    bankNo = baseResult.getData().get(0).getPayNo();
                    payName = baseResult.getData().get(0).getPayName();
                    mTvBankName.setText(baseResult.getData().get(0).getPayInfoName());
                    int length = baseResult.getData().get(0).getPayNo().length();
                    if (length > 4) {
                        endNum = baseResult.getData().get(0).getPayNo().substring(length - 4, length);
                    }
                    mTvTailNumber.setText(endNum);
                    switch (baseResult.getData().get(0).getPayInfoName()) {
                        case "光大银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.gaungda)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "广发银行股份有限公司":
                            Glide.with(mActivity)
                                    .load(R.mipmap.gaungfa)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "工商银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.gongshang)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "中国工商银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.gongshang)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "华夏银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.huaxia)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;

                        case "中国建设银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.jianshe)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "建设银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.jianshe)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "中国交通银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.jiaotong)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "民生银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.minsheng)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "宁波银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.ningbo)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "农业银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.nongye)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "中国农业银行贷记卡":
                            Glide.with(mActivity)
                                    .load(R.mipmap.nongye)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "浦发银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.pufa)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "兴业银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.xinye)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "邮政储蓄银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.youzheng)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "邮储银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.youzheng)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "招商银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.zhaoshan)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);

                            break;
                        case "浙商银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.zheshang)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "中国银行":

                            Glide.with(mActivity)
                                    .load(R.mipmap.zhongguo)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;
                        case "中信银行":
                            Glide.with(mActivity)
                                    .load(R.mipmap.zhongxin)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;

                        default:
                            Glide.with(mActivity)
                                    .load(R.drawable.avatar)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(mIvBrankLogo);
                            break;

                    }

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
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                finish();
                break;
        }
    }
}
