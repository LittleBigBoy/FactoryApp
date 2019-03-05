package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.contract.InfoManageContract;
import com.zhenhaikj.factoryside.mvp.model.InfoManageModel;
import com.zhenhaikj.factoryside.mvp.presenter.InfoManagePresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChageUserNameActivity extends BaseActivity<InfoManagePresenter, InfoManageModel> implements View.OnClickListener, InfoManageContract.View {
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
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    String UserId;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_username;
    }

    @Override
    protected void initData() {
        mTvTitle.setText("修改昵称");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        SPUtils spUtils=SPUtils.getInstance("token");
        UserId=spUtils.getString("userName");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);

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
            case R.id.btn_save:
                if (mEtUsername.getText().toString().isEmpty()){
                    MyUtils.showToast(mActivity,"昵称不能为空");
                }else {
                    String name=mEtUsername.getText().toString().trim();
                    mPresenter.UpdateAccountNickName(UserId,name);
                }
                break;
        }
    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                MyUtils.showToast(mActivity,"修改成功");
                startActivity(new Intent(mActivity,PersonalInformationActivity.class));
                break;
            case 401:
                break;
        }
    }

    @Override
    public void UpdatePassword(BaseResult<Data> baseResult) {

    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {

    }
}
