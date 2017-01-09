package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2017/1/5.
 * des:各种笔触效果绘制出来的路径和垂直镜像
 */

public class ThreeView extends View {

    private Paint mPaint;
    private Bitmap srcBitmap;
    private Bitmap finalBitmap;
    private Path mPath;
    private Paint linePaint;
    PathEffect[] mEffects = new PathEffect[6];

    public ThreeView(Context context) {
        super(context);
        init(context);
    }

    public ThreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ThreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        linePaint = new Paint();
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mPaint.setShader(new LinearGradient(0, srcBitmap.getHeight(), 0, srcBitmap.getHeight() + srcBitmap.getHeight() / 4, 0xdd000000, 0x10000000, Shader.TileMode.CLAMP));
        Matrix matrix = new Matrix();
        matrix.setScale(1f, -1f);//将原图旋转
        matrix.postTranslate(0,srcBitmap.getHeight() * 2);
        finalBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);

        mPath = new Path();
        linePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        canvas.drawBitmap(finalBitmap, 0, srcBitmap.getHeight(), null);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, srcBitmap.getHeight(), srcBitmap.getWidth(), srcBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);

        mPath.moveTo(20, 0);
        for (int i = 0; i < 30; i++) {
            mPath.lineTo(35 * i, (float) (100 * Math.random()));
        }

        mEffects[0] = null;
        mEffects[1] = new CornerPathEffect(30);
        mEffects[2] = new DiscretePathEffect(3f, 5f);
        mEffects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
        mPath.addRect(0, 0, 8, 8, Path.Direction.CCW);
        mEffects[4] = new PathDashPathEffect(mPath, 12, 0, PathDashPathEffect.Style.ROTATE);
        mEffects[5] = new ComposePathEffect(mEffects[3], mEffects[1]);

        for (int i = 0; i < mEffects.length; i++) {
            linePaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath, linePaint);
            canvas.translate(0, 200);
        }
    }
}
