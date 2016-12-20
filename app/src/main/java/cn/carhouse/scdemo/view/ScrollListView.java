package cn.carhouse.scdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by Administrator
 * on 2016/12/15.
 * des:带有弹性的listview
 */

public class ScrollListView extends ListView {

    private int maxOverScrollDistance = 200;

    public ScrollListView(Context context) {
        super(context);
        initViews(context);
    }

    public ScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);

    }

    public ScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);

    }

    private void initViews(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float density = metrics.density;
        maxOverScrollDistance = (int) (density * maxOverScrollDistance);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollDistance, isTouchEvent);
    }
}
