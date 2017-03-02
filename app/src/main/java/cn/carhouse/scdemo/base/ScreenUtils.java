package cn.carhouse.scdemo.base;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Administrator
 * on 2017/3/1.
 * des:
 */

public class ScreenUtils {

    /**
     * 获取屏幕相关参数
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * 获取屏幕密度
     *
     * @param context
     * @return
     */
    public static float getDeviceDensity(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        display.getMetrics(displayMetrics);
        return displayMetrics.density;
    }
}
