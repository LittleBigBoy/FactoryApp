package com.emjiayuan.factoryside.mvp.contract;


import com.emjiayuan.nll.base.BaseModel;
import com.emjiayuan.nll.base.BasePresenter;
import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.base.BaseView;
import com.emjiayuan.nll.model.Course;

import java.util.List;

import io.reactivex.Observable;


public interface CourseContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Course>>> getData(String pageindex, String pagesize);
    }

    interface View extends BaseView {
        void success(BaseResult<List<Course>> baseResult);
        void fail(BaseResult<List<Course>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void getData(String pageindex,String pagesize);
    }
}
