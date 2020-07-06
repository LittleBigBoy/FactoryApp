package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class GetFactoryProdResult implements Serializable {


    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Count : 0
     * Data : [{"Id":542,"BrandCategoryID":542,"BrandID":308,"BrandName":"奥克斯","UserID":"18888888888","CategoryID":287,"CategoryName":"冰洗类","SubCategoryID":262,"SubCategoryName":"冷柜","ProductTypeID":263,"ProductTypeName":"卧式冷柜≤300升","CourseCount":null,"Imge":null,"IsUse":"Y","ProdModelID":26,"ProdModel":"测试型号","UseNum":17,"page":1,"limit":999,"Version":0},{"Id":541,"BrandCategoryID":541,"BrandID":308,"BrandName":"奥克斯","UserID":"18888888888","CategoryID":287,"CategoryName":"冰洗类","SubCategoryID":281,"SubCategoryName":"洗衣机","ProductTypeID":283,"ProductTypeName":"全自动波轮洗衣机","CourseCount":null,"Imge":null,"IsUse":"Y","ProdModelID":27,"ProdModel":"321321","UseNum":5,"page":1,"limit":999,"Version":0},{"Id":544,"BrandCategoryID":544,"BrandID":372,"BrandName":"pop","UserID":"18888888888","CategoryID":287,"CategoryName":"冰洗类","SubCategoryID":262,"SubCategoryName":"冷柜","ProductTypeID":263,"ProductTypeName":"卧式冷柜≤300升","CourseCount":null,"Imge":null,"IsUse":"Y","ProdModelID":26,"ProdModel":"测试型号","UseNum":2,"page":1,"limit":999,"Version":0},{"Id":540,"BrandCategoryID":540,"BrandID":370,"BrandName":"yy","UserID":"18888888888","CategoryID":287,"CategoryName":"冰洗类","SubCategoryID":262,"SubCategoryName":"冷柜","ProductTypeID":263,"ProductTypeName":"卧式冷柜≤300升","CourseCount":null,"Imge":null,"IsUse":"Y","ProdModelID":26,"ProdModel":"测试型号","UseNum":1,"page":1,"limit":999,"Version":0},{"Id":545,"BrandCategoryID":545,"BrandID":373,"BrandName":"m3","UserID":"18888888888","CategoryID":287,"CategoryName":"冰洗类","SubCategoryID":262,"SubCategoryName":"冷柜","ProductTypeID":263,"ProductTypeName":"卧式冷柜≤300升","CourseCount":null,"Imge":null,"IsUse":"Y","ProdModelID":26,"ProdModel":"测试型号","UseNum":1,"page":1,"limit":999,"Version":0}]
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
         * Id : 542
         * BrandCategoryID : 542
         * BrandID : 308
         * BrandName : 奥克斯
         * UserID : 18888888888
         * CategoryID : 287
         * CategoryName : 冰洗类
         * SubCategoryID : 262
         * SubCategoryName : 冷柜
         * ProductTypeID : 263
         * ProductTypeName : 卧式冷柜≤300升
         * CourseCount : null
         * Imge : null
         * IsUse : Y
         * ProdModelID : 26
         * ProdModel : 测试型号
         * UseNum : 17
         * page : 1
         * limit : 999
         * Version : 0
         */

        private int Id;
        private int BrandCategoryID;
        private int BrandID;
        private String BrandName;
        private String UserID;
        private int CategoryID;
        private String CategoryName;
        private int SubCategoryID;
        private String SubCategoryName;
        private int ProductTypeID;
        private String ProductTypeName;
        private Object CourseCount;
        private Object Imge;
        private String IsUse;
        private int ProdModelID;
        private String ProdModel;
        private int UseNum;
        private int page;
        private int limit;
        private int Version;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getBrandCategoryID() {
            return BrandCategoryID;
        }

        public void setBrandCategoryID(int BrandCategoryID) {
            this.BrandCategoryID = BrandCategoryID;
        }

        public int getBrandID() {
            return BrandID;
        }

        public void setBrandID(int BrandID) {
            this.BrandID = BrandID;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public int getCategoryID() {
            return CategoryID;
        }

        public void setCategoryID(int CategoryID) {
            this.CategoryID = CategoryID;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public int getSubCategoryID() {
            return SubCategoryID;
        }

        public void setSubCategoryID(int SubCategoryID) {
            this.SubCategoryID = SubCategoryID;
        }

        public String getSubCategoryName() {
            return SubCategoryName;
        }

        public void setSubCategoryName(String SubCategoryName) {
            this.SubCategoryName = SubCategoryName;
        }

        public int getProductTypeID() {
            return ProductTypeID;
        }

        public void setProductTypeID(int ProductTypeID) {
            this.ProductTypeID = ProductTypeID;
        }

        public String getProductTypeName() {
            return ProductTypeName;
        }

        public void setProductTypeName(String ProductTypeName) {
            this.ProductTypeName = ProductTypeName;
        }

        public Object getCourseCount() {
            return CourseCount;
        }

        public void setCourseCount(Object CourseCount) {
            this.CourseCount = CourseCount;
        }

        public Object getImge() {
            return Imge;
        }

        public void setImge(Object Imge) {
            this.Imge = Imge;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public int getProdModelID() {
            return ProdModelID;
        }

        public void setProdModelID(int ProdModelID) {
            this.ProdModelID = ProdModelID;
        }

        public String getProdModel() {
            return ProdModel;
        }

        public void setProdModel(String ProdModel) {
            this.ProdModel = ProdModel;
        }

        public int getUseNum() {
            return UseNum;
        }

        public void setUseNum(int UseNum) {
            this.UseNum = UseNum;
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
