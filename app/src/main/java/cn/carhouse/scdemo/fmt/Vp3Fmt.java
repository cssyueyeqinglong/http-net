package cn.carhouse.scdemo.fmt;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.carhouse.scdemo.BaseBean;
import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.LoadingAndRetryManager;
import cn.carhouse.scdemo.base.OnLoadingAndRetryListener;
import cn.carhouse.scdemo.base.RcyBaseHolder;
import cn.carhouse.scdemo.base.RcyQuickAdapter;

/**
 * Created by Administrator
 * on 2016/11/29.
 * des:
 */

public class Vp3Fmt extends Fragment {

    private int state;

    private MyAdapter mAdapter;
    private RecyclerView mRcv;

    private LoadingAndRetryManager manager;

    List<BaseBean> mDatas = new ArrayList<BaseBean>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public static Vp3Fmt newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("state", position);
        Vp3Fmt fragment = new Vp3Fmt();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = getArguments().getInt("state");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_02, null);
        findViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleData();
    }

    private void findViews(View view) {

        mRcv = (RecyclerView) view.findViewById(R.id.rcv);

    }


    private List<BaseBean> addData(int count) {
        mDatas.clear();
        for (int i = 0; i < count; i++) {
            mDatas.add(new BaseBean());
        }
        return mDatas;
    }


    private void handleData() {
        if (mAdapter == null) {
            mAdapter = new MyAdapter(addData(state * 2), R.layout.item_rcv,
                    getContext());
        }
        if (null == mRcv.getLayoutManager()) {
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            mRcv.setLayoutManager(manager);
        }
        if (null == mRcv.getAdapter()) {
            mRcv.setAdapter(mAdapter);
        }

        manager = LoadingAndRetryManager.generate(mRcv, new OnLoadingAndRetryListener() {
            @Override
            public void setRetryEvent(View retryView) {
                retryView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        manager.showContent();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                List<BaseBean> datas = addData(state * 2);
                if (datas.size() == 0) {
                    manager.showEmpty();
                } else manager.showContent();
            }
        }, 1500);


    }

    private class MyAdapter extends RcyQuickAdapter<BaseBean> {


        public MyAdapter(List<BaseBean> data, int layoutId, Context context) {
            super(data, layoutId, context);
        }

        @Override
        public void convert(RcyBaseHolder holder, BaseBean item, int position) {

        }
    }
}
