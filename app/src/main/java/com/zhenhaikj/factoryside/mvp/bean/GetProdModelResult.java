package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class GetProdModelResult implements Serializable {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : [{"Id":26,"ID":26,"ModelName":"测试型号","FactoryID":"18888888888","IsUse":"Y","SpecificationsID":263,"Version":0}]
     */

    private int StatusCode;
    private String Info;
    private int Count;
    private List<DataBean> Data;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * Id : 26
         * ID : 26
         * ModelName : 测试型号
         * FactoryID : 18888888888
         * IsUse : Y
         * SpecificationsID : 263
         * Version : 0
         */

        private int Id;
        private int ID;
        private String ModelName;
        private String FactoryID;
        private String IsUse;
        private int SpecificationsID;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getModelName() {
            return ModelName;
        }

        public void setModelName(String ModelName) {
            this.ModelName = ModelName;
        }

        public String getFactoryID() {
            return FactoryID;
        }

        public void setFactoryID(String FactoryID) {
            this.FactoryID = FactoryID;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public int getSpecificationsID() {
            return SpecificationsID;
        }

        public void setSpecificationsID(int SpecificationsID) {
            this.SpecificationsID = SpecificationsID;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }
    }
}
