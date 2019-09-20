package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class DepositWithDraw implements Serializable {

    /**
     * code : 0
     * msg : success
     * count : 4
     * data : [{"Id":50,"WithDrawID":50,"UserID":"15757964771","PayMoney":1000,"PayInfo":"盘锦市商业银行","PayName":"评教","PayNo":"621089","State":"0","CreateTime":"2019-09-10T10:32:10","ApproveTime":null,"ApproveUser":null,"IsUse":"Y","DrawType":"Deposit","page":1,"limit":999,"Version":0},{"Id":47,"WithDrawID":47,"UserID":"15757964771","PayMoney":800,"PayInfo":"盘锦市商业银行","PayName":"评教","PayNo":"621089","State":"0","CreateTime":"2019-09-06T12:26:54","ApproveTime":null,"ApproveUser":null,"IsUse":"Y","DrawType":"Deposit","page":1,"limit":999,"Version":0},{"Id":45,"WithDrawID":45,"UserID":"15757964771","PayMoney":1000,"PayInfo":"盘锦市商业银行","PayName":"评教","PayNo":"621089","State":"0","CreateTime":"2019-09-06T12:01:11","ApproveTime":null,"ApproveUser":null,"IsUse":"Y","DrawType":"Deposit","page":1,"limit":999,"Version":0},{"Id":44,"WithDrawID":44,"UserID":"15757964771","PayMoney":1000,"PayInfo":"盘锦市商业银行","PayName":"评教","PayNo":"621089","State":"0","CreateTime":"2019-09-06T11:52:25","ApproveTime":null,"ApproveUser":null,"IsUse":"Y","DrawType":"Deposit","page":1,"limit":999,"Version":0}]
     */

    private String code;
    private String msg;
    private String count;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Id : 50
         * WithDrawID : 50
         * UserID : 15757964771
         * PayMoney : 1000
         * PayInfo : 盘锦市商业银行
         * PayName : 评教
         * PayNo : 621089
         * State : 0
         * CreateTime : 2019-09-10T10:32:10
         * ApproveTime : null
         * ApproveUser : null
         * IsUse : Y
         * DrawType : Deposit
         * page : 1
         * limit : 999
         * Version : 0
         */

        private int Id;
        private int WithDrawID;
        private String UserID;
        private int PayMoney;
        private String PayInfo;
        private String PayName;
        private String PayNo;
        private String State;
        private String CreateTime;
        private Object ApproveTime;
        private Object ApproveUser;
        private String IsUse;
        private String DrawType;
        private int page;
        private int limit;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getWithDrawID() {
            return WithDrawID;
        }

        public void setWithDrawID(int WithDrawID) {
            this.WithDrawID = WithDrawID;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public int getPayMoney() {
            return PayMoney;
        }

        public void setPayMoney(int PayMoney) {
            this.PayMoney = PayMoney;
        }

        public String getPayInfo() {
            return PayInfo;
        }

        public void setPayInfo(String PayInfo) {
            this.PayInfo = PayInfo;
        }

        public String getPayName() {
            return PayName;
        }

        public void setPayName(String PayName) {
            this.PayName = PayName;
        }

        public String getPayNo() {
            return PayNo;
        }

        public void setPayNo(String PayNo) {
            this.PayNo = PayNo;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public Object getApproveTime() {
            return ApproveTime;
        }

        public void setApproveTime(Object ApproveTime) {
            this.ApproveTime = ApproveTime;
        }

        public Object getApproveUser() {
            return ApproveUser;
        }

        public void setApproveUser(Object ApproveUser) {
            this.ApproveUser = ApproveUser;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public String getDrawType() {
            return DrawType;
        }

        public void setDrawType(String DrawType) {
            this.DrawType = DrawType;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }
    }
}
