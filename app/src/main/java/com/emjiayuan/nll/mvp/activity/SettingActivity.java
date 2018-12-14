package com.emjiayuan.nll.mvp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseActivity;
import com.emjiayuan.nll.event.UpdateEvent;
import com.emjiayuan.nll.model.LoginResult;
import com.emjiayuan.nll.model.UserInfo;
import com.emjiayuan.nll.utils.DownLoadManager;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.emjiayuan.nll.utils.SpUtils;
import com.google.gson.Gson;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import butterknife.BindView;
import me.iwf.photopicker.PhotoPicker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tx)
    ImageView mTx;
    @BindView(R.id.username)
    TextView mUsername;
    @BindView(R.id.pwd_ll)
    LinearLayout mPwdLl;
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.version)
    TextView mVersion;
    @BindView(R.id.update_ll)
    LinearLayout mUpdateLl;
    @BindView(R.id.login_out)
    Button mLoginOut;
    private String imagepath;
    private String nicknames;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("设置");
        mTvTitle.setVisibility(View.VISIBLE);
        mIconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
        mPwdLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                startActivity(new Intent(mActivity, CgpwdActivity.class));
            }
        });
        mUpdateLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                getAppVersion();
            }
        });
        user();
    }

    public void user() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());

//new call
        Call call = MyOkHttp.GetCall("User.getUserInfo", formBody);
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
                MyUtils.e("------获取用户信息------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void editUserInfo() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        if (imagepath != null) {
            formBody.add("headimg", imagepath);
        }
//new call
        Call call = MyOkHttp.GetCall("user.editUserInfo", formBody);
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
                MyUtils.e("------修改用户信息------", result);
                Message message = new Message();
                message.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void getAppVersion() {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("versionno", Integer.toString(MyUtils.getAppVersionCode(mActivity)));
//new call
        Call call = MyOkHttp.GetCall("system.getAppVersion", formBody);
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
                MyUtils.e("------检查更新------", result);
                Message message = new Message();
                message.what = 3;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }

    public void setData() {
        Glide.with(mActivity).load(mUser.getHeadimg()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.default_tx).error(R.drawable.default_tx)).into(mTx);
        mUsername.setText(mUser.getPhone());
        mVersion.setText(MyUtils.getAppVersionName(mActivity));
    }

    private UserInfo mUser;
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
                            mUser = gson.fromJson(data, LoginResult.class).getInfo();
                            setData();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            JSONObject jsonObject1 = new JSONObject(data);
                            imagepath = jsonObject1.getString("imgurl");
                            editUserInfo();
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        Gson gson = new Gson();
                        if ("200".equals(code)) {
                            MyUtils.showToast(mActivity, message);
                            user();
                            EventBus.getDefault().post(new UpdateEvent(""));
                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
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
                            JSONObject jsonObject1 = new JSONObject(data);

                            String is_update = jsonObject1.getString("is_update");
                            final String app_download_url = jsonObject1.getString("download_url");
//                            String appcode = "234";
//                            final String app_download_url = "http://pz0.3dn.mse.sogou.com/semob5.13.5_154189_R14189_121106002_build47127_2.1.0.2133.apk";
                            if ("1".equals(is_update)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                                builder.setTitle("更新");
                                builder.setMessage("检测到有更新,是否立刻更新？");
                                builder.setNegativeButton("稍后更新", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                builder.setPositiveButton("立刻更新", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (MyUtils.isWifi(mActivity)) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                                            builder.setTitle("提示");
                                            builder.setMessage("您当前正在使用移动网络，继续下载将消耗流量");
                                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });
                                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    downLoadApk(app_download_url);
                                                }
                                            });
                                            builder.create().show();
                                        } else {
                                            downLoadApk(app_download_url);
                                        }
                                    }
                                });
                                builder.create().show();
                            } else {
                                MyUtils.showToast(mActivity, "当前已是最新版本");
                            }


                        } else {
                            MyUtils.showToast(mActivity, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;


            }
        }
    };

    /*
     * 从服务器中下载APK
     */
    protected void downLoadApk(final String url) {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = DownLoadManager.getFileFromServer(url, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
//                    Toast.makeText(mActivity, "下载失败!",
//                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        //判断是否是AndroidN以及更高的版本

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            Uri contentUri = FileProvider.getUriForFile(mActivity, "com.emjiayuan.nll.fileProvider", file);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");

        } else {

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");

        }
        startActivity(intent);
    }

    @Override
    protected void setListener() {
        mLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                Global.loginResult = null;
                SpUtils.putObject(mActivity, "loginResult", Global.loginResult);
                Intent intent=new Intent(mActivity, LoginActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //登出
                Toast.makeText(mActivity, "退出成功！", Toast.LENGTH_SHORT).show();
            }
        });
        mTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "test"), PhotoPicker.REQUEST_CODE);
//                PhotoPicker.builder()
//                        .setPhotoCount(1)
//                        .start(SettingActivity.this);
            }
        });
        mNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MyUtils.isFastClick()) {
                    return;
                }
                View vw = LayoutInflater.from(mActivity).inflate(R.layout.edit_layout, null);
                final EditText et = vw.findViewById(R.id.et);
                et.setText(mUser.getNickname());
                et.setSelection(et.getText().length());
                new AlertDialog.Builder(mActivity).setTitle("修改昵称")
                        .setView(vw)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                nicknames = et.getText().toString();
                                editUserInfo();
//                                Toast.makeText(getApplicationContext(), et.getText().toString(),Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, 3);
    }

    private void startCrop(Uri sourceUri) {
//        Uri sourceUri = Uri.parse("http://star.xiziwang.net/uploads/allimg/140512/19_140512150412_1.jpg");
        //裁剪后保存到文件中
        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "SampleCropImage.png"));
        UCrop.of(sourceUri, destinationUri).withAspectRatio(1, 1).withMaxResultSize(300, 300).start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoPicker.REQUEST_CODE) {
//                List<String> photos = null;
                if (data != null) {
//                    photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    Uri uri = data.getData();
//                    crop(uri);
                    startCrop(uri);
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                // 从剪切图片返回的数据
                if (data != null) {
                    /*Bitmap bitmap = data.getParcelableExtra("data");
                    //防止低版本没有办法获取数据，导致奔溃
                    if(bitmap ==null){
                        return;
                    }
                    //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                    //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
                    uploadImgFor64(imageString);*/
                    Uri croppedFileUri = UCrop.getOutput(data);
                    //获取默认的下载目录
                    String downloadsDirectoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                    String filename = String.format("%d_%s", Calendar.getInstance().getTimeInMillis(), croppedFileUri.getLastPathSegment());
                    Bitmap bitmap = BitmapFactory.decodeFile(croppedFileUri.getPath());
                    if (bitmap == null) {
                        return;
                    }
                    //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                    //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
                    uploadImgFor64("data:image/png;base64," + imageString);
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
            }
        }
        //裁切失败
        if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(this, "裁切图片失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadImgFor64(String imageString) {
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
                message.what = 1;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                message.setData(bundle);
                myHandler.sendMessage(message);
            }
        });
    }
}
