package com.zhenhaikj.factoryside.mvp.bean;

import java.io.Serializable;
import java.util.List;

public class Search implements Serializable {

    /**
     * code : 0
     * msg : success
     * count : 1
     * data : [{"Id":2000002402,"OrderID":2000002402,"TypeID":1,"TypeName":"维修","SubTypeID":0,"SubTypeName":null,"CategoryID":281,"CategoryName":"洗衣机","SubCategoryID":283,"SubCategoryName":"全自动波轮洗衣机","Memo":"坏了","BrandID":213,"BrandName":"小霸王","SendAddress":null,"ProductType":null,"ProductTypeID":null,"Num":1,"ProvinceCode":"330000","CityCode":"330100","AreaCode":"330102","DistrictCode":"330102001","Address":"浙江省杭州市上城区清波街道街道","Longitude":"120.158768","Dimension":"30.237650","UserID":"15757964771","Guarantee":"Y","UserName":"评教","Phone":"15925897121","CreateDate":"2019-10-17T14:31:36","AudDate":"2019-10-17T14:31:36","ReceiveOrderDate":"2019-10-17T14:31:36","RepairCompleteDate":"2019-10-17T14:31:36","AppraiseDate":"2019-10-17T14:31:36","State":"4","StateHtml":"服务中","Extra":"N","ExtraTime":"0","ExtraFee":0,"IsUse":"Y","SendUser":"13806652840","OrgSendUser":null,"LoginUser":"System","IsPay":"Y","OrderMoney":38,"InitMoney":0,"BeyondMoney":0,"QuaMoney":0,"BeyondID":0,"BeyondState":null,"BeyondDistance":"0","Accessory":null,"AccessorySequency":null,"AccessoryApplyState":null,"AccessoryAndServiceApplyState":null,"AccessoryState":null,"AccessorySendState":null,"AccessoryMoney":0,"AccessorySearchState":"0","AccessoryApplyDate":"2019-10-17T14:31:36","AccessoryMemo":null,"AccessoryServiceMoney":0,"Service":null,"ServiceMoney":0,"ServiceApplyState":null,"ServiceApplyDate":"2019-10-17T14:31:36","AccessoryAndServiceApplyDate":null,"StarOrder":null,"FStarOrder":"Y","PostPayType":"","PostMoney":0,"NewMoney":null,"AddressBack":"","IsReturn":"","ApplyNum":0,"QApplyNum":0,"MallID":0,"EndRemark":null,"Grade":0,"Grade1":0,"Grade2":0,"Grade3":0,"ReturnAccessory":null,"ReturnAccessoryMsg":null,"ApplyCancel":null,"OrgAppraise":null,"UpdateTime":"2019-10-17T14:31:36","LateTime":"0001-01-01T00:00:00","IsExtraTime":null,"OrderPayStr":null,"ThirdPartyNo":null,"ExpressNo":null,"RecycleOrderHour":48,"IsRecevieGoods":"N","AppointmentMessage":"预约成功","AppointmentState":"Y","IsLate":null,"Distance":147.4,"DistanceTureOrFalse":false,"SendOrderState":null,"SendOrderMsg":null,"IsSendRepair":null,"OrderSource":null,"OrderImg":[],"ReturnaccessoryImg":[],"OrderAccessroyDetail":[],"OrderServiceDetail":[],"OrderBeyondImg":[],"OrderComplaintDetail":[],"OrderAppraiseDetail":[],"SendOrderList":[{"Id":2722,"SendID":2722,"CreateDate":"2019-10-17T10:33:33","UserID":"13806652840","OrderID":2000002402,"State":"1","UpdateDate":"2019-10-17T10:34:07","ServiceDate":"2019-10-17T10:30:56","ServiceDate2":"2019-10-17T10:30:56","LoginUser":"system","IsUse":"Y","CategoryID":281,"CategoryName":"洗衣机","SubTypeID":0,"SubTypeName":null,"Memo":"坏了","BrandID":213,"BrandName":"小霸王","ProductType":null,"ProvinceCode":"330000","CityCode":"330100","AreaCode":"330102","Address":"浙江省杭州市上城区清波街道街道","Guarantee":"Y","UserName":"评教","Phone":"18067138219","AppointmentState":"1","AppointmentMessage":"预约成功","page":1,"limit":10,"Version":0}],"LeavemessageList":[],"LeavemessageimgList":[],"IsPressFactory":null,"WorkerComplaint":null,"FactoryComplaint":null,"IsLook":"2","FIsLook":"2","page":0,"limit":0,"StateList":[],"AppointmentRefuseState":null,"AccessoryRefuseState":null,"FactoryApplyState":null,"OrderApplyState":null,"AccessoryIsPay":null,"OrderSort":null,"IsSettlement":null,"SettlementMoney":0,"SettlementTime":"2019-10-17T14:31:36","OrderAccessoryStr":null,"Version":0}]
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
         * Id : 2000002402
         * OrderID : 2000002402
         * TypeID : 1
         * TypeName : 维修
         * SubTypeID : 0
         * SubTypeName : null
         * CategoryID : 281
         * CategoryName : 洗衣机
         * SubCategoryID : 283
         * SubCategoryName : 全自动波轮洗衣机
         * Memo : 坏了
         * BrandID : 213
         * BrandName : 小霸王
         * SendAddress : null
         * ProductType : null
         * ProductTypeID : null
         * Num : 1
         * ProvinceCode : 330000
         * CityCode : 330100
         * AreaCode : 330102
         * DistrictCode : 330102001
         * Address : 浙江省杭州市上城区清波街道街道
         * Longitude : 120.158768
         * Dimension : 30.237650
         * UserID : 15757964771
         * Guarantee : Y
         * UserName : 评教
         * Phone : 15925897121
         * CreateDate : 2019-10-17T14:31:36
         * AudDate : 2019-10-17T14:31:36
         * ReceiveOrderDate : 2019-10-17T14:31:36
         * RepairCompleteDate : 2019-10-17T14:31:36
         * AppraiseDate : 2019-10-17T14:31:36
         * State : 4
         * StateHtml : 服务中
         * Extra : N
         * ExtraTime : 0
         * ExtraFee : 0
         * IsUse : Y
         * SendUser : 13806652840
         * OrgSendUser : null
         * LoginUser : System
         * IsPay : Y
         * OrderMoney : 38
         * InitMoney : 0
         * BeyondMoney : 0
         * QuaMoney : 0
         * BeyondID : 0
         * BeyondState : null
         * BeyondDistance : 0
         * Accessory : null
         * AccessorySequency : null
         * AccessoryApplyState : null
         * AccessoryAndServiceApplyState : null
         * AccessoryState : null
         * AccessorySendState : null
         * AccessoryMoney : 0
         * AccessorySearchState : 0
         * AccessoryApplyDate : 2019-10-17T14:31:36
         * AccessoryMemo : null
         * AccessoryServiceMoney : 0
         * Service : null
         * ServiceMoney : 0
         * ServiceApplyState : null
         * ServiceApplyDate : 2019-10-17T14:31:36
         * AccessoryAndServiceApplyDate : null
         * StarOrder : null
         * FStarOrder : Y
         * PostPayType :
         * PostMoney : 0
         * NewMoney : null
         * AddressBack :
         * IsReturn :
         * ApplyNum : 0
         * QApplyNum : 0
         * MallID : 0
         * EndRemark : null
         * Grade : 0
         * Grade1 : 0
         * Grade2 : 0
         * Grade3 : 0
         * ReturnAccessory : null
         * ReturnAccessoryMsg : null
         * ApplyCancel : null
         * OrgAppraise : null
         * UpdateTime : 2019-10-17T14:31:36
         * LateTime : 0001-01-01T00:00:00
         * IsExtraTime : null
         * OrderPayStr : null
         * ThirdPartyNo : null
         * ExpressNo : null
         * RecycleOrderHour : 48
         * IsRecevieGoods : N
         * AppointmentMessage : 预约成功
         * AppointmentState : Y
         * IsLate : null
         * Distance : 147.4
         * DistanceTureOrFalse : false
         * SendOrderState : null
         * SendOrderMsg : null
         * IsSendRepair : null
         * OrderSource : null
         * OrderImg : []
         * ReturnaccessoryImg : []
         * OrderAccessroyDetail : []
         * OrderServiceDetail : []
         * OrderBeyondImg : []
         * OrderComplaintDetail : []
         * OrderAppraiseDetail : []
         * SendOrderList : [{"Id":2722,"SendID":2722,"CreateDate":"2019-10-17T10:33:33","UserID":"13806652840","OrderID":2000002402,"State":"1","UpdateDate":"2019-10-17T10:34:07","ServiceDate":"2019-10-17T10:30:56","ServiceDate2":"2019-10-17T10:30:56","LoginUser":"system","IsUse":"Y","CategoryID":281,"CategoryName":"洗衣机","SubTypeID":0,"SubTypeName":null,"Memo":"坏了","BrandID":213,"BrandName":"小霸王","ProductType":null,"ProvinceCode":"330000","CityCode":"330100","AreaCode":"330102","Address":"浙江省杭州市上城区清波街道街道","Guarantee":"Y","UserName":"评教","Phone":"18067138219","AppointmentState":"1","AppointmentMessage":"预约成功","page":1,"limit":10,"Version":0}]
         * LeavemessageList : []
         * LeavemessageimgList : []
         * IsPressFactory : null
         * WorkerComplaint : null
         * FactoryComplaint : null
         * IsLook : 2
         * FIsLook : 2
         * page : 0
         * limit : 0
         * StateList : []
         * AppointmentRefuseState : null
         * AccessoryRefuseState : null
         * FactoryApplyState : null
         * OrderApplyState : null
         * AccessoryIsPay : null
         * OrderSort : null
         * IsSettlement : null
         * SettlementMoney : 0
         * SettlementTime : 2019-10-17T14:31:36
         * OrderAccessoryStr : null
         * Version : 0
         */

