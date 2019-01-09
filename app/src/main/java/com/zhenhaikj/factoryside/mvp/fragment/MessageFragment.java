package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.AllWorkOrdersActivity;
import com.zhenhaikj.factoryside.mvp.activity.BatchOrderActivity;
import com.zhenhaikj.factoryside.mvp.activity.CustomerServiceActivity;
import com.zhenhaikj.factoryside.mvp.activity.HomeInstallationActivity;
import com.zhenhaikj.factoryside.mvp.activity.HomeMaintenanceActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.model.HomeModel;
import com.zhenhaikj.factoryside.mvp.presenter.HomePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MessageFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements HomeContract.View {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void success(BaseResult<HomeData> baseResult) {

    }
}
