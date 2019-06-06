package com.zhenhaikj.factoryside.mvp.contract;

import com.zhenhaikj.factoryside.mvp.base.BaseModel;
import com.zhenhaikj.factoryside.mvp.base.BasePresenter;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.base.BaseView;
import com.zhenhaikj.factoryside.mvp.bean.CanInvoice;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

public interface InvoicedContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<List<CanInvoice>>>> GetCanInvoiceByUserid(String UserId,String IsInvoice);

        Observable<BaseResult<Data<CompanyInfo>>> GetmessageBytype(String UserId);

        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);

        Observable<BaseResult<Data<String>>> AddInvoice(String UserID,
                                                        String Heads,
                                                        String Credit,
                                                        String Content,
                                                        String Money,
                                                        String State,
                                                        String Emails,
                                                        String Approve,
                                                        String PuID,
                                                        String Count);

        Observable<BaseResult<Data<List<CanInvoice>>>> GetInvoiceByUserid(String UserId);

    }

    interface View extends BaseView {
        void GetCanInvoiceByUserid(BaseResult<Data<List<CanInvoice>>> baseResult);

        void GetmessageBytype(BaseResult<Data<CompanyInfo>> baseResult);

        void GetUserInfoList(BaseResult<UserInfo> baseResult);

        void AddInvoice(BaseResult<Data<String>> baseResult);

        void GetInvoiceByUserid(BaseResult<Data<List<CanInvoice>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void GetCanInvoiceByUserid(String UserId,String IsInvoice);

        public abstract void GetmessageBytype(String UserId);

        public abstract void GetUserInfoList(String UserId, String limit);

        public abstract void AddInvoice(String UserID,
                                        String Heads,
                                        String Credit,
                                        String Content,
                                        String Money,
                                        String State,
                                        String Emails,
                                        String Approve,
                                        String PuID,
                                        String Count);

        public abstract void GetInvoiceByUserid(String UserId);
    }
}
