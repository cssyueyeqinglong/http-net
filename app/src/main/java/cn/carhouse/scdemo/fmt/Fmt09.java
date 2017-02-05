package cn.carhouse.scdemo.fmt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.BaseTitleFmt;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:布局动画，让viewgroup中的控件出现时显示动画
 */

public class Fmt09 extends BaseTitleFmt {


    public static Fmt09 newInstance() {

        Bundle args = new Bundle();
        Fmt09 fragment = new Fmt09();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;
    private LinearLayout ll;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_014;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
        ll = (LinearLayout) view.findViewById(R.id.ll);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText(this.getClass().getSimpleName() + "=========");

        ScaleAnimation scaleAnimation=new ScaleAnimation(0,1,0,1);
        scaleAnimation.setDuration(200);
        LayoutAnimationController controller=new LayoutAnimationController(scaleAnimation,0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        ll.setLayoutAnimation(controller);
    }


    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt10.newInstance());
            }
        });

    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

}
