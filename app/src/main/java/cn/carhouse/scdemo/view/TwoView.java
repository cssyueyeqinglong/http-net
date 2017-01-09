package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2017/1/4.
 * des:刮刮乐
 */

public class TwoView extends View {
    private Paint mPaint;
    private Path mPath;
    private Bitmap bitmap;
    private Bitmap bGbitmap;
    private Canvas mCanvas;

    public TwoView(Context context) {
        super(context);
        init(context);
    }

    public TwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TwoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();
        bGbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test01);
        Log.d("lg","bGbitmap.getWidth()=="+bGbitmap.getWidth());
        Log.d("lg","bGbitmap.getWidth()=="+bGbitmap.getHeight());
        bitmap=Bitmap.createBitmap(bGbitmap.getWidth(),bGbitmap.getHeight(), Bitmap.Config.ARGB_8888);

        mCanvas=new Canvas(bitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bGbitmap,0,0,null);
        canvas.drawBitmap(bitmap,0,0,null);
    }
}
