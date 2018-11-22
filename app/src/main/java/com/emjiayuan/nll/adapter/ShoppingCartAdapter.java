package com.emjiayuan.nll.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emjiayuan.nll.Global;
import com.emjiayuan.nll.R;
import com.emjiayuan.nll.event.ShoppingCartUpdateEvent;
import com.emjiayuan.nll.interfaces.AllCheckListener;
import com.emjiayuan.nll.model.ShoppingCart;
import com.emjiayuan.nll.utils.MyOkHttp;
import com.emjiayuan.nll.utils.MyUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class ShoppingCartAdapter extends BaseAdapter {
    private Context mContext;
    private static String TAG = "MenuAdapter";

    private ArrayList<ShoppingCart> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;
    private ListView lv;
    private CheckBox checkBox;
    private int index = -1;
    private String[] text;
    private ViewHolder holder;
    private AllCheckListener allCheckListener;
    private Map<Integer, Boolean> map = new HashMap<>();// 存放已被选中的CheckBox

    public Map<Integer, Boolean> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Boolean> map) {
        this.map = map;
        notifyDataSetChanged();
    }

    public void setGrouplists(ArrayList<ShoppingCart> grouplists) {
        this.grouplists = grouplists;
        notifyDataSetChanged();
    }

    public void delete(int position) {
        grouplists.remove(position);
        notifyDataSetChanged();
    }

    public ShoppingCartAdapter(Context mContext, ArrayList<ShoppingCart> grouplists, ListView lv, AllCheckListener listener) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
        this.lv = lv;
        this.allCheckListener = listener;
        text = new String[4];
//        text=new String[grouplists.size()];
    }

    @Override
    public int getCount() {
        return grouplists.size();
//        return 4;
    }

    @Override
    public ShoppingCart getItem(int position) {
        return grouplists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {// 如果是第一次显示该页面(要记得保存到holder中供下次直接从缓存中调用)
            convertView = mInflater.inflate(R.layout.shopping_cart_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用holder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }
        final ShoppingCart item = grouplists.get(position);
        int count = Integer.parseInt(item.getCartnum());
        Glide.with(mContext).load(item.getImages()).into(holder.mIcon);
        holder.mName.setText(item.getName());
        holder.mPrice.setText("¥" + item.getPrice());
        holder.mEtCount.setText("" + count);
        holder.mUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCar(item.getProductid(), "1", "1", position);
            }
        });
        holder.mDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCar(item.getProductid(), "2", "1", position);
            }
        });
        holder.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    map.put(position, true);
                } else {
                    map.remove(position);
                }
                item.setChecked(b);
                //监听每个item，若所有checkbox都为选中状态则更改main的全选checkbox状态
                EventBus.getDefault().post(new ShoppingCartUpdateEvent(grouplists));
                for (ShoppingCart shoppingCart : grouplists) {
                    if (!shoppingCart.isChecked()) {
                        allCheckListener.onCheckedChanged(false);
                        return;
                    }
                }
                allCheckListener.onCheckedChanged(true);

            }
        });
        if (map != null && map.containsKey(position)) {
            holder.mCheck.setChecked(true);
        } else {
            holder.mCheck.setChecked(false);
        }
        return convertView;
    }
    public void updateCar(String productid, String option, String num, final int position) {
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("userid", Global.loginResult.getId());
        formBody.add("productid", productid);
        formBody.add("option", option);
        formBody.add("num", num);
        Log.d("------参数------", formBody.build().toString());
//new call
        Call call = MyOkHttp.GetCall("cart.addOrUpdateCart", formBody);
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
                MyUtils.e("------操作购物车结果------", result);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("result", result);
                bundle.putInt("position", position);
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
            int position = bundle.getInt("position");
            switch (msg.what) {
                case 0:
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        String data = jsonObject.getString("data");
                        if ("200".equals(code)) {
                            JSONObject object = new JSONObject(data);
                            String cartnum = object.getString("cartnum");
                            updateItem(position, cartnum);
                            EventBus.getDefault().post(new ShoppingCartUpdateEvent(grouplists));
                        } else {
                            MyUtils.showToast(mContext, message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    /**
     * 刷新指定item
     */
    private void updateItem(int index, String cartnum) {
        if (lv == null) {
            return;
        }
        // 获取当前可以看到的item位置
        int visiblePosition = lv.getFirstVisiblePosition();
        // 如添加headerview后 firstview就是hearderview
        // 所有索引+1 取第一个view
        // View view = listview.getChildAt(index - visiblePosition + 1);
        // 获取点击的view
        View view = lv.getChildAt(index - visiblePosition);
        if (view != null) {
            ShoppingCart shoppingCart = grouplists.get(index);
            shoppingCart.setCartnum(cartnum);
            EditText etcount = (EditText) view.findViewById(R.id.et_count);
            etcount.setText(cartnum);
        }

    }

    class ViewHolder {
        @BindView(R.id.check)
        CheckBox mCheck;
        @BindView(R.id.check_ll)
        LinearLayout mCheckLl;
        @BindView(R.id.icon)
        ImageView mIcon;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.price)
        TextView mPrice;
        @BindView(R.id.down)
        TextView mDown;
        @BindView(R.id.et_count)
        EditText mEtCount;
        @BindView(R.id.up)
        TextView mUp;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
