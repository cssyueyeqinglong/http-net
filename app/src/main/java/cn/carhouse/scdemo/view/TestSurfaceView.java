package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator
 * on 2017/1/7.
 * des:
 */

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;//初始化画布
    private boolean isOnDrawing;//子线程标志位
    private Path mPath;
    private Paint mPaint;

    private int x;
    private int y = 400;

    public TestSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public TestSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setTextSize(20);
        mPaint.setColor(Color.BLACK);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isOnDrawing = true;
        new Thread(this).start();//在此开启子线程进行绘制
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isOnDrawing = false;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (isOnDrawing) {
            draw();
        }
        long endTime = System.currentTimeMillis();
        if (endTime - startTime < 100) {//不用绘制的太频繁，节约资源
            try {
                SystemClock.sleep(100 - (endTime - startTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            //drawSomeThing
            mCanvas.drawColor(Color.WHITE);//surfaceView背景
            mCanvas.drawPath(mPath, mPaint);
            mCanvas.translate(100,0);
            mCanvas.restore();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {//此方法放在final中保证每次绘制的内容都能够提交
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
        x += 1;
        y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
        mPath.lineTo(x, y);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d("dx", "event====================");
//        float x = event.getX();
//        float y = event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mPath.moveTo(x,y);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mPath.lineTo(x,y);
//                break;
//            case MotionEvent.ACTION_UP:
////                float dx = x - startx;
////                Log.d("dx", "dx==" + dx);
////                mCanvas.translate(dx, 0);
//                break;
//        }
//        return true;
//    }
}
