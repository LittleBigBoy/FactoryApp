package com.emjiayuan.nll;

import com.emjiayuan.nll.utils.CEComplexComparator;
import com.emjiayuan.nll.utils.MyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
    public static final String URL = "http://emapi.jb.emjiayuan.com/";//测试服

    public static final String SAVE_CITY_KEY = "save_city_key";

    //测试用服务器
//    public static final String BASE_URL = "http://onlygx.oicp.io/";
//    public static final String URL = "http://onlygx.oicp.io/app/";

    public static final String APP_ID = "wxae66509b894c60cf";


    static HttpLoggingInterceptor loggingInterceptor;

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

    public static Interceptor getInterceptor(final String method) {
//        if (null == interceptor) {
            interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder builder=chain.request().newBuilder();
                    String time=Long.toString(System.currentTimeMillis());
                    time=time.substring(0,time.length()-3);
                    HashMap<String,String> map=new HashMap();
                    map.put("method",method);
                    map.put("platform","APP_ANDROID");
                    map.put("ip","");
                    map.put("protocol-version","1.0");
                    map.put("device-no", "");
                    map.put("client-version","");
                    map.put("ios-apnstoken","");
                    map.put("time",time);
                    ArrayList<String> list=new ArrayList<String>();
                    for (String key:map.keySet()){
                        list.add(key);
                    }
                    CEComplexComparator  com=new CEComplexComparator();
                    Collections.sort(list, com);
                    String text="";
                    for(String i:list){
                        text+=i+"="+map.get(i)+"&";
                    }
                    text=text.substring(0,text.lastIndexOf("&"));
                    text+="2ba11b3facf96f1bff8ca4c8ca11b03e";
                    return   chain.proceed(builder.addHeader("User-Agent", "OkHttp Headers.java")
                            .addHeader("sign",MyUtils.md5(text))
                            .addHeader("method",method)
                            .addHeader("platform","APP_ANDROID")
                            .addHeader("ip","")
                            .addHeader("protocol-version","1.0")
                            .addHeader("device-no","")
                            .addHeader("client-version","")
                            .addHeader("ios-apnstoken","")
                            .addHeader("time",time).build());
                }
            };
//        }
        return interceptor;
    }

    static OkHttpClient client;

    public static OkHttpClient getClient(String method) {
//        if (null == client) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(getInterceptor(method))
                    .addInterceptor(getLoggingInterceptor())
                    .build();
//        }
        return client;
    }
}
