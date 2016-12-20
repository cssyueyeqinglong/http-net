package cn.carhouse.scdemo.fmt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.act.TestAct;
import cn.carhouse.scdemo.base.BaseTitleFmt;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:
 */

public class Fmt04 extends BaseTitleFmt {

    public static Fmt04 newInstance() {

        Bundle args = new Bundle();

        Fmt04 fragment = new Fmt04();
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
        view.findViewById(R.id.circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                addFragment(Vp3Fmt.newInstance(0));
            }
        });
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
