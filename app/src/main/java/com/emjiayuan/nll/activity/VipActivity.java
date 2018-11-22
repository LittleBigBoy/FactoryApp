package com.emjiayuan.nll.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.PrivilegeAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Privilege;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class VipActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "VipActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.icon_search)
    ImageView iconSearch;
    @BindView(R.id.rv_privilege)
    RecyclerView mRvPrivilege;
    @BindView(R.id.cbox)
    CheckBox mCbox;
    @BindView(R.id.ll_check)
    LinearLayout mLlCheck;
    @BindView(R.id.description)
    TextView mDescription;
    private PrivilegeAdapter mPrivilegeAdapter;
    private ArrayList<Privilege> mPrivilegeList;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("开通会员");
        mRvPrivilege.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mPrivilegeList = new ArrayList<>();
        for (int i = 0; i < 119; i++) {
            mPrivilegeList.add(new Privilege(R.drawable.all, "高级合伙人", "产品一律8.5折，价值2000元"));
        }
        mPrivilegeAdapter = new PrivilegeAdapter(R.layout.privilege_item, mPrivilegeList);
        mRvPrivilege.setAdapter(mPrivilegeAdapter);
    }

    @Override
    protected void setListener() {
        iconBack.setOnClickListener(this);
        iconSearch.setOnClickListener(this);
        mLlCheck.setOnClickListener(this);
        mDescription.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
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
            case R.id.ll_check:
                if (mCbox.isChecked()){
                    mCbox.setChecked(false);
                }else{
                    mCbox.setChecked(true);
                }
                break;
            case R.id.description:
//                finish();
                break;
        }
    }
}