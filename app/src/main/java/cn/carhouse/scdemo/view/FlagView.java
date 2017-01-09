package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2017/1/3.
 * des:旗帜飘扬
 */

public class FlagView extends View {

    private Bitmap bitmap;
    private int WIDTH = 200;
    private int HEIGHT = 200;
    private int A = 2;
    private float[] orig;
    private float[] verts;
    private float width;
    private float height;
    private Paint mPaint;

    public FlagView(Context context) {
        super(context);
        init(context);
    }

    public FlagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FlagView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        mPaint = new Paint();

    }

    private void init(Context context) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_road5);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        WIDTH = getWidth();
        HEIGHT = getHeight();
        orig = new float[WIDTH * HEIGHT * 2];
        verts = new float[WIDTH * HEIGHT * 2];

        int index = 0;
        for (int y = 0; y < HEIGHT; y++) {
            float fy = height * y / HEIGHT;
            for (int x = 0; x < WIDTH; x++) {
                float fx = width * x / WIDTH;
                orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy + 100;
                index += 1;
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        flagWave();
//        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
//        invalidate();

        Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    private void flagWave() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if((j * (WIDTH + 1) + i) * 2 + 0<verts.length-1&&(j * (WIDTH + 1) + i) * 2 + 1<verts.length-1){
                    verts[(j * (WIDTH + 1) + i) * 2 + 0] += 0;
                    float offsetY = (float) Math.sin((float) i / WIDTH * 2 * Math.PI);
                    verts[(j * (WIDTH + 1) + i) * 2 + 1] = orig[(j * WIDTH + i) * 2 + 1] + offsetY * A;
                }
            }
        }
    }
}
