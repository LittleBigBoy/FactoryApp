package com.zhenhaikj.factoryside.mvp.contract;


import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.Course;

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
