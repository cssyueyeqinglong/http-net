package cn.carhouse.scdemo.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.carhouse.scdemo.base.MyApplication;

/**
 * Created by Administrator
 * on 2017/3/2.
 * des:
 */

public class GameItem extends FrameLayout {

    private int mCardShowNum;//要显示在item的数据
    private TextView mTvNum;
    private LayoutParams mParams;


    public GameItem(Context context) {
        super(context);
    }

    public GameItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameItem(Context context, int cardShowNum) {
        super(context);
        this.mCardShowNum = cardShowNum;
        //初始化item
        initCardItem();
    }

    private void initCardItem() {
        setBackgroundColor(Color.GRAY);
        mTvNum = new TextView(getContext());
        setNum(mCardShowNum);
        //修改不同布局的字体大小问题
        int gameLines = MyApplication.mSp.getInt(MyApplication.KEY_GAME_LINES, 4);//获取行数
        if (gameLines == 4) {
            mTvNum.setTextSize(35);
        } else if (gameLines == 5) {
            mTvNum.setTextSize(25);
        } else {
            mTvNum.setTextSize(20);
        }
        TextPaint textPaint = mTvNum.getPaint();
        textPaint.setFakeBoldText(true);
        mTvNum.setGravity(Gravity.CENTER);

        mParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mParams.setMargins(5, 5, 5, 5);
        addView(mTvNum, mParams);
    }

    public void setNum(int showNum) {
        this.mCardShowNum = showNum;
        if (showNum == 0) {
            mTvNum.setText("0");
        } else {
            mTvNum.setText("" + showNum);
        }
        int color;
        switch (showNum) {
            case 0:
                color = 0x00000000;
                break;
            case 2:
                color = 0xffeee5db;
                break;
            case 4:
                color = 0xffeee0ca;
                break;
            case 8:
                color = 0xfff2c17a;
                break;
            case 16:
                color = 0xfff59667;
                break;
            case 32:
                color = 0xfff68c6f;
                break;
            case 64:
                color = 0xfff66e3c;
                break;
            case 128:
                color = 0xffedcf74;
                break;
            case 256:
                color = 0xffedcc64;
                break;
            case 512:
                color = 0xffedc854;
                break;
            case 1024:
                color = 0xffedc54f;
                break;
            case 2048:
                color = 0xffedc32e;
                break;
            default:
                color = 0xff3c4a34;
                break;
        }
        mTvNum.setBackgroundColor(color);
    }

    public int getNum() {
        return mCardShowNum;
    }

    public View getItemView() {
        return mTvNum;
    }
}
