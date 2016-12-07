package cn.carhouse.scdemo.fmt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.carhouse.scdemo.base.BaseTitleFmt;
import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:
 */

public class Fmt02 extends BaseTitleFmt {


    public static Fmt02 newInstance() {

        Bundle args = new Bundle();

        Fmt02 fragment = new Fmt02();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_01;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#ff0000"));
        tv.setText("Fmt02==========");
    }

    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt03.newInstance());
            }
        });
    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }
}
