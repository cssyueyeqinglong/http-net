package cn.carhouse.scdemo.base;

import android.app.Application;
import android.content.SharedPreferences;


import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.xutils.x;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/12/1.
 * des:
 */

public class MyApplication extends Application {

    public static SharedPreferences mSp;

    public static int mGameGoal;//游戏目标

    public static int mGameLines;//行数

    public static int mItemSize;//单个条目的宽高

    public static int SCORE = 0;//游戏分数

    public static String SP_HIGH_SCORE = "SP_HIGHSCORE";
    public static String KEY_HIGH_SCORE = "KEY_HighScore";
    public static String KEY_GAME_LINES = "KEY_Game_Lines";
    public static String KEY_GAME_GOAL = "KEY_Game_Goal";


    @Override
    public void onCreate() {
        super.onCreate();

        mSp = getSharedPreferences(SP_HIGH_SCORE, 0);
        mGameLines = mSp.getInt(KEY_GAME_LINES, 4);
        mGameGoal = mSp.getInt(KEY_GAME_GOAL, 2048);

        mItemSize = 0;

        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.pager_error;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.pager_loading_my;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.pager_empty;

        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
        Logger.init().logLevel(LogLevel.FULL);
    }
}
