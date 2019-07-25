package com.zhenhaikj.factoryside.mvp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.utils.ZXingUtils;

public class QRCodeDialog extends Dialog {

    private ImageView img_qrcode;
    private Context context;
    private String UserId;




    /*  ---------------------------------- 构造方法 -------------------------------------  */
    public QRCodeDialog(Context context, String UserId) {
        super(context);
        this.context=context;
        this.UserId=UserId;
    }

    public QRCodeDialog(Context context, int themeResId) {
        super(context, R.style.MyDialog);
    }

    protected QRCodeDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /*  ---------------------------------- onCreate-------------------------------------  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_qrcode);//自定义布局

        //按空白处不能取消动画
//       setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();

    }

    /**
     * 初始化界面的确定和取消监听器
     */




    /**
     * 初始化界面控件
     */
    private void initView() {
        img_qrcode =  findViewById(R.id.img_sub_account_qrcode);
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {

//        Glide.with(context)
//                .load(Config.SUB_ACCOUNT_QRCODE+UserId)
//                .into(img_qrcode);
//        http://admin.xigyu.com/regchildaccount?ParentUserID=17855837725
        Bitmap bitmap = ZXingUtils.createQRImage("http://admin.xigyu.com/regchildaccount?ParentUserID="+UserId+"&roleType=6", 600, 600, BitmapFactory.decodeResource(context.getResources(), R.drawable.icon));
        img_qrcode.setImageBitmap(bitmap);
    }
}
