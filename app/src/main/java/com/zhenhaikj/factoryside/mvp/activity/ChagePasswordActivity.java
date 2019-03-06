package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ChagePasswordActivity extends BaseActivity<InfoManagePresenter, InfoManageModel> implements View.OnClickListener , InfoManageContract.View {

    private static final String TAG = "ChagePasswordActivity";
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
    @BindView(R.id.et_old_password)
    EditText mEtOldPassword;
    @BindView(R.id.et_new_password)
    EditText mEtNewPassword;
    @BindView(R.id.et_new_password_again)
    EditText mEtNewPasswordAgain;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    private String userId;
    private UserInfo.UserInfoDean userInfoDean=new UserInfo.UserInfoDean();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("修改密码");
        SPUtils spUtils=SPUtils.getInstance("token");
        userId=spUtils.getString("userName");
        Log.d(TAG,"........"+userId);
        mPresenter.GetUserInfoList(userId,"1");
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_save:
                String old_password=mEtOldPassword.getText().toString();
                String new_password=mEtNewPassword.getText().toString();
                String new_password_again=mEtNewPasswordAgain.getText().toString();

                if (old_password.equals("")||new_password.equals("")||new_password_again.equals("")){
                    Toast.makeText(ChagePasswordActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                }else {

                    // Log.d("用户的密码为11",userInfoDean.getPassWord());
                    if (!old_password.equals(userInfoDean.getPassWord())){
                        Toast.makeText(ChagePasswordActivity.this,"请输入正确的密码",Toast.LENGTH_LONG).show();

                    }else {

                        if (!new_password.equals(new_password_again)){ //两次输入的账号密码不一致
                            Toast.makeText(ChagePasswordActivity.this,"两次输入的密码不一致",Toast.LENGTH_LONG).show();

                        }else
                        {
                            Toast.makeText(ChagePasswordActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                            mPresenter.UpdatePassword(userId,new_password);
                        }

                    }

                }
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
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                userInfoDean=baseResult.getData().getData().get(0);
                break;
            case 401:
                break;
        }
    }

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {

    }

    @Override
    public void UpdatePassword(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                finish();
                break;
            case 401:
                break;
        }
    }

    @Override
    public void UploadAvator(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateSex(BaseResult<Data> baseResult) {

    }
}
