package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/12/13.
 * des:画圆
 */

public class CirclView extends View {

    private int mWidth;
    private int mHeight;
    private int mCricleXY;
    private float mRadious;
    private Paint mCirclePaint;
    private Paint paintDegree;

    public CirclView(Context context) {
        super(context);
        initPaint();
    }

    public CirclView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    public CirclView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    private void initPaint() {
        mCirclePaint = new Paint();
        mCirclePaint.setColor(getResources().getColor(R.color.colorAccent));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setAntiAlias(true);

        paintDegree = new Paint();
        paintDegree.setColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getContentWidth(widthMeasureSpec),
                getContentWidth(heightMeasureSpec));

        Log.d("tag", "onMeasure===========");
    }

    private int getContentWidth(int widthMeasureSpec) {
        int lastZize = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            lastZize = size;
        } else {
            lastZize = 200;
            if (mode == MeasureSpec.AT_MOST) {
                lastZize = Math.min(lastZize, size);
            }
        }
        return lastZize;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        Log.d("tag", "onSizeChanged===========");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCricleXY = mWidth / 2;
        mRadious = mWidth / 2F;


        canvas.drawCircle(mCricleXY, mCricleXY, mRadious, mCirclePaint);//画圆


        paintDegree.setStrokeWidth(3);
        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
                String text = i + "";
                canvas.drawText(text, mWidth / 2 - paintDegree.measureText(text) / 2, mHeight / 2 - mWidth / 2 + 60 + 30 + 4, paintDegree);
            } else {
                paintDegree.setStrokeWidth(2);
                paintDegree.setTextSize(24);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 30, paintDegree);
                String text = i + "";
                canvas.drawText(text, mWidth / 2 - text.length() / 2, mHeight / 2 - mWidth / 2 + 30 + 30 + 4, paintDegree);
            }

            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }

        canvas.save();//将当前画布保留，后续操作相当于在一个新的画布中操作
        //画指针
        paintDegree.setStrokeWidth(20);
        canvas.translate(mWidth / 2, mHeight / 2);//将原点坐标平移到圆心
        canvas.drawLine(0,0,100,100,paintDegree);

        paintDegree.setStrokeWidth(10);
        canvas.drawLine(0,0,100,300,paintDegree);

        canvas.restore();//将新画出来的画布和之前保存的画布保存
    }
}
