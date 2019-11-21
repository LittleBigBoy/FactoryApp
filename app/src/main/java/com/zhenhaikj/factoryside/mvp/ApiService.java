package com.zhenhaikj.factoryside.mvp;

import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;
import com.zhenhaikj.factoryside.mvp.bean.Address;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Article;
import com.zhenhaikj.factoryside.mvp.bean.BankCard;
import com.zhenhaikj.factoryside.mvp.bean.Bill;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.CanInvoice;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.CategoryData;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.CompanyInfo;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Data2;
import com.zhenhaikj.factoryside.mvp.bean.DepositRecharge;
import com.zhenhaikj.factoryside.mvp.bean.DepositWithDraw;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.Freezing;
import com.zhenhaikj.factoryside.mvp.bean.FrozenMoney;
import com.zhenhaikj.factoryside.mvp.bean.GetCategory;
import com.zhenhaikj.factoryside.mvp.bean.GetFactoryData;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.bean.LeaveMessage;
import com.zhenhaikj.factoryside.mvp.bean.Logistics;
import com.zhenhaikj.factoryside.mvp.bean.Message;
import com.zhenhaikj.factoryside.mvp.bean.MessageData;
import com.zhenhaikj.factoryside.mvp.bean.MonthBill;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.bean.Recharge;
import com.zhenhaikj.factoryside.mvp.bean.RedPointData;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.bean.SingleQuantity;
import com.zhenhaikj.factoryside.mvp.bean.SubUserInfo;
import com.zhenhaikj.factoryside.mvp.bean.Track;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WXpayInfo;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;
import com.zhenhaikj.factoryside.mvp.widget.OrderFreezing;

