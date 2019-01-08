package com.zhenhaikj.factoryside.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.interfaces.HandleBackInterface;
import com.zhenhaikj.factoryside.mvp.utils.HandleBackUtil;
import com.zhenhaikj.factoryside.mvp.utils.TUtil;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 当使用viewpager加载Fragment，沉浸式的使用，原理懒加载
 * Created by geyifeng on 2017/4/7.
 */
public abstract class BaseLazyFragment<P extends BasePresenter, M extends BaseModel> extends Fragment implements HandleBackInterface,BaseView {
    public P mPresenter;
    public M mModel;

    public RxManager mRxManage;
    protected Activity mActivity;
    protected View mRootView;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsPrepare;

    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;

    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = obtainPresenter();
        mModel = obtainModel();
        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
            if (this instanceof BaseView) {
                mPresenter.setVM(this, mModel);
            }
            mPresenter.onCreate(savedInstanceState);
        }

        mRxManage = new RxManager();
    }
    protected P obtainPresenter() {
        return TUtil.getT(this, 0);
    }

    protected M obtainModel() {
        return TUtil.getT(this, 1);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, mRootView);
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            initData();
            if (isImmersionBarEnabled())
                initImmersionBar();
        }
        initView();
        setListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        unbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    /**
     * 是否懒加载
     *
     * @return the boolean
     */
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisible() {
        onLazyLoad();
    }

    private void onLazyLoad() {
        if (mIsVisible && mIsPrepare) {
            mIsPrepare = false;
            initData();
        }
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected abstract int setLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initData();
    public View getEmptyView() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data,null);
    }
    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(android.R.color.transparent);
        mImmersionBar.fitsSystemWindows(false);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    /**
     * view与数据绑定
     */
    protected abstract void initView();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 用户不可见执行
     */
    protected void onInvisible() {

    }

    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }

    /**
     * 检查网络状态
     * @return
     */
    public boolean checkNetwork() {
        ConnectivityManager conn = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = conn.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onBackPressed() {
        return HandleBackUtil.handleBackPress(this);
    }

    @Override
    public void contentLoading() {

    }

    @Override
    public void contentLoadingComplete() {

    }

    @Override
    public void contentLoadingError() {

    }

    @Override
    public void contentLoadingEmpty() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
