package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.ArticleActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.contract.ArticleContract;
import com.zhenhaikj.factoryside.mvp.contract.HomeContract;
import com.zhenhaikj.factoryside.mvp.model.ArticleModel;
import com.zhenhaikj.factoryside.mvp.presenter.ArticlePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class NoticeFragment extends BaseLazyFragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.ll_platform_policy)
    LinearLayout mLlPlatformPolicy;
    @BindView(R.id.ll_news)
    LinearLayout mLlNews;
    @BindView(R.id.ll_order_must_read)
    LinearLayout mLlOrderMustRead;

    private String mParam1;
    private String mParam2;
    private Intent intent;

    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
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
        return R.layout.fragment_notice;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlPlatformPolicy.setOnClickListener(this);
        mLlNews.setOnClickListener(this);
        mLlOrderMustRead.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }


    @Override
    public void onClick(View v) {
        intent = new Intent(mActivity, ArticleActivity.class);
        switch (v.getId()){
//            case R.id.ll_announcement:
//                intent.putExtra("CategoryId","3");
//                intent.putExtra("title","系统消息");
//                break;
            case R.id.ll_platform_policy:
                intent.putExtra("CategoryId","4");
                intent.putExtra("title","平台政策");
                break;
            case R.id.ll_news:
                intent.putExtra("CategoryId","5");
                intent.putExtra("title","平台新闻");
                break;
            case R.id.ll_order_must_read:
                intent.putExtra("CategoryId","6");
                intent.putExtra("title","接单必读");
                break;
        }
        startActivity(intent);
    }


}
