package cn.carhouse.scdemo.fmt;

import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
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
 * des:svg动画实例
 * <p>
 * ：步骤：
 * 1.创建一个静态的svg动画，即vectorDrawable xml文件========》instance_01.xml
 * 2.创建一个变换的objectanimatro动画=====》instance_ani_01.xml
 * 3.使用animateredvectorDrawable将上面两个文件粘合 ======》
 */

public class Fmt11 extends BaseTitleFmt {


    public static Fmt11 newInstance() {

        Bundle args = new Bundle();
        Fmt11 fragment = new Fmt11();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;
    private TextView tv1;
    private ImageView iv;
    private ImageView iv1;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_016;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        iv = (ImageView) view.findViewById(R.id.iv);
        iv1 = (ImageView) view.findViewById(R.id.iv1);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText(this.getClass().getSimpleName() + "=========");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //3d动画
            MyOwnAnimator3D animator = new MyOwnAnimator3D();
            animator.setDuration(2000);
            tv.setAnimation(animator);


            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAnimation();
                }
            });
            iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Drawable drawable = iv1.getDrawable();
                    if (drawable instanceof Animatable) {
                        ((Animatable) drawable).start();
                    }
                }
            });
        }

    }

    private void startAnimation() {
        Drawable drawable = iv.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt12.newInstance());
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = tv1.getBackground();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
//                addFragment(Fmt11.newInstance());
            }
        });

    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

}
