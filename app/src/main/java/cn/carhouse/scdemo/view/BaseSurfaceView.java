package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator
 * on 2017/1/7.
 * des:自定义surfaceView的模板代码
 * surfaceView和view的区别
 * <p>
 * 1.view是适用于主动刷新，surfaceView是适用于被动刷新（如频繁刷新）
 * 2.view是在主线程中刷新，而surfaceView是在子线程中刷新
 * 3.surfaceView使用了双缓存机制
 */

public class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;//初始化画布
    private boolean isOnDrawing;//子线程标志位

    public BaseSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public BaseSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

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

    private void draw() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
            //drawSomeThing
            drawSomeThing(mCanvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {//此方法放在final中保证每次绘制的内容都能够提交
            if (mCanvas != null) {
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
        afterDrawing(mCanvas);
    }

    protected void drawSomeThing(Canvas canvas) {

    }

    protected void afterDrawing(Canvas canvas) {

    }
}
