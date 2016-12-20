package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator
 * on 2016/12/14.
 * des:自定义scrollview
 */

public class MyScrollView extends ViewGroup {

    private double mScreenHeight;
    private Scroller mScroller;
    private int mStart;

    public MyScrollView(Context context) {
        super(context);
        initPaint(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenHeight = Math.floor(dm.heightPixels);
        mScroller = new Scroller(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("tag", "onDraw=======");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }
        Log.d("tag", "onMeasure=======");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        layoutParams.height = (int) (childCount * mScreenHeight);//设置viewgroup的高度为孩子个数乘以屏幕高度
        setLayoutParams(layoutParams);

        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != View.GONE) {
                childAt.layout(l, (int) (i * mScreenHeight), r, (int) ((i + 1) * mScreenHeight));
            }
        }

        Log.d("tag", "onLayout===========");
    }

    int mLastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStart = getScrollY();
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = (int) (mLastY - event.getRawY());

                if (getScrollY() < 0) {
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScreenHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                int dScrollY = scrollY - mStart;
                if (dScrollY > 0) {
                    if (dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, (int) (mScreenHeight - dScrollY));
                    }
                } else {
                    if (-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    } else {
                        mScroller.startScroll(0, getScrollY(), 0, (int) (-mScreenHeight - dScrollY));
                    }
                }

                break;
        }
        postInvalidate();
        return true;

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
