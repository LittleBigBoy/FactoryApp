package com.zhenhaikj.factoryside.mvp.fragment;

import android.os.Bundle;

import com.zhenhaikj.factoryside.R;
import com.zhenhaikj.factoryside.mvp.adapter.TrackAdapter;
import com.zhenhaikj.factoryside.mvp.base.BaseLazyFragment;
import com.zhenhaikj.factoryside.mvp.base.BaseResult;
import com.zhenhaikj.factoryside.mvp.bean.Track;
import com.zhenhaikj.factoryside.mvp.contract.TrackContract;
import com.zhenhaikj.factoryside.mvp.model.TrackModel;
import com.zhenhaikj.factoryside.mvp.presenter.TrackPresenter;
import com.zhenhaikj.factoryside.mvp.widget.Divideritemdecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class TrackFragment extends BaseLazyFragment<TrackPresenter, TrackModel> implements TrackContract.View {

    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "TrackFragment";
    @BindView(R.id.track_rv)
    RecyclerView mTrackRv;

    private List<Track> list=new ArrayList<>();

    private String mParam1;
    private String mParam2;
    private TrackAdapter adapter;


    public static TrackFragment newInstance(String param1, String param2) {
        TrackFragment fragment = new TrackFragment();
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

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_track;
    }

    @Override
    protected void initData() {
        mPresenter.GetOrderRecordByOrderID(mParam1);

//        Divideritemdecoration divideritemdecoration=new Divideritemdecoration(mActivity);
//        mTrackRv.addItemDecoration(divideritemdecoration);

        adapter = new TrackAdapter(R.layout.logistics_recycle_item,list);
        mTrackRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mTrackRv.setAdapter(adapter);
        adapter.setEmptyView(getEmptyView());
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                list=baseResult.getData();
                adapter.setNewData(list);
                break;
            case 401:
                break;
        }
    }
}
