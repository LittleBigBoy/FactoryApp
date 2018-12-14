package com.emjiayuan.nll;

import com.emjiayuan.nll.base.BaseResult;
import com.emjiayuan.nll.model.Category;
import com.emjiayuan.nll.model.Course;
import com.emjiayuan.nll.model.HomeData;
import com.emjiayuan.nll.model.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
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
    Observable<BaseResult<Order>> orderInfo(@Query("accountId") String accountId);

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
