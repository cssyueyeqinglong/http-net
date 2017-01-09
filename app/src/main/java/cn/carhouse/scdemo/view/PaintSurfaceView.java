package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator
 * on 2017/1/7.
 * des://画图板
 */

public class PaintSurfaceView extends BaseSurfaceView {

    private Path mPath;
    private Paint mPaint;

    public PaintSurfaceView(Context context) {
        super(context);
        initView(context);
    }

    public PaintSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PaintSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setTextSize(20);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void drawSomeThing(Canvas canvas) {
        canvas.drawColor(Color.WHITE);//清除画布之前的内容
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void afterDrawing(Canvas canvas) {
        super.afterDrawing(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("dx", "event====================");
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
//                float dx = x - startx;
//                Log.d("dx", "dx==" + dx);
//                mCanvas.translate(dx, 0);
                break;
        }
        return true;
    }
}
