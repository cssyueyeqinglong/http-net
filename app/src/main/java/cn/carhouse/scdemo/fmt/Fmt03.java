package cn.carhouse.scdemo.fmt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.carhouse.scdemo.base.BaseTitleFmt;
import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.act.TestAct;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:
 */

public class Fmt03 extends BaseTitleFmt {

    public static Fmt03 newInstance() {

        Bundle args = new Bundle();

        Fmt03 fragment = new Fmt03();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_04;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText("Fmt03==========");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,TestAct.class));
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }
}
