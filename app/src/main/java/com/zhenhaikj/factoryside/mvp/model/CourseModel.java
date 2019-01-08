package com.zhenhaikj.factoryside.mvp.model;

import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Course;
import com.zhenhaikj.factoryside.mvp.contract.CourseContract;
import com.zhenhaikj.factoryside.mvp.ApiRetrofit;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class CourseModel implements CourseContract.Model {
    @Override
    public Observable<BaseResult<List<Course>>> getData(String pageindex, String pagesize) {
        return ApiRetrofit.getDefault("College.getCollegeList").getCourse(pageindex,pagesize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
