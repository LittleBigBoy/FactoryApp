package com.emjiayuan.nll.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emjiayuan.nll.R;
import com.emjiayuan.nll.adapter.CollectionAdapter;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.interfaces.AllCheckListener;
import com.emjiayuan.nll.model.Product;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.utils.SpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

public class HistoryActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


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
    @BindView(R.id.gv_cellection)
    GridView mGvCellection;
    @BindView(R.id.empty_view)
    LinearLayout mEmptyView;
    @BindView(R.id.checkAll)
    CheckBox mCheckAll;
    @BindView(R.id.check)
    TextView mCheck;
    @BindView(R.id.delete)
    TextView mDelete;
    @BindView(R.id.manage_ll)
    LinearLayout mManageLl;
    private CollectionAdapter adapter;
    private ArrayList<Product> list = new ArrayList<>();
    private boolean mIsFromItem = false;
    private ArrayList<Product> selectList = new ArrayList<>();
    private String productid = "";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvSave.setVisibility(View.GONE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setText("管理");
        mTvSave.setOnClickListener(this);
        mTvTitle.setText("浏览历史");
        list = (ArrayList<Product>) SpUtils.getDataList(mActivity,"history");
        adapter = new CollectionAdapter(mActivity, list, new AllCheckListener() {
            @Override
            public void onCheckedChanged(boolean b) {
                //根据不同的情况对maincheckbox做处理
                if (!b && !mCheckAll.isChecked()) {
                    return;
                } else if (!b && mCheckAll.isChecked()) {
                    mIsFromItem = true;
                    mCheckAll.setChecked(false);
                } else if (b) {
                    mIsFromItem = false;
                    mCheckAll.setChecked(true);
                }
            }
        });
        mGvCellection.setAdapter(adapter);
        mGvCellection.setEmptyView(mEmptyView);
        mGvCellection.setNumColumns(3);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mGvCellection.setOnItemClickListener(this);
        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //当监听来源为点击item改变maincbk状态时不在监听改变，防止死循环
                if (mIsFromItem) {
                    mIsFromItem = false;
                    Log.e("mainCheckBox", "此时我不可以触发");
                    return;
                }

                //改变数据
                for (Product carBean : list) {
                    carBean.setChecked(b);
                }
                Map<Integer, Boolean> map = new HashMap<>();
                for (int i = 0; i < list.size(); i++) {
                    if (b) {
                        map.put(i, true);
                    } else {
                        map.remove(i);
                    }
                }
                if (adapter != null) {
                    adapter.setMap(map);
                    //刷新listview
                    adapter.notifyDataSetChanged();
                }

            }
        });
        mDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (!MyUtils.isFastClick()) {
            return;
        }
        Intent intent = new Intent(HistoryActivity.this, ProductDetailActivity.class);
        intent.putExtra("productid", list.get(i).getId());
        startActivity(intent);
    }

}
