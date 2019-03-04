package com.zhenhaikj.factoryside.mvp;

import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Area;
import com.zhenhaikj.factoryside.mvp.bean.Brand;
import com.zhenhaikj.factoryside.mvp.bean.Category;
import com.zhenhaikj.factoryside.mvp.bean.City;
import com.zhenhaikj.factoryside.mvp.bean.Course;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.District;
import com.zhenhaikj.factoryside.mvp.bean.HomeData;
import com.zhenhaikj.factoryside.mvp.bean.Product;
import com.zhenhaikj.factoryside.mvp.bean.ProductType;
import com.zhenhaikj.factoryside.mvp.bean.Province;
import com.zhenhaikj.factoryside.mvp.bean.UserInfo;
import com.zhenhaikj.factoryside.mvp.bean.WorkOrder;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("Account/Reg")
    Observable<BaseResult<Data<String>>> Reg(@Field("mobile") String mobile, @Field("type") String type, @Field("code") String code, @Field("roleType") String roleType);

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
    Observable<BaseResult<Data<String>>> LoginOn(@Field("userName") String userName, @Field("passWord") String passWord,@Field("roleType") String roleType);

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
     */
    @FormUrlEncoded
    @POST("Order/GetOrderInfo")
    Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(@Field("OrderID") String OrderID);

    /**
     * 添加品牌
     */
    @FormUrlEncoded
    @POST("FactoryConfig/AddFactoryBrand")
    Observable<BaseResult<Data>> AddFactoryBrand(@Field("UserID") String UserID, @Field("FBrandName") String FBrandName);

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
    Observable<BaseResult<Data<List<Category>>>> GetFactoryCategory(@Field("ParentID") String ParentID);

