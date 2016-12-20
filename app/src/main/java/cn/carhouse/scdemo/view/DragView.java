package cn.carhouse.scdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by Administrator
 * on 2016/12/15.
 * des:可以随着屏幕滑动跟着滑动的view
 */

public class DragView extends View {
    private int startX, startY;
    private Scroller mScroller;

    public DragView(Context context) {
        super(context);
        init(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int xOffset = x - startX;
                int yOffset = y - startY;
//                layout(getLeft() + (x - startX),getTop()+(y-startY),getRight()+(x - startX),getBottom()+(y-startY));
//                offsetLeftAndRight(xOffset);
//                offsetTopAndBottom(yOffset);
                ((View) getParent()).scrollBy(-xOffset, -yOffset);

                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(), -viewGroup.getScrollX(), -viewGroup.getScrollY());////这里是通过父控件来获取view的滑动坐标
                invalidate();
                break;

        }
        return true;
    }

    @Override
    public void computeScroll() {//该方法在onDraw中调用，实现模拟滑动
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {//scroller是否执行完毕
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