import org.json.JSONArray;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    /**
     * 判断用户名是否可用
     */
    @FormUrlEncoded
    @POST("Account/ValidateUserName")
    Observable<BaseResult<String>> ValidateUserName(@Field("UserID") String userName);

    /**
     * 获取短信
     */
    @FormUrlEncoded
    @POST("Message/Send")
    Observable<BaseResult<Data<String>>> GetCode(@Field("mobile") String mobile, @Field("type") String type, @Field("roleType") String roleType);


    /*短信登陆*/
    /*短信登陆*/
    @FormUrlEncoded
    @POST("Account/LoginOnMessage")
    Observable<BaseResult<Data<String>>> LoginOnMessage(@Field("mobile") String mobile,
                                                        @Field("code") String code,
                                                        @Field("roleType") String roleType);

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("Account/Reg")
    Observable<BaseResult<Data<String>>> Reg(@Field("mobile") String mobile,
                                             @Field("type") String type,
                                             @Field("code") String code,
                                             @Field("roleType") String roleType,
                                             @Field("password") String password);

    /*
     *新增获取更新推送账户的token以及tags， 工厂的type是6 师傅的type是7 ， createtime可以不传 UserID为登录用户名
     * */
    @FormUrlEncoded
    @POST("Message/AddAndUpdatePushAccount")
    Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(@Field("token") String token,
                                                                 @Field("type") String type,
                                                                 @Field("UserID") String UserID);

    /**
     * app用户登录
     */
    @FormUrlEncoded
    @POST("Account/LoginOn")
    Observable<BaseResult<Data<String>>> LoginOn(@Field("userName") String userName, @Field("passWord") String passWord, @Field("roleType") String roleType);

    /**
     * app获取用户信息
     */
    @POST("Account/GetUserInfo")
    Observable<BaseResult<String>> GetUserInfo(@Body RequestBody json);

    /**
     * app获取用户信息
     */
    @FormUrlEncoded
    @POST("Account/GetUserInfo")
    Observable<BaseResult<String>> GetUserInfo(@Field("userName") String userName);

    /**
     * 获取工单列表
     * 废除-1，待审核0，派单中1，服务中2，已完成3
     */
    @FormUrlEncoded
    @POST("Order/FactoryGetOrderList")
    Observable<BaseResult<WorkOrder>> FactoryGetOrderList(@Field("UserID") String UserID, @Field("State") String state, @Field("page") String page, @Field("limit") String limit);

    /**
     * 获取工单详情
     * 通过OrderID获取工单详情
     * MessageType:1工厂2师傅端3平台
     */
    @FormUrlEncoded
    @POST("Order/GetOrderInfo")
    Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(@Field("OrderID") String OrderID,
                                                            @Field("MessageType") String MessageType);

    /**
     * 添加品牌
     */
    @FormUrlEncoded
    @POST("FactoryConfig/AddFactoryBrand")
    Observable<BaseResult<Data>> AddFactoryBrand(@Field("UserID") String UserID, @Field("FBrandName") String FBrandName);

    /**
     * 添加品牌对应的分类BrandID,Categorys(逗号（,）分割)
     *
     * @param BrandID
     * @param CategoryID
     * @return
     */
    @FormUrlEncoded
    @POST("FactoryConfig/AddBrandCategory")
    Observable<BaseResult<Data>> AddBrandCategory(
            @Field("BrandID") String BrandID,
            @Field("CategoryID") String CategoryID,
            @Field("SubCategoryID") String SubCategoryID,
            @Field("ProductTypeID") String ProductTypeID
    );

    /**
     * 获取品牌
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryBrand")
    Observable<BaseResult<List<Brand>>> GetFactoryBrand(@Field("UserID") String UserID);

//    @FormUrlEncoded
//    @POST("FactoryConfig/GetFactoryBrand")
//    Observable<BaseResult<List<ProductType>>> GetBrand(@Field("UserID") String UserID);

    /**
     * 获取分类
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryCategory")
    Observable<BaseResult<CategoryData>> GetFactoryCategory(@Field("ParentID") String ParentID);

//    @FormUrlEncoded
//    @POST("FactoryConfig/GetFactoryCategory")
//    Observable<BaseResult<Data<List<ProductType>>>> GetCategory(@Field("ParentID") String ParentID);

    /**
     * 获取型号
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetBrandWithCategory")
    Observable<BaseResult<Data<List<GetCategory>>>> GetBrandWithCategory(@Field("UserID") String UserID,
                                                                         @Field("BrandID") String BrandID);

    /**
     * 获取子分类
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryCategory")
    Observable<BaseResult<CategoryData>> GetChildFactoryCategory(@Field("ParentID") String ParentID);

    /**
     * 获取型号
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryProducttype")
    Observable<BaseResult<Data<List<ProductType>>>> GetFactoryProducttype(@Field("FBrandID") String FBrandID, @Field("FCategoryID") String FCategoryID);

    /**
     * 获取型号by  UserID
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetBrandCategory")
    Observable<BaseResult<Data<List<Category>>>> GetBrandCategory(@Field("UserID") String UserID);


    @POST("FactoryConfig/GetFactoryProducttype")
    Observable<BaseResult<Data<List<ProductType>>>> GetProducttype();


    /**
     * 删除工厂产品型号
     */
    @FormUrlEncoded
    @POST("FactoryConfig/DeleteFactoryProducttype")
    Observable<BaseResult<Data>> DeleteFactoryProducttype(@Field("FProductTypeID") String FProductTypeID);

    /**
     * 删除工厂产品型号新
     */
    @FormUrlEncoded
    @POST("FactoryConfig/DeleteBrandcategory")
    Observable<BaseResult<Data>> DeleteFactoryProduct(@Field("UserID") String UserID,
                                                      @Field("BrandID") String BrandID,
                                                      @Field("ProductTypeID") String ProductTypeID);

    /**
     * 删除工厂品牌
     */
    @FormUrlEncoded
    @POST("FactoryConfig/DeleteFactoryBrand")
    Observable<BaseResult<Data>> DeleteFactoryBrand(@Field("FBrandID") String FBrandID);

    /**
     * 获取属性
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryAccessory")
    Observable<BaseResult<Accessory>> GetFactoryAccessory(@Field("FProductTypeID") String FProductTypeID);

    /*
     *
     * 获取工厂配件信息
     * */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryAccessory")
    Observable<BaseResult<GetFactoryData<Accessory2>>> GetFactoryAccessory2(@Field("FCategoryID") String FProductTypeID);

    /**
     * 获取省
     */
    @POST("Config/GetProvince")
    Observable<BaseResult<List<Province>>> GetProvince();

    /**
     * 获取市
     */
    @FormUrlEncoded
    @POST("Config/GetCity")
    Observable<BaseResult<Data<List<City>>>> GetCity(@Field("parentcode") String parentcode);

    /**
     * 获取区
     */
    @FormUrlEncoded
    @POST("Config/GetArea")
    Observable<BaseResult<Data<List<Area>>>> GetArea(@Field("parentcode") String parentcode);

    /*
     * 获取街道，乡镇
     * */
    @FormUrlEncoded
    @POST("Config/GetDistrict")
    Observable<BaseResult<Data<List<District>>>> GetDistrict(@Field("parentcode") String parentcode);


    /**
     * 发布工单
     * TypeID;//分类ID 1维修 2安装 3其他服务
     * TypeName;//
     * UserID;//用户id
     * FBrandID;//品牌id
     * FBrandName;//品牌名
     * FCategoryID;//分类id
     * FCategoryName;//分类名
     * FProductTypeID;//型号id
     * FProductType;//型号名
     * ProvinceCode;//省code
     * CityCode;//市code
     * AreaCode;//区code
     * Address;//详细地址
     * UserName;//客户姓名
     * Phone;//客户手机
     * Memo;//故障描述
     * RecycleOrderHour;//回收时间
     * Guarantee;//保内Y保外N
     * AccessorySendState;//是否已发配件 Y是N否
     * Extra;//是否加急Y是N否
     * ExtraTime;//加急时间
     * ExtraFee;//加急费用
     * AddressBack：旧配件寄件地址
     * FactoryAccessoryID  ：配件id
     * FactoryAccessoryName：配件名称
     * IsReturn：是否返件
     * PostPayType：到付，现付
     * 去掉 AccessorySendState，
     * IsRecevieGoods:是否发配件
     */
