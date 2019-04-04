package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.ArticleActivity;
import com.zhenhaikj.factoryside.mvp.activity.OrderMessageActivity;
import com.zhenhaikj.factoryside.mvp.activity.TransactionMessageActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class InformationFragment extends BaseLazyFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.ll_work_order_message)
    LinearLayout mLlWorkOrderMessage;
    @BindView(R.id.ll_transaction_news)
    LinearLayout mLlTransactionNews;
    @BindView(R.id.ll_announcement)
    LinearLayout mLlAnnouncement;

    private String mParam1;
    private String mParam2;

    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
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
        return R.layout.fragment_information;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlAnnouncement.setOnClickListener(this);
        mLlWorkOrderMessage.setOnClickListener(this);
        mLlTransactionNews.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_announcement:
                Intent intent = new Intent(mActivity, ArticleActivity.class);
                intent.putExtra("CategoryId","3");
                intent.putExtra("title","系统消息");
                startActivity(intent);
                break;
            case R.id.ll_work_order_message:
                startActivity(new Intent(getActivity(), OrderMessageActivity.class));
                break;
            case R.id.ll_transaction_news:
                startActivity(new Intent(getActivity(), TransactionMessageActivity.class));
                break;
        }
    }
}
