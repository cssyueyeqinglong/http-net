package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator
 * on 2016/12/13.
 * des:芝麻信用图
 */

public class Test01View extends View {
    private float viewWith;
    private float viewHeight;

    private Paint boundsPaint;//边界线画笔
    private int maxScore = 750;
    private int minScore = 550;
    private Paint textPaint;
    private int textSize;

    private String[] monthText = new String[]{"6月", "7月", "8月", "9月", "10月", "11月"};
    private int[] score = new int[]{560, 700, 580, 650, 720, 580};
    private int monthCount = 6;
    private int selectMonth = 6;//选中的月份
    private Paint monthPaint;
    private Path brokenPath;
    private Paint brokenPaint;
    private ArrayList<Point> scorePoints;

    public Test01View(Context context) {
        this(context, null);
    }

    public Test01View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Test01View(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);

    }

    private void initPaint() {

        brokenPath = new Path();

        brokenPaint = new Paint();
        brokenPaint.setAntiAlias(true);
        brokenPaint.setStyle(Paint.Style.STROKE);
        brokenPaint.setStrokeWidth(dip2px(1));
        brokenPaint.setStrokeCap(Paint.Cap.ROUND);


        boundsPaint = new Paint();
        boundsPaint.setAntiAlias(true);
        boundsPaint.setStyle(Paint.Style.STROKE);
        boundsPaint.setStrokeWidth(dip2px(1));
        boundsPaint.setColor(Color.parseColor("#999999"));
        boundsPaint.setStrokeCap(Paint.Cap.ROUND);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor((Color.parseColor("#999999")));
        textPaint.setTextSize(dip2px(14));

        monthPaint = new Paint();
        monthPaint.setAntiAlias(true);
        monthPaint.setStyle(Paint.Style.STROKE);
        monthPaint.setStrokeWidth(dip2px(2));
        monthPaint.setColor(Color.parseColor("#eeeeee"));
        monthPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWith = w;
        viewHeight = h;
        initData();
    }

    private void initData() {
        scorePoints = new ArrayList<>();
        float maxScoreYCoordinate = viewHeight * 0.15f;
        float minScoreYCoordinate = viewHeight * 0.5f;

        Log.v("ScoreTrend", "initData: " + maxScoreYCoordinate);

        float newWith = viewWith - (viewWith * 0.15f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        int coordinateX;

        for (int i = 0; i < score.length; i++) {
            Log.v("ScoreTrend", "initData: " + score[i]);
            Point point = new Point();
            coordinateX = (int) (newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.15f));
            point.x = coordinateX;
            if (score[i] > maxScore) {
                score[i] = maxScore;
            } else if (score[i] < minScore) {
                score[i] = minScore;
            }
            point.y = (int) (((float) (maxScore - score[i]) / (maxScore - minScore)) * (minScoreYCoordinate - maxScoreYCoordinate) + maxScoreYCoordinate);
            scorePoints.add(point);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBounds(canvas, viewWith * 0.15f, viewHeight * 0.15f, viewWith, viewHeight * 0.15f);
        drawBounds(canvas, viewWith * 0.15f, viewHeight * 0.5f, viewWith, viewHeight * 0.5f);

        drawText(canvas);
        drawMonthLine(canvas);
        drawBrokenLine(canvas);

        drawPoints(canvas);
    }

    /**
     * 画上下边界线
     * * @param canvas 画布
     *
     * @param startX 起始点X坐标
     * @param startY 起始点Y坐标
     * @param stopX  终点X坐标
     * @param stopY  终点Y坐标
     */
    private void drawBounds(Canvas canvas, float startX, float startY, float stopX, float stopY) {
        boundsPaint.setPathEffect(new DashPathEffect(new float[]{40, 10}, 0));//设置绘制路径的效果，如点画线等
        boundsPaint.setStrokeWidth(dip2px(1));

        // 实例化路径
        Path mPath = new Path();
        mPath.reset();
        // 定义路径的起点
        mPath.moveTo(startX, startY);
        mPath.lineTo(stopX, stopY);
        canvas.drawPath(mPath, boundsPaint);
    }


    private void drawText(Canvas canvas) {
        canvas.drawText(String.valueOf(maxScore), viewWith * 0.1f - dip2px(10), viewHeight * 0.15f, textPaint);
        canvas.drawText(String.valueOf(minScore), viewWith * 0.1f - dip2px(10), viewHeight * 0.5f, textPaint);


        textPaint.setColor(0xff7c7c7c);

        float newWith = viewWith - (viewWith * 0.15f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        float coordinateX;//分隔线X坐标
        textPaint.setTextSize(dip2px(12));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.parseColor("#ff0f00"));
        textSize = (int) textPaint.getTextSize();
        for (int i = 0; i < monthText.length; i++) {
            coordinateX = newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.15f);

            if (i == selectMonth - 1) {

                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setColor(Color.parseColor("#ff0000"));
                RectF r2 = new RectF();
                r2.left = coordinateX - textSize - dip2px(4);
                r2.top = viewHeight * 0.7f + dip2px(4) + textSize / 2;
                r2.right = coordinateX + textSize + dip2px(4);
                r2.bottom = viewHeight * 0.7f + dip2px(4) + textSize + dip2px(8);
                canvas.drawRoundRect(r2, 10, 10, textPaint);

            }
            //绘制月份
            canvas.drawText(monthText[i], coordinateX, viewHeight * 0.7f + dip2px(4) + textSize + dip2px(5), textPaint);

            textPaint.setColor(Color.parseColor("#ff0f00"));

        }
    }

    //绘制月份的直线(包括刻度)
    private void drawMonthLine(Canvas canvas) {
        monthPaint.setStrokeWidth(dip2px(2));
        canvas.drawLine(0, viewHeight * 0.7f, viewWith, viewHeight * 0.7f, monthPaint);

        float newWith = viewWith - (viewWith * 0.15f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        float coordinateX;//分隔线X坐标
        for (int i = 0; i < monthCount; i++) {
            coordinateX = newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.15f);
            canvas.drawLine(coordinateX, viewHeight * 0.7f, coordinateX, viewHeight * 0.7f + dip2px(4), monthPaint);
        }
    }

    //绘制折线
    private void drawBrokenLine(Canvas canvas) {
        brokenPath.reset();
        brokenPaint.setColor(0xff02bbb7);
        brokenPaint.setStyle(Paint.Style.STROKE);
        if (score.length == 0) {
            return;
        }
        Log.v("ScoreTrend", "drawBrokenLine: " + scorePoints.get(0));
        brokenPath.moveTo(scorePoints.get(0).x, scorePoints.get(0).y);
        for (int i = 0; i < scorePoints.size(); i++) {
            brokenPath.lineTo(scorePoints.get(i).x, scorePoints.get(i).y);
        }
        canvas.drawPath(brokenPath, brokenPaint);

    }

    private void drawPoints(Canvas canvas) {
        if (scorePoints.size() == 0) return;
        brokenPaint.setStrokeWidth(dip2px(1));
        for (int i = 0; i < scorePoints.size(); i++) {
            brokenPaint.setColor(0xff0055ff);
            brokenPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dip2px(3), brokenPaint);
            brokenPaint.setColor(Color.WHITE);
            brokenPaint.setStyle(Paint.Style.FILL);
            if (i == selectMonth - 1) {
                brokenPaint.setColor(0xffd0f3f2);
                canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dip2px(8f), brokenPaint);
                brokenPaint.setColor(0xff81dddb);
                canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dip2px(5f), brokenPaint);

                //绘制浮动文本背景框
                drawFloatTextBackground(canvas, scorePoints.get(i).x, scorePoints.get(i).y - dip2px(8f));

                textPaint.setColor(0xffffffff);
                //绘制浮动文字
                canvas.drawText(String.valueOf(score[i]), scorePoints.get(i).x, scorePoints.get(i).y - dip2px(5f) - textSize, textPaint);
            }

            brokenPaint.setColor(0xffffffff);
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dip2px(1.5f), brokenPaint);
            brokenPaint.setStyle(Paint.Style.STROKE);
            brokenPaint.setColor(0xff0055ff);
            canvas.drawCircle(scorePoints.get(i).x, scorePoints.get(i).y, dip2px(2.5f), brokenPaint);
        }

    }

    private void drawFloatTextBackground(Canvas canvas, int x, int y) {
        brokenPath.reset();
        brokenPaint.setColor(0xffd0f3f2);
        brokenPaint.setStyle(Paint.Style.FILL);

        //P1
        Point point = new Point(x, y);
        brokenPath.moveTo(point.x, point.y);

        //P2
        point.x = point.x + dip2px(5);
        point.y = point.y - dip2px(5);
        brokenPath.lineTo(point.x, point.y);

        //P3
        point.x = point.x + dip2px(12);
        brokenPath.lineTo(point.x, point.y);

        //P4
        point.y = point.y - dip2px(17);
        brokenPath.lineTo(point.x, point.y);

        //P5
        point.x = point.x - dip2px(34);
        brokenPath.lineTo(point.x, point.y);

        //P6
        point.y = point.y + dip2px(17);
        brokenPath.lineTo(point.x, point.y);

        //P7
        point.x = point.x + dip2px(12);
        brokenPath.lineTo(point.x, point.y);

        //最后一个点连接到第一个点
        brokenPath.lineTo(x, y);

        canvas.drawPath(brokenPath, brokenPaint);

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
            if (x > (scorePoints.get(i).x - dip2px(8) * 2) && x < (scorePoints.get(i).x + dip2px(8) * 2)) {
                if (y > (scorePoints.get(i).y - dip2px(8) * 2) && y < (scorePoints.get(i).y + dip2px(8) * 2)) {
                    selectMonth = i + 1;
                    return true;
                }
            }
        }

        //月份触摸区域
        //计算每个月份X坐标的中心点
        float monthTouchY = viewHeight * 0.7f - dip2px(3);//减去dipToPx(3)增大触摸面积

        float newWith = viewWith - (viewWith * 0.15f) * 2;//分隔线距离最左边和最右边的距离是0.15倍的viewWith
        float validTouchX[] = new float[monthText.length];
        for (int i = 0; i < monthText.length; i++) {
            validTouchX[i] = newWith * ((float) (i) / (monthCount - 1)) + (viewWith * 0.15f);
        }

        if (y > monthTouchY) {
            for (int i = 0; i < validTouchX.length; i++) {
                Log.v("ScoreTrend", "validateTouch: validTouchX:" + validTouchX[i]);
                if (x < validTouchX[i] + dip2px(8) && x > validTouchX[i] - dip2px(8)) {
                    Log.v("ScoreTrend", "validateTouch: " + (i + 1));
                    selectMonth = i + 1;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
