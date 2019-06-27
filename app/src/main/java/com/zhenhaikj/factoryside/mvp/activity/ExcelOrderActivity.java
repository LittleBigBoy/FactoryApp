package com.zhenhaikj.factoryside.mvp.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.ExcelOrderAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseActivity;
import com.zhenhaikj.factoryside.mvp.bean.Order;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jxl.Sheet;
import jxl.Workbook;

/*excel表单发货*/
public class ExcelOrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
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
    @BindView(R.id.rv)
    RecyclerView mRv;


    private ExcelOrderAdapter excelOrderAdapter;
    private InputStream inStream;
    private ArrayList<Order> orderArrayList = new ArrayList<>();
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.red);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_excelorder;
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("批量发单");
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("选择文件");


    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mTvSave.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE); // 如果少了这句，有些机型上面不能正常打开文件管理器，比如金立
        intent.setType("*/*");
        startActivityForResult(intent, 10001);
    }



    /**
     * 获取 excel 表格中的数据,不能在主线程中调用
     *
     * @param xlsName excel 表格的名称
     * @param index   第几张表格中的数据
     */
    private ArrayList<Order> getXlsData(String xlsName, int index) {
        orderArrayList = new ArrayList<>();

        //AssetManager assetManager = getAssets();
        try {
            Workbook workbook = Workbook.getWorkbook(inStream);
            Sheet sheet = workbook.getSheet(index);
            int sheetNum = workbook.getNumberOfSheets();
            int sheetRows = sheet.getRows();
            int sheetColumns = sheet.getColumns();

           // Log.d(TAG, "the num of sheets is " + sheetNum);
           // Log.d(TAG, "the name of sheet is  " + sheet.getName());
           // Log.d(TAG, "total rows is 行=" + sheetRows);
           // Log.d(TAG, "total cols is 列=" + sheetColumns);

            for (int i = 1; i < sheetRows; i++) {
                Order order = new Order();
                order.setTypeID(sheet.getCell(0, i).getContents());
                order.setTypeName(sheet.getCell(1, i).getContents());
                order.setUserID(sheet.getCell(2, i).getContents());
                order.setFBrandID(sheet.getCell(3, i).getContents());
                order.setBrandName(sheet.getCell(4, i).getContents());
                order.setFCategoryID(sheet.getCell(5, i).getContents());
                order.setCategoryName(sheet.getCell(6, i).getContents());
                order.setSubCategoryID(sheet.getCell(7, i).getContents());
                order.setSubCategoryName(sheet.getCell(8, i).getContents());
                order.setProvinceCode(sheet.getCell(9, i).getContents());
                order.setCityCode(sheet.getCell(10, i).getContents());
                order.setAreaCode(sheet.getCell(11, i).getContents());
                order.setDistrictCode(sheet.getCell(12, i).getContents());
                order.setAddress(sheet.getCell(13, i).getContents());
                order.setUserName(sheet.getCell(14, i).getContents());
                order.setPhone(sheet.getCell(15, i).getContents());
                order.setMemo(sheet.getCell(16, i).getContents());
                order.setOrderMoney(sheet.getCell(17, i).getContents());
                order.setRecycleOrderHour(sheet.getCell(18, i).getContents());
                order.setGuarantee(sheet.getCell(19, i).getContents());
                order.setAccessorySendState(sheet.getCell(20, i).getContents());
                order.setExtra(sheet.getCell(21, i).getContents());
                order.setExtraTime(sheet.getCell(22, i).getContents());
                order.setExtraFee(sheet.getCell(23, i).getContents());
                order.setNum(sheet.getCell(24, i).getContents());
                order.setIsRecevieGoods(sheet.getCell(25, i).getContents());
                order.setExpressNo(sheet.getCell(26, i).getContents());
                orderArrayList.add(order);
            }

            workbook.close();

        } catch (Exception e) {
          //  Log.e(TAG, "read error=" + e, e);

        }

        Log.d("=====>orderArrayList", String.valueOf(orderArrayList.size()));

        return orderArrayList;
    }



    //在异步方法中 调用
    private class ExcelDataLoader extends AsyncTask<String, Void, ArrayList<Order>> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList<Order> doInBackground(String... params) {
            return getXlsData(params[0], 0);
        }

        @Override
        protected void onPostExecute(ArrayList<Order> orders) {

            if(orders != null && orders.size()>0){
                //存在数据
                excelOrderAdapter=new ExcelOrderAdapter(R.layout.item_excelorder,orderArrayList);
                mRv.setLayoutManager(new LinearLayoutManager(mActivity));
                mRv.setAdapter(excelOrderAdapter);
            }else {
                //加载失败
            }

        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode==10001){
                Uri uri = data.getData();
                Log.d("=======>",uri.getEncodedPath());

                if (uri.getEncodedPath().length()>3){
                    if ("xls".equals(uri.getEncodedPath().substring(uri.getEncodedPath().length()-3,uri.getEncodedPath().length()))){
                        ContentResolver resolver = getContentResolver();
                        try {
                            inStream=resolver.openInputStream(uri);

                            if (inStream!=null){
                                new ExcelDataLoader().execute("test.xls");
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }else {

                        Toast.makeText(mActivity,"目前仅支持xls格式文件！！",Toast.LENGTH_SHORT).show();

                    }





                }




            }
        }

    }





}