//    @FormUrlEncoded
    @POST("Order/AddOrder")
    Observable<BaseResult<Data<String>>> AddOrder(
//            @Field("TypeID") String TypeID,
//            @Field("TypeName") String TypeName,
//            @Field("UserID") String UserID,
//            @Field("BrandID") String FBrandID,
//            @Field("BrandName") String BrandName,
//            @Field("CategoryID") String FCategoryID,
//            @Field("CategoryName") String CategoryName,
//            @Field("SubCategoryID") String SubCategoryID,
//            @Field("SubCategoryName") String SubCategoryName,
//            @Field("ProvinceCode") String ProvinceCode,
//            @Field("CityCode") String CityCode,
//            @Field("AreaCode") String AreaCode,
//            @Field("DistrictCode") String DistrictCode,
//            @Field("Address") String Address,
//            @Field("UserName") String UserName,
//            @Field("Phone") String Phone,
//            @Field("Memo") String Memo,
//            @Field("OrderMoney") String OrderMoney,
//            @Field("RecycleOrderHour") String RecycleOrderHour,
//            @Field("Guarantee") String Guarantee,
//            @Field("Extra") String Extra,
//            @Field("ExtraTime") String ExtraTime,
//            @Field("ExtraFee") String ExtraFee,
//            @Field("Num") String Num,
//            @Field("IsRecevieGoods") String IsRecevieGoods,
//            @Field("ExpressNo") String ExpressNo,
            @Body RequestBody json);


    @FormUrlEncoded
    @POST("Order/AddOrder")
    Observable<BaseResult<Data<String>>> AddOrderService(@Field("TypeID") String TypeID,
                                                         @Field("TypeName") String TypeName,
                                                         @Field("UserID") String UserID,
                                                         @Field("BrandID") String FBrandID,
                                                         @Field("BrandName") String BrandName,
                                                         @Field("CategoryID") String FCategoryID,
                                                         @Field("CategoryName") String CategoryName,
                                                         @Field("SubCategoryID") String SubCategoryID,
                                                         @Field("SubCategoryName") String SubCategoryName,
                                                         @Field("ProvinceCode") String ProvinceCode,
                                                         @Field("CityCode") String CityCode,
                                                         @Field("AreaCode") String AreaCode,
                                                         @Field("DistrictCode") String DistrictCode,
                                                         @Field("Address") String Address,
                                                         @Field("UserName") String UserName,
                                                         @Field("Phone") String Phone,
                                                         @Field("Memo") String Memo,
                                                         @Field("OrderMoney") String OrderMoney,
                                                         @Field("RecycleOrderHour") String RecycleOrderHour,
                                                         @Field("Guarantee") String Guarantee,
                                                         @Field("Num") String Num);

    /*获取用户信息*/
    @FormUrlEncoded
    @POST("Account/GetUserInfoList")
    Observable<BaseResult<UserInfo>> GetUserInfoList(@Field("UserID") String UserID,
                                                     @Field("limit") String limit);

    /*修改支付密码*/
    @FormUrlEncoded
    @POST("Account/ChangePayPassword")
    Observable<BaseResult<Data>> ChangePayPassword(@Field("UserID") String UserID,
                                                   @Field("OldPayPassword") String OldPayPassword,
                                                   @Field("PayPassword") String PayPassword);


    /*获取用户账单*/
    @FormUrlEncoded
    @POST("Account/AccountBill")
    Observable<BaseResult<Data<Bill>>> AccountBill(@Field("UserID") String UserID,
                                                   @Field("state") String state,
                                                   @Field("page") String page,
                                                   @Field("limit") String limit);

    /*获取用户每月账单*/
    @FormUrlEncoded
    @POST("Account/MonthBill")
    Observable<BaseResult<Data<MonthBill>>> MonthBill(@Field("UserID") String UserID,
                                                      @Field("state") String state);

    /*获取用户冻结记录*/
    @FormUrlEncoded
    @POST("account/GetFrozenMoney")
    Observable<BaseResult<Data<FrozenMoney>>> GetFrozenMoney(@Field("UserID") String UserID);

    /*修改昵称*/
    @FormUrlEncoded
    @POST("Account/UpdateAccountNickName")
    Observable<BaseResult<Data>> UpdateAccountNickName(@Field("UserID") String UserID,
                                                       @Field("NickName") String NickName);


    /*修改密码*/

    @FormUrlEncoded
    @POST("Account/UpdatePassword")
    Observable<BaseResult<Data>> UpdatePassword(@Field("UserID") String UserID,
                                                @Field("Password") String Password);

    /**
     * 充值信息
     *
     * @param UserID      账号
     * @param TotalAmount 金额
     * @param Type        1余额 2 诚意金 3订单支付
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/GetOrderStr")
    Observable<BaseResult<Data<String>>> GetOrderStr(@Field("UserID") String UserID,
                                                     @Field("BisId") String BisId,
                                                     @Field("OrderId") String OrderId,
                                                     @Field("TotalAmount") String TotalAmount,
                                                     @Field("Type") String Type,
                                                     @Field("JsonStr") JSONArray JsonStr
    );

    /**
     * 充值信息
     *
     * @param UserID      账号
     * @param TotalAmount 金额
     * @param Type        1余额 2 诚意金 3订单支付
     * @param Style       工厂传factory 商城mall
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/GetWXOrderStr")
    Observable<BaseResult<Data<WXpayInfo>>> GetWXOrderStr(@Field("UserID") String UserID,
                                                          @Field("BisId") String BisId,
                                                          @Field("OrderId") String OrderId,
                                                          @Field("TotalAmount") String TotalAmount,
                                                          @Field("Type") String Type,
                                                          @Field("Style") String Style,
                                                          @Field("JsonStr") JSONArray JsonStr);

    /**
     * 微信人工回调OutTradeNo
     *
     * @param OutTradeNo
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/WXNotifyManual")
    Observable<BaseResult<Data<String>>> WXNotifyManual(@Field("out_trade_no") String OutTradeNo);

    /**
     * 对某笔单子发起质保
     *
     * @param OrderID 订单id
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApplyCustomService")
    Observable<BaseResult<Data<String>>> ApplyCustomService(
            @Field("OrderID") String OrderID
    );

    /**
     * 审核远程费
     *
     * @param OrderID     订单id
     * @param BeyondState -1拒绝 1通过
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApproveBeyondMoney")
    Observable<BaseResult<Data<String>>> ApproveBeyondMoney(
            @Field("OrderID") String OrderID,
            @Field("BeyondState") String BeyondState,
            @Field("BeyondMoney") String BeyondMoney
    );

    /**
     * 工厂投诉
     *
     * @param OrderID 订单id
     * @param Content 投诉原因
     * @return
     */
    @FormUrlEncoded
    @POST("Order/FactoryComplaint")
    Observable<BaseResult<Data<String>>> FactoryComplaint(
            @Field("OrderID") String OrderID,
            @Field("Content") String Content
    );

    /**
     * 工厂确认订单 结算
     *
     * @param OrderID     订单id
     * @param PayPassword 支付密码
     * @return
     */
    @FormUrlEncoded
    @POST("Order/FactoryEnsureOrder")
    Observable<BaseResult<Data<String>>> FactoryEnsureOrder(
            @Field("OrderID") String OrderID,
            @Field("PayPassword") String PayPassword
    );

    @FormUrlEncoded
    @POST("Order/NowEnSureOrder")
    Observable<BaseResult<Data<String>>> NowEnSureOrder(
            @Field("OrderID") String OrderID
    );

    /*
    * 用户确认订单 结算
    * */
    @FormUrlEncoded
    @POST("Order/NowPayEnSureOrder")
    Observable<BaseResult<Data<String>>> NowPayEnSureOrder(
            @Field("OrderID") String OrderID,
            @Field("PayPassword") String PayPassword
    );

    /**
     * 用户确认订单 结算
     *
     * @param OrderID     订单id
     * @param PayPassword 支付密码
     * @return
     */
    @FormUrlEncoded
    @POST("Order/EnSureOrder")
    Observable<BaseResult<Data<String>>> EnSureOrder(
            @Field("OrderID") String OrderID,
            @Field("PayPassword") String PayPassword
    );

    /**
     * 审核配件申请
     *
     * @param OrderID             订单id
     * @param AccessoryApplyState -1拒绝 1通过
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApproveOrderAccessory")
    Observable<BaseResult<Data<String>>> ApproveOrderAccessory(
            @Field("OrderID") String OrderID,
            @Field("AccessoryApplyState") String AccessoryApplyState,
            @Field("NewMoney") String NewMoney,
            @Field("OrderAccessoryID") String OrderAccessoryID
    );

    /**
     * 审核服务申请
     *
     * @param OrderID 订单id
     * @param State   -1拒绝 1通过
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApproveOrderService")
    Observable<BaseResult<Data<String>>> ApproveOrderService(
            @Field("OrderID") String OrderID,
            @Field("State") String State,
            @Field("OrderServiceID") String OrderServiceID
    );


    /**
     * 审核配件跟服务申请
     *
     * @param OrderID                       订单id
     * @param AccessoryAndServiceApplyState -1拒绝 1通过
     * @return
     */
    @FormUrlEncoded
    @POST("Order/ApproveOrderAccessoryAndService")
    Observable<BaseResult<Data<String>>> ApproveOrderAccessoryAndService(
            @Field("OrderID") String OrderID,
            @Field("AccessoryAndServiceApplyState") String AccessoryAndServiceApplyState,
            @Field("PostPayType") String PostPayType,
            @Field("IsReturn") String IsReturn
    );

    /**
     * 工厂添加配件快递信息
     *
     * @param OrderID   订单id
     * @param ExpressNo 快递单号
     * @return
     */
    @FormUrlEncoded
    @POST("Order/AddOrUpdateExpressNo")
    Observable<BaseResult<Data<String>>> AddOrUpdateExpressNo(
            @Field("OrderID") String OrderID,
            @Field("ExpressNo") String ExpressNo
    );


    /**
     * 快递信息
     *
     * @param ExpressNo 快递单号
     * @return
     */
    @FormUrlEncoded
    @POST("Order/GetExpressInfo")
    Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(@Field("ExpressNo") String ExpressNo);

    /*
     * 取消订单
     * */
    @FormUrlEncoded
    @POST("Order/UpdateOrderState")
    Observable<BaseResult<Data<String>>> UpdateOrderState(
            @Field("OrderID") String OrderID,
            @Field("State") String State
    );


    /*
     * 修改头像
     * */
    @POST("Upload/UploadAvator")
    Observable<BaseResult<Data<String>>> UploadAvator(@Body RequestBody json);

    /*修改性别*/
    @FormUrlEncoded
    @POST("Account/UpdateSex")
    Observable<BaseResult<Data>> UpdateSex(@Field("UserID") String UserID,
                                           @Field("Sex") String Sex);

    /**
     * 添加收货地址
     *
     * @param UserID
     * @param Province
     * @param City
     * @param Area
     * @param Address
     * @param Default
     * @param UserName
     * @param Phone
     * @return
     */
    @FormUrlEncoded
    @POST("Account/AddAccountAddress")
    Observable<BaseResult<Data<String>>> AddAccountAddress(
            @Field("UserID") String UserID,
            @Field("Province") String Province,
            @Field("City") String City,
            @Field("Area") String Area,
            @Field("District") String District,
            @Field("Address") String Address,
            @Field("IsDefault") String Default,
            @Field("UserName") String UserName,
            @Field("Phone") String Phone
    );

    /**
     * 修改收货地址
     *
     * @param ID
     * @param UserID
     * @param Province
     * @param City
     * @param Area
     * @param Address
     * @param Default
     * @param UserName
     * @param Phone
     * @return
     */
    @FormUrlEncoded
    @POST("Account/UpdateAccountAddress")
    Observable<BaseResult<Data<String>>> UpdateAccountAddress(
            @Field("AccountAdressID") String ID,
            @Field("UserID") String UserID,
            @Field("Province") String Province,
            @Field("City") String City,
            @Field("Area") String Area,
            @Field("District") String District,
            @Field("Address") String Address,
            @Field("IsDefault") String Default,
            @Field("UserName") String UserName,
            @Field("Phone") String Phone
    );

    /**
     * 删除收货地址
     *
     * @param ID
     * @return
     */
    @FormUrlEncoded
    @POST("Account/DeleteAccountAddress")
    Observable<BaseResult<Data<String>>> DeleteAccountAddress(
            @Field("AccountAdressID") String ID
    );

    /**
     * 获取收货地址列表
     *
     * @param UserID
     * @return
     */
    @FormUrlEncoded
    @POST("Account/GetAccountAddress")
    Observable<BaseResult<List<Address>>> GetAccountAddress(
            @Field("UserID") String UserID
    );

    /**
     * 旧件是否需要返件
     *
     * @param OrderID
     * @param IsReturn
     * @param AddressBack
     * @param PostPayType
     * @return
     */
    @FormUrlEncoded
    @POST("Order/UpdateIsReturnByOrderID")
    Observable<BaseResult<Data<String>>> UpdateIsReturnByOrderID(
            @Field("OrderID") String OrderID,
            @Field("IsReturn") String IsReturn,
            @Field("AddressBack") String AddressBack,
            @Field("PostPayType") String PostPayType
    );

    /*获取文章
     * 3系统消息 4平台政策 5平台新闻 6接单必读
     * */
    @FormUrlEncoded
    @POST("Cms/GetListCategoryContentByCategoryID")
    Observable<BaseResult<Article>> GetListCategoryContentByCategoryID(@Field("CategoryID") String CategoryID,
                                                                       @Field("page") String page,
                                                                       @Field("limit") String limit);


    /**
     * 获取首页数据
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<BaseResult<HomeData>> getHome(@Field("userid") String userid);


    /*获取消息*/

    /*获取个人消息  1.交易消息类型  2.订单消息类型*/
    @FormUrlEncoded
