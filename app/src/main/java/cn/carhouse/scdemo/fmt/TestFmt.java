package cn.carhouse.scdemo.fmt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.RcyAdapterHelper;
import cn.carhouse.scdemo.base.RcyBaseHolder;

/**
 * ================================================================
 * 版权: 爱车小屋所有（C） 2016
 * <p/>
 * 作者：刘付文 （61128910@qq.com）
 * <p/>
 * 时间: 2016-09-30 12:46
 * <p/>
 * 描述：
 * ================================================================
 */
public class TestFmt extends Fragment {
    private List<String> mDatas = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i <= 100; i++) {
            mDatas.add("Test Data  " + i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        ListView mListView = new ListView(getActivity());
//
//        MainAdapter mAdapter = new MainAdapter();
//        mListView.setAdapter(mAdapter);
        RecyclerView recyclerView=new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RcyAdapterHelper<String>(R.layout.item_tv,mDatas) {
            @Override
            public void convert(RcyBaseHolder holder, String item, int position) {
            holder.setText(R.id.tv,mDatas.get(position));
            }

        });
        return recyclerView;
    }


}
