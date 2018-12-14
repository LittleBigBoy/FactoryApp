package com.emjiayuan.nll.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;


/**
 * MVP
 * 基类presenter
 */
public abstract class BasePresenter<V, M> {
    public Context mContext;
    public M mModel;
    public V mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
    }

    public void onCreate(@Nullable Bundle savedInstanceState){

    }

    public void onStart() {
    }

    public void onResume(){

    }

    public void onPause(){

    }

    public void onStop(){

    }

    public void onDestroy() {
        mRxManage.clear();
    }
}
