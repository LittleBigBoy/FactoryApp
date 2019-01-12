package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.AllWorkOrdersActivity;
import com.zhenhaikj.factoryside.mvp.activity.WalletActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.contract.PurchaseContract;
import com.zhenhaikj.factoryside.mvp.model.PurchaseModel;
import com.zhenhaikj.factoryside.mvp.presenter.PurchasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class MineFragment extends BaseLazyFragment<PurchasePresenter, PurchaseModel> implements View.OnClickListener, PurchaseContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.iv_service)
    ImageView mIvService;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_profile_image)
    ImageView mIvProfileImage;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.ll_gift)
    LinearLayout mLlGift;
    @BindView(R.id.my_top)
    LinearLayout mMyTop;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_recharge)
    TextView mTvRecharge;
    @BindView(R.id.ll_all_order)
    LinearLayout mLlAllOrder;
    @BindView(R.id.ll_to_be_returned)
    LinearLayout mLlToBeReturned;
    @BindView(R.id.ll_to_be_confirmed)
    LinearLayout mLlToBeConfirmed;
    @BindView(R.id.ll_complete)
    LinearLayout mLlComplete;
    @BindView(R.id.ll_my_wallet)
    LinearLayout mLlMyWallet;
    @BindView(R.id.ll_service_phone)
    LinearLayout mLlServicePhone;
    @BindView(R.id.ll_personal_info)
    LinearLayout mLlPersonalInfo;
    @BindView(R.id.ll_sub_account_management)
    LinearLayout mLlSubAccountManagement;
    @BindView(R.id.ll_qr_code)
    LinearLayout mLlQrCode;
    @BindView(R.id.ll_consume)
    LinearLayout mLlConsume;
    @BindView(R.id.ll_add_brand)
    LinearLayout mLlAddBrand;
    @BindView(R.id.ll_feedback)
    LinearLayout mLlFeedback;
    @BindView(R.id.ll_about)
    LinearLayout mLlAbout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private String mParam1;
    private String mParam2;
    private Bundle bundle;
    private Intent intent;


    public MineFragment() {
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
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
        mImmersionBar.statusBarView(mToolbar);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        Glide.with(mActivity).load(R.drawable.avatar).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar)).into(mIvProfileImage);
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIvSetting.setOnClickListener(this);
        mIvService.setOnClickListener(this);
        mLlGift.setOnClickListener(this);
        mTvRecharge.setOnClickListener(this);

        mLlAllOrder.setOnClickListener(this);
        mLlToBeReturned.setOnClickListener(this);
        mLlToBeConfirmed.setOnClickListener(this);
        mLlComplete.setOnClickListener(this);

        mLlMyWallet.setOnClickListener(this);
        mLlServicePhone.setOnClickListener(this);
        mLlPersonalInfo.setOnClickListener(this);
        mLlSubAccountManagement.setOnClickListener(this);
        mLlQrCode.setOnClickListener(this);
        mLlConsume.setOnClickListener(this);
        mLlAddBrand.setOnClickListener(this);
        mLlFeedback.setOnClickListener(this);
        mLlAbout.setOnClickListener(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.iv_service:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_gift:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_all_order:
                /*bundle = new Bundle();
                bundle.putString("title","");
                bundle.putInt("position", position);
                intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);*/
                break;
            case R.id.ll_to_be_returned:
                /*bundle = new Bundle();
                bundle.putString("title", "所有订单");
                bundle.putInt("position", position);
                intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);*/
                break;
            case R.id.ll_to_be_confirmed:
                /*bundle = new Bundle();
                bundle.putString("title","");
                bundle.putInt("position", position);
                intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);*/
                break;
            case R.id.ll_complete:
                /*bundle = new Bundle();
                bundle.putString("title","");
                bundle.putInt("position", position);
                intent = new Intent(mActivity, AllWorkOrdersActivity.class);
                intent.putExtras(bundle);
                ActivityUtils.startActivity(intent);*/
                break;
            case R.id.ll_my_wallet:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_service_phone:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_personal_info:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_sub_account_management:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_qr_code:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_consume:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_add_brand:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_feedback:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
            case R.id.ll_about:
                startActivity(new Intent(mActivity,WalletActivity.class));
                break;
        }
    }

    @Override
    public void success(BaseResult<List<Category>> baseResult) {

    }

    @Override
    public void fail(BaseResult<List<Category>> baseResult) {

    }
}
