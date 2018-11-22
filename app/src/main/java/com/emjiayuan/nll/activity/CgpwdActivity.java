package com.emjiayuan.nll.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class CgpwdActivity extends BaseActivity {


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
    @BindView(R.id.old_pwd)
    EditText mOldPwd;
    @BindView(R.id.new_pwd)
    EditText mNewPwd;
    @BindView(R.id.re_pwd)
    EditText mRePwd;
    private String oldpassword;
    private String newpassword;
    private String confirmpassword;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_cgpwd;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("修改密码");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("确认修改");
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                oldpassword = mOldPwd.getText().toString();
                newpassword = mNewPwd.getText().toString();
                confirmpassword = mRePwd.getText().toString();
                if ("".equals(oldpassword)) {
                    MyUtils.showToast(mActivity, "请输入当前密码！");
                    return;
                }
                if ("".equals(newpassword)) {
                    MyUtils.showToast(mActivity, "请输入新密码！");
                    return;
                }
                if ("".equals(confirmpassword)) {
                    MyUtils.showToast(mActivity, "请确认密码！");
                    return;
                }
                if (!newpassword.equals(confirmpassword)) {
                    MyUtils.showToast(mActivity, "两次输入密码不一致！");
                    return;
                }
                editUserPassword();
            }
        });
    }

    @Override
    protected void setListener() {

    }


    public void editUserPassword() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("oldpassword", oldpassword);
        formBody.add("newpassword", newpassword);
        formBody.add("confirmpassword", confirmpassword);

//new call
        Call call = MyOkHttp.GetCall("user.editUserPassword", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyUtils.e("------修改用户密码------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            switch (msg.what) {

                case 0:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            MyUtils.showToast(mActivity, message);
                            finish();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

}
