package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.TypeAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.contract.AddBrandContract;
import com.zhenhaikj.factoryside.mvp.model.AddBrandModel;
import com.zhenhaikj.factoryside.mvp.presenter.AddBrandPresenter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class ModelChooseActivity extends BaseActivity<AddBrandPresenter, AddBrandModel> implements View.OnClickListener, AddBrandContract.View {


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
    @BindView(R.id.rl_model)
    RecyclerView mRlModel;
    @BindView(R.id.iv_add_model)
    ImageView mIvAddModel;
    private List<Category> productTypeList;
    private TypeAdapter typeAdapter;
    private String userID;
    private String FProductTypeID;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_model;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("型号列表");
        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetBrandCategory(userID);
        typeAdapter = new TypeAdapter(R.layout.item_type_choose, productTypeList);
        mRlModel.setLayoutManager(new LinearLayoutManager(mActivity));
        mRlModel.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mActivity,HomeMaintenanceActivity2.class);
                intent.putExtra("type",productTypeList.get(position));
                setResult(100,intent);
                finish();
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIvAddModel.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.icon_back:
                finish();
                break;
            case R.id.iv_add_model:
                startActivityForResult(new Intent(mActivity,AddModelActivity.class),100);
                break;
        }
    }

    @Override
    public void AddFactoryBrand(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetFactoryCategory(BaseResult<CategoryData> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                break;
            case 401:

                break;
        }
    }

    @Override
    public void GetChildFactoryCategory(BaseResult<CategoryData> baseResult) {

    }

    @Override
    public void GetProducttype(BaseResult<Data<List<ProductType>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                break;
            case 401:
                break;
        }
    }

    @Override
    public void DeleteFactoryProducttype(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort("删除成功！");
                mPresenter.GetBrandCategory(userID);
                break;
            default:
                ToastUtils.showShort("删除失败！");
                break;
        }
    }

    @Override
    public void DeleteFactoryBrand(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetBrand(BaseResult<List<Brand>> baseResult) {

    }

    @Override
    public void AddBrandCategory(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetChildFactoryCategory2(BaseResult<CategoryData> baseResult) {

    }

    @Override
    public void DeleteFactoryProduct(BaseResult<Data> baseResult) {

    }

    @Override
    public void GetBrandCategory(BaseResult<Data<List<Category>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                productTypeList = baseResult.getData().getItem2();
                typeAdapter.setNewData(productTypeList);
                break;
            default:
                ToastUtils.showShort("获取型号失败！");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.GetBrandCategory(userID);
    }
}