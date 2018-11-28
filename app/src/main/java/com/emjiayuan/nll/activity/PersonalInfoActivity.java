package com.emjiayuan.nll.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.model.UserInfo;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.yalantis.ucrop.UCrop;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.ll_avatar)
    LinearLayout mLlAvatar;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.iv_positive_card)
    ImageView mIvPositiveCard;
    @BindView(R.id.iv_negative_card)
    ImageView mIvNegativeCard;
    @BindView(R.id.btn_next)
    Button mBtnNext;
    @BindView(R.id.delete1)
    ImageView mDelete1;
    @BindView(R.id.delete2)
    ImageView mDelete2;
    private PopupWindow mPopupWindow;
    private String FilePath;
    private ArrayList<Object> permissions;
    private String mName;
    private String mPhone;
    private String userid;
    private String headimg="";
    private String idcardjust="";
    private String idcardback="";

    private UserInfo mUserInfo;
    private String token;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initData() {
        mUserInfo= (UserInfo) getIntent().getSerializableExtra("userInfo");
        if (mUserInfo != null) {
            headimg=mUserInfo.getHeadimg();
            idcardjust=mUserInfo.getIdcard_just();
            idcardback=mUserInfo.getIdcard_back();
            Glide.with(mActivity).load(headimg).into(mIvAvatar);
            mEtName.setText(mUserInfo.getTruename());
            mEtPhone.setText(mUserInfo.getPhone());
            Glide.with(mActivity).load(idcardjust).into(mIvPositiveCard);
            Glide.with(mActivity).load(idcardback).into(mIvNegativeCard);
            mDelete1.setVisibility(View.VISIBLE);
            mDelete2.setVisibility(View.VISIBLE);
        }
        getQnToken();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mIconSearch.setOnClickListener(this);
        mLlAvatar.setOnClickListener(this);
        mIvPositiveCard.setOnClickListener(this);
        mIvNegativeCard.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mDelete1.setOnClickListener(this);
        mDelete2.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.delete1:
                idcardjust="";
                Glide.with(mActivity).load(R.drawable.positive_card).into(mIvPositiveCard);
                mDelete1.setVisibility(View.GONE);
                break;
            case R.id.delete2:
                idcardback="";
                Glide.with(mActivity).load(R.drawable.negative_card).into(mIvNegativeCard);
                mDelete2.setVisibility(View.GONE);
                break;
            case R.id.ll_avatar:
                if (requestPermissions()) {
                    showPopupWindow(101, 102);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10001);
                }
                break;
            case R.id.iv_positive_card:
                if (requestPermissions()) {
                    showPopupWindow(201, 202);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10002);
                }
                break;
            case R.id.iv_negative_card:
                if (requestPermissions()) {
                    showPopupWindow(301, 302);
                } else {
                    requestPermissions(permissions.toArray(new String[permissions.size()]), 10003);
                }
                break;
            case R.id.btn_next:
                mName = mEtName.getText().toString().trim();
                mPhone = mEtPhone.getText().toString().trim();
                if ("".equals(headimg)) {
                    MyUtils.showToast(mActivity, "请补全头像！");
                    return;
                }
                if ("".equals(mName)) {
                    MyUtils.showToast(mActivity, "请输入您的姓名！");
                    return;
                }
                if ("".equals(mPhone)) {
                    MyUtils.showToast(mActivity, "请输入您的手机号！");
                    return;
                }
                if ("".equals(idcardjust)) {
                    MyUtils.showToast(mActivity, "请添加身份证正面！");
                    return;
                }
                if ("".equals(idcardback)) {
                    MyUtils.showToast(mActivity, "请添加身份证反面！");
                    return;
                }
                addOrUpdateUserInfo();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10001:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许
                    showPopupWindow(101, 102);
                } else {
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10002:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许
                    showPopupWindow(201, 202);
                } else {
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
            case 10003:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许
                    showPopupWindow(301, 302);
                } else {
                    MyUtils.showToast(mActivity, "相关权限未开启");
                }
                break;
        }
    }

    /**
     * 弹出Popupwindow
     */
    public void showPopupWindow(final int code1, final int code2) {
        View popupWindow_view = LayoutInflater.from(mActivity).inflate(R.layout.pop_photopicker, null);
        TextView camera = popupWindow_view.findViewById(R.id.camera);
        TextView gallery = popupWindow_view.findViewById(R.id.gallery);
        TextView cancel = popupWindow_view.findViewById(R.id.cancel);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// 指定开启系统相机的Action
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                String f = System.currentTimeMillis() + ".jpg";
                String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/nll";
                FilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/nll/" + f;
                File dirfile = new File(fileDir);
                if (!dirfile.exists()) {
                    dirfile.mkdirs();
                }
                File file = new File(FilePath);
                Uri fileUri;
                if (Build.VERSION.SDK_INT >= 24) {
                    fileUri = FileProvider.getUriForFile(mActivity, "com.emjiayuan.nll.fileProvider", file);
                } else {
                    fileUri = Uri.fromFile(file);
                }

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, code1);
                mPopupWindow.dismiss();
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "test"), code2);
                mPopupWindow.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                MyUtils.setWindowAlpa(mActivity, false);
            }
        });
        if (mPopupWindow != null && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(popupWindow_view, Gravity.BOTTOM, 0, 0);
        }
        MyUtils.setWindowAlpa(mActivity, true);
    }

    private void startCrop(Uri sourceUri) {
//        Uri sourceUri = Uri.parse("http://star.xiziwang.net/uploads/allimg/140512/19_140512150412_1.jpg");
        //裁剪后保存到文件中
        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "SampleCropImage.png"));
        UCrop.of(sourceUri, destinationUri).withAspectRatio(1, 1).withMaxResultSize(300, 300).start(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 101:
                    File file = new File(FilePath);
                    startCrop(Uri.fromFile(file));
                    break;
                case 102:
                    if (data != null) {
                        Uri uri = data.getData();
                        startCrop(uri);
                    }
                    break;
                case 201:
                    Glide.with(mActivity).load(FilePath).into(mIvPositiveCard);
                    mDelete1.setVisibility(View.VISIBLE);
                    Bitmap bitmap = BitmapFactory.decodeFile(FilePath);
                    if (bitmap == null) {
                        return;
                    }
                    uploadimg(FilePath,1);
                    break;
                case 202:
                    if (data != null) {
                        Uri uri = data.getData();
                        Glide.with(mActivity).load(uri).into(mIvPositiveCard);
                        mDelete1.setVisibility(View.VISIBLE);
                        uploadimg(MyUtils.getRealPathFromUri(mActivity,uri),1);
                    }
                    break;
                case 301:
                    Glide.with(mActivity).load(FilePath).into(mIvNegativeCard);
                    mDelete2.setVisibility(View.VISIBLE);
                    uploadimg(FilePath,2);
                    break;
                case 302:
                    if (data != null) {
                        Uri uri = data.getData();
                        Glide.with(mActivity).load(uri).into(mIvNegativeCard);
                        mDelete2.setVisibility(View.VISIBLE);
                        uploadimg(MyUtils.getRealPathFromUri(mActivity,uri),2);
                    }
                    break;
                case UCrop.REQUEST_CROP:
                    if (data != null) {
                        Uri croppedFileUri = UCrop.getOutput(data);
                        //获取默认的下载目录
                        String downloadsDirectoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                        String filename = String.format("%d_%s", Calendar.getInstance().getTimeInMillis(), croppedFileUri.getLastPathSegment());
                        uploadimg(croppedFileUri.getPath(),0);
                        File saveFile = new File(downloadsDirectoryPath, filename);
                        //保存下载的图片
                        FileInputStream inStream = null;
                        FileOutputStream outStream = null;
                        FileChannel inChannel = null;
                        FileChannel outChannel = null;
                        try {
                            inStream = new FileInputStream(new File(croppedFileUri.getPath()));
                            outStream = new FileOutputStream(saveFile);
                            inChannel = inStream.getChannel();
                            outChannel = outStream.getChannel();
                            inChannel.transferTo(0, inChannel.size(), outChannel);
                            Glide.with(mActivity).load(saveFile).into(mIvAvatar);
//                        Toast.makeText(this, "裁切后的图片保存在：" + saveFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                outChannel.close();
                                outStream.close();
                                inChannel.close();
                                inStream.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
            }
        }
    }

    //请求权限
    private boolean requestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            permissions = new ArrayList<>();
