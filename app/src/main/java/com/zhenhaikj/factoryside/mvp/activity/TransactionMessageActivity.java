package com.zhenhaikj.factoryside.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.MessageAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.MessageContract;
import com.zhenhaikj.factoryside.mvp.model.MessageModel;
import com.zhenhaikj.factoryside.mvp.presenter.MessagePresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionMessageActivity extends BaseActivity<MessagePresenter, MessageModel> implements View.OnClickListener, MessageContract.View {

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
    @BindView(R.id.tv_message_number)
    TextView mTvMessageNumber;
    @BindView(R.id.textView1)
    TextView mTextView1;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.rl_new_message)
    RelativeLayout mRlNewMessage;
    @BindView(R.id.rv_transactionmessage)
    RecyclerView mRvTransactionmessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_old_number)
    TextView mTvOldNumber;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.rl_old_message)
    RelativeLayout mRlOldMessage;
    @BindView(R.id.rv_transactionmessage_historical)
    RecyclerView mRvTransactionmessageHistorical;
    @BindView(R.id.refreshLayout_historical)
    SmartRefreshLayout mRefreshLayoutHistorical;
    private ArrayList<Message> messagesList = new ArrayList<>();
    private MessageAdapter messageAdapter;

    private int pageIndex = 1;
    private String userId;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_transactionmessage;
    }

    @Override
    protected void initData() {

        messageAdapter = new MessageAdapter(R.layout.item_message, messagesList);
        mRvTransactionmessage.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvTransactionmessage.setAdapter(messageAdapter);

        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetListmessageByType(userId, "1", "0", "10", "1");

    }

    @Override
    protected void initView() {
        mTvTitle.setText("交易消息");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);


        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageIndex = 1;
                //list.clear();
                mPresenter.GetListmessageByType(userId, "1", "0", "10", Integer.toString(pageIndex));
                messageAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                mPresenter.GetListmessageByType(userId, "1", "0", "10", Integer.toString(pageIndex));
                messageAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
    public void GetListmessageByType(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {
                    if (pageIndex == 1) {
                        messagesList.clear();
                        messageAdapter.notifyDataSetChanged();
                    } else {
                        if (pageIndex == 1) {
                            messagesList.clear();
                            messagesList.addAll(baseResult.getData().getData());
                            messageAdapter.notifyDataSetChanged();
                        } else {
                            messagesList.addAll(baseResult.getData().getData());
                            messageAdapter.setNewData(messagesList);
                        }

                    }
                }
                break;
            case 401:
                break;
        }
    }
}
