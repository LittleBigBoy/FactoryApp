package com.zhenhaikj.factoryside.mvp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.MessageAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.contract.MessageContract;
import com.zhenhaikj.factoryside.mvp.model.MessageModel;
import com.zhenhaikj.factoryside.mvp.presenter.MessagePresenter;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*工单消息*/
public class OrderMessageActivity2 extends BaseActivity<MessagePresenter, MessageModel> implements MessageContract.View, View.OnClickListener {

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

    private MessageAdapter messageAdapter;
    private int pageIndex = 1;

    private String userId;
    private List<Message> list = new ArrayList<>();//未读
    private int pos;
    private int type = 1;//1.工单消息  2.交易信息
    private ZLoadingDialog dialog = new ZLoadingDialog(this); //loading
    private int subType;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_ordermessage2;
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
        messageAdapter = new MessageAdapter(R.layout.item_message, list);
        mRvOrdermessage.setAdapter(messageAdapter);
        type = getIntent().getIntExtra("type", 1);
        subType = getIntent().getIntExtra("subType", 1);
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        showProgress();
        mPresenter.GetMessageList(userId, Integer.toString(type), Integer.toString(subType), "10", Integer.toString(pageIndex));
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
                mPresenter.GetMessageList(userId, Integer.toString(type), Integer.toString(subType), "10", Integer.toString(pageIndex));
                refreshlayout.finishRefresh();
                refreshlayout.resetNoMoreData();
            }
        });
        //上拉加载更多
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.GetMessageList(userId, Integer.toString(type), Integer.toString(subType), "10", Integer.toString(pageIndex));
                refreshlayout.finishLoadMore();
            }
        });
        messageAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                pos = position;
                switch (view.getId()) {
                    case R.id.ll_order_message:
                        mPresenter.AddOrUpdatemessage(((Message) adapter.getData().get(position)).getMessageID(), "2");
                        if (!"0".equals(((Message) adapter.getData().get(position)).getOrderID())) {
                            Intent intent = new Intent(mActivity, WarrantyActivity.class);
                            intent.putExtra("OrderID", ((Message) adapter.getData().get(position)).getOrderID());
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }


    @Override
    public void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (pageIndex != 1 && baseResult.getData().getData().size() == 0) {
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                }
                list.addAll(baseResult.getData().getData());
                messageAdapter.setNewData(list);
                hideProgress();
                break;
            default:
                break;
        }
    }

    @Override
    public void AllRead(BaseResult<MessageData<List<Message>>> baseResult) {
            switch (baseResult.getStatusCode()){
                case 200:
//                    if (baseResult.getData().isItem1()) {
                        list.get(pos).setIsLook("2");
                        messageAdapter.setNewData(list);
                        EventBus.getDefault().post("orderempty");
                        EventBus.getDefault().post("order_num");
                        EventBus.getDefault().post("transaction_num");
//                    }
                    hideProgress();
                    break;
            }
    }

    @Override
    public void GetReadMessageList(BaseResult<MessageData<List<Message>>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().getData() == null) {

                } else {

                }
                break;
            default:
                break;
        }

    }

    /*更新消息为已读*/
    @Override
    public void AddOrUpdatemessage(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    list.get(pos).setIsLook("2");
                    messageAdapter.setNewData(list);
                    EventBus.getDefault().post("orderempty");
                    EventBus.getDefault().post("order_num");
                    EventBus.getDefault().post("transaction_num");
                    mRefreshLayout.autoRefresh();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_actionbar_return:
                OrderMessageActivity2.this.finish();
                break;
            case R.id.tv_all_read:
                showProgress();
                mPresenter.AllRead(userId,Integer.toString(type),Integer.toString(subType));
                break;
        }
    }

//    public void showLoading() {
//        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
//                .setLoadingColor(Color.BLACK)//颜色
//                .setHintText("请稍后...")
//                .setHintTextSize(14) // 设置字体大小 dp
//                .setHintTextColor(Color.BLACK)  // 设置字体颜色
//                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
//                .setCanceledOnTouchOutside(false)//点击外部无法取消
//                .show();
//    }
//
//    public void cancleLoading() {
//        dialog.dismiss();
//    }
}
