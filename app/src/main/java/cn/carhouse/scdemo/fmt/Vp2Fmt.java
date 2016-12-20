package cn.carhouse.scdemo.fmt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/11/29.
 * des:
 */

public class Vp2Fmt extends Fragment {


    public static Vp2Fmt newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("state", position);
        Vp2Fmt fragment = new Vp2Fmt();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_vp02, null);
    }
}