//    @POST("Cms/GetListmessageByType")//分页无效
    @POST("Cms/GetmessageListByType")//分页有效
    Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(@Field("UserID") String UserID,
                                                                      @Field("Type") String Type,
                                                                      @Field("SubType") String SubType,
                                                                      @Field("limit") String limit,
                                                                      @Field("page") String page,
                                                                      @Field("IsLook") String IsLook);

    /*标记个人消息全部已读  1.交易消息类型  2.订单消息类型*/
    @FormUrlEncoded
//    @POST("Cms/GetListmessageByType")//分页无效
    @POST("Cms/AllRead")//分页有效
    Observable<BaseResult<MessageData<List<Message>>>> AllRead(@Field("UserID") String UserID,
                                                                      @Field("Type") String Type,
                                                                      @Field("SubType") String SubType);


    /*更新消息状态点击后*/
    @FormUrlEncoded
    @POST("Cms/AddOrUpdatemessage")
    Observable<BaseResult<Data<String>>> AddOrUpdatemessage(@Field("MessageID") String MessageID,
                                                            @Field("IsLook") String IsLook);


    /*意见反馈*/
    @FormUrlEncoded
    @POST("Account/AddOpinion")
    Observable<BaseResult<Data<String>>> AddOpinion(@Field("UserID") String UserID,
                                                    @Field("BackType") String BackType,
                                                    @Field("Content") String Content);

    /*工单跟踪*/
    @FormUrlEncoded
    @POST("Order/GetOrderRecordByOrderID")
    Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(@Field("OrderID") String OrderID);


    /*tablayout显示未读小红点*/
    @FormUrlEncoded
    @POST("Order/FactoryGetOrderRed")
    Observable<BaseResult<RedPointData>> FactoryGetOrderRed(@Field("UserID") String UserID);

    /*更新工单消息为已读*/
    @FormUrlEncoded
    @POST("Order/UpdateOrderFIsLook")
    Observable<BaseResult<Data<String>>> UpdateOrderFIsLook(@Field("OrderID") String OrderID,
                                                            @Field("IsLook") String IsLook,
                                                            @Field("FIsLook") String FIsLook);

    /**
     * 退出登录
     */
    @FormUrlEncoded
    @POST("Account/LoginOut")
    Observable<BaseResult<Data<String>>> LoginOut(@Field("UserID") String UserID, @Field("Type") String type);

    /**
     * 冻结金额
     */
    @FormUrlEncoded
    @POST("order/GetOrderAccessoryMoney")
    Observable<BaseResult<Data<String>>> GetOrderAccessoryMoney(@Field("OrderID") String OrderID);

    /**
     * 实名认证
     * "UserID": "string",
     * "CompanyName": "string",
     * "CompanyNum": "string",
     * "ManagyName": "string",
     * "ManagyPhone": "string",
     * "ServicePhone": "string",
     * "FinancePhone": "string",
     * "ArtisanPhone": "string",
     * "PhoneUrl": "string",
     * "Province": "string",
     * "City": "string",
     * "Area": "string",
     * "District": "string",
     * "Address": "string",
     * "IfAuth": "string",
     * "IsUse": "string",
     */
    @FormUrlEncoded
    @POST("Account/FactoryApplyAuthInfo")
    Observable<BaseResult<Data<String>>> FactoryApplyAuthInfo(@Field("UserID") String UserID,
                                                              @Field("CompanyName") String CompanyName,
                                                              @Field("CompanyNum") String CompanyNum,
                                                              @Field("ManagyName") String ManagyName,
                                                              @Field("ManagyPhone") String ManagyPhone,
                                                              @Field("ServicePhone") String ServicePhone,
                                                              @Field("FinancePhone") String FinancePhone,
                                                              @Field("ArtisanPhone") String ArtisanPhone,
                                                              @Field("PhoneUrl") String PhoneUrl,
                                                              @Field("Province") String Province,
                                                              @Field("City") String City,
                                                              @Field("Area") String Area,
                                                              @Field("District") String District,
                                                              @Field("Address") String Address,
                                                              @Field("IfAuth") String IfAuth,
                                                              @Field("IsUse") String IsUse);

    /**
     * 上传营业执照
     */
    @POST("Upload/IDCardUpload")
    Observable<BaseResult<Data<String>>> IDCardUpload(@Body RequestBody json);


    /**
     * 获取公司信息
     */
    @FormUrlEncoded
    @POST("account/GetmessageBytype")
    Observable<BaseResult<Data<CompanyInfo>>> GetmessageBytype(@Field("UserID") String UserID);

    /**
     * 获取开票条目
     * IsInvoice:0  未开
     * IsInvoice：1 已开
     * IsInvoice：2  审核中
     */
    @FormUrlEncoded
    @POST("Account/GetCanInvoiceByUserid")
    Observable<BaseResult<Data<List<CanInvoice>>>> GetCanInvoiceByUserid(@Field("UserID") String UserID,
                                                                         @Field("IsInvoice") String IsInvoice);

    /**
     * 获取开票记录
     */
    @FormUrlEncoded
    @POST("Account/GetInvoiceByUserid")
    Observable<BaseResult<Data<List<CanInvoice>>>> GetInvoiceByUserid(@Field("UserID") String UserID);

    /**
     * 提交开票请求
     * "UserID": "string",
     * "Heads": "string", 发票抬头
     * "Credit": "string", 信用代码
     * "Content": "string",发票内容
     * "Money": 0,发票金额
     * "State": "string",发票类型
     * "Emails": "string",发票邮箱
     * "Approve": "string",1:通过 2:拒绝 3:审核中
     */
    @FormUrlEncoded
    @POST("Account/AddInvoice")
    Observable<BaseResult<Data<String>>> AddInvoice(@Field("UserID") String UserID,
                                                    @Field("Heads") String Heads,
                                                    @Field("Credit") String Credit,
                                                    @Field("Content") String Content,
                                                    @Field("Money") String Money,
                                                    @Field("State") String State,
                                                    @Field("Emails") String Emails,
                                                    @Field("Approve") String Approve,
                                                    @Field("PuID") String PuID,
                                                    @Field("Count") String Count);


    /*批量发单*/
    @POST("BatchOrder/BatchAddOrder")
    Observable<BaseResult<String>> BatchAddOrder(@Body RequestBody json);


    /*
     * 删除订单
     * */
    @FormUrlEncoded
    @POST("order/ApplyCancelOrder")
    Observable<BaseResult<Data<String>>> ApplyCancelOrder(@Field("OrderID") String OrderID);


    /*
     * 审核配件,配件价格为0
     * */
    @FormUrlEncoded
    @POST("FactoryConfig/UpdateFactoryAccessorybyFactory")
    Observable<BaseResult<Data<String>>> UpdateFactoryAccessorybyFactory(@Field("Id") String Id,
                                                                         @Field("AccessoryName") String AccessoryName,
                                                                         @Field("AccessoryPrice") String AccessoryPrice,
                                                                         @Field("OrderAccessoryId") String OrderAccessoryId);

    /*
     * 审核配件,配件价格不为0
     * */
    @FormUrlEncoded
    @POST("Order/ApproveOrderAccessoryByModifyPrice")
    Observable<BaseResult<Data<String>>> ApproveOrderAccessoryByModifyPrice(@Field("OrderID") String OrderID,
                                                                            @Field("AccessoryApplyState") String AccessoryApplyState,
                                                                            @Field("NewMoney") String NewMoney,
                                                                            @Field("OrderAccessoryID") String OrderAccessoryID);

    /*
  获取子账号*/
    @FormUrlEncoded
    @POST("Account/GetChildAccountByParentUserID")
    Observable<BaseResult<List<SubUserInfo.SubUserInfoDean>>>
    GetChildAccountByParentUserID(@Field("ParentUserID") String ParentUserID);

    /*
     * 注销子账号
     * */
    @FormUrlEncoded
    @POST("Account/CancelChildAccount")
    Observable<BaseResult<Data<String>>> CancelChildAccount(@Field("UserID") String UserID,
                                                            @Field("ParentUserID") String ParentUserID);

    /*根据银行卡号获取银行名 判断后台是否支持该银行的提现*/

    @FormUrlEncoded
    @POST("Account/GetBankNameByCardNo")
    Observable<BaseResult<Data<String>>> GetBankNameByCardNo(@Field("CardNo") String CardNo);

    /*提取保证金*/
    @FormUrlEncoded
    @POST("Account/WithDrawDeposit")
    Observable<BaseResult<Data<String>>> WithDrawDeposit(@Field("DrawMoney") String DrawMoney,
                                                         @Field("CardNo") String CardNo,
                                                         @Field("UserID") String UserID,
                                                         @Field("CardName") String CardName);

    /*添加银行卡*/
    @FormUrlEncoded
    @POST("Account/AddorUpdateAccountPayInfo")
    Observable<BaseResult<Data<String>>> AddorUpdateAccountPayInfo(@Field("UserID") String UserID,
                                                                   @Field("PayInfoCode") String PayInfoCode,
                                                                   @Field("PayInfoName") String PayInfoName,
                                                                   @Field("PayNo") String PayNo,
                                                                   @Field("PayName") String PayName);

    /*获取银行卡*/
    @FormUrlEncoded
    @POST("Account/GetAccountPayInfoList")
    Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(@Field("UserID") String UserID);


    /*充值诚意金记录*/
    @FormUrlEncoded
    @POST("Account/DepositRechargeList")
    Observable<BaseResult<Data<DepositRecharge>>> DepositRechargeList(@Field("UserID") String UserID,
                                                                      @Field("state") String state);


    /*
     *提取诚意金记录
     * state:0 待提现，
     * state:1 提现成功
     * */
    @FormUrlEncoded
    @POST("Account/GetDepositWithDrawList")
    Observable<BaseResult<DepositWithDraw>> GetDepositWithDrawList(@Field("UserID") String UserID,
                                                                   @Field("state") String state);

    /**
     * 留言
     */
    @FormUrlEncoded
    @POST("LeaveMessage/AddLeaveMessageForOrder ")
    Observable<BaseResult<Data<String>>> AddLeaveMessageForOrder(@Field("UserId") String UserID,
                                                                 @Field("Type") String Type,
                                                                 @Field("OrderId") String OrderId,
                                                                 @Field("Content") String Content,
                                                                 @Field("photo") String photo);

    /*
     * 留言图片
     * */
    @POST("Upload/LeaveMessageImg")
    Observable<BaseResult<Data<String>>> LeaveMessageImg(@Body RequestBody json);

    /*
     * 星标工单
     * */
    @FormUrlEncoded
    @POST("Order/GetFStarOrder")
    Observable<BaseResult<Data<String>>> GetFStarOrder(@Field("OrderID") String OrderID,
                                                       @Field("FStarOrder") String FStarOrder);

    /*
     *根据手机号搜索
     * */
    @FormUrlEncoded
    @POST("Order/GetOrderInfoList")
    Observable<BaseResult<Search>> GetOrderInfoList(@Field("Phone") String Phone,
                                                    @Field("OrderID") String OrderID,
                                                    @Field("UserID") String UserID,
                                                    @Field("limit") String limit,
                                                    @Field("page") String page);

    /*获取工单数量*/
    @FormUrlEncoded
    @POST("Order/GetUserOrderNum")
    Observable<BaseResult<Search>> GetUserOrderNum(@Field("UserID") String UserID);


    /*获取用户充值账单
     *
     * 状态 :1充值2支付3提现4待支付 收入=5,支付完成待确认=6,商城支付=7, 退款=8
     * */
    @FormUrlEncoded
    @POST("Account/RechargeRecord")
    Observable<BaseResult<Data2<Recharge>>> RechargeRecord(@Field("UserID") String UserID,
                                                                 @Field("CreateTimeStart") String CreateTimeStart,
                                                                 @Field("CreateTimeEnd") String CreateTimeEnd,
                                                                 @Field("StateName") String StateName,
                                                                 @Field("State") String State,
                                                                 @Field("page") String page,
                                                                 @Field("limit") String limit);


    /*
     * 冻结金额
     * */
    @FormUrlEncoded
    @POST("Account/FreezingAmount")
    Observable<BaseResult<Data<Freezing>>> FreezingAmount(@Field("UserID") String UserID,
                                                    @Field("CreateTimeStart") String CreateTimeStart,
                                                    @Field("CreateTimeEnd") String CreateTimeEnd,
                                                    @Field("page") String page,
                                                    @Field("limit") String limit);

    /*
     * 冻结金额
     * TypeID 1维修 2安装 3质保 4送修
     * */
    @FormUrlEncoded
    @POST("Account/WorkOrderTime")
    Observable<BaseResult<Data<SingleQuantity>>> WorkOrderTime(@Field("UserID") String UserID,
                                                         @Field("CreateTimeStart") String CreateTimeStart,
                                                         @Field("CreateTimeEnd") String CreateTimeEnd,
                                                         @Field("TypeID") String TypeID,
                                                         @Field("page") String page,
                                                         @Field("limit") String limit);


    /*
     * 预冻结金额
     * */
    @FormUrlEncoded
    @POST("Account/getOrderFreezing")
    Observable<BaseResult<Data<List<OrderFreezing>>>> getOrderFreezing(@Field("OrderID") String OrderID);

    /*可用余额*/
    @FormUrlEncoded
    @POST("Account/GetRemainMoney")
    Observable<BaseResult<Data<String>>> GetRemainMoney(@Field("UserID") String UserID);

    /*多次发送请求*/
    @GET("Uniq/GetUniqId")
    Observable<BaseResult<String>> GetUniqId();

    /*
     * 留言查看过
     * 1  未读
     * 2  已读
     * */
    @FormUrlEncoded
    @POST("LeaveMessage/LeaveMessageWhetherLook")
    Observable<BaseResult<Data>> LeaveMessageWhetherLook(@Field("OrderID") String OrderID,
                                                                            @Field("factoryIslook") String factoryIslook,
                                                                            @Field("workerIslook") String workerIslook,
                                                                            @Field("platformIslook") String platformIslook

    );

    /*
     * 留言消息
     * 1  师傅
     * 2 工厂
     * 3 平台
     * */
    @FormUrlEncoded
    @POST("LeaveMessage/GetNewsLeaveMessage")
    Observable<BaseResult<Data<LeaveMessage>>> GetNewsLeaveMessage(@Field("UserID") String UserID,
                                                                   @Field("Type") String Type,
                                                                   @Field("limit") String limit,
                                                                   @Field("page") String page

    );
}
