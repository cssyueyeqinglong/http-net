package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator
 * on 2017/3/17.
 * des:
 */

public class MyCircleView extends View {

    private int mWidth;
    private int mHeight;
    private Paint mCPaint;
    private Path mPath;

    public MyCircleView(Context context) {
        super(context);
        init();
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCPaint = new Paint();
        mCPaint.setColor(Color.GRAY);
        mCPaint.setStyle(Paint.Style.STROKE);
        mCPaint.setStrokeWidth(2);
        mPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth/2, mCPaint);
        canvas.drawCircle(mWidth / 2, mHeight / 2,  mWidth/2-40, mCPaint);

        mPath.moveTo(mWidth/2,0);
//        mPath.lineTo(mWidth/2,40);
//        RectF rectF=new RectF(mWidth/2,0,(int)(mWidth/2+mWidth/2*Math.sin(80)),(int)(mWidth/2-mWidth/2*Math.cos(80)));
        RectF rectF=new RectF(0,0,mWidth,mWidth);
        mPath.addArc(rectF,0,80);
        mCPaint.setStyle(Paint.Style.STROKE);
        mCPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mCPaint);
        canvas.drawArc(0,0,mWidth,mWidth,0,90,true,mCPaint);
//        canvas.rotate(80);
//        canvas.save();//将当前画布保留，后续操作相当于在一个新的画布中操作
//        canvas.translate(mWidth / 2, mHeight / 2);//将原点坐标平移到圆心
//
//        mPath.reset();
//        mPath.lineTo(mWidth/2-60+40,0);
//
//        canvas.drawPath(mPath,mCPaint);
    }
}
