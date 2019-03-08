package com.zhenhaikj.factoryside.mvp.fragment;

import android.os.Bundle;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.model.HomeModel;
import com.zhenhaikj.factoryside.mvp.presenter.HomePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ReturnFragment extends BaseLazyFragment<HomePresenter, HomeModel> implements HomeContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//

    private String mParam1;
    private String mParam2;
    public static ReturnFragment newInstance(String param1, String param2) {
        ReturnFragment fragment = new ReturnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_return
                ;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }
}
