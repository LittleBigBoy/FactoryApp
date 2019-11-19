package com.zhenhaikj.factoryside.mvp.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.aip.asrwakeup3.core.recog.MyRecognizer;
import com.baidu.aip.asrwakeup3.core.recog.listener.ChainRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.IRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.MessageStatusRecogListener;
import com.baidu.aip.asrwakeup3.uiasr.params.OnlineRecogParams;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DigitalDialogInput;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.SearchAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Data;
import com.zhenhaikj.factoryside.mvp.bean.Search;
import com.zhenhaikj.factoryside.mvp.contract.SearchContract;
import com.zhenhaikj.factoryside.mvp.model.SearchModel;
import com.zhenhaikj.factoryside.mvp.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<SearchPresenter, SearchModel> implements View.OnClickListener, SearchContract.View {
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.back)
    TextView mBack;
    @BindView(R.id.rv_search)
    RecyclerView mRvSearch;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_microphone)
    ImageView mIvMicrophone;
    private String userId;
    private SearchAdapter searchAdapter;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    /**
     * 对话框界面的输入参数
     */
    private DigitalDialogInput input;
    private ChainRecogListener chainRecogListener;
    private boolean running;
    private OnlineRecogParams apiParams;
    protected Handler handler;
    private MyRecognizer myRecognizer;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        mImmersionBar.init();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SPUtils spUtils = SPUtils.getInstance("token");
        userId = spUtils.getString("userName");
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);

        IRecogListener listener = new MessageStatusRecogListener(handler);
        // DEMO集成步骤 1.1 1.3 初始化：new一个IRecogListener示例 & new 一个 MyRecognizer 示例,并注册输出事件
        if (myRecognizer == null) {
            myRecognizer = new MyRecognizer(mActivity, listener);
        }
        /**
         * 有2个listner，一个是用户自己的业务逻辑，如MessageStatusRecogListener。另一个是UI对话框的。
         * 使用这个ChainRecogListener把两个listener和并在一起
         */
        chainRecogListener = new ChainRecogListener();
        // DigitalDialogInput 输入 ，MessageStatusRecogListener可替换为用户自己业务逻辑的listener
        chainRecogListener.addListener(new MessageStatusRecogListener(handler));
        myRecognizer.setEventListener(chainRecogListener); // 替换掉原来的listener
    }

    @Override
    protected void setListener() {
        mBack.setOnClickListener(this);
        mIvMicrophone.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                String searchname = mEtSearch.getText().toString();
                if (searchname == null || "".equals(searchname)) {
                    ToastUtils.showShort("请输入用户手机号或者工单号");
                } else {
                    StringBuilder stringBuilder = new StringBuilder(searchname);
                    String name = stringBuilder.substring(0, 1);
                    if ("1".equals(name)) {
                        mPresenter.GetOrderInfoList(searchname, "", userId, "999", "1");
                    } else {
                        mPresenter.GetOrderInfoList("", searchname, userId, "999", "1");
                    }
                }

                break;
            case R.id.iv_microphone:
                // 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
                final Map<String, Object> params = fetchParams();

                // BaiduASRDigitalDialog的输入参数
                input = new DigitalDialogInput(myRecognizer, chainRecogListener, params);
                BaiduASRDigitalDialog.setInput(input); // 传递input信息，在BaiduASRDialog中读取,
                Intent intent = new Intent(this, BaiduASRDigitalDialog.class);

                // 修改对话框样式
                // intent.putExtra(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_ORANGE_DEEPBG);

                running = true;
                startActivityForResult(intent, 2);
//                startActivity(new Intent(mActivity,ActivityUiDialog.class));
                break;
        }
    }

    protected Map<String, Object> fetchParams() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mActivity);
        //  上面的获取是为了生成下面的Map， 自己集成时可以忽略
        apiParams = new OnlineRecogParams();
        Map<String, Object> params = apiParams.fetch(sp);
        //  集成时不需要上面的代码，只需要params参数。
        return params;
    }


    @Override
    public void GetOrderInfoList(BaseResult<Search> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                searchAdapter = new SearchAdapter(R.layout.order_item, baseResult.getData().getData());
                mRvSearch.setLayoutManager(new LinearLayoutManager(mActivity));
                mRvSearch.setAdapter(searchAdapter);
                searchAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                        switch (view.getId()) {
                            case R.id.iv_copy:
                                String id = baseResult.getData().getData().get(position).getOrderID();
                                myClip = ClipData.newPlainText("", id);
                                myClipboard.setPrimaryClip(myClip);
                                ToastUtils.showShort("复制成功");
                                break;
                            case R.id.iv_star:
                                if (view.isSelected()) {
                                    view.setSelected(false);
                                    mPresenter.GetFStarOrder(baseResult.getData().getData().get(position).getOrderID(), "N");
                                } else {
                                    view.setSelected(true);
                                    mPresenter.GetFStarOrder(baseResult.getData().getData().get(position).getOrderID(), "Y");
                                }
                                break;

                            case R.id.tv_see_detail:
                                Intent intent1 = new Intent(mActivity, WarrantyActivity.class);
                                intent1.putExtra("OrderID", baseResult.getData().getData().get(position).getOrderID());
                                startActivity(intent1);
                                break;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void GetFStarOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        running = false;
        if (requestCode == 2) {
            String message = "";
            if (resultCode == RESULT_OK) {
                ArrayList results = data.getStringArrayListExtra("results");
                if (results != null && results.size() > 0) {
                    message += results.get(0);
                }
            } else {
                message += "";
            }
            mEtSearch.setText(message);
            if (!message.isEmpty()){
                StringBuilder stringBuilder = new StringBuilder(message);
                String name = stringBuilder.substring(0, 1);
                if ("1".equals(name)) {
                    mPresenter.GetOrderInfoList(message, "", userId, "999", "1");
                } else {
                    mPresenter.GetOrderInfoList("", message, userId, "999", "1");
                }
            }else {
                return;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!running) {
            myRecognizer.release();
            finish();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (!running) {
            myRecognizer.release();
//            finish();
        }
    }
}
