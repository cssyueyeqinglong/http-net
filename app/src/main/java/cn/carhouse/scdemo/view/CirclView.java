package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
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
    private RectF mArcRecf;
    private Paint mCirclePaint;
    private Paint mArcPaint;

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

        mArcPaint = new Paint();
        mArcPaint.setColor(getResources().getColor(R.color.colorAccent));
        mArcPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getContentWidth(widthMeasureSpec),
                getContentWidth(heightMeasureSpec));
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCricleXY = mWidth / 2;
        mRadious = mWidth / 2F;

        mArcRecf = new RectF(mWidth * 0.1F, mWidth * 0.1F, mWidth * 0.9F, mWidth * 0.9F);

        canvas.drawCircle(mCricleXY, mCricleXY, mRadious, mCirclePaint);//画圆

        canvas.drawArc(mArcRecf, 270, 80, true, mCirclePaint);
    }
}
