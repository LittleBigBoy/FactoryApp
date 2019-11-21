package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.MessageAdapter2;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.contract.LeaveMeaasgeContract;
import com.zhenhaikj.factoryside.mvp.model.LeaveMeaasgeModel;
import com.zhenhaikj.factoryside.mvp.presenter.LeaveMeaasgePreaenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

/*留言消息*/
public class LeaveMessageActivity extends BaseActivity<LeaveMeaasgePreaenter, LeaveMeaasgeModel> implements LeaveMeaasgeContract.View, View.OnClickListener {

    @BindView(R.id.img_actionbar_return)
    ImageView mImgActionbarReturn;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_ordermessage)
    RecyclerView mRvOrdermessage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_all_read)
    TextView mTvAllRead;

    private MessageAdapter2 messageAdapter;
    private int pageIndex = 1;

    private String userId;
    private List<LeaveMessage.DataBean> list = new ArrayList<>();//未读
    private int pos;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_leavemessage;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        mRvOrdermessage.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrdermessage.setHasFixedSize(true);
        mRvOrdermessage.setNestedScrollingEnabled(false);
        messageAdapter = new MessageAdapter2(R.layout.item_message, list);
        mRvOrdermessage.setAdapter(messageAdapter);

        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        mPresenter.GetNewsLeaveMessage(userId,"10",String.valueOf(pageIndex));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mImgActionbarReturn.setOnClickListener(this);
        mTvAllRead.setOnClickListener(this);
        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                list.clear();
                mPresenter.GetNewsLeaveMessage(userId,"10",String.valueOf(pageIndex));
                refreshlayout.finishRefresh();
                refreshlayout.resetNoMoreData();
            }
        });

        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++; //页数加1
                mPresenter.GetNewsLeaveMessage(userId,"10",String.valueOf(pageIndex));
                refreshLayout.finishLoadMore();
            }
        });

        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                pos = position;
                switch (view.getId()) {
                    case R.id.ll_order_message:
                        mPresenter.LeaveMessageWhetherLook(list.get(position).getOrderId());
                        if (!"0".equals(list.get(position).getOrderId())) {
                            Intent intent = new Intent(mActivity, WarrantyActivity.class);
                            intent.putExtra("OrderID", list.get(position).getOrderId());
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_actionbar_return:
                LeaveMessageActivity.this.finish();
                break;
            case R.id.tv_all_read:
                showProgress();
                break;
        }
    }

    @Override
    public void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (pageIndex != 1 && baseResult.getData().getItem2().getData().size() == 0) {
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                }
                list.addAll(baseResult.getData().getItem2().getData());
                messageAdapter.setNewData(list);
                break;
        }
    }

    @Override
    public void LeaveMessageWhetherLook(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()) {
                    list.get(pos).setFactoryIslook("2");
                    messageAdapter.setNewData(list);
                    EventBus.getDefault().post("orderempty");
                    EventBus.getDefault().post("LeaveMessage");
                    mRefreshLayout.autoRefresh();
                }
                break;
        }
    }
}
