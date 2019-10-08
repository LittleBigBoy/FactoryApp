package com.zhenhaikj.factoryside.mvp.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lxj.xpopup.XPopup;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.Config;
import com.zhenhaikj.factoryside.mvp.adapter.NewAddAccessoriesAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Accessory;
import com.zhenhaikj.factoryside.mvp.bean.Accessory2;
import com.zhenhaikj.factoryside.mvp.bean.FAccessory;
import com.zhenhaikj.factoryside.mvp.bean.GetFactoryData;
import com.zhenhaikj.factoryside.mvp.contract.NewAddAccessoriesContract;
import com.zhenhaikj.factoryside.mvp.model.NewAddAccessoriesModel;
import com.zhenhaikj.factoryside.mvp.presenter.NewAddAccessoriesPresenter;
import com.zhenhaikj.factoryside.mvp.widget.MyPackagePopup;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃   神兽保佑
//    ┃　　　┃   代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　┣┓
//    ┃　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

/**
 * Data:2019/7/30
 * Time:10:55
 * author:ying
 **/
/*添加配件新页面*/
public class NewAddAccessoriesActivity extends BaseActivity<NewAddAccessoriesPresenter, NewAddAccessoriesModel> implements NewAddAccessoriesContract.View, View.OnClickListener {

    @BindView(R.id.tv_choose)
    TextView mTvChoose;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    setAnim(ball, startLocation);// 开始执行动画
                    break;
            }
        }
    };


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.et_search)
    EditText mEtsearch;
    @BindView(R.id.ll_my_package)
    RelativeLayout mLlmy_package;
    @BindView(R.id.img_package)
    ImageView mImgpackage;
    @BindView(R.id.tv_num_bg)
    TextView mTvnum_bg;
    @BindView(R.id.tv_next)
    TextView mTvnext;
    @BindView(R.id.img_return)
    ImageView mImgreturn;

    //动画
    private int[] startLocation;
    private ImageView ball;// 小圆点
    private ViewGroup viewGroup;//动画层
    //数字圆点

    private int num;


    private NewAddAccessoriesAdapter newAddAccessoriesAdapter;
    private List<Accessory2> list_accessory = new ArrayList<>();// 获取第一次全部所有的配件
    private List<Accessory2> list_search = new ArrayList<>();//搜索到的配件

    private List<Accessory2> list_collect = new ArrayList<>(); //收藏的配件
    private List<Accessory2> list_add = new ArrayList<>(); //收藏的配件
    private Map<Integer, Accessory2> map_collect = new HashMap<>();//收藏的配件map集合
   FAccessory.OrderAccessoryStrBean.OrderAccessoryBean mfAccessory = new FAccessory.OrderAccessoryStrBean.OrderAccessoryBean();

    private ZLoadingDialog dialog = new ZLoadingDialog(this); //loading
    private AlertDialog underReviewDialog;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_newaddaccessories;
    }

    @Override
    protected void initData() {
        String subCategoryID = getIntent().getStringExtra("SubCategoryID");
        showLoading();
        mPresenter.GetFactoryAccessory(subCategoryID);
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        newAddAccessoriesAdapter = new NewAddAccessoriesAdapter(R.layout.item_newaddaccessories, list_search);
        rv.setAdapter(newAddAccessoriesAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mLlmy_package.setOnClickListener(this);
        mTvnext.setOnClickListener(this);
        mImgreturn.setOnClickListener(this);
        mTvChoose.setOnClickListener(this);

        mEtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if ("".equals(s.toString())) {
                    //没内容
                    list_search.clear();
                    list_search.addAll(list_accessory);
                    newAddAccessoriesAdapter.notifyDataSetChanged();
                } else {
                    list_search.clear();
                    list_search.addAll(searchAccessory(s.toString(), list_accessory));
                    newAddAccessoriesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        newAddAccessoriesAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.img_add:
                        num++;//背包内数量+1
                        if (map_collect.get(position) == null) {
                            map_collect.put(position, ((Accessory2) adapter.getData().get(position)));
                        } else {
                            int count = map_collect.get(position).getCount();
                            count++;
                            map_collect.get(position).setCount(count);
                        }

                        list_collect.clear();
                        //将map对象转为list
                        Collection<Accessory2> collection = map_collect.values();
                        Iterator<Accessory2> iterator = collection.iterator();
                        while (iterator.hasNext()) {
                            Accessory2 value = (Accessory2) iterator.next();
                            list_collect.add(value);
                        }

                        startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                        view.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                        ball = new ImageView(NewAddAccessoriesActivity.this);// buyImg是动画的图片，我的是一个小球（R.drawable.sign）
                        getBallImageResource(ball);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(0);
                            }
                        }).start();
                        break;

                }
            }
        });

    }

    /*获取配件*/
    @Override
    public void GetFactoryAccessory(BaseResult<GetFactoryData<Accessory2>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                cancelLoading();
                if (baseResult.getData().getData() != null) {
                    list_accessory.addAll(baseResult.getData().getData());
                    list_search.addAll(list_accessory);
                    newAddAccessoriesAdapter.notifyDataSetChanged();
                }
                break;
        }

    }

    public List searchAccessory(String name, List list) {
        List results = new ArrayList();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);
        for (int i = 0; i < list.size(); i++) {
            Matcher matcher = pattern.matcher(((Accessory2) list.get(i)).getAccessoryName());
            if (matcher.find()) {
                results.add(list.get(i));
            }
        }
        return results;
    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("获取配件中请稍等...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }

    public void cancelLoading() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }


    private void setAnim(final View v, int[] startLocation) {
        viewGroup = null;
        viewGroup = createAnimLayout();
        viewGroup.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(viewGroup, v,
                startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        mLlmy_package.getLocationInWindow(endLocation);// mLlmy_package是那个抛物线最后掉落的控件

        // 计算位移
        int endX = 0 - startLocation[0] + 80;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.2f);
        alphaAnimation.setDuration(800);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setFillAfter(true);


        final AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.addAnimation(alphaAnimation);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {

                if (num != 0) {
                    mTvnum_bg.setVisibility(View.VISIBLE);
                    mTvnum_bg.setText(num + "");
                }

                v.setVisibility(View.GONE);
                set.cancel();
                animation.cancel();


            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_my_package:
                new XPopup.Builder(mActivity)
                        .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                        .asCustom(new MyPackagePopup(mActivity, list_collect, mActivity))/*.enableDrag(false)*/
                        .show();

                break;
            case R.id.tv_next://下一步
                if (list_collect.isEmpty()) {
                    Toast.makeText(mActivity, "请先选择配件", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("list_collect", (Serializable) list_collect);
                    setResult(Config.APPLY_RESULT, intent);
                    finish();
                }

                break;
            case R.id.img_return:
                finish();
                break;
            case R.id.tv_choose:
//                View under_review = LayoutInflater.from(mActivity).inflate(R.layout.dialog_add_accessories, null);
//                final EditText et_accessories_name = under_review.findViewById(R.id.et_accessories_name);
//                Button btn_add1 = under_review.findViewById(R.id.btn_add);
//                btn_add1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        tv_accessory_name.setText(et_accessories_name.getText());
//                        num++;//背包内数量+1
//                        Accessory accessory=new Accessory();
//                        accessory.setFAccessoryID("0");
//                        accessory.setAccessoryName(et_accessories_name.getText().toString());
//                        accessory.setFCategoryID(list_accessory.get(0).getFCategoryID());
//                        accessory.setCount(1);
//                        list_add.add(accessory);
//                        Intent intent = new Intent();
//                        intent.putExtra("list_collect", (Serializable) list_add);
//                        setResult(Config.APPLY_RESULT, intent);
//                        NewAddAccessoriesActivity.this.finish();
//                        underReviewDialog.dismiss();
//                    }
//                });
//                underReviewDialog = new AlertDialog.Builder(mActivity).setView(under_review)
//                        .create();
//                underReviewDialog.show();
//                Window window = underReviewDialog.getWindow();
////                window.setContentView(under_review);
//                WindowManager.LayoutParams lp = window.getAttributes();
////                lp.alpha = 0.5f;
//                // 也可按屏幕宽高比例进行设置宽高
////                Display display = mActivity.getWindowManager().getDefaultDisplay();
////                lp.width = (int) (display.getWidth() * 0.6);
////                lp.height = under_review.getHeight();
////                lp.width = 300;
////                lp.height = 400;
//
//                window.setAttributes(lp);
////                window.setDimAmount(0.1f);
//                window.setBackgroundDrawable(new ColorDrawable());
                break;

        }


    }

    public void getBallImageResource(ImageView ball) {
        int Num = new Random().nextInt(7);
        switch (Num) {
            case 0:
                ball.setImageResource(R.mipmap.ic_add_1);// 设置buyImg的图片
                break;
            case 1:
                ball.setImageResource(R.mipmap.ic_add_2);
                break;
            case 2:
                ball.setImageResource(R.mipmap.ic_add_3);
                break;
            case 3:
                ball.setImageResource(R.mipmap.ic_add_4);
                break;
            case 4:
                ball.setImageResource(R.mipmap.ic_add_5);
                break;
            case 5:
                ball.setImageResource(R.mipmap.ic_add_6);
                break;
            case 6:
                ball.setImageResource(R.mipmap.ic_add_7);
                break;

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