//            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//            }
//            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            }
            if (mActivity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }

            if (permissions.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
    /**
     * 七牛token
     */
    public void getQnToken() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
//new call
        Call call = MyOkHttp.GetCall("system.getQnToken", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                MyUtils.e("------七牛token------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void addOrUpdateUserInfo(){
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());//传递键值对参数
        formBody.add("headimg", headimg);//传递键值对参数
        formBody.add("truename", mName);//传递键值对参数
        formBody.add("phone", mPhone);//传递键值对参数
        formBody.add("idcardjust", idcardjust);//传递键值对参数
        formBody.add("idcardback", idcardback);//传递键值对参数
//        formBody.add("status", "1");//传递键值对参数
//new call
        Call call = MyOkHttp.GetCall("User.addOrUpdateUserInfo", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyUtils.e("-----注册结果-------", result);
                Message message = new Message();
                message.what = 3;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void uploadImgFor64(String imageString, final int what) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("imagedata", imageString);

//new call
        Call call = MyOkHttp.GetCall("system.uploadImgFor64", formBody);
//请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("------------", e.toString());
//                        myHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyUtils.e("------上传图片------", result);
                Message message = new Message();
                message.what = what;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            switch (msg.what) {
                case 0:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONObject jsonObject1 = new JSONObject(data);
                            token = jsonObject1.getString("token");
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            Intent intent=new Intent(mActivity,ShopInfoActivity.class);
                            if (mUserInfo!=null){
                                intent.putExtra("userInfo",mUserInfo);
                            }
                            startActivity(intent);
                            finish();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    };
    /*public void uploadimg(String filePath,int what){

        try {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            if (bitmap == null) {
                return;
            }
            //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
            //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
            uploadImgFor64("data:image/png;base64," + imageString,what);
        } catch (Exception e) {
            e.printStackTrace();
       } finally {
        }
    }*/

    public void uploadimg(String filepath, final int what){
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
//                .recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
//                .recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();

// 重用uploadManager。一般地，只需要创建一个uploadManager对象
//        data = <File对象、或 文件路径、或 字节数组>
//        String key = <指定七牛服务上的文件名，或 null>;
//        String token = <从服务端SDK获取>;
        UploadManager uploadManager = new UploadManager(config);
                uploadManager.put(filepath, "upload_file/app/"+MyUtils.getRandomFileName(), token, new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo info, JSONObject res) {
                                //res包含hash、key等信息，具体字段取决于上传策略的设置
                                if(info.isOK()) {
                                    switch (what){
                                        case 0:
                                            headimg="http://qiniu.emjiayuan.com/"+key;
                                            break;
                                        case 1:
                                            idcardjust="http://qiniu.emjiayuan.com/"+key;
                                            break;
                                        case 2:
                                            idcardback="http://qiniu.emjiayuan.com/"+key;
                                            break;
                                    }
                                } else {
                                    MyUtils.showToast(mActivity,"图片上传失败！");
                                    Log.i("qiniu", "Upload Fail");
                                    //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                                }
                                Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                            }
                        },
                        new UploadOptions(null, null, false,
                                new UpProgressHandler(){
                                    public void progress(String key, double percent){
                                        MyUtils.e("上传进度啊", key + ": " + percent);
//                                        pd.setProgress((int) (percent*100));
                                    }
                                }, null));
    }

}