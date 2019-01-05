package com.emjiayuan.factoryside.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emjiayuan.factoryside.mvp.activity.CollectionActivity;
import com.emjiayuan.factoryside.mvp.activity.EnterpriseActivity;
import com.emjiayuan.factoryside.mvp.activity.HelpActivity;
import com.emjiayuan.factoryside.mvp.activity.HezuoActivity;
import com.emjiayuan.factoryside.mvp.activity.HistoryActivity;
import com.emjiayuan.factoryside.mvp.activity.LogisticsActivity;
import com.emjiayuan.factoryside.mvp.activity.OrderNormalActivity;
import com.emjiayuan.factoryside.mvp.activity.SettingActivity;
import com.emjiayuan.factoryside.mvp.activity.SpitActivity;
import com.emjiayuan.factoryside.mvp.activity.address.AddressActivity;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.base.BaseLazyFragment;
import com.emjiayuan.nll.event.UpdateEvent;
import com.emjiayuan.nll.model.LoginResult;
import com.emjiayuan.nll.model.UserInfo;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;
import com.google.gson.Gson;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.io.IOException;

import androidx.annotation.NonNull;
import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class AllWorkOrdersFragment extends BaseLazyFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.profile_image)
    ImageView mProfileImage;
    @BindView(R.id.nickname)
    TextView mNickname;
    @BindView(R.id.my_top)
    LinearLayout mMyTop;
    @BindView(R.id.normal_dfk_ll)
    LinearLayout mNormalDfkLl;
    @BindView(R.id.normal_dfh_ll)
    LinearLayout mNormalDfhLl;
    @BindView(R.id.normal_dsh_ll)
    LinearLayout mNormalDshLl;
    @BindView(R.id.normal_all_ll)
    LinearLayout mNormalAllLl;
    @BindView(R.id.soup_dfk_ll)
    LinearLayout mSoupDfkLl;
    @BindView(R.id.soup_dfh_ll)
    LinearLayout mSoupDfhLl;
    @BindView(R.id.soup_dsh_ll)
    LinearLayout mSoupDshLl;
    @BindView(R.id.soup_all_ll)
    LinearLayout mSoupAllLl;
    @BindView(R.id.wlcx_ll)
    LinearLayout mWlcxLl;
    @BindView(R.id.address_ll)
    LinearLayout mAddressLl;
    @BindView(R.id.service_ll)
    LinearLayout mServiceLl;
    @BindView(R.id.spit_ll)
    LinearLayout mSpitLl;
    @BindView(R.id.help_ll)
    LinearLayout mHelpLl;
    @BindView(R.id.hezuo_ll)
    LinearLayout mHezuoLl;
    @BindView(R.id.record_ll)
    LinearLayout mRecordLl;
    @BindView(R.id.collection_ll)
    LinearLayout mCollectionLl;
    @BindView(R.id.qyjs_ll)
    LinearLayout mQyjsLl;
    @BindView(R.id.setting_ll)
    LinearLayout mSettingLl;
    @BindView(R.id.banner)
    Banner mBanner;


    private String mParam1;
    private String mParam2;
    private String[] images = new String[]{
            "http://qiniu.emjiayuan.com/upload_file/ems/2018092111329525354",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100815514348272",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018101113731538120",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018100911071275167",
            "http://qiniu.emjiayuan.com/upload_file/ems/2018071817991346559",
    };

    public AllWorkOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllWorkOrdersFragment newInstance(String param1, String param2) {
        AllWorkOrdersFragment fragment = new AllWorkOrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        super.initImmersionBar();
//        mImmersionBar.fitsSystemWindows(false);
//        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        refreshLayout.setEnableLoadMore(false);
        getUserInfo();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getUserInfo();
            }
        });
    }

    public void setData(){
        Glide.with(mActivity).load(mUser.getHeadimg()).apply(RequestOptions.circleCropTransform().placeholder(R.drawable.default_tx).error(R.drawable.default_tx)).into(mProfileImage);
        mNickname.setText(mUser.getTruename());
        mNickname.setTextColor(getResources().getColor(R.color.gray_one));
    }

    public void getUserInfo() {
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
                        refreshLayout.finishRefresh();
                    } catch (Exception e) {
                        refreshLayout.finishRefresh();
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    @Override
    protected void setListener() {
        mNormalDfkLl.setOnClickListener(this);
        mNormalDfhLl.setOnClickListener(this);
        mNormalDshLl.setOnClickListener(this);
        mNormalAllLl.setOnClickListener(this);

        mSoupDfkLl.setOnClickListener(this);
        mSoupDfhLl.setOnClickListener(this);
        mSoupDshLl.setOnClickListener(this);
        mSoupAllLl.setOnClickListener(this);

        mWlcxLl.setOnClickListener(this);
        mAddressLl.setOnClickListener(this);
        mServiceLl.setOnClickListener(this);
        mSpitLl.setOnClickListener(this);
        mHelpLl.setOnClickListener(this);
        mHezuoLl.setOnClickListener(this);
        mRecordLl.setOnClickListener(this);
        mCollectionLl.setOnClickListener(this);
        mQyjsLl.setOnClickListener(this);
        mSettingLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()){
            case R.id.normal_dfk_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","普通订单");
                intent.putExtra("curTab",0);
                startActivity(intent);
                break;
            case R.id.normal_dfh_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","普通订单");
                intent.putExtra("curTab",1);
                startActivity(intent);
                break;
            case R.id.normal_dsh_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","普通订单");
                intent.putExtra("curTab",2);
                startActivity(intent);
                break;
            case R.id.normal_all_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","普通订单");
                intent.putExtra("curTab",4);
                startActivity(intent);
                break;
            case R.id.soup_dfk_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","汤料订单");
                intent.putExtra("curTab",0);
                startActivity(intent);
                break;
            case R.id.soup_dfh_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","汤料订单");
                intent.putExtra("curTab",1);
                startActivity(intent);
                break;
            case R.id.soup_dsh_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","汤料订单");
                intent.putExtra("curTab",2);
                startActivity(intent);
                break;
            case R.id.soup_all_ll:
                intent=new Intent(mActivity,OrderNormalActivity.class);
                intent.putExtra("order_type","汤料订单");
                intent.putExtra("curTab",3);
                startActivity(intent);
                break;
            case R.id.wlcx_ll:
                startActivity(new Intent(mActivity,LogisticsActivity.class));
                break;
            case R.id.address_ll:
                startActivity(new Intent(mActivity,AddressActivity.class));
                break;
            case R.id.service_ll:
                String title = "伊穆家园客服";
                /**
                 * 设置访客来源，标识访客是从哪个页面发起咨询的，用于客服了解用户是从什么页面进入。
                 * 三个参数分别为：来源页面的url，来源页面标题，来源页面额外信息（保留字段，暂时无用）。
                 * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                 */
                ConsultSource source = new ConsultSource("app", "app", "custom information string");
                /**
                 * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                 * 如果返回为false，该接口不会有任何动作
                 *
                 * @param context 上下文
                 * @param title   聊天窗口的标题
                 * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                 */
                Unicorn.openServiceActivity(mActivity, title, source);
                break;
            case R.id.spit_ll:
                startActivity(new Intent(mActivity,SpitActivity.class));
                break;
            case R.id.help_ll:
                startActivity(new Intent(mActivity,HelpActivity.class));
                break;
            case R.id.hezuo_ll:
                startActivity(new Intent(mActivity,HezuoActivity.class));
                break;
            case R.id.record_ll:
                startActivity(new Intent(mActivity,HistoryActivity.class));
                break;
            case R.id.collection_ll:
                startActivity(new Intent(mActivity,CollectionActivity.class));
                break;
            case R.id.qyjs_ll:
                startActivity(new Intent(mActivity,EnterpriseActivity.class));
                break;
            case R.id.setting_ll:
                startActivity(new Intent(mActivity,SettingActivity.class));
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(UpdateEvent event) {
        getUserInfo();
    }
}
