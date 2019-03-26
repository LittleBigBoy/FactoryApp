package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.labels.LabelsView;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.BrandChooseAdapter;
import com.zhenhaikj.factoryside.mvp.adapter.CategoryAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.contract.AddBrandContract;
import com.zhenhaikj.factoryside.mvp.model.AddBrandModel;
import com.zhenhaikj.factoryside.mvp.presenter.AddBrandPresenter;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AddModelActivity extends BaseActivity<AddBrandPresenter, AddBrandModel> implements View.OnClickListener, AddBrandContract.View {


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
    @BindView(R.id.tv_choose_brand)
    TextView mTvChooseBrand;
    @BindView(R.id.ll_choose_brand)
    LinearLayout mLlChooseBrand;
    @BindView(R.id.tv_choose_category)
    TextView mTvChooseCategory;
    @BindView(R.id.ll_choose_category)
    LinearLayout mLlChooseCategory;
    @BindView(R.id.et_type)
    EditText mEtType;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.et_price)
    EditText mEtPrice;
    @BindView(R.id.lv_popular)
    LabelsView mLvPopular;
    private SPUtils spUtils;
    private String userID;
    private List<Category> popularList;
    private LabelsView lv_popular;
    private RecyclerView rv_choose;
    private ImageView iv_close;
    private String FCategoryID;
    private String CategoryName;
    private PopupWindow popupWindow;
    private List<Category> chooseList;
    private CategoryAdapter chooseAdapter;
    private String SubCategoryName;
    private String SubCategoryID;
    private String FProductTypeID;
    private String ProductTypeName;
    private List<Brand> brandList;
    private BrandChooseAdapter brandsAdapter;
    private String BrandName="";
    private String FBrandID;
    private String InitPrice;
    private List<Category> selectLabelDatas;
    private String categorys="";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_model;
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
        mTvTitle.setText("添加型号");
//        for (int i = 0; i < 36; i++) {
//            brandList.add(new Brand());
//        }
        spUtils = SPUtils.getInstance("token");
        userID = spUtils.getString("userName");
        mPresenter.GetFactoryCategory("999");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlChooseBrand.setOnClickListener(this);
        mLlChooseCategory.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);

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
            case R.id.ll_choose_brand:
                mPresenter.GetBrand(userID);
                break;
            case R.id.ll_choose_category:

                break;
            case R.id.btn_add:
//                ProductTypeName = mEtType.getText().toString().trim();
                InitPrice = mEtPrice.getText().toString().trim();
                if ("".equals(BrandName)) {
                    ToastUtils.showShort("请选择品牌！");
                    return;
                }
                selectLabelDatas =mLvPopular.getSelectLabelDatas();
                if (selectLabelDatas.size()==0) {
                    ToastUtils.showShort("请选择分类！");
                    return;
                }
                for (int i = 0; i < selectLabelDatas.size(); i++) {
                    categorys += selectLabelDatas.get(i).getFCategoryID()+",";
                }
                categorys=categorys.substring(0,categorys.lastIndexOf(","));
//                if ("".equals(ProductTypeName)) {
//                    ToastUtils.showShort("请输入型号！");
//                    return;
//                }
//                 if ("".equals(InitPrice)) {
//                    ToastUtils.showShort("请输入服务价格！");
//                    return;
//                }
                mPresenter.AddBrandCategory(FBrandID, categorys);
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
                CategoryData data = baseResult.getData();
                if ("0".equals(data.getCode())) {
                    popularList = data.getData();
                    if (popularList.size() == 0) {
                        ToastUtils.showShort("无分类，请联系管理员添加！");
                    } else {
                        mLvPopular.setLabels(popularList, new LabelsView.LabelTextProvider<Category>() {
                            @Override
                            public CharSequence getLabelText(TextView label, int position, Category data) {
                                return data.getFCategoryName();
                            }
                        });
                    }
                } else {
                    ToastUtils.showShort("获取分类失败！");
                }
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetChildFactoryCategory(BaseResult<CategoryData> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                CategoryData data = baseResult.getData();
                if ("0".equals(data.getCode())) {
                    chooseList = data.getData();
                    if (chooseList.size() == 0) {
                        ToastUtils.showShort("无分类，请联系管理员添加！");
                    } else {
                        rv_choose.setLayoutManager(new LinearLayoutManager(mActivity));
                        chooseAdapter = new CategoryAdapter(R.layout.item_choose, chooseList);
                        rv_choose.setAdapter(chooseAdapter);
                        chooseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                SubCategoryName = chooseList.get(position).getFCategoryName();
                                mTvChooseCategory.setText(SubCategoryName);
                                SubCategoryID = chooseList.get(position).getFCategoryID();
                                popupWindow.dismiss();
                            }
                        });
                    }
                } else {
                    ToastUtils.showShort("获取分类失败！");
                }
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
    }

    @Override
    public void GetBrand(BaseResult<List<Brand>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                brandList = baseResult.getData();
                if (brandList.size() == 0) {
                    ToastUtils.showShort("你还没添加品牌，请先添加品牌！");
                    startActivity(new Intent(mActivity, BrandActivity.class));
                } else {
                    brandsAdapter = new BrandChooseAdapter(R.layout.category_item, brandList);
                    showPopWindow(mTvChooseBrand, brandsAdapter, brandList);
                }
                break;
            default:
//                ToastUtils.showShort(baseResult.getData());
                break;
        }
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
    public void AddBrandCategory(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    ToastUtils.showShort("添加成功！");
                    setResult(100);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetBrandCategory(BaseResult<Data<List<Category>>> baseResult) {

    }

    @Override
    public void DeleteFactoryProducttype(BaseResult<Data> baseResult) {

    }

    @Override
    public void DeleteFactoryBrand(BaseResult<Data> baseResult) {

    }

    public void showPopWindowGetCategory(final TextView tv) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_brand, null);
        lv_popular = contentView.findViewById(R.id.lv_popular);
        rv_choose = contentView.findViewById(R.id.rv_choose);
        iv_close = contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        lv_popular.setLabels(popularList, new LabelsView.LabelTextProvider<Category>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, Category data) {
                return data.getFCategoryName();
            }
        });
        FCategoryID = popularList.get(0).getId();
        CategoryName = popularList.get(0).getFCategoryName();
        mPresenter.GetChildFactoryCategory(popularList.get(0).getId());
        lv_popular.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                if (isSelect) {
                    FCategoryID = ((Category) data).getId();
                    CategoryName = ((Category) data).getFCategoryName();
                    mPresenter.GetChildFactoryCategory(((Category) data).getId());
                }
            }
        });

        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        popupWindow.setWidth(tv.getWidth());
        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(tv, 0, 10);
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    public void showPopWindow(final TextView tv, BaseQuickAdapter adapter, final List list) {

        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.category_pop, null);
        final RecyclerView rv = contentView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popupWindow.dismiss();
                if (list.get(position) instanceof Brand) {
                    FBrandID = ((Brand) list.get(position)).getFBrandID();
                    BrandName = ((Brand) list.get(position)).getFBrandName();
                    tv.setText(BrandName);
                    mTvChooseCategory.setText("");
                    FCategoryID = null;
                    FProductTypeID = null;
                    CategoryName = null;
                    SubCategoryID = null;
                    SubCategoryName = null;
                }
            }
        });
        popupWindow = new PopupWindow(contentView);
        popupWindow.setWidth(tv.getWidth());
        if (list.size() > 5) {
            popupWindow.setHeight(600);
        } else {
            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        }
//        popupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAsDropDown(tv, 0, 10);
//            popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}