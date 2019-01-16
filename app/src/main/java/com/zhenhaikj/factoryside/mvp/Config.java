package com.zhenhaikj.factoryside.mvp;

import com.blankj.utilcode.util.SPUtils;
import com.zhenhaikj.factoryside.mvp.utils.MyUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2018/4/18.
 */
public class Config {
    public static boolean IS_DEBUG = true;

    //47.104.175.133:80
//    public static final String HTTP_URL = "http://192.168.2.132:8080/app/";
//    public static final String BASE_URL = "http://192.168.2.132:8080/";
//    public static final String URL = "http://192.168.2.132:8080/app/";
    public static final String HTTP_URL = "https://www.jbshch.com/app/";
    public static final String BASE_URL = "https://www.jbshch.com/";
//    public static final String URL = "https://www.jbshch.com/app/";
//    public static final String URL = "https://api.emjiayuan.com/";//正式服
//    public static final String URL = "http://emapi.jb.emjiayuan.com/";//测试服
    public static final String URL = "http://47.96.126.145:8001/api/";//测试服

    public static final String SAVE_CITY_KEY = "save_city_key";

    //测试用服务器
//    public static final String BASE_URL = "http://onlygx.oicp.io/";
//    public static final String URL = "http://onlygx.oicp.io/app/";

    public static final String APP_ID = "wxae66509b894c60cf";


    static HttpLoggingInterceptor loggingInterceptor;
    private static SPUtils spUtils;

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        if (null == loggingInterceptor) {
            loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    MyUtils.e("okhttp", message );
                }
            });
        }
        if (IS_DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return loggingInterceptor;
    }

    static Interceptor interceptor;

    public static Interceptor getInterceptor() {
//        if (null == interceptor) {
            interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request.Builder builder=chain.request().newBuilder();
                    return   chain.proceed(builder
                            .addHeader("userName", spUtils.getString("userName"))
                            .addHeader("adminToken", spUtils.getString("adminToken"))
                            .build());
                }
            };
//        }
        return interceptor;
    }

    static OkHttpClient client;

    public static OkHttpClient getClient() {
//        if (null == client) {
        spUtils = SPUtils.getInstance("token");
        if (spUtils.getString("userName")==null){
            client = new OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .build();
        }else{
            client = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor())
                    .addInterceptor(getLoggingInterceptor())
                    .build();
        }

//        }
        return client;
    }
}
