package com.zhenhaikj.factoryside.mvp.utils;

import android.util.Log;

import com.zhenhaikj.factoryside.mvp.Global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MyOkHttp {
    public static Call GetCall(String method, FormBody.Builder formBody){
        final String result=null;
        String time=Long.toString(System.currentTimeMillis());
        time=time.substring(0,time.length()-3);
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        HashMap<String,String> map=new HashMap();
        map.put("method",method);
        map.put("platform","APP_ANDROID");
        map.put("ip","");
        map.put("protocol-version","1.0");
        map.put("device-no", Global.device_no);
        map.put("client-version","");
        map.put("ios-apnstoken",Global.token);
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
        Log.d("------------",text);
        Log.d("------------",MyUtils.md5(text));

        /*FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("username","119");//传递键值对参数
        formBody.add("password","110");//传递键值对参数*/
        //创建一个Request
        final Request request = new Request.Builder()
//                .url("https://api.emjiayuan.com/api.php")//正式服
                .url("http://emapi.jb.emjiayuan.com/api.php")//测试服
                .post(formBody.build())
                .header("User-Agent", "OkHttp Headers.java")
                .header("sign",MyUtils.md5(text))
                .header("method",method)
                .header("platform","APP_ANDROID")
//                .header("app-key","2ba11b3facf96f1bff8ca4c8ca11b03e")
                .header("ip","")
                .header("protocol-version","1.0")
                .header("device-no",Global.device_no)
                .header("client-version","")
                .header("ios-apnstoken",Global.token)
                .header("time",time)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        /*call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("------------",response.body().string());
//                        Log.d("------------",MyUtils.decodeUnicode(response.body().string()));
                Log.d("------------",response.headers().toString());
//                        MyUtils.showToast(LoginActivity.this, response.body().string());
            }
        });*/
        return call;
    }

}
