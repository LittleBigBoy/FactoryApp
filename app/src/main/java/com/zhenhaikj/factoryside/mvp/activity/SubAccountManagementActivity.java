package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.SubAccountAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.SubUserInfo;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.SubAccountContract;
import com.zhenhaikj.factoryside.mvp.model.SubAccountModel;
import com.zhenhaikj.factoryside.mvp.presenter.SubAccountPresenter;
import com.zhenhaikj.factoryside.mvp.widget.CommonDialog_Home;
import com.zhenhaikj.factoryside.mvp.widget.QRCodeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubAccountManagementActivity extends BaseActivity<SubAccountPresenter, SubAccountModel> implements View.OnClickListener, SubAccountContract.View {
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
    RelativeLayout mActionbarLayout;
    @BindView(R.id.ll_add_account)
    LinearLayout mLlAddAccount;
    @BindView(R.id.rv_sub_account)
    RecyclerView mRvSubAccount;
    @BindView(R.id.img_add_sub_account)
    ImageView mImg_sub_account_qrcode;
    @BindView(R.id.tv_add_sub_account)
    TextView mTv_add_sub_account;
    @BindView(R.id.iv_wu)
    ImageView mIvWu;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartrefresh;

    //    private ArrayList<SubAccount> subAccountArrayList;
    SPUtils spUtils = SPUtils.getInstance("token");

    private String userID;//用户id
    private QRCodeDialog qrCodeDialog;
    private UserInfo.UserInfoDean userInfoDean = new UserInfo.UserInfoDean();
        private List<SubUserInfo.SubUserInfoDean> subUserInfolist = new ArrayList<>();
    private SubAccountAdapter subAccountAdapter;
    private int cancleposition;

    @Override
    protected int setLayoutId() {
        return R.layout.activtity_sub_account_management;
    }

    @Override
    protected void initData() {
        userID = spUtils.getString("userName"); //获取用户id

       /* subAccountArrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            subAccountArrayList.add(new SubAccount());
        }*/
        mPresenter.GetUserInfoList(userID, "1");
        mPresenter.GetChildAccountByParentUserID(userID);


        subAccountAdapter = new SubAccountAdapter(R.layout.item_sub_account, subUserInfolist, mActivity);
        mRvSubAccount.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSubAccount.setAdapter(subAccountAdapter);
        subAccountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_close_account:
                    case R.id.img_tv_close_account:
                        final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                        dialog.setMessage("是否删除子账号")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {//删除用户
                                cancleposition = position;
                                mPresenter.CancelChildAccount(((SubUserInfo.SubUserInfoDean) adapter.getData().get(position)).getUserID(), userID);
                                dialog.dismiss();
                            }

                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }
                        }).show();


                        break;
                    default:
                        break;
                }

            }
        });


    }

    @Override
    public void initView() {
        mTvTitle.setText("子账号管理");
        mTvTitle.setVisibility(View.VISIBLE);

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mImg_sub_account_qrcode.setOnClickListener(this);
        mTv_add_sub_account.setOnClickListener(this);

        mSmartrefresh.setEnableLoadMoreWhenContentNotFull(false);
        mSmartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.GetChildAccountByParentUserID(userID);
                mSmartrefresh.finishRefresh();

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
        switch (v.getId()) {
            case R.id.icon_back:
                SubAccountManagementActivity.this.finish();
                break;
            case R.id.img_add_sub_account:
            case R.id.tv_add_sub_account:

                if (userInfoDean.getParentUserID() == null) {
                    qrCodeDialog = new QRCodeDialog(mActivity, userID);
                    qrCodeDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
                    qrCodeDialog.show();
                } else {//存在父账号
                    final CommonDialog_Home dialog = new CommonDialog_Home(this);
                    dialog.setMessage("已是子账号不能再添加")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(true).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
                            dialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                        }
                    }).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                userInfoDean = baseResult.getData().getData().get(0);

                break;
            default:

                break;

        }
    }

    @Override
    public void GetChildAccountByParentUserID(BaseResult<List<SubUserInfo.SubUserInfoDean>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:

                if (baseResult.getData().size() == 0) {
                    mIvWu.setVisibility(View.VISIBLE);
                } else {
                    mIvWu.setVisibility(View.GONE);
                    subUserInfolist.clear();
                    subUserInfolist.addAll(baseResult.getData());
                    subAccountAdapter.notifyDataSetChanged();
                }

                break;
            default:
                break;

        }


    }

    @Override
    public void CancelChildAccount(BaseResult<Data<String>> baseResult) {

        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    subAccountAdapter.remove(cancleposition);

                }

                break;
            default:
                break;
        }
    }
}