        private int Id;
        private String OrderID;
        private int TypeID;
        private String TypeName;
        private int SubTypeID;
        private Object SubTypeName;
        private int CategoryID;
        private String CategoryName;
        private int SubCategoryID;
        private String SubCategoryName;
        private String Memo;
        private int BrandID;
        private String BrandName;
        private Object SendAddress;
        private Object ProductType;
        private Object ProductTypeID;
        private int Num;
        private String ProvinceCode;
        private String CityCode;
        private String AreaCode;
        private String DistrictCode;
        private String Address;
        private String Longitude;
        private String Dimension;
        private String UserID;
        private String Guarantee;
        private String UserName;
        private String Phone;
        private String CreateDate;
        private String AudDate;
        private String ReceiveOrderDate;
        private String RepairCompleteDate;
        private String AppraiseDate;
        private String State;
        private String StateHtml;
        private String Extra;
        private String ExtraTime;
        private int ExtraFee;
        private String IsUse;
        private String SendUser;
        private Object OrgSendUser;
        private String LoginUser;
        private String IsPay;
        private int OrderMoney;
        private int InitMoney;
        private int BeyondMoney;
        private int QuaMoney;
        private int BeyondID;
        private Object BeyondState;
        private String BeyondDistance;
        private Object Accessory;
        private Object AccessorySequency;
        private Object AccessoryApplyState;
        private Object AccessoryAndServiceApplyState;
        private Object AccessoryState;
        private Object AccessorySendState;
        private int AccessoryMoney;
        private String AccessorySearchState;
        private String AccessoryApplyDate;
        private Object AccessoryMemo;
        private int AccessoryServiceMoney;
        private Object Service;
        private int ServiceMoney;
        private Object ServiceApplyState;
        private String ServiceApplyDate;
        private Object AccessoryAndServiceApplyDate;
        private Object StarOrder;
        private String FStarOrder;
        private String PostPayType;
        private int PostMoney;
        private Object NewMoney;
        private String AddressBack;
        private String IsReturn;
        private int ApplyNum;
        private int QApplyNum;
        private int MallID;
        private Object EndRemark;
        private int Grade;
        private int Grade1;
        private int Grade2;
        private int Grade3;
        private Object ReturnAccessory;
        private Object ReturnAccessoryMsg;
        private Object ApplyCancel;
        private Object OrgAppraise;
        private String UpdateTime;
        private String LateTime;
        private Object IsExtraTime;
        private Object OrderPayStr;
        private Object ThirdPartyNo;
        private Object ExpressNo;
        private int RecycleOrderHour;
        private String IsRecevieGoods;
        private String AppointmentMessage;
        private String AppointmentState;
        private Object IsLate;
        private double Distance;
        private boolean DistanceTureOrFalse;
        private Object SendOrderState;
        private Object SendOrderMsg;
        private Object IsSendRepair;
        private Object OrderSource;
        private Object IsPressFactory;
        private Object WorkerComplaint;
        private Object FactoryComplaint;
        private String IsLook;
        private String FIsLook;
        private int page;
        private int limit;
        private Object AppointmentRefuseState;
        private Object AccessoryRefuseState;
        private Object FactoryApplyState;
        private Object OrderApplyState;
        private Object AccessoryIsPay;
        private Object OrderSort;
        private Object IsSettlement;
        private int SettlementMoney;
        private String SettlementTime;
        private Object OrderAccessoryStr;
        private int Version;
        private List<?> OrderImg;
        private List<?> ReturnaccessoryImg;
        private List<?> OrderAccessroyDetail;
        private List<?> OrderServiceDetail;
        private List<?> OrderBeyondImg;
        private List<?> OrderComplaintDetail;
        private List<?> OrderAppraiseDetail;
        private List<SendOrderListBean> SendOrderList;
        private List<?> LeavemessageList;
        private List<?> LeavemessageimgList;
        private List<?> StateList;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public int getTypeID() {
            return TypeID;
        }

