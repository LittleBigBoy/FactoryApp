package com.zhenhaikj.factoryside.mvp.presenter;


import com.zhenhaikj.factoryside.mvp.contract.CourseContract;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Course;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CoursePresenter extends CourseContract.Presenter {

    @Override
    public void getData(final String pageindex, String pagesize) {
        mModel.getData(pageindex,pagesize)
                .subscribe(new Observer<BaseResult<List<Course>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<List<Course>> baseResult) {
                        if ("200".equals(baseResult.getCode())){
                            mView.hideProgress();
                            mView.success(baseResult);
                        }else{
                            mView.fail(baseResult);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
