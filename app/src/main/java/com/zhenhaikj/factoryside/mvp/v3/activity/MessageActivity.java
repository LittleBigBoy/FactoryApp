package com.zhenhaikj.factoryside.mvp.v3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.SPUtils;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.activity.LeaveMessageActivity;
import com.zhenhaikj.factoryside.mvp.activity.OrderMessageActivity2;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.GetMessagePag;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.v3.mvp.contract.MessageContract;
import com.zhenhaikj.factoryside.mvp.v3.mvp.model.MessageModel;
import com.zhenhaikj.factoryside.mvp.v3.mvp.presenter.MessagePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageActivity extends BaseActivity<MessagePresenter, MessageModel> implements View.OnClickListener , MessageContract.View{
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
    @BindView(R.id.tv_system_notification)
    TextView mTvSystemNotification;
    @BindView(R.id.ll_system_notification)
    LinearLayout mLlSystemNotification;
    @BindView(R.id.tv_on_site_reminder)
    TextView mTvOnSiteReminder;
    @BindView(R.id.ll_on_site_reminder)
    LinearLayout mLlOnSiteReminder;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.tv_order_message_number)
    TextView mTvOrderMessageNumber;
    @BindView(R.id.tv_order_message)
    TextView mTvOrderMessage;
    @BindView(R.id.tv_order_message_time)
    TextView mTvOrderMessageTime;
    @BindView(R.id.ll_order_message)
    LinearLayout mLlOrderMessage;
    @BindView(R.id.tv_accessories_news_number)
    TextView mTvAccessoriesNewsNumber;
    @BindView(R.id.tv_accessories_news)
    TextView mTvAccessoriesNews;
    @BindView(R.id.tv_accessories_news_time)
    TextView mTvAccessoriesNewsTime;
    @BindView(R.id.ll_accessories_news)
    LinearLayout mLlAccessoriesNews;
    @BindView(R.id.tv_review_notice_number)
    TextView mTvReviewNoticeNumber;
    @BindView(R.id.tv_review_notice)
    TextView mTvReviewNotice;
    @BindView(R.id.tv_review_notice_time)
    TextView mTvReviewNoticeTime;
    @BindView(R.id.ll_review_notice)
    LinearLayout mLlReviewNotice;
    @BindView(R.id.tv_transaction_notification_number)
    TextView mTvTransactionNotificationNumber;
    @BindView(R.id.tv_transaction_notification)
    TextView mTvTransactionNotification;
    @BindView(R.id.tv_transaction_notification_time)
    TextView mTvTransactionNotificationTime;
    @BindView(R.id.ll_transaction_notification)
    LinearLayout mLlTransactionNotification;
    @BindView(R.id.tv_complaint_message_number)
    TextView mTvComplaintMessageNumber;
    @BindView(R.id.tv_complaint_message)
    TextView mTvComplaintMessage;
    @BindView(R.id.tv_complaint_message_time)
    TextView mTvComplaintMessageTime;
    @BindView(R.id.ll_complaint_message)
    LinearLayout mLlComplaintMessage;
    private SPUtils spUtils;
    private String userId;
    private Intent intent;
    private GetMessagePag data;

    @Override
    protected int setLayoutId() {
        return R.layout.v3_activity_message;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("消息通知");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.GONE);
        mTvSave.setText("全部已读");
        spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        showProgress();
//        mPresenter.GetOrderMessageList(userId, "0", "1", "1");//工单消息
//        mPresenter.GetTransactionMessageList(userId, "0", "1", "1");
        mPresenter.GetNewsLeaveMessage(userId, "1", "1");
