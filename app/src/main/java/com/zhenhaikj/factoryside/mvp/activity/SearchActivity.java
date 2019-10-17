package com.zhenhaikj.factoryside.mvp.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.SearchAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.contract.SearchContract;
import com.zhenhaikj.factoryside.mvp.model.SearchModel;
import com.zhenhaikj.factoryside.mvp.presenter.SearchPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<SearchPresenter, SearchModel> implements View.OnClickListener, SearchContract.View {
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.back)
    TextView mBack;
    @BindView(R.id.rv_search)
    RecyclerView mRvSearch;
    @BindView(R.id.view)
    View mView;
    private String userId;
    private List<Search> list=new ArrayList<>();
    private SearchAdapter searchAdapter;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SPUtils spUtils=SPUtils.getInstance("token");
        userId = spUtils.getString("userName");

        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void setListener() {
        mBack.setOnClickListener(this);
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
            case R.id.back:
                String searchname=mEtSearch.getText().toString();
                if (searchname==null||"".equals(searchname)){
                    ToastUtils.showShort("请输入用户手机号或者工单号");
                }else {
                    StringBuilder stringBuilder = new StringBuilder(searchname);
                    String name=stringBuilder.substring(0,1);
                    if ("1".equals(name)){
                        mPresenter.GetOrderInfoList(searchname,"",userId,"999","1");
                    }else {
                        mPresenter.GetOrderInfoList("",searchname,userId,"999","1");
                    }
                }

                break;
        }
    }

    @Override
    public void GetOrderInfoList(BaseResult<Search> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                searchAdapter = new SearchAdapter(R.layout.order_item,baseResult.getData().getData());
                mRvSearch.setLayoutManager(new LinearLayoutManager(mActivity));
                mRvSearch.setAdapter(searchAdapter);
                searchAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                        switch (view.getId()) {
                            case R.id.iv_copy:
                                String id = baseResult.getData().getData().get(position).getOrderID();
                                myClip = ClipData.newPlainText("", id);
                                myClipboard.setPrimaryClip(myClip);
                                ToastUtils.showShort("复制成功");
                                break;
                            case R.id.iv_star:
                                if (view.isSelected()){
                                    view.setSelected(false);
                                    mPresenter.GetFStarOrder(baseResult.getData().getData().get(position).getOrderID(),"N");
                                }else {
                                    view.setSelected(true);
                                    mPresenter.GetFStarOrder(baseResult.getData().getData().get(position).getOrderID(),"Y");
                                }
                                break;

                            case R.id.tv_see_detail:
                                Intent intent1 = new Intent(mActivity, WarrantyActivity.class);
                                intent1.putExtra("OrderID", baseResult.getData().getData().get(position).getOrderID());
                                startActivity(intent1);
                                break;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void GetFStarOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                break;
        }
    }
}