        public void setTypeID(int TypeID) {
            this.TypeID = TypeID;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public int getSubTypeID() {
            return SubTypeID;
        }

        public void setSubTypeID(int SubTypeID) {
            this.SubTypeID = SubTypeID;
        }

        public Object getSubTypeName() {
            return SubTypeName;
        }

        public void setSubTypeName(Object SubTypeName) {
            this.SubTypeName = SubTypeName;
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

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
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

        public Object getSendAddress() {
            return SendAddress;
        }

        public void setSendAddress(Object SendAddress) {
            this.SendAddress = SendAddress;
        }

        public Object getProductType() {
            return ProductType;
        }

        public void setProductType(Object ProductType) {
            this.ProductType = ProductType;
        }

        public Object getProductTypeID() {
            return ProductTypeID;
        }

        public void setProductTypeID(Object ProductTypeID) {
            this.ProductTypeID = ProductTypeID;
        }

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }

        public String getProvinceCode() {
            return ProvinceCode;
        }

        public void setProvinceCode(String ProvinceCode) {
            this.ProvinceCode = ProvinceCode;
        }

        public String getCityCode() {
            return CityCode;
        }

        public void setCityCode(String CityCode) {
            this.CityCode = CityCode;
        }

        public String getAreaCode() {
            return AreaCode;
        }

        public void setAreaCode(String AreaCode) {
            this.AreaCode = AreaCode;
        }

        public String getDistrictCode() {
            return DistrictCode;
        }

        public void setDistrictCode(String DistrictCode) {
            this.DistrictCode = DistrictCode;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getDimension() {
            return Dimension;
        }

        public void setDimension(String Dimension) {
            this.Dimension = Dimension;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getGuarantee() {
            if ("Y".equals(Guarantee)){
                return "保内";
            }else{
                return "保外";
            }
        }

        public void setGuarantee(String Guarantee) {
            this.Guarantee = Guarantee;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getAudDate() {
            return AudDate;
        }

        public void setAudDate(String AudDate) {
            this.AudDate = AudDate;
        }

        public String getReceiveOrderDate() {
            return ReceiveOrderDate;
        }

        public void setReceiveOrderDate(String ReceiveOrderDate) {
            this.ReceiveOrderDate = ReceiveOrderDate;
        }

        public String getRepairCompleteDate() {
            return RepairCompleteDate;
        }

        public void setRepairCompleteDate(String RepairCompleteDate) {
            this.RepairCompleteDate = RepairCompleteDate;
        }

        public String getAppraiseDate() {
            return AppraiseDate;
        }

        public void setAppraiseDate(String AppraiseDate) {
            this.AppraiseDate = AppraiseDate;
        }

        public String getState() {
            String status="";
            switch (State){
                case "-2":
                    status="申请废除工单";
                    break;
                case "-1":
                    status="退单处理";
                    break;
                case "0":
                    status="待审核";
                    break;
                case "1":
                    status="待接单";
                    break;
                case "2":
                    status="已接单待联系客户";
                    break;
                case "3":
                    status="已联系客户待服务";
                    break;
                case "4":
                    status="服务中";
                    break;
                case "5":
                    status="服务完成";
                    break;
                case "6":
                    status="待评价";
                    break;
                case "7":
                    status="已完成";
                    break;
                case "9":
                    status="远程费审核";
                    break;
            }
            return status;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getStateHtml() {
            return StateHtml;
        }

        public void setStateHtml(String StateHtml) {
            this.StateHtml = StateHtml;
        }

        public String getExtra() {
            return Extra;
        }

        public void setExtra(String Extra) {
            this.Extra = Extra;
        }

        public String getExtraTime() {
            return ExtraTime;
        }

        public void setExtraTime(String ExtraTime) {
            this.ExtraTime = ExtraTime;
        }

        public int getExtraFee() {
            return ExtraFee;
        }

        public void setExtraFee(int ExtraFee) {
            this.ExtraFee = ExtraFee;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public String getSendUser() {
            return SendUser;
        }

        public void setSendUser(String SendUser) {
            this.SendUser = SendUser;
        }

        public Object getOrgSendUser() {
            return OrgSendUser;
        }

        public void setOrgSendUser(Object OrgSendUser) {
            this.OrgSendUser = OrgSendUser;
        }

        public String getLoginUser() {
            return LoginUser;
        }

        public void setLoginUser(String LoginUser) {
            this.LoginUser = LoginUser;
        }

        public String getIsPay() {
            return IsPay;
        }

        public void setIsPay(String IsPay) {
            this.IsPay = IsPay;
        }

        public int getOrderMoney() {
            return OrderMoney;
        }

        public void setOrderMoney(int OrderMoney) {
            this.OrderMoney = OrderMoney;
        }

        public int getInitMoney() {
            return InitMoney;
        }

        public void setInitMoney(int InitMoney) {
            this.InitMoney = InitMoney;
        }

        public int getBeyondMoney() {
            return BeyondMoney;
        }

        public void setBeyondMoney(int BeyondMoney) {
            this.BeyondMoney = BeyondMoney;
        }

        public int getQuaMoney() {
            return QuaMoney;
        }

        public void setQuaMoney(int QuaMoney) {
            this.QuaMoney = QuaMoney;
        }

        public int getBeyondID() {
            return BeyondID;
        }

        public void setBeyondID(int BeyondID) {
            this.BeyondID = BeyondID;
        }

        public Object getBeyondState() {
            return BeyondState;
        }

        public void setBeyondState(Object BeyondState) {
            this.BeyondState = BeyondState;
        }

        public String getBeyondDistance() {
            return BeyondDistance;
        }

        public void setBeyondDistance(String BeyondDistance) {
            this.BeyondDistance = BeyondDistance;
        }

        public Object getAccessory() {
            return Accessory;
        }

        public void setAccessory(Object Accessory) {
            this.Accessory = Accessory;
        }

        public Object getAccessorySequency() {
            return AccessorySequency;
        }

        public void setAccessorySequency(Object AccessorySequency) {
            this.AccessorySequency = AccessorySequency;
        }

        public Object getAccessoryApplyState() {
            return AccessoryApplyState;
        }

        public void setAccessoryApplyState(Object AccessoryApplyState) {
            this.AccessoryApplyState = AccessoryApplyState;
        }

        public Object getAccessoryAndServiceApplyState() {
            return AccessoryAndServiceApplyState;
        }

        public void setAccessoryAndServiceApplyState(Object AccessoryAndServiceApplyState) {
            this.AccessoryAndServiceApplyState = AccessoryAndServiceApplyState;
        }

        public Object getAccessoryState() {
            return AccessoryState;
        }

        public void setAccessoryState(Object AccessoryState) {
            this.AccessoryState = AccessoryState;
        }

        public Object getAccessorySendState() {
            return AccessorySendState;
        }

        public void setAccessorySendState(Object AccessorySendState) {
            this.AccessorySendState = AccessorySendState;
        }

        public int getAccessoryMoney() {
            return AccessoryMoney;
        }

        public void setAccessoryMoney(int AccessoryMoney) {
            this.AccessoryMoney = AccessoryMoney;
        }

        public String getAccessorySearchState() {
            return AccessorySearchState;
        }

        public void setAccessorySearchState(String AccessorySearchState) {
            this.AccessorySearchState = AccessorySearchState;
        }

        public String getAccessoryApplyDate() {
            return AccessoryApplyDate;
        }

        public void setAccessoryApplyDate(String AccessoryApplyDate) {
            this.AccessoryApplyDate = AccessoryApplyDate;
        }

        public Object getAccessoryMemo() {
            return AccessoryMemo;
        }

        public void setAccessoryMemo(Object AccessoryMemo) {
            this.AccessoryMemo = AccessoryMemo;
        }

        public int getAccessoryServiceMoney() {
            return AccessoryServiceMoney;
        }

        public void setAccessoryServiceMoney(int AccessoryServiceMoney) {
            this.AccessoryServiceMoney = AccessoryServiceMoney;
        }

        public Object getService() {
            return Service;
        }

        public void setService(Object Service) {
            this.Service = Service;
        }

        public int getServiceMoney() {
            return ServiceMoney;
        }

        public void setServiceMoney(int ServiceMoney) {
            this.ServiceMoney = ServiceMoney;
        }

        public Object getServiceApplyState() {
            return ServiceApplyState;
        }

        public void setServiceApplyState(Object ServiceApplyState) {
            this.ServiceApplyState = ServiceApplyState;
        }

        public String getServiceApplyDate() {
            return ServiceApplyDate;
        }

        public void setServiceApplyDate(String ServiceApplyDate) {
            this.ServiceApplyDate = ServiceApplyDate;
        }

        public Object getAccessoryAndServiceApplyDate() {
            return AccessoryAndServiceApplyDate;
        }

        public void setAccessoryAndServiceApplyDate(Object AccessoryAndServiceApplyDate) {
            this.AccessoryAndServiceApplyDate = AccessoryAndServiceApplyDate;
        }

        public Object getStarOrder() {
            return StarOrder;
        }

        public void setStarOrder(Object StarOrder) {
            this.StarOrder = StarOrder;
        }

        public String getFStarOrder() {
            return FStarOrder;
        }

        public void setFStarOrder(String FStarOrder) {
            this.FStarOrder = FStarOrder;
        }

        public String getPostPayType() {
            return PostPayType;
        }

        public void setPostPayType(String PostPayType) {
            this.PostPayType = PostPayType;
        }

        public int getPostMoney() {
            return PostMoney;
        }

        public void setPostMoney(int PostMoney) {
            this.PostMoney = PostMoney;
        }

        public Object getNewMoney() {
            return NewMoney;
        }

        public void setNewMoney(Object NewMoney) {
            this.NewMoney = NewMoney;
        }

        public String getAddressBack() {
            return AddressBack;
        }

        public void setAddressBack(String AddressBack) {
            this.AddressBack = AddressBack;
        }

        public String getIsReturn() {
            return IsReturn;
        }

        public void setIsReturn(String IsReturn) {
            this.IsReturn = IsReturn;
        }

        public int getApplyNum() {
            return ApplyNum;
        }

        public void setApplyNum(int ApplyNum) {
            this.ApplyNum = ApplyNum;
        }

        public int getQApplyNum() {
            return QApplyNum;
        }

        public void setQApplyNum(int QApplyNum) {
            this.QApplyNum = QApplyNum;
        }

        public int getMallID() {
            return MallID;
        }

        public void setMallID(int MallID) {
            this.MallID = MallID;
        }

        public Object getEndRemark() {
            return EndRemark;
        }

        public void setEndRemark(Object EndRemark) {
            this.EndRemark = EndRemark;
        }

        public int getGrade() {
            return Grade;
        }

        public void setGrade(int Grade) {
            this.Grade = Grade;
        }

        public int getGrade1() {
            return Grade1;
        }

        public void setGrade1(int Grade1) {
            this.Grade1 = Grade1;
        }

        public int getGrade2() {
            return Grade2;
        }

        public void setGrade2(int Grade2) {
            this.Grade2 = Grade2;
        }

        public int getGrade3() {
            return Grade3;
        }

        public void setGrade3(int Grade3) {
            this.Grade3 = Grade3;
        }

        public Object getReturnAccessory() {
            return ReturnAccessory;
        }

        public void setReturnAccessory(Object ReturnAccessory) {
            this.ReturnAccessory = ReturnAccessory;
        }

        public Object getReturnAccessoryMsg() {
            return ReturnAccessoryMsg;
        }

        public void setReturnAccessoryMsg(Object ReturnAccessoryMsg) {
            this.ReturnAccessoryMsg = ReturnAccessoryMsg;
        }

        public Object getApplyCancel() {
            return ApplyCancel;
        }

        public void setApplyCancel(Object ApplyCancel) {
            this.ApplyCancel = ApplyCancel;
        }

        public Object getOrgAppraise() {
            return OrgAppraise;
        }

        public void setOrgAppraise(Object OrgAppraise) {
            this.OrgAppraise = OrgAppraise;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getLateTime() {
            return LateTime;
        }

        public void setLateTime(String LateTime) {
            this.LateTime = LateTime;
        }

        public Object getIsExtraTime() {
            return IsExtraTime;
        }

        public void setIsExtraTime(Object IsExtraTime) {
            this.IsExtraTime = IsExtraTime;
        }

        public Object getOrderPayStr() {
            return OrderPayStr;
        }

        public void setOrderPayStr(Object OrderPayStr) {
            this.OrderPayStr = OrderPayStr;
        }

        public Object getThirdPartyNo() {
            return ThirdPartyNo;
        }

        public void setThirdPartyNo(Object ThirdPartyNo) {
            this.ThirdPartyNo = ThirdPartyNo;
        }

        public Object getExpressNo() {
            return ExpressNo;
        }

        public void setExpressNo(Object ExpressNo) {
            this.ExpressNo = ExpressNo;
        }

        public int getRecycleOrderHour() {
            return RecycleOrderHour;
        }

        public void setRecycleOrderHour(int RecycleOrderHour) {
            this.RecycleOrderHour = RecycleOrderHour;
        }

        public String getIsRecevieGoods() {
            return IsRecevieGoods;
        }

        public void setIsRecevieGoods(String IsRecevieGoods) {
            this.IsRecevieGoods = IsRecevieGoods;
        }

        public String getAppointmentMessage() {
            return AppointmentMessage;
        }

        public void setAppointmentMessage(String AppointmentMessage) {
            this.AppointmentMessage = AppointmentMessage;
        }

        public String getAppointmentState() {
            return AppointmentState;
        }

        public void setAppointmentState(String AppointmentState) {
            this.AppointmentState = AppointmentState;
        }

        public Object getIsLate() {
            return IsLate;
        }

        public void setIsLate(Object IsLate) {
            this.IsLate = IsLate;
        }

        public double getDistance() {
            return Distance;
        }

        public void setDistance(double Distance) {
            this.Distance = Distance;
        }

        public boolean isDistanceTureOrFalse() {
            return DistanceTureOrFalse;
        }

        public void setDistanceTureOrFalse(boolean DistanceTureOrFalse) {
            this.DistanceTureOrFalse = DistanceTureOrFalse;
        }

        public Object getSendOrderState() {
            return SendOrderState;
        }

        public void setSendOrderState(Object SendOrderState) {
            this.SendOrderState = SendOrderState;
        }

        public Object getSendOrderMsg() {
            return SendOrderMsg;
        }

        public void setSendOrderMsg(Object SendOrderMsg) {
            this.SendOrderMsg = SendOrderMsg;
        }

        public Object getIsSendRepair() {
            return IsSendRepair;
        }

        public void setIsSendRepair(Object IsSendRepair) {
            this.IsSendRepair = IsSendRepair;
        }

        public Object getOrderSource() {
            return OrderSource;
        }

        public void setOrderSource(Object OrderSource) {
            this.OrderSource = OrderSource;
        }

        public Object getIsPressFactory() {
            return IsPressFactory;
        }

        public void setIsPressFactory(Object IsPressFactory) {
            this.IsPressFactory = IsPressFactory;
        }

        public Object getWorkerComplaint() {
            return WorkerComplaint;
        }

        public void setWorkerComplaint(Object WorkerComplaint) {
            this.WorkerComplaint = WorkerComplaint;
        }

        public Object getFactoryComplaint() {
            return FactoryComplaint;
        }

        public void setFactoryComplaint(Object FactoryComplaint) {
            this.FactoryComplaint = FactoryComplaint;
        }

        public String getIsLook() {
            return IsLook;
        }

        public void setIsLook(String IsLook) {
            this.IsLook = IsLook;
        }

        public String getFIsLook() {
            return FIsLook;
        }

        public void setFIsLook(String FIsLook) {
            this.FIsLook = FIsLook;
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

        public Object getAppointmentRefuseState() {
            return AppointmentRefuseState;
        }

        public void setAppointmentRefuseState(Object AppointmentRefuseState) {
            this.AppointmentRefuseState = AppointmentRefuseState;
        }

        public Object getAccessoryRefuseState() {
            return AccessoryRefuseState;
        }

        public void setAccessoryRefuseState(Object AccessoryRefuseState) {
            this.AccessoryRefuseState = AccessoryRefuseState;
        }

        public Object getFactoryApplyState() {
            return FactoryApplyState;
        }

        public void setFactoryApplyState(Object FactoryApplyState) {
            this.FactoryApplyState = FactoryApplyState;
        }

        public Object getOrderApplyState() {
            return OrderApplyState;
        }

        public void setOrderApplyState(Object OrderApplyState) {
            this.OrderApplyState = OrderApplyState;
        }

        public Object getAccessoryIsPay() {
            return AccessoryIsPay;
        }

        public void setAccessoryIsPay(Object AccessoryIsPay) {
            this.AccessoryIsPay = AccessoryIsPay;
        }

        public Object getOrderSort() {
            return OrderSort;
        }

        public void setOrderSort(Object OrderSort) {
            this.OrderSort = OrderSort;
        }

        public Object getIsSettlement() {
            return IsSettlement;
        }

        public void setIsSettlement(Object IsSettlement) {
            this.IsSettlement = IsSettlement;
        }

        public int getSettlementMoney() {
            return SettlementMoney;
        }

        public void setSettlementMoney(int SettlementMoney) {
            this.SettlementMoney = SettlementMoney;
        }

        public String getSettlementTime() {
            return SettlementTime;
        }

        public void setSettlementTime(String SettlementTime) {
            this.SettlementTime = SettlementTime;
        }

        public Object getOrderAccessoryStr() {
            return OrderAccessoryStr;
        }

        public void setOrderAccessoryStr(Object OrderAccessoryStr) {
            this.OrderAccessoryStr = OrderAccessoryStr;
        }

        public int getVersion() {
            return Version;
        }

        public void setVersion(int Version) {
            this.Version = Version;
        }

        public List<?> getOrderImg() {
            return OrderImg;
        }

        public void setOrderImg(List<?> OrderImg) {
            this.OrderImg = OrderImg;
        }

        public List<?> getReturnaccessoryImg() {
            return ReturnaccessoryImg;
        }

        public void setReturnaccessoryImg(List<?> ReturnaccessoryImg) {
            this.ReturnaccessoryImg = ReturnaccessoryImg;
        }

        public List<?> getOrderAccessroyDetail() {
            return OrderAccessroyDetail;
        }

        public void setOrderAccessroyDetail(List<?> OrderAccessroyDetail) {
            this.OrderAccessroyDetail = OrderAccessroyDetail;
        }

        public List<?> getOrderServiceDetail() {
            return OrderServiceDetail;
        }

        public void setOrderServiceDetail(List<?> OrderServiceDetail) {
            this.OrderServiceDetail = OrderServiceDetail;
        }

        public List<?> getOrderBeyondImg() {
            return OrderBeyondImg;
        }

        public void setOrderBeyondImg(List<?> OrderBeyondImg) {
            this.OrderBeyondImg = OrderBeyondImg;
        }

        public List<?> getOrderComplaintDetail() {
            return OrderComplaintDetail;
        }

        public void setOrderComplaintDetail(List<?> OrderComplaintDetail) {
            this.OrderComplaintDetail = OrderComplaintDetail;
        }

        public List<?> getOrderAppraiseDetail() {
            return OrderAppraiseDetail;
        }

        public void setOrderAppraiseDetail(List<?> OrderAppraiseDetail) {
            this.OrderAppraiseDetail = OrderAppraiseDetail;
        }

        public List<SendOrderListBean> getSendOrderList() {
            return SendOrderList;
        }

        public void setSendOrderList(List<SendOrderListBean> SendOrderList) {
            this.SendOrderList = SendOrderList;
        }

        public List<?> getLeavemessageList() {
            return LeavemessageList;
        }

        public void setLeavemessageList(List<?> LeavemessageList) {
            this.LeavemessageList = LeavemessageList;
        }

        public List<?> getLeavemessageimgList() {
            return LeavemessageimgList;
        }

        public void setLeavemessageimgList(List<?> LeavemessageimgList) {
            this.LeavemessageimgList = LeavemessageimgList;
        }

        public List<?> getStateList() {
            return StateList;
        }

        public void setStateList(List<?> StateList) {
            this.StateList = StateList;
        }

        public static class SendOrderListBean {
            /**
             * Id : 2722
             * SendID : 2722
             * CreateDate : 2019-10-17T10:33:33
             * UserID : 13806652840
             * OrderID : 2000002402
             * State : 1
             * UpdateDate : 2019-10-17T10:34:07
             * ServiceDate : 2019-10-17T10:30:56
             * ServiceDate2 : 2019-10-17T10:30:56
             * LoginUser : system
             * IsUse : Y
             * CategoryID : 281
             * CategoryName : 洗衣机
             * SubTypeID : 0
             * SubTypeName : null
             * Memo : 坏了
             * BrandID : 213
             * BrandName : 小霸王
             * ProductType : null
             * ProvinceCode : 330000
             * CityCode : 330100
             * AreaCode : 330102
             * Address : 浙江省杭州市上城区清波街道街道
             * Guarantee : Y
             * UserName : 评教
             * Phone : 18067138219
             * AppointmentState : 1
             * AppointmentMessage : 预约成功
             * page : 1
             * limit : 10
             * Version : 0
             */

            private int Id;
            private int SendID;
            private String CreateDate;
            private String UserID;
            private int OrderID;
            private String State;
            private String UpdateDate;
            private String ServiceDate;
            private String ServiceDate2;
            private String LoginUser;
            private String IsUse;
            private int CategoryID;
            private String CategoryName;
            private int SubTypeID;
            private Object SubTypeName;
            private String Memo;
            private int BrandID;
            private String BrandName;
            private Object ProductType;
            private String ProvinceCode;
            private String CityCode;
            private String AreaCode;
            private String Address;
            private String Guarantee;
            private String UserName;
            private String Phone;
            private String AppointmentState;
            private String AppointmentMessage;
            private int page;
            private int limit;
            private int Version;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getSendID() {
                return SendID;
            }

            public void setSendID(int SendID) {
                this.SendID = SendID;
            }

            public String getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(String CreateDate) {
                this.CreateDate = CreateDate;
            }

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public int getOrderID() {
                return OrderID;
            }

            public void setOrderID(int OrderID) {
                this.OrderID = OrderID;
            }

            public String getState() {
                return State;
            }

            public void setState(String State) {
                this.State = State;
            }

            public String getUpdateDate() {
                return UpdateDate;
            }

            public void setUpdateDate(String UpdateDate) {
                this.UpdateDate = UpdateDate;
            }

            public String getServiceDate() {
                return ServiceDate;
            }

            public void setServiceDate(String ServiceDate) {
                this.ServiceDate = ServiceDate;
            }

            public String getServiceDate2() {
                return ServiceDate2;
            }

            public void setServiceDate2(String ServiceDate2) {
                this.ServiceDate2 = ServiceDate2;
            }

            public String getLoginUser() {
                return LoginUser;
            }

            public void setLoginUser(String LoginUser) {
                this.LoginUser = LoginUser;
            }

            public String getIsUse() {
                return IsUse;
            }

            public void setIsUse(String IsUse) {
                this.IsUse = IsUse;
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

            public int getSubTypeID() {
                return SubTypeID;
            }

            public void setSubTypeID(int SubTypeID) {
                this.SubTypeID = SubTypeID;
            }

            public Object getSubTypeName() {
                return SubTypeName;
            }

            public void setSubTypeName(Object SubTypeName) {
                this.SubTypeName = SubTypeName;
            }

            public String getMemo() {
                return Memo;
            }

            public void setMemo(String Memo) {
                this.Memo = Memo;
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

            public Object getProductType() {
                return ProductType;
            }

            public void setProductType(Object ProductType) {
                this.ProductType = ProductType;
            }

            public String getProvinceCode() {
                return ProvinceCode;
            }

            public void setProvinceCode(String ProvinceCode) {
                this.ProvinceCode = ProvinceCode;
            }

            public String getCityCode() {
                return CityCode;
            }

            public void setCityCode(String CityCode) {
                this.CityCode = CityCode;
            }

            public String getAreaCode() {
                return AreaCode;
            }

            public void setAreaCode(String AreaCode) {
                this.AreaCode = AreaCode;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getGuarantee() {
                return Guarantee;
            }

            public void setGuarantee(String Guarantee) {
                this.Guarantee = Guarantee;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getAppointmentState() {
                return AppointmentState;
            }

            public void setAppointmentState(String AppointmentState) {
                this.AppointmentState = AppointmentState;
            }

            public String getAppointmentMessage() {
                return AppointmentMessage;
            }

            public void setAppointmentMessage(String AppointmentMessage) {
                this.AppointmentMessage = AppointmentMessage;
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
}
