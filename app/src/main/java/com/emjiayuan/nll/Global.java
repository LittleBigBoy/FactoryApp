package com.emjiayuan.nll;

import com.emjiayuan.nll.model.LoginResult;
import com.emjiayuan.nll.model.News;
import com.emjiayuan.nll.model.Product;

import java.util.ArrayList;
import java.util.List;

import cn.addapp.pickers.entity.Province;

public class Global {
//    public static LoginResult loginResult;
    public static LoginResult loginResult=new LoginResult("1");
//    public static UserInfo mUserInfo=new UserInfo();
    public static String location_name="";
    public static String provinceid="0";
    public static String device_no="";
    public static String token="";
    public static ArrayList<Product> list;
    public static List<Product> historylist=new ArrayList<>();
    public static ArrayList<Province> datas;
    public static List<String> images;
    public static ArrayList<News> news;
}
