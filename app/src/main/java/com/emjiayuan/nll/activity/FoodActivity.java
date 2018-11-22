package com.emjiayuan.nll.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.ConstellationAdapter;
import com.emjiayuan.nll.adapter.NormalAdapter;
import com.emjiayuan.nll.adapter.StoreAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.Store;
import com.emjiayuan.nll.widget.RecyclerViewDivider;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class FoodActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "FoodActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    @BindView(R.id.icon_back)
    ImageView iconBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.icon_search)
    ImageView iconSearch;
    private String headers[] = {
            "城市",
            "年龄",
            "性别",
            "星座"
    };
    private List<View> popupViews = new ArrayList<>();

    private NormalAdapter cityAdapter;
    private NormalAdapter ageAdapter;
    private NormalAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;

    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    private int constellationPosition = 0;
    private StoreAdapter mStoreAdapter;
    private List<Store> mStoreList;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_food;
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView() {
        tvTitle.setText("清真美食");
        //init city menu
        final RecyclerView cityView = new RecyclerView(mActivity);
        cityAdapter = new NormalAdapter(R.layout.normal_item, Arrays.asList(citys));
        cityView.setLayoutManager(new LinearLayoutManager(mActivity));
        cityView.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.VERTICAL));
        cityView.setAdapter(cityAdapter);

        //init age menu
        final RecyclerView ageView = new RecyclerView(mActivity);
        ageView.setLayoutManager(new LinearLayoutManager(mActivity));
        ageView.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.VERTICAL));
        ageAdapter = new NormalAdapter(R.layout.normal_item, Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final RecyclerView sexView = new RecyclerView(mActivity);
        sexView.setLayoutManager(new LinearLayoutManager(mActivity));
        sexView.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.VERTICAL));
        sexAdapter = new NormalAdapter(R.layout.normal_item, Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init constellation
        final RecyclerView constellationView = new RecyclerView(mActivity);
//        constellationView.setBackgroundColor(R.color.colorAccent);
//        constellationView.setLayoutManager(new GridLayoutManager(mActivity, 5));
        constellationView.setLayoutManager(new LinearLayoutManager(mActivity));
        constellationView.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.VERTICAL));
        constellationAdapter = new ConstellationAdapter(R.layout.normal_item, Arrays.asList(constellations));
        constellationView.setAdapter(constellationAdapter);
//        TextView ok = ButterKnife.findById(constellationView, R.id.ok);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDropDownMenu.setTabText(constellationPosition == 0 ? headers[3] : constellations[constellationPosition]);
//                mDropDownMenu.closeMenu();
//            }
//        });

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);
        popupViews.add(constellationView);

        //add item click event
//        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                cityAdapter.setCheckItem(position);
//                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
//                mDropDownMenu.closeMenu();
//            }
//        });
        cityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);
                mDropDownMenu.closeMenu();
            }
        });
        ageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });
        constellationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mDropDownMenu.setTabText(position == 0 ? headers[3] : constellations[position]);
                mDropDownMenu.closeMenu();
            }
        });

        //init dropdownview
        RecyclerView rvContent = new RecyclerView(mActivity);
        rvContent.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT));
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        rvContent.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL, 2, R.color.gray));
        mStoreList = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            mStoreList.add(new Store(R.drawable.all, "兰州正宗牛肉拉面", 3, "宁波市镇海区东威大厦", "3.7km"));
        }
        mStoreAdapter = new StoreAdapter(R.layout.store_item, mStoreList);
        mStoreAdapter.openLoadAnimation();
        mStoreAdapter.isFirstOnly(false);
        mStoreAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        rvContent.setAdapter(mStoreAdapter);
        mStoreAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(mActivity,StoreDetailActivity.class));
            }
        });
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, rvContent);
    }

    @Override
    protected void setListener() {
        iconBack.setOnClickListener(this);
        iconSearch.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_search:
                finish();
        }
    }
}