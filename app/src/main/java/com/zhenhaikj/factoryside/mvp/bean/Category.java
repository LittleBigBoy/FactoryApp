package com.zhenhaikj.factoryside.mvp.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable, MultiItemEntity {


    /**
     * count : 2
     * data : [{"Id":282,"FCategoryID":282,"FCategoryName":"全自动滚筒洗衣机","ParentID":281,"ParentName":"洗衣机","BrandID":274,"BrandName":"小西瓜","IsUse":"Y","InitPrice":0,"InstallPrice":0,"InBrief":0,"Inminor":0,"Inmiddle":0,"Inlarge":0,"GeInitPrice":0,"GeInstallPrice":0,"GeInBrief":0,"GeInminor":0,"GeInmiddle":0,"GeInlarge":0,"Level":null,"page":1,"limit":999,"Level1ID":0,"Level1Name":null,"Version":0},{"Id":251,"FCategoryID":251,"FCategoryName":"单门 容积X≤100","ParentID":250,"ParentName":"冰箱","BrandID":322,"BrandName":"火龙果","IsUse":"Y","InitPrice":0,"InstallPrice":0,"InBrief":0,"Inminor":0,"Inmiddle":0,"Inlarge":0,"GeInitPrice":0,"GeInstallPrice":0,"GeInBrief":0,"GeInminor":0,"GeInmiddle":0,"GeInlarge":0,"Level":null,"page":1,"limit":999,"Level1ID":0,"Level1Name":null,"Version":0}]
     */

    private int count;
    private List<DataBean> data;
    private int itemType;
    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements MultiItemEntity {
        /**
         * Id : 282
         * FCategoryID : 282
         * FCategoryName : 全自动滚筒洗衣机
         * ParentID : 281
         * ParentName : 洗衣机
         * BrandID : 274
         * BrandName : 小西瓜
         * IsUse : Y
         * InitPrice : 0.0
         * InstallPrice : 0.0
         * InBrief : 0.0
         * Inminor : 0.0
         * Inmiddle : 0.0
         * Inlarge : 0.0
         * GeInitPrice : 0.0
         * GeInstallPrice : 0.0
         * GeInBrief : 0.0
         * GeInminor : 0.0
         * GeInmiddle : 0.0
         * GeInlarge : 0.0
         * Level : null
         * page : 1
         * limit : 999
         * Level1ID : 0
         * Level1Name : null
         * Version : 0
         */

        private String Id;
        private String FCategoryID;
        private String FCategoryName;
        private int ParentID;
        private String ParentName;
        private String BrandID;
        private String BrandName;
        private String IsUse;
        private double InitPrice;
        private double InstallPrice;
        private double InBrief;
        private double Inminor;
        private double Inmiddle;
        private double Inlarge;
        private double GeInitPrice;
        private double GeInstallPrice;
        private double GeInBrief;
        private double GeInminor;
        private double GeInmiddle;
        private double GeInlarge;
        private Object Level;
        private int page;
        private int limit;
        private int Level1ID;
        private Object Level1Name;
        private int Version;
        private boolean Selected;

        private int itemType;
        @Override
        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public boolean isSelected() {
            return Selected;
        }

        public void setSelected(boolean selected) {
            Selected = selected;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getFCategoryID() {
            return FCategoryID;
        }

        public void setFCategoryID(String FCategoryID) {
            this.FCategoryID = FCategoryID;
        }

        public String getFCategoryName() {
            return FCategoryName;
        }

        public void setFCategoryName(String FCategoryName) {
            this.FCategoryName = FCategoryName;
        }

        public int getParentID() {
            return ParentID;
        }

        public void setParentID(int ParentID) {
            this.ParentID = ParentID;
        }

        public String getParentName() {
            return ParentName;
        }

        public void setParentName(String ParentName) {
            this.ParentName = ParentName;
        }

        public String getBrandID() {
            return BrandID;
        }

        public void setBrandID(String BrandID) {
            this.BrandID = BrandID;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public double getInitPrice() {
            return InitPrice;
        }

        public void setInitPrice(double InitPrice) {
            this.InitPrice = InitPrice;
        }

        public double getInstallPrice() {
            return InstallPrice;
        }

        public void setInstallPrice(double InstallPrice) {
            this.InstallPrice = InstallPrice;
        }

        public double getInBrief() {
            return InBrief;
        }

        public void setInBrief(double InBrief) {
            this.InBrief = InBrief;
        }

        public double getInminor() {
            return Inminor;
        }

        public void setInminor(double Inminor) {
            this.Inminor = Inminor;
        }

        public double getInmiddle() {
            return Inmiddle;
        }

        public void setInmiddle(double Inmiddle) {
            this.Inmiddle = Inmiddle;
        }

        public double getInlarge() {
            return Inlarge;
        }

        public void setInlarge(double Inlarge) {
            this.Inlarge = Inlarge;
        }

        public double getGeInitPrice() {
            return GeInitPrice;
        }

        public void setGeInitPrice(double GeInitPrice) {
            this.GeInitPrice = GeInitPrice;
        }

        public double getGeInstallPrice() {
            return GeInstallPrice;
        }

        public void setGeInstallPrice(double GeInstallPrice) {
            this.GeInstallPrice = GeInstallPrice;
        }

        public double getGeInBrief() {
            return GeInBrief;
        }

        public void setGeInBrief(double GeInBrief) {
            this.GeInBrief = GeInBrief;
        }

        public double getGeInminor() {
            return GeInminor;
        }

        public void setGeInminor(double GeInminor) {
            this.GeInminor = GeInminor;
        }

        public double getGeInmiddle() {
            return GeInmiddle;
        }

        public void setGeInmiddle(double GeInmiddle) {
            this.GeInmiddle = GeInmiddle;
        }

        public double getGeInlarge() {
            return GeInlarge;
        }

        public void setGeInlarge(double GeInlarge) {
            this.GeInlarge = GeInlarge;
        }

        public Object getLevel() {
            return Level;
        }

        public void setLevel(Object Level) {
            this.Level = Level;
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

        public int getLevel1ID() {
            return Level1ID;
        }

        public void setLevel1ID(int Level1ID) {
            this.Level1ID = Level1ID;
        }

        public Object getLevel1Name() {
            return Level1Name;
        }

        public void setLevel1Name(Object Level1Name) {
            this.Level1Name = Level1Name;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }
    }
}
