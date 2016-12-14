package cn.carhouse.scdemo.view;

import android.content.Context;
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
        }


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
    }

    int mLastY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = (int) event.getRawY();
                Log.d("tag", "otionEvent.ACTION_DOWN======" + event.getRawY() + ",mScreenHeight===" + mScreenHeight);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("tag", "otionEvent.ACTION_MOVE======" + event.getRawY());
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int dy = (int) (mLastY - event.getRawY());

                Log.d("tag", "dy======" + dy);
                Log.d("tag", "getScrollY======" + getScrollY());
                Log.d("tag", "getHeight======" + getHeight());
                if (getScrollY() < 0) {
                    dy = getScrollY() + dy;
                }
//                if (getScrollY() > getHeight() - mScreenHeight) {
//                    dy = 0;
//                }
                scrollBy(0, dy);
                mLastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
//        return super.onTouchEvent(event);

    }
}
