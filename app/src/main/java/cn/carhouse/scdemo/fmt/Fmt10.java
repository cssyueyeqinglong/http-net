package cn.carhouse.scdemo.fmt;

import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.carhouse.scdemo.MyOwnAnimator3D;
import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.BaseTitleFmt;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:布局动画，让viewgroup中的控件出现时显示动画,svg动画
 */

public class Fmt10 extends BaseTitleFmt {


    public static Fmt10 newInstance() {

        Bundle args = new Bundle();
        Fmt10 fragment = new Fmt10();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;
    private LinearLayout ll;
    private ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_015;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        iv = (ImageView) view.findViewById(R.id.iv);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText(this.getClass().getSimpleName() + "=========");

        //布局中加入动画，让子控件动起来
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(200);
        LayoutAnimationController controller = new LayoutAnimationController(scaleAnimation, 0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        ll.setLayoutAnimation(controller);


        //3d动画
        MyOwnAnimator3D animator = new MyOwnAnimator3D();
        animator.setDuration(2000);
        tv.setAnimation(animator);

        //svg动画
        ((Animatable) iv.getDrawable()).start();
    }


    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt11.newInstance());
            }
        });

    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

}
