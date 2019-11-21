package com.zhenhaikj.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.ArticleActivity;
import com.zhenhaikj.factoryside.mvp.activity.LeaveMessageActivity;
import com.zhenhaikj.factoryside.mvp.activity.OrderMessageActivity2;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.ArticleContract;
import com.zhenhaikj.factoryside.mvp.model.ArticleModel;
import com.zhenhaikj.factoryside.mvp.presenter.ArticlePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import q.rorbin.badgeview.QBadgeView;

public class InformationFragment extends BaseLazyFragment<ArticlePresenter, ArticleModel> implements View.OnClickListener, ArticleContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.ll_work_order_message)
    LinearLayout mLlWorkOrderMessage;
    @BindView(R.id.ll_transaction_news)
    LinearLayout mLlTransactionNews;
    @BindView(R.id.ll_announcement)
    LinearLayout mLlAnnouncement;
    @BindView(R.id.tv_work_order_message)
    TextView mTvWorkOrderMessage;
    @BindView(R.id.ll_workmessage)
    LinearLayout mLlWorkmessage;
    @BindView(R.id.tv_trading_information)
    TextView mTvTradingInformation;
    @BindView(R.id.ll_transactionmessage)
    LinearLayout mLlTransactionmessage;
    @BindView(R.id.tv_system_information)
    TextView mTvSystemInformation;
    @BindView(R.id.tv_leave_message)
    TextView mTvLeaveMessage;
    @BindView(R.id.ll_leave)
    LinearLayout mLlLeave;
    @BindView(R.id.ll_leave_message)
    LinearLayout mLlLeaveMessage;
    private QBadgeView workqBadgeView;
    private QBadgeView transactionqBadgeView;
    private QBadgeView LeaveMessageBadgeView;
    private String mParam1;
    private String mParam2;

    private SPUtils spUtils = SPUtils.getInstance("token");
    private String userId = spUtils.getString("userName");

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
        EventBus.getDefault().register(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initData() {
        workqBadgeView = new QBadgeView(mActivity);
        workqBadgeView.bindTarget(mLlWorkmessage);
        workqBadgeView.setBadgeGravity(Gravity.CENTER | Gravity.END);


        transactionqBadgeView = new QBadgeView(mActivity);
        transactionqBadgeView.bindTarget(mLlTransactionmessage);
        transactionqBadgeView.setBadgeGravity(Gravity.CENTER | Gravity.END);

        LeaveMessageBadgeView = new QBadgeView(mActivity);
        LeaveMessageBadgeView.bindTarget(mLlLeave);
        LeaveMessageBadgeView.setBadgeGravity(Gravity.CENTER | Gravity.END);


        mPresenter.GetOrderMessageList(userId, "0", "99", "1");
        mPresenter.GetTransactionMessageList(userId, "0", "99", "1");
        mPresenter.GetNewsLeaveMessage(userId,"10","1");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlAnnouncement.setOnClickListener(this);
        mLlWorkOrderMessage.setOnClickListener(this);
        mLlTransactionNews.setOnClickListener(this);
        mLlLeaveMessage.setOnClickListener(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        switch (name) {
            case "transaction_num":
                mPresenter.GetTransactionMessageList(userId, "0", "99", "1");
                break;
            case "order_num":
                mPresenter.GetOrderMessageList(userId, "0", "99", "1");
                break;
            case "LeaveMessage":
                mPresenter.GetNewsLeaveMessage(userId,"10","1");
                break;

        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ll_announcement:
                intent = new Intent(mActivity, ArticleActivity.class);
                intent.putExtra("CategoryId", "3");
                intent.putExtra("title", "系统消息");
                startActivity(intent);
                break;
            case R.id.ll_work_order_message://工单消息
                intent = new Intent(getActivity(), OrderMessageActivity2.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.ll_transaction_news://交易信息
                intent = new Intent(getActivity(), OrderMessageActivity2.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.ll_leave_message:
                startActivity(new Intent(mActivity, LeaveMessageActivity.class));
                break;
        }
    }

    @Override
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {

    }

    @Override
    public void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    workqBadgeView.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getCount() >= 99) {
                    workqBadgeView.setVisibility(View.VISIBLE);
                    workqBadgeView.setBadgeNumber(99);
                } else {
                    workqBadgeView.setVisibility(View.VISIBLE);
                    workqBadgeView.setBadgeNumber(baseResult.getData().getCount());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getCount() == 0) {
                    transactionqBadgeView.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getCount() >= 99) {
                    transactionqBadgeView.setVisibility(View.VISIBLE);
                    transactionqBadgeView.setBadgeNumber(99);
                } else {
                    transactionqBadgeView.setVisibility(View.VISIBLE);
                    transactionqBadgeView.setBadgeNumber(baseResult.getData().getCount());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getItem2().getNoLeaveMessage()==0) {
                    LeaveMessageBadgeView.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getItem2().getNoLeaveMessage() >= 99) {
                    LeaveMessageBadgeView.setVisibility(View.VISIBLE);
                    LeaveMessageBadgeView.setBadgeNumber(99);
                } else {
                    LeaveMessageBadgeView.setVisibility(View.VISIBLE);
                    LeaveMessageBadgeView.setBadgeNumber(baseResult.getData().getItem2().getNoLeaveMessage());
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
