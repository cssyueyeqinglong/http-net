package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/12/13.
 * des:条形图
 */

public class RecfView extends View {

    private int mWidth;
    private int mHeight;
    private int[] flags = new int[]{30, 70, 40, 80, 20, 50, 80};
    private int itemWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;

    public RecfView(Context context) {
        super(context);
        initPaint();
    }

    public RecfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RecfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("tag", "onSizeChanged");
        mWidth = getWidth();
        mHeight = getHeight();
        mLinearGradient = new LinearGradient(0, mHeight, (float) (mWidth * 0.6 ), (float) (mWidth * 0.4 ), new int[]{Color.YELLOW, Color.RED, Color.BLUE}, null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("tag", "draw" + System.currentTimeMillis());
        itemWidth = mWidth / 10;
        int offset = 2;
        for (int i = 0; i < flags.length; i++) {
            float left = itemWidth * i + offset * i;
            canvas.drawRect(left, (float) Math.random() * mHeight, left + itemWidth, mHeight, mPaint);
        }

        postInvalidateDelayed(300);
    }
}
