package com.emjiayuan.factoryside.mvp.model;

import com.emjiayuan.factoryside.mvp.contract.CourseContract;
import com.emjiayuan.nll.ApiRetrofit;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Course;

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
