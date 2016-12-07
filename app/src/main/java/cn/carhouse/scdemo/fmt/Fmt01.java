package cn.carhouse.scdemo.fmt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.carhouse.scdemo.act.Test01Act;
import cn.carhouse.scdemo.base.BaseTitleFmt;
import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:
 */

public class Fmt01 extends BaseTitleFmt {

    public static Fmt01 newInstance() {

        Bundle args = new Bundle();

        Fmt01 fragment = new Fmt01();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;

    private ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_01;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
        iv= (ImageView) view.findViewById(R.id.iv);
    }

    @Override
    protected void handleData() {

        tv.setText("Fmt01==========");
        iv.setColorFilter(getResources().getColor(R.color.colorAccent));

    }

    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt02.newInstance());
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,Test01Act.class));
            }
        });

    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }
}
