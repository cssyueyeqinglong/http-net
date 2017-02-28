package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import cn.carhouse.scdemo.R;

import static org.xutils.common.util.DensityUtil.dip2px;

/**
 * Created by Administrator
 * on 2017/2/23.
 * des:
 */

public class Test11View extends View {

    private Paint linePaint;
    private int viewWith;
    private int viewHeight;
    private String[] moneyText = new String[]{"0", "10", "20", "50", "100", "200"};
    private int moneyCount = 6;
    private Paint textPaint;
    private int selectedMoney = 0;
    private ArrayList<Point> scorePoints;
    private Bitmap bitmap;

    public Test11View(Context context) {
        super(context);
        initText(context);
    }

    public Test11View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initText(context);
    }

    public Test11View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initText(context);
    }

    private void initText(Context context) {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(dip2px(4));
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setColor(Color.parseColor("#e6e6e6"));

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor((Color.parseColor("#dd2828")));
        textPaint.setTextSize(dip2px(16));

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.selected_money);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWith = w;
        viewHeight = h;
        initData();
    }

    //计算点的坐标
    private void initData() {
        scorePoints = new ArrayList<Point>();
        float newWith = viewWith - (viewWith * 0.1f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        int coordinateX;
        for (int i = 0; i < moneyText.length; i++) {
            Log.v("ScoreTrend", "initData: " + moneyText[i]);
            Point point = new Point();
            coordinateX = (int) (newWith * ((float) (i) / (moneyCount - 1)) + (viewWith * 0.1f));
            point.x = coordinateX;
            point.y = (int) (viewHeight * 0.4f);
            scorePoints.add(point);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(viewWith * 0.1f, viewHeight * 0.4f, viewWith * 0.9f, viewHeight * 0.4f, linePaint);
        drawText(canvas);
        drawPoint(canvas);
    }

    //绘制money文本
    private void drawText(Canvas canvas) {
        float coordinateX;
        int textSize;
        int monthCount = moneyText.length;
        float newWith = viewWith - (viewWith * 0.1f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        textSize = (int) textPaint.getTextSize();
        for (int i = 0; i < moneyText.length; i++) {
            coordinateX = newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.1f);
            canvas.drawText(moneyText[i], coordinateX, viewHeight * 0.6f + dip2px(4) + textSize + dip2px(5), textPaint);
        }
    }

    //绘制点坐标
    private void drawPoint(Canvas canvas) {
        if (scorePoints.size() == 0) return;
        textPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < scorePoints.size(); i++) {
            Point point = scorePoints.get(i);
            canvas.drawCircle(point.x, point.y, dip2px(10), textPaint);
            if (i < selectedMoney) {
                textPaint.setColor(Color.parseColor("#dd2828"));
            } else {
                textPaint.setColor(Color.parseColor("#E6E6E6"));
            }
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dip2px(10), textPaint);
        }
        if (selectedMoney > 0) {
            linePaint.setColor(Color.parseColor("#dd2828"));
            canvas.drawLine(scorePoints.get(0).x, scorePoints.get(0).y, scorePoints.get(selectedMoney).x, scorePoints.get(selectedMoney).y, linePaint);
            canvas.drawBitmap(bitmap,scorePoints.get(selectedMoney).x-bitmap.getWidth()/2, scorePoints.get(selectedMoney).y-bitmap.getHeight()/2,new Paint());
        }
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(Color.parseColor("#dd2828"));
        linePaint.setColor(Color.parseColor("#e6e6e6"));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                onActionUpEvent(event);
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return true;
    }

    private void onActionUpEvent(MotionEvent event) {
        boolean isValidTouch = validateTouch(event.getX(), event.getY());

        if (isValidTouch) {
            invalidate();//请求重绘
        }
    }

    //是否是有效的触摸范围
    private boolean validateTouch(float x, float y) {

        //曲线触摸区域
        for (int i = 0; i < scorePoints.size(); i++) {
            // dipToPx(8)乘以2为了适当增大触摸面积
            if (x > (scorePoints.get(i).x - dip2px(10) * 2) && x < (scorePoints.get(i).x + dip2px(10) * 2)) {
                if (y > (scorePoints.get(i).y - dip2px(10) * 2) && y < (scorePoints.get(i).y + dip2px(10) * 2)) {
                    selectedMoney = i;
                    return true;
                }
            }
        }

        //money触摸区域
        //计算每个money X坐标的中心点
        float monthTouchY = viewHeight * 0.6f - dip2px(20);//减去dipToPx(3)增大触摸面积

        float newWith = viewWith - (viewWith * 0.1f) * 2;//分隔线距离最左边和最右边的距离是0.1倍的viewWith
        float validTouchX[] = new float[moneyText.length];
        for (int i = 0; i < moneyText.length; i++) {
            validTouchX[i] = newWith * ((float) (i) / (moneyCount - 1)) + (viewWith * 0.1f);
        }

        if (y > monthTouchY) {
            for (int i = 0; i < validTouchX.length; i++) {
                Log.v("ScoreTrend", "validateTouch: validTouchX:" + validTouchX[i]);
                if (x < validTouchX[i] + dip2px(10) && x > validTouchX[i] - dip2px(10)) {
                    Log.v("ScoreTrend", "validateTouch: " + (i + 1));
                    selectedMoney = i;
                    return true;
                }
            }
        }
        return false;
    }
}
