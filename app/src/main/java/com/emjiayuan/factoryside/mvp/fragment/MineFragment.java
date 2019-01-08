package com.emjiayuan.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emjiayuan.factoryside.mvp.activity.ShoppingCartActivity;
import com.emjiayuan.factoryside.mvp.contract.PurchaseContract;
import com.emjiayuan.factoryside.mvp.model.PurchaseModel;
import com.emjiayuan.factoryside.mvp.presenter.PurchasePresenter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Category;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class MineFragment extends BaseLazyFragment<PurchasePresenter, PurchaseModel> implements View.OnClickListener, PurchaseContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.profile_image)
    ImageView mProfileImage;
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.my_top)
    LinearLayout mMyTop;
    @BindView(R.id.normal_dfk_ll)
    LinearLayout mNormalDfkLl;
    @BindView(R.id.normal_dfh_ll)
    LinearLayout mNormalDfhLl;
    @BindView(R.id.normal_dsh_ll)
    LinearLayout mNormalDshLl;
    @BindView(R.id.normal_all_ll)
    LinearLayout mNormalAllLl;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String mParam1;
    private String mParam2;


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
    protected int setLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        mPresenter.getData();
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_car:
                startActivity(new Intent(mActivity, ShoppingCartActivity.class));
                break;
            case R.id.shopping_cart_fab:
                startActivity(new Intent(mActivity, ShoppingCartActivity.class));
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
