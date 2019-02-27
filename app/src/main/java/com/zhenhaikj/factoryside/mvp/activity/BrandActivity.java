package com.zhenhaikj.factoryside.mvp.activity;

import android.app.AlertDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BrandAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CategoryAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.contract.AddBrandContract;
import com.zhenhaikj.factoryside.mvp.model.AddBrandModel;
import com.zhenhaikj.factoryside.mvp.presenter.AddBrandPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class BrandActivity extends BaseActivity<AddBrandPresenter, AddBrandModel> implements View.OnClickListener, AddBrandContract.View {

    private static final String TAG = "BrandActivity";
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
    @BindView(R.id.rl_brand)
    RecyclerView mRlBrand;
    @BindView(R.id.iv_add_brand)
    ImageView mIvAddBrand;
    @BindView(R.id.view)
    View mView;
    private List<ProductType> brandList = new ArrayList<>();
    private Brand brand;
    private BrandAdapter brandAdapter;
    private String brandName;
    private EditText et_brandName;
    private Button btn_next;
    private String userID;
    private String FBrandID;//品牌id
    private String FCategoryID;//分类id
    private TextView tv_choose_category;
    private String category;
    private View dialog;
    private AlertDialog alertDialog;
    private AlertDialog categoryDialog;
    private PopupWindow popupWindow;
    private List<Category> categoryList;
    private QMUIPopup qmuiPopup;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_brand;
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
        mTvTitle.setText("添加品牌");
//        for (int i = 0; i < 36; i++) {
//            brandList.add(new Brand());
//        }
        brandAdapter = new BrandAdapter(R.layout.item_brand, brandList);
        mRlBrand.setLayoutManager(new LinearLayoutManager(mActivity));
        mRlBrand.setAdapter(brandAdapter);
        brandAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_delete:
                        String id = brandAdapter.getData().get(position).getFProductTypeID();
//                        Log.d(TAG,"........."+id);
                        mPresenter.DeleteFactoryProducttype(id);
                        mPresenter.GetProducttype();
                        break;
                    case R.id.rl_brand:

                        break;
                }
            }
        });




        SPUtils spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetProducttype();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIvAddBrand.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_brand:
                dialog = LayoutInflater.from(mActivity).inflate(R.layout.dialog_brand_name, null);
                et_brandName = dialog.findViewById(R.id.et_enter);
                btn_next = dialog.findViewById(R.id.btn_next);
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        brandName = et_brandName.getText().toString();
                        if ("".equals(brandName) || brandName == null) {
                            MyUtils.showToast(mActivity, "请输入品牌名称！");
                            return;
                        }
                        mPresenter.AddFactoryBrand(userID, brandName);
                    }
                });
                alertDialog = new AlertDialog.Builder(mActivity).setView(dialog).create();
                alertDialog.show();
                break;
            case R.id.icon_back:
                finish();
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
    public void AddFactoryBrand(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data data = baseResult.getData();
                if (data.isItem1()) {
                    alertDialog.dismiss();
                    MyUtils.showToast(mActivity, "添加品牌成功！");
                    /*View dialog=LayoutInflater.from(mActivity).inflate(R.layout.dialog_choose_category,null);
                    tv_choose_category = dialog.findViewById(R.id.tv_choose_category);
                    btn_next = dialog.findViewById(R.id.btn_next);
                    tv_choose_category.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPresenter.GetFactoryCategory();
                        }
                    });
                    btn_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            category = tv_choose_category.getText().toString();
//                                mPresenter.AddFactoryBrand(userID,brandName);
                        }
                    });
                    categoryDialog=new AlertDialog.Builder(mActivity).setView(dialog).create();
                    categoryDialog.show();*/
                } else {
                    MyUtils.showToast(mActivity, "添加品牌失败！");
                }
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetFactoryCategory(BaseResult<Data<List<Category>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                categoryList = baseResult.getData().getItem2();
                showPopWindow();
                break;
            case 401:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

//    @Override
//    public void GetBrand(BaseResult<List<ProductType>> baseResult) {
//        switch (baseResult.getStatusCode()){
//            case 200:
//                brandList=baseResult.getData();
//                brandAdapter.setNewData(brandList);
//                FBrandID=brandList.get(1).getFBrandID();
//                mPresenter.GetCategory(FBrandID);
//                break;
//            case 401:
//                break;
//        }
//    }
//
//    @Override
//    public void GetCategory(BaseResult<Data<List<ProductType>>> baseResult) {
//        switch (baseResult.getStatusCode()){
//            case 200:
//                brandList=baseResult.getData().getItem2();
//                brandAdapter.setNewData(brandList);
//                break;
//            case 401:
//                break;
//        }
//    }

    @Override
    public void GetProducttype(BaseResult<Data<List<ProductType>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                brandList = baseResult.getData().getItem2();
                brandAdapter.setNewData(brandList);
                break;
            case 401:
                break;
        }
    }

    @Override
    public void DeleteFactoryProducttype(BaseResult<Data<List<ProductType>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:

                break;
            case 401:
                break;
        }
    }

    public void showPopWindow() {

        View view = LayoutInflater.from(mActivity).inflate(R.layout.category_pop, null);
        final RecyclerView rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        CategoryAdapter adapter = new CategoryAdapter(R.layout.category_item, categoryList);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupWindow.dismiss();
                tv_choose_category.setText(categoryList.get(position).getFCategoryName());
            }
        });
        popupWindow = new PopupWindow(view, tv_choose_category.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(tv_choose_category, 0, 10);
//            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }

    }
}