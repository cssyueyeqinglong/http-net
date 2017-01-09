package cn.carhouse.scdemo.fmt;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.BaseTitleFmt;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:颜色矩阵
 */

public class Fmt06 extends BaseTitleFmt {

    private Bitmap bitmap;
    private Bitmap mOut;

    public static Fmt06 newInstance() {

        Bundle args = new Bundle();

        Fmt06 fragment = new Fmt06();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;
    private ImageView iv;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_011;
    }

    @Override
    protected void initView(View view) {
        iv = (ImageView) view.findViewById(R.id.iv);
        tv = (TextView) view.findViewById(R.id.tv);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText("Fmt03==========");


        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_road5);
        iv.setImageBitmap(bitmap);
        mOut = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }


    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Canvas canvas = new Canvas(mOut);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    canvas.drawRoundRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), 80, 80, paint);
                }
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, 0, 0, paint);
                iv.setImageBitmap(mOut);
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt07.newInstance());
            }
        });
    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

}
