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
 * des:绘制正弦曲线
 */

public class MySurfaceView extends BaseSurfaceView {

    private Path mPath;
    private Paint mPaint;

    private int x;
    private int y = 400;
    private float startx;

    public MySurfaceView(Context context) {
        super(context);
        initView(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mPath = new Path();
        mPath.moveTo(x, y);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void drawSomeThing(Canvas canvas) {
        canvas.drawColor(Color.WHITE);//清除画布之前的内容
        canvas.drawPath(mPath, mPaint);

    }

    @Override
    protected void afterDrawing(Canvas canvas) {
        x += 1;
        y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
        mPath.lineTo(x, y);
        canvas.translate(x, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("dx", "event====================");
        float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startx = x;
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                float dx = x - startx;
                Log.d("dx", "dx==" + dx);
//                mCanvas.translate(dx, 0);
                break;
        }
        return true;
    }
}