//    @FormUrlEncoded
//    @POST("FactoryConfig/GetFactoryCategory")
//    Observable<BaseResult<Data<List<ProductType>>>> GetCategory(@Field("ParentID") String ParentID);

    /**
     * 获取子分类
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryCategory")
    Observable<BaseResult<Data<List<Category>>>> GetChildFactoryCategory(@Field("ParentID") String ParentID);

    /**
     * 获取型号
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryProducttype")
    Observable<BaseResult<Data<List<ProductType>>>> GetFactoryProducttype(@Field("FBrandID") String FBrandID, @Field("FCategoryID") String FCategoryID);


    @POST("FactoryConfig/GetFactoryProducttype")
    Observable<BaseResult<Data<List<ProductType>>>> GetProducttype();


    /*
     *删除工厂产品型号
     * */
    @FormUrlEncoded
    @POST("FactoryConfig/DeleteFactoryProducttype")
    Observable<BaseResult<Data<List<ProductType>>>> DeleteFactoryProducttype(@Field("FProductTypeID") String FProductTypeID);

    /**
     * 获取属性
     */
    @FormUrlEncoded
    @POST("FactoryConfig/GetFactoryAccessory")
    Observable<BaseResult<Accessory>> GetFactoryAccessory(@Field("FProductTypeID") String FProductTypeID);

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
     */
    @FormUrlEncoded
    @POST("Order/AddOrder")
    Observable<BaseResult<Data<String>>> AddOrder(@Field("TypeID") String TypeID,
                                                  @Field("TypeName") String TypeName,
                                                  @Field("UserID") String UserID,
                                                  @Field("BrandID") String FBrandID,
                                                  @Field("BrandName") String BrandName,
                                                  @Field("CategoryID") String FCategoryID,
                                                  @Field("CategoryName") String CategoryName,
                                                  @Field("SubCategoryID") String SubCategoryID,
                                                  @Field("SubCategoryName") String SubCategoryName,
                                                  @Field("ProductTypeID") String FProductTypeID,
                                                  @Field("ProductType") String ProductType,
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
                                                  @Field("AccessorySendState") String AccessorySendState,
                                                  @Field("Extra") String Extra,
                                                  @Field("ExtraTime") String ExtraTime,
                                                  @Field("ExtraFee") String ExtraFee,
                                                  @Field("Num") String Num);


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
                                                         @Field("ProductTypeID") String FProductTypeID,
                                                         @Field("ProductType") String ProductType,
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
     * 获取首页数据
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<BaseResult<HomeData>> getHome(@Field("userid") String userid);

    /**
     * 获取学院数据
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<BaseResult<List<Course>>> getCourse(@Field("pageindex") String pageindex, @Field("pagesize") String pagesize);

    /**
     * 获取采购数据
     */
    @POST("api.php")
    Observable<BaseResult<List<Category>>> getCategory();

    /**
     * 添加到购物车
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<BaseResult> addCart(
            @Field("userid") String userid,
            @Field("productid") String productid,
            @Field("option") String option,
            @Field("num") String num
    );

    /**
     * 获取产品列表
     */
    @FormUrlEncoded
    @POST("api.php")
    Observable<BaseResult<List<Product>>> getProduct(
            @Field("categoryid") String categoryid,
            @Field("pageindex") String pageindex,
            @Field("pagesize") String pagesize
    );

    /*@GET("/app/index/cartList")
    Observable<BaseResult<List<CartBean>>> cartList(@Query("userId") String userId);

    //发送短信验证码
    @GET("smsSend")
    Observable<BaseResult> smsSend(@Query("phone") String phone, @Query("type") String type);

    //app用户注册
    @GET("register")
    Observable<BaseResult> register(@Query("phone") String phone, @Query("pwd") String pwd, @Query("code") String code);

    //app用户登录
    @GET("login")
    Observable<BaseResult<User>> login(@Query("userName") String phone, @Query("pwd") String pwd);

    //忘记密码
    @GET("/app/againRegister")
    Observable<BaseResult> againRegister(@Query("phone") String phone, @Query("code") String code, @Query("pwd") String pwd);

    //修改密码
    @FormUrlEncoded
    @POST("updatePwd")
    Observable<BaseResult> updatePwd(@Field("userId") String userId, @Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd);

    //updatePhone //修改绑定手机号
    @FormUrlEncoded
    @POST("updatePhone")
    Observable<BaseResult>updatePhone(@Field("userId") String userId, @Field("phone") String phone, @Field("code") String code);

    //修改头像
    @POST("user/updateUserHead")
    Observable<BaseResult> updateUserHead(@Body MultipartBody multipartBody);

    //修改个人信息
    @FormUrlEncoded
    @POST("user/updateUserInfo")
    Observable<BaseResult> updateUserInfo(@Field("id") String id, @Field("nickName") String nickName, @Field("sex") String sex);

    @FormUrlEncoded
    @POST("user/addAddress")
    Observable<BaseResult> addAddress(@Field("accountId") String userId, @Field("name") String ressName,
                                      @Field("province") String province, @Field("city") String city,
                                      @Field("county") String county, @Field("address") String address,
                                      @Field("postalCode") String postCode,
                                      @Field("phone") String phoneNumber, @Field("status") String status);

    // 修改收货地址信息 /app/user/updateAddress
    @FormUrlEncoded
    @POST("user/updateAddress")
    Observable<BaseResult> updateAddress(@Field("id") String id, @Field("name") String ressName,
                                         @Field("province") String province, @Field("city") String city,
                                         @Field("county") String county, @Field("address") String address,
                                         @Field("postalCode") String phone,
                                         @Field("phone") String phoneNumber, @Field("status") String status);

    //user/getMineCoupons  我的优惠券列表
    @GET("user/getMineCoupons")
    Observable<BaseResult<List<Coupon>>> getMineCoupons(@Query("accountId") String accountId);

    //user/getMineAddress 收货地址列表
    @GET("user/getMineAddress")
    Observable<BaseResult<List<Address>>> getMineAddress(@Query("accountId") String accountId);

    //user/deleteAddress 删除收货地址
    @FormUrlEncoded
    @POST("user/deleteAddress")
    Observable<BaseResult> deleteAddress(@Field("id") String id);

    // cart/getCarts  购物车列表
    @GET("cart/getCarts")
    Observable<BaseResult<CartBean>> getCarts(@Query("accountId") String accountId);

    // cart/updateCartNum  修改商品的数量
    @GET("cart/updateCartNum")
    Observable<BaseResult> updateCartNum(@Query("id") String id, @Query("num") int num);

    // cart/calCarts
    @GET("cart/calCarts")
    Observable<BaseResult<String>> calCarts(@Query("ids") String ids);

    // order/cartSettlement 购物车结算
    @GET("order/cartSettlement")
    Observable<BaseResult<CartSettlement>> cartSettlement(@Query("accountId") String accountId, @Query("ids") String ids, @Query("addressId") String addressId);

    //order/selectUseCoupons
    @GET("order/selectUseCoupons")
    Observable<BaseResult<List<Coupon>>> selectUseCoupons(@Query("accountId") String accountId, @Query("userCouponIds") String userCouponIds, @Query("goodId") String goodId, @Query("totalMoney") String totalMoney, @Query("companyId") String companyId);

    // cart/deleteByIds
    @GET("cart/deleteByIds")
    Observable<BaseResult> deleteByIds(@Query("ids") String ids);

    //order/buyNowForSettlement 立即购买去结算
    @FormUrlEncoded
    @POST("order/buyNowForSettlement")
    Observable<BaseResult<CommitOrder>> buyNowForSettlement(@Field("accountId") String accountId, @Field("goodsId") String goodsId,
                                                            @Field("num") String num, @Field("skuId") String skuId, @Field("status") String status,
                                                            @Field("addressId") String addressId);

    //立即购买生成订单
    @GET("order/buyNowForApp")
    Observable<BaseResult<ConfirOrder>> buyNowForApp(@Query("accountId") String accountId, @Query("goodsId") String goodsId,
                                                     @Query("num") String num, @Query("skuId") String skuId, @Query("status") String status,
                                                     @Query("addressId") String addressId, @Query("couponId") String couponId);

    //order/cartForOrder
    @GET("order/cartForOrder")
    Observable<BaseResult<ConfirOrder>> cartForOrder(@Query("accountId") String accountId, @Query("ids") String ids, @Query("addressId") String addressId);

    //pay/goAlipayForApp 支付宝支付
    @GET("pay/goAlipayForApp")
    Observable<BaseResult<String>> goAlipayForApp(@Query("status") String status, @Query("orderId") String orderId);

    // user/getMineFootprints 足迹列表
    @GET("user/getMineFootprints")
    Observable<BaseResult<List<ZujiBean>>> getMineFootprints(@Query("accountId") String accountId);

    // collection/getCollectionList 收藏商品//企业
    @GET("collection/getCollectionList")
    Observable<BaseResult<List<Company>>> getCompanyList(@Query("accountId") String accountId, @Query("type") String type);

    // collection/getCollectionList 收藏商品//企业
    @GET("collection/getCollectionList")
    Observable<BaseResult<List<Collection>>> getCollectionList(@Query("accountId") String accountId, @Query("type") String type);

    @GET("collection/cancelCollection")
    Observable<BaseResult> cancelCollection(@Query("id") String id);

    @GET("mineOrder/getOtherAllOrders")
    Observable<BaseResult<PageModel<SmallOrder>>> getOtherAllOrders(@Query("accountId") String accountId, @Query("status") String status, @Query("page") String page, @Query("size") String size);

    @GET("mineOrder/orderInfo")
//获取订单
    Observable<BaseResult<WorkOrder>> orderInfo(@Query("accountId") String accountId);

    // mineOrder/orderDetailsInfo 订单详情
    @GET("mineOrder/orderDetailsInfo")
    Observable<BaseResult<OrderDetail>> orderDetailsInfo(@Query("id") String id);

    // mineOrder/deleteOrder
    @GET("mineOrder/deleteOrder")
    Observable<BaseResult> deleteOrder(@Query("orderId") String orderId);

    // mineOrder/cancelOrder
    @GET("mineOrder/cancelOrder")
    Observable<BaseResult> cancelOrder(@Query("orderId") String orderId);

    @GET("pay/balanceForApp")
//余额支付
    Observable<BaseResult> balanceForApp(@Query("accountId") String accountId, @Query("code") String code, @Query("orderId") String orderId);

    //支付宝支付
    @GET("pay/payForApp")
    Observable<BaseResult<String>> aliPayForApp(@Query("status") String status, @Query("orderId") String orderId);

    //微信支付
    @GET("pay/payForApp")
    Observable<BaseResult<PayReq>> weixinPayForApp(@Query("status") String status, @Query("orderId") String orderId);

    @GET("user/myCollage")
//我的拼团
    Observable<BaseResult<List<SmallOrder>>> myCollage(@Query("accountId") String accountId);

    @GET("user/getBalance")
//查询余额
    Observable<BaseResult<String>> getBalance(@Query("accountId") String accountId);

    @GET("mineOrder/qrsh")
    Observable<BaseResult> qrsh(@Query("odId") String odId);

    //获取物流信息
    @GET("mineOrder/getOrderTracesByJson")
    Observable<BaseResult<String>> getOrderTracesByJson(@Query("odId") String odId);

    @POST("mineOrder/saveCommentScore")
    Observable<BaseResult> saveCommentScore(@Body MultipartBody multipartBody);

    @FormUrlEncoded
    @POST("mineOrder/afterSaleApply")
    Observable<BaseResult> afterSaleApply(@Field("odId") String odId, @Field("refundStatus") String refundStatus, @Field("refundReason") String refundReason);

    @GET("mineOrder/getMinePreOrder")
    Observable<BaseResult<List<MyAdvanceOrder>>> getMinePreOrder(@Query("accountId") String accountId);

    @GET("quickLogin")
    Observable<BaseResult<User>> quickLogin(@Query("openId") String openId, @Query("type") String type);

    @GET("registerForQqOrWx")
    Observable<BaseResult<User>> registerForQqOrWx(@Query("openId") String openId, @Query("type") String type, @Query("phone") String phone, @Query("pwd") String pwd, @Query("code") String code, @Query("otherName") String otherName);

    @GET("pay/getOrderMoney")
//获取订单支付金额
    Observable<BaseResult<NeedPay>> getOrderMoney(@Query("orderId") String orderId);

    @GET("user/putForward")
//提现
    Observable<BaseResult<String>> putForward(@Query("accountId") String accountId, @Query("money") String money, @Query("code") String code);

    @GET("bindingQqOrWx")
//绑定
    Observable<BaseResult> bindingQqOrWx(@Query("openId") String openId, @Query("type") String type, @Query("accountId") String accountId, @Query("otherName") String otherName);

    @GET("unbindQqOrWx")
//解除绑定
    Observable<BaseResult> unbindQqOrWx(@Query("type") String type, @Query("accountId") String accountId);

    //绑定支付宝
    @FormUrlEncoded
    @POST("user/bindAlipay")
    Observable<BaseResult> bindAlipay(@Field("accountId") String accountId, @Field("alipayId") String alipayId, @Field("alipayName") String alipayName);

    //user/modifyAlipay 修改支付宝
    @FormUrlEncoded
    @POST("user/modifyAlipay")
    Observable<BaseResult> modifyAlipay(@Field("accountId") String accountId, @Field("alipayId") String alipayId, @Field("alipayName") String alipayName, @Field("reason") String reason);

    @GET("goodDetails/invitationCollage")
    Observable<BaseResult<String>> invitationCollage(@Query("goodsId") String goodsId);

    @GET("version/checkOne")
    Observable<BaseResult<Version>> checkOne();*/
}
