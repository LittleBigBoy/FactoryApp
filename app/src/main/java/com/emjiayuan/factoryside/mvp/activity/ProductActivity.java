package com.emjiayuan.factoryside.mvp.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.factoryside.mvp.contract.ProductContract;
import com.emjiayuan.factoryside.mvp.model.ProductModel;
import com.emjiayuan.factoryside.mvp.presenter.ProductPresenter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.ProductAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Product;
import com.emjiayuan.nll.utils.GlideImageLoader;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ProductActivity extends BaseActivity<ProductPresenter,ProductModel> implements View.OnClickListener,ProductContract.View {


    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.iv_car)
    ImageView mIvCar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.shopping_cart_fab)
    FloatingActionButton mShoppingCartFab;

    private Banner bannerTop;
    private String[] images = new String[]{
            "http://qiniu.emjiayuan.com/upload_file/ems/2018092111329525354",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100815514348272",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018101113731538120",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100911071275167",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018071817991346559",
    };
    private String categoryid = "";
    private String topimage = "";
    private int pageindex = 1;
    private List<Product> mProductList=new ArrayList<>();
    private ArrayList<String> mImagelist;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected void initData() {
        categoryid = getIntent().getStringExtra("categoryid");
        topimage = getIntent().getStringExtra("topimage");
        mPresenter.getProduct(categoryid,Integer.toString(pageindex),"3");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageindex = 1;
                mProductList.clear();
                refreshLayout.setNoMoreData(false);
                mPresenter.getProduct(categoryid,Integer.toString(pageindex),"3");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageindex++;
                mPresenter.getProduct(categoryid,Integer.toString(pageindex),"3");
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mShoppingCartFab.setOnClickListener(this);
        mEtSearch.setOnClickListener(this);
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
            case R.id.shopping_cart_fab:
                startActivity(new Intent(mActivity, ShoppingCartActivity.class));
                break;
            case R.id.et_search:
                startActivity(new Intent(mActivity, SearchActivity.class));
                break;
        }
    }

    @Override
    public void getProduct(BaseResult<List<Product>> baseResult) {
        mProductList.addAll(baseResult.getData());
        ProductAdapter productAdapter = new ProductAdapter(R.layout.product_item, mProductList);
        View top = LayoutInflater.from(mActivity).inflate(R.layout.banner_top_product, null);
        bannerTop = top.findViewById(R.id.banner_top);
        productAdapter.addHeaderView(top);
        mRvProduct.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvProduct.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 1, R.color.gray));
//                            productAdapter.openLoadAnimation();
//                            productAdapter.isFirstOnly(true);
//                            productAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRvProduct.setAdapter(productAdapter);
        productAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productid", mProductList.get(position).getId());
                startActivity(intent);
            }
        });
        productAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.addCart("1",mProductList.get(position).getId(),"0","1");
            }
        });
        bannerTop.setImageLoader(new GlideImageLoader());
        mImagelist = new ArrayList();
        mImagelist.add(topimage);
        bannerTop.setImages(mImagelist);
        bannerTop.start();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    @Override
    public void getProductFail(BaseResult<List<Product>> baseResult) {
        if (pageindex!=1){
            refreshLayout.finishLoadMoreWithNoMoreData();
        }else{
            ToastUtils.showShort(baseResult.getMessage());
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void addCart(BaseResult baseResult) {
        ToastUtils.showShort(baseResult.getMessage());
    }
}