//        mPresenter.GetTicketMessageList(userId, "10", "1", "1");//配件消息
//        mPresenter.GetReviewMessageList(userId, "11", "1", "1");
//        mPresenter.GetComplaintMessageList(userId, "12", "1", "1");
        mPresenter.GetmessagePag(userId);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlSystemNotification.setOnClickListener(this);
        mLlOrderMessage.setOnClickListener(this);
        mLlTransactionNotification.setOnClickListener(this);
        mLlMessage.setOnClickListener(this);
        mLlAccessoriesNews.setOnClickListener(this);
        mLlReviewNotice.setOnClickListener(this);
        mLlComplaintMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_system_notification:
                startActivity(new Intent(mActivity, SystemNotificationActivity.class));
                break;
            case R.id.ll_order_message:
                intent = new Intent(mActivity, OrderMessageActivity2.class);
                intent.putExtra("type", 2);
                intent.putExtra("subType", 0);
                startActivity(intent);
                break;
            case R.id.ll_transaction_notification:
                intent = new Intent(mActivity, OrderMessageActivity2.class);
                intent.putExtra("type", 1);
                intent.putExtra("subType", 0);
                startActivity(intent);
                break;
            case R.id.ll_message:
                startActivity(new Intent(mActivity, LeaveMessageActivity.class));
                break;
            case R.id.ll_accessories_news:
                intent = new Intent(mActivity, OrderMessageActivity2.class);
                intent.putExtra("type", 2);
                intent.putExtra("subType", 10);
                startActivity(intent);
                break;
            case R.id.ll_review_notice:
                intent = new Intent(mActivity, OrderMessageActivity2.class);
                intent.putExtra("type", 2);
                intent.putExtra("subType", 11);
                startActivity(intent);
                break;
            case R.id.ll_complaint_message:
                intent = new Intent(mActivity, OrderMessageActivity2.class);
                intent.putExtra("type", 2);
                intent.putExtra("subType", 12);
                startActivity(intent);
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
    public void GetListCategoryContentByCategoryID(BaseResult<Article> baseResult) {

    }

    @Override
    public void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetTicketMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetReviewMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetComplaintMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult) {

    }

    @Override
    public void GetNewsLeaveMessage(BaseResult<Data<LeaveMessage>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().getItem2().getNoLeaveMessage()==0) {
                    mTvMessage.setVisibility(View.INVISIBLE);
                    return;
                } else if (baseResult.getData().getItem2().getNoLeaveMessage() >= 99) {
                    mTvMessage.setVisibility(View.VISIBLE);
                    mTvMessage.setText("99");
                } else {
                    mTvMessage.setVisibility(View.VISIBLE);
                    mTvMessage.setText(baseResult.getData().getItem2().getNoLeaveMessage()+"");
                }
                hideProgress();
                break;
        }
    }

    @Override
    public void GetmessagePag(BaseResult<Data<GetMessagePag>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                hideProgress();
                data = baseResult.getData().getItem2();
                if (baseResult.getData().isItem1()){
                    if (data.getCount2() == 0) {
                        mTvOrderMessageNumber.setVisibility(View.GONE);
                        mTvOrderMessage.setText("暂无新的订单消息");
                        mTvOrderMessageTime.setVisibility(View.GONE);
                    } else if (data.getCount2() >= 99) {
                        mTvOrderMessageNumber.setVisibility(View.VISIBLE);
                        mTvOrderMessageNumber.setText("99");
                        mTvOrderMessageTime.setVisibility(View.VISIBLE);
                        mTvOrderMessage.setText(data.getData2().getContent());
                        mTvOrderMessageTime.setText(data.getData2().getNowtime().substring(0,10));
                    } else {
                        mTvOrderMessageNumber.setVisibility(View.VISIBLE);
                        mTvOrderMessageNumber.setText(data.getCount2()+"");
                        mTvOrderMessageTime.setVisibility(View.VISIBLE);
                        mTvOrderMessage.setText(data.getData2().getContent());
                        mTvOrderMessageTime.setText(data.getData2().getNowtime().substring(0,10));
                    }

                    if (data.getCount3() == 0) {
                        mTvAccessoriesNewsNumber.setVisibility(View.GONE);
                        mTvAccessoriesNews.setText("暂无新的配件消息");
                        mTvAccessoriesNewsTime.setVisibility(View.GONE);
                    } else if (data.getCount3() >= 99) {
                        mTvAccessoriesNewsNumber.setVisibility(View.VISIBLE);
                        mTvAccessoriesNewsNumber.setText("99");
                        mTvAccessoriesNewsTime.setVisibility(View.VISIBLE);
                        mTvAccessoriesNews.setText(data.getData3().getContent());
                        mTvAccessoriesNewsTime.setText(data.getData3().getNowtime().substring(0,10));
                    } else {
                        mTvAccessoriesNewsNumber.setVisibility(View.VISIBLE);
                        mTvAccessoriesNewsNumber.setText(data.getCount3()+"");
                        mTvAccessoriesNewsTime.setVisibility(View.VISIBLE);
                        mTvAccessoriesNews.setText(data.getData3().getContent());
                        mTvAccessoriesNewsTime.setText(data.getData3().getNowtime().substring(0,10));
                    }

                    if (data.getCount4() == 0) {
                        mTvReviewNoticeNumber.setVisibility(View.GONE);
                        mTvReviewNotice.setText("暂无新的审核消息");
                        mTvReviewNoticeTime.setVisibility(View.GONE);
                    } else if (data.getCount4() >= 99) {
                        mTvReviewNoticeNumber.setVisibility(View.VISIBLE);
                        mTvReviewNoticeNumber.setText("99");
                        mTvReviewNoticeTime.setVisibility(View.VISIBLE);
                        mTvReviewNotice.setText(data.getData4().getContent());
                        mTvReviewNoticeTime.setText(data.getData4().getNowtime().substring(0,10));
                    } else {
                        mTvReviewNoticeNumber.setVisibility(View.VISIBLE);
                        mTvReviewNoticeNumber.setText(data.getCount4()+"");
                        mTvReviewNoticeTime.setVisibility(View.VISIBLE);
                        mTvReviewNotice.setText(data.getData4().getContent());
                        mTvReviewNoticeTime.setText(data.getData4().getNowtime().substring(0,10));
                    }


                    if (data.getCount5() == 0) {
                        mTvTransactionNotificationNumber.setVisibility(View.INVISIBLE);
                        mTvTransactionNotification.setText("暂无新的交易消息");
                        mTvTransactionNotificationTime.setVisibility(View.GONE);
                    } else if (data.getCount5() >= 99) {
                        mTvTransactionNotificationNumber.setVisibility(View.VISIBLE);
                        mTvTransactionNotificationNumber.setText("99");
                        mTvTransactionNotification.setText(data.getData5().getContent());
                        mTvTransactionNotificationTime.setText(data.getData5().getNowtime().substring(0,10));
                        mTvTransactionNotificationTime.setVisibility(View.VISIBLE);
                    } else {
                        mTvTransactionNotificationNumber.setVisibility(View.VISIBLE);
                        mTvTransactionNotificationNumber.setText(data.getCount5()+"");
                        mTvTransactionNotification.setText(data.getData5().getContent());
                        mTvTransactionNotificationTime.setText(data.getData5().getNowtime().substring(0,10));
                        mTvTransactionNotificationTime.setVisibility(View.VISIBLE);
                    }

                    if (data.getCount6() == 0) {
                        mTvComplaintMessageNumber.setVisibility(View.GONE);
                        mTvComplaintMessage.setText("暂无新的投诉消息");
                        mTvComplaintMessageTime.setVisibility(View.GONE);
                    } else if (data.getCount6() >= 99) {
                        mTvComplaintMessageNumber.setVisibility(View.VISIBLE);
                        mTvComplaintMessageNumber.setText("99");
                        mTvComplaintMessageTime.setVisibility(View.VISIBLE);
                        mTvComplaintMessage.setText(data.getData6().getContent());
                        mTvComplaintMessageTime.setText(data.getData6().getNowtime().substring(0,10));
                    } else {
                        mTvComplaintMessageNumber.setVisibility(View.VISIBLE);
                        mTvComplaintMessageNumber.setText(data.getCount6()+"");
                        mTvComplaintMessageTime.setVisibility(View.VISIBLE);
                        mTvComplaintMessage.setText(data.getData6().getContent());
                        mTvComplaintMessageTime.setText(data.getData6().getNowtime().substring(0,10));
                    }
                }
                break;

            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String num) {
        switch (num) {
            case "orderempty":
                mPresenter.GetNewsLeaveMessage(userId, "1", "1");
                mPresenter.GetmessagePag(userId);
                break;

        }
    }
}
