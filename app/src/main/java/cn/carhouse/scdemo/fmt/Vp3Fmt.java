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
import cn.carhouse.scdemo.base.BaseTitleFmt;
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


    public static Vp3Fmt newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("state", position);
        Vp3Fmt fragment = new Vp3Fmt();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_08, null);
    }

}
