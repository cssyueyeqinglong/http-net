package cn.carhouse.scdemo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Administrator
 * on 2016/12/19.
 * des:仿qq侧滑
 */

public class DragHelperView extends FrameLayout {

    private ViewDragHelper mViewDragHelper;
    private View mMainView;
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mMainView == child;//如果当前触摸的child是mMainView时开始检测，意味着只有mainview是可以拖动的
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {//水平方向的滑动。默认情况下是0，即为不滑动
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {//top代表child在竖直方向上移动的距离，dy代表与上一次移动的增量
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {//拖动结束后调用
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓缓移动到指定位置
            Log.e("getLeft", "getLeft===" + mMainView.getLeft());
            if (mMainView.getLeft() < 500) {
                //关闭菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(DragHelperView.this);
            } else {
//打开菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(DragHelperView.this);
            }
        }
    };
    private int mScreenWidth;
    private View mMenuView;
    private int mWidth;

    public DragHelperView(Context context) {
        super(context);
        init(context);
    }


    public DragHelperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public DragHelperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMainView.getMeasuredWidth();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);//将触摸事件传递给viewdraghelper
        return true;
    }

    @Override
    public void computeScroll() {//模拟平滑移动的过程
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);

    }
}
