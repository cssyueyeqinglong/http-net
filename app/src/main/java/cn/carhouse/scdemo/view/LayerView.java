package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator
 * on 2016/12/20.
 * des:layer图层view
 */

public class LayerView extends View {

    private Paint mPaint;
    private int LAYER_FLAGS=100;

    public LayerView(Context context) {
        super(context);
        init(context);
    }

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150,150,100,mPaint);

        canvas.saveLayerAlpha(0,0,400,400,255,Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(Color.RED);
        canvas.drawCircle(200,200,100,mPaint);
        canvas.restore();
    }
}
