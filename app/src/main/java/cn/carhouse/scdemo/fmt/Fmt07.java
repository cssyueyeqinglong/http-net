package cn.carhouse.scdemo.fmt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.BaseTitleFmt;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:颜色矩阵
 */

public class Fmt07 extends BaseTitleFmt {


    public static Fmt07 newInstance() {

        Bundle args = new Bundle();
        Fmt07 fragment = new Fmt07();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_012;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText(this.getClass().getSimpleName() + "=========");
    }


    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt08.newInstance());
            }
        });

    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

}
