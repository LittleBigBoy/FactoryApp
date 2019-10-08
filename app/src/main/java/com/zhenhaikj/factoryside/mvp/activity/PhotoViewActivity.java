package com.zhenhaikj.factoryside.mvp.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;

import butterknife.BindView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class PhotoViewActivity extends BaseActivity {
    @BindView(R.id.photoview)
    PhotoView photoview;

    @BindView(R.id.view)
    View view;
    private String PhotoUrl;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_view_photos;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.black);
        mImmersionBar.statusBarView(view);
        mImmersionBar.fitsSystemWindows(false);
    }

    @Override
    protected void initData() {
        PhotoUrl=getIntent().getStringExtra("PhotoUrl");
        Log.d("=====>",PhotoUrl);
    }
    @Override
    protected void initView() {
        photoview.setScaleType(ImageView.ScaleType.CENTER);
        photoview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        photoview.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
            }
        });

        RequestOptions options=new RequestOptions().fitCenter().error(R.drawable.image_loading);
        Glide.with(mActivity)
                .load(PhotoUrl)
                .apply(options)
                //   .placeholder(R.mipmap.ic_launcher)//加载过程中图片未显示时显示的本地图片
                // .error(R.mipmap.ic_launcher)//加载异常时显示的图片
                //  .centerCrop()//图片图填充ImageView设置的大小
                .into(photoview);
    }

    @Override
    protected void setListener() {

    }
}
