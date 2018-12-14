package com.emjiayuan.nll.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.emjiayuan.nll.MainActivity;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.OrderConfirm;
import com.emjiayuan.nll.utils.MyUtils;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;


public class SuccessActivity extends BaseActivity {


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
    @BindView(R.id.back_home)
    Button mBackHome;
    @BindView(R.id.see_order)
    Button mSeeOrder;
    @BindView(R.id.share_order)
    Button mShareOrder;
    private String orderid = "";
    private OrderConfirm.ProductsBean product;
//    private UMShareListener mShareListener;
//    private ShareAction mShareAction;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_success;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderid = intent.getStringExtra("orderid");
            product = (OrderConfirm.ProductsBean) intent.getSerializableExtra("product");
        }
    }

    @Override
    protected void initView() {
        mTvTitle.setText("支付成功");
        mTvSave.setVisibility(View.VISIBLE);
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setText("完成");
        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
//        mShareListener = new CustomShareListener(this);
//        /*增加自定义按钮的分享面板*/
//        mShareAction = new ShareAction(this).setDisplayList(
//                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,
//                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.MORE)
//                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
//                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
//                .setShareboardclickCallback(new ShareBoardlistener() {
//                    @Override
//                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                        if (snsPlatform.mShowWord.equals("复制文本")) {
//                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
//                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
//                            Toast.makeText(mActivity, "已复制", Toast.LENGTH_LONG).show();
//
//                        } else {
//                            UMWeb web = new UMWeb("https://h5.youzan.com/wscshop/feature/ihODHu0rwF?redirect_count=1");
//                            web.setTitle("伊穆家园");
//                            web.setDescription(product.getName());
//                            web.setThumb(new UMImage(mActivity, product.getImages()));
//                            new ShareAction(SuccessActivity.this).withMedia(web)
//                                    .setPlatform(share_media)
//                                    .setCallback(mShareListener)
//                                    .share();
//                        }
//                    }
//                });
    }


    @Override
    protected void setListener() {
        mBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
            }
        });
        mSeeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                intent.putExtra("orderid", orderid);
                intent.putExtra("type", 0);
                startActivity(intent);
                finish();
            }
        });
//        shareOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!MyUtils.isFastClick()) {
//                    return;
//                }
//                mShareAction.open();
//            }
//        });
    }

//    private static class CustomShareListener implements UMShareListener {
//
//        private WeakReference<SuccessActivity> mActivity;
//
//        private CustomShareListener(SuccessActivity activity) {
//            mActivity = new WeakReference(activity);
//        }
//
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//
//        }
//
//        @Override
//        public void onResult(SHARE_MEDIA platform) {
//
//            if (platform.name().equals("WEIXIN_FAVORITE")) {
//                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
//            } else {
//                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
//                        && platform != SHARE_MEDIA.EMAIL
//                        && platform != SHARE_MEDIA.FLICKR
//                        && platform != SHARE_MEDIA.FOURSQUARE
//                        && platform != SHARE_MEDIA.TUMBLR
//                        && platform != SHARE_MEDIA.POCKET
//                        && platform != SHARE_MEDIA.PINTEREST
//
//                        && platform != SHARE_MEDIA.INSTAGRAM
//                        && platform != SHARE_MEDIA.GOOGLEPLUS
//                        && platform != SHARE_MEDIA.YNOTE
//                        && platform != SHARE_MEDIA.EVERNOTE) {
//                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, Throwable t) {
//            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
//                    && platform != SHARE_MEDIA.EMAIL
//                    && platform != SHARE_MEDIA.FLICKR
//                    && platform != SHARE_MEDIA.FOURSQUARE
//                    && platform != SHARE_MEDIA.TUMBLR
//                    && platform != SHARE_MEDIA.POCKET
//                    && platform != SHARE_MEDIA.PINTEREST
//
//                    && platform != SHARE_MEDIA.INSTAGRAM
//                    && platform != SHARE_MEDIA.GOOGLEPLUS
//                    && platform != SHARE_MEDIA.YNOTE
//                    && platform != SHARE_MEDIA.EVERNOTE) {
//                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
//
//            }
//
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform) {
//
//            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        /** attention to this below ,must add this**/
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }
//
//    /**
//     * 屏幕横竖屏切换时避免出现window leak的问题
//     */
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mShareAction.close();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        UMShareAPI.get(this).release();
//    }
}
