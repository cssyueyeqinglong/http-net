package cn.carhouse.scdemo.base;

import android.app.Application;


import org.xutils.x;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/12/1.
 * des:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.pager_error;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.pager_loading_my;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.pager_empty;

        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
    }
}
