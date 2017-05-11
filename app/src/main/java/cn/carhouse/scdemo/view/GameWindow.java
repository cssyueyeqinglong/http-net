package cn.carhouse.scdemo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;

import cn.carhouse.scdemo.base.MyApplication;
import cn.carhouse.scdemo.base.ScreenUtils;

/**
 * Created by Administrator
 * on 2017/3/2.
 * des:游戏面板
 */

public class GameWindow extends GridLayout implements View.OnTouchListener {
    private int mScoreHistory;
    private int mGameLines;
    private GameItem[][] mGameMatrix;
    private int[][] mGameMatrixHistory;
    private ArrayList<Integer> mCalList;
    private ArrayList<Point> mBlanks;
    private int mHighScore;
    private float mStartX;
    private float mStartY;
    private float mEndX;
    private float mEndY;
    private int mKeyItemNum = -1;
    private int mTarget;

    public GameWindow(Context context) {
        super(context);
        initGameMartrix();
    }

    public GameWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameMartrix();
    }

    public GameWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameMartrix();
    }

    /**
     * 初始化View
     */
    private void initGameMartrix() {
        //初始化矩阵
        removeAllViews();
        mScoreHistory = 0;
        MyApplication.SCORE = 0;
        MyApplication.mGameLines = MyApplication.mSp.getInt(MyApplication.KEY_GAME_LINES, 4);
        mGameLines = MyApplication.mGameLines;
        mGameMatrix = new GameItem[mGameLines][mGameLines];
        mGameMatrixHistory = new int[mGameLines][mGameLines];
        mCalList = new ArrayList<Integer>();
        mBlanks = new ArrayList<Point>();
        mHighScore = MyApplication.mSp.getInt(MyApplication.KEY_HIGH_SCORE, 0);
        mTarget = MyApplication.mSp.getInt(MyApplication.KEY_GAME_GOAL, 2048);
        setColumnCount(mGameLines);
        setRowCount(mGameLines);
        setOnTouchListener(this);
        //初始化view参数
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        MyApplication.mItemSize = metrics.widthPixels / MyApplication.mGameLines;
        initGameView(MyApplication.mItemSize);

    }

    private void initGameView(int cardSize) {
        removeAllViews();
        GameItem card;
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                card = new GameItem(getContext(), 0);
                addView(card, cardSize, cardSize);
                //初始化GameMatrix全部为0，空格list为所有元素
                mGameMatrix[i][j] = card;
                mBlanks.add(new Point(i, j));
            }
        }

        //添加随机数据
        addRandomNum();
        addRandomNum();
    }

    private void addRandomNum() {
        getBlanks();
        if (mBlanks.size() > 0) {
            int randowNum = (int) (Math.random() * mBlanks.size());
            Point randomPoint = mBlanks.get(randowNum);
            mGameMatrix[randomPoint.x][randomPoint.y].setNum(Math.random() > 0.2d ? 2 : 4);
            animCreate(mGameMatrix[randomPoint.x][randomPoint.y]);
        }

    }

    /**
     * 生成动画
     *
     * @param item
     */
    private void animCreate(GameItem item) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.1f, 1, 0.1f, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        scaleAnimation.setDuration(100);
        item.setAnimation(null);
        item.getItemView().startAnimation(scaleAnimation);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                saveHistoryMatrix();
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                mEndX = event.getX();
                mEndY = event.getY();
                judgeDirection(mEndX - mStartX, mEndY - mStartY);
                if (isMoved()) {
                    addRandomNum();
                    //修改显示分数
//                    Game.getGameActivity().setScore(MyApplication.SCORE,0);
                }
                checkCompleted();
                break;
        }
        return true;
    }

    /**
     * 判断滑动方向
     *
     * @param dx
     * @param dy
     */
    private void judgeDirection(float dx, float dy) {
        int density = (int) ScreenUtils.getDeviceDensity(getContext());
        int slideDis = 5 * density;
        int maxDis = 200 * density;
        boolean flagNormal = (Math.abs(dx) > slideDis || Math.abs(dy) > slideDis) && (Math.abs(dx) < maxDis) && (Math.abs(dy) < maxDis);
        boolean flagSupper = Math.abs(dx) > maxDis || Math.abs(dy) > maxDis;
        if (flagNormal && !flagSupper) {
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > slideDis) {
                    swipRight();
                } else {
                    swipLeft();
                }
            } else {
                if (dy > slideDis) {
                    swipDown();
                } else {
                    swipUp();
                }
            }
        } else if (flagSupper) {//开启超级权限
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            final EditText et = new EditText(getContext());
            builder.setTitle("Back Door").setView(et).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (!TextUtils.isEmpty(et.getText().toString())) {
                        addSuperNum(Integer.valueOf(et.getText().toString()));
                        checkCompleted();
                    }
                }
            }).setNegativeButton("ByeBye", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    //添加自己想要添加的数字
    private void addSuperNum(int num) {
        getBlanks();
        if (mBlanks.size() > 0) {
            int randowNum = (int) (Math.random() * mBlanks.size());
            Point randomPoint = mBlanks.get(randowNum);
            mGameMatrix[randomPoint.x][randomPoint.y].setNum(num);
        }
    }

    /**
     * 滑动事件左
     */
    private void swipLeft() {
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                int currentNum = mGameMatrix[i][j].getNum();
                if (currentNum != 0) {
                    if (mKeyItemNum == -1) {
                        mKeyItemNum = currentNum;
                    } else {
                        if (mKeyItemNum == currentNum) {
                            mCalList.add(mKeyItemNum * 2);
                            MyApplication.SCORE += mKeyItemNum * 2;
                            mKeyItemNum = -1;
                        } else {
                            mCalList.add(mKeyItemNum);
                            mKeyItemNum = currentNum;
                        }
                    }
                } else {
                    continue;
                }
            }
            if (mKeyItemNum != -1) {
                mCalList.add(mKeyItemNum);
            }
            //改变item值
            for (int j = 0; j < mCalList.size(); j++) {
                mGameMatrix[i][j].setNum(mCalList.get(j));
            }
            for (int j = mCalList.size(); j < mGameLines; j++) {
                mGameMatrix[i][j].setNum(0);
            }
            //重置行参数
            mKeyItemNum = -1;
            mCalList.clear();
        }
    }

    /**
     * 滑动事件:右
     */
    private void swipRight() {
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                int currentNum = mGameMatrix[i][mGameLines-1-j].getNum();
                if (currentNum != 0) {
                    if (mKeyItemNum == -1) {
                        mKeyItemNum = currentNum;
                    } else {
                        if (mKeyItemNum == currentNum) {
                            mCalList.add(mKeyItemNum * 2);
                            MyApplication.SCORE += mKeyItemNum * 2;
                            mKeyItemNum = -1;
                        } else {
                            mCalList.add(mKeyItemNum);
                            mKeyItemNum = currentNum;
                        }
                    }
                } else {
                    continue;
                }
            }
            if (mKeyItemNum != -1) {
                mCalList.add(mKeyItemNum);
            }
            //改变item值
            for (int j = 0; j < mCalList.size(); j++) {
                mGameMatrix[i][mGameLines-1-j].setNum(mCalList.get(j));
            }
            for (int j =0 ; j < mGameLines-mCalList.size(); j++) {
                mGameMatrix[i][j].setNum(0);
            }
            //重置行参数
            mKeyItemNum = -1;
            mCalList.clear();
        }
    }

    /**
     * 滑动事件:上
     */
    private void swipUp() {
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                int currentNum = mGameMatrix[j][i].getNum();
                if (currentNum != 0) {
                    if (mKeyItemNum == -1) {
                        mKeyItemNum = currentNum;
                    } else {
                        if (mKeyItemNum == currentNum) {
                            mCalList.add(mKeyItemNum * 2);
                            MyApplication.SCORE += mKeyItemNum * 2;
                            mKeyItemNum = -1;
                        } else {
                            mCalList.add(mKeyItemNum);
                            mKeyItemNum = currentNum;
                        }
                    }
                } else {
                    continue;
                }
            }
            if (mKeyItemNum != -1) {
                mCalList.add(mKeyItemNum);
            }
            //改变item值
            for (int j = 0; j < mCalList.size(); j++) {
                mGameMatrix[j][i].setNum(mCalList.get(j));
            }
            for (int j = mCalList.size(); j < mGameLines; j++) {
                mGameMatrix[j][i].setNum(0);
            }
            //重置行参数
            mKeyItemNum = -1;
            mCalList.clear();
        }
    }
    /**
     * 滑动事件:下
     */
    private void swipDown() {
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                int currentNum = mGameMatrix[mGameLines-1-j][i].getNum();
                if (currentNum != 0) {
                    if (mKeyItemNum == -1) {
                        mKeyItemNum = currentNum;
                    } else {
                        if (mKeyItemNum == currentNum) {
                            mCalList.add(mKeyItemNum * 2);
                            MyApplication.SCORE += mKeyItemNum * 2;
                            mKeyItemNum = -1;
                        } else {
                            mCalList.add(mKeyItemNum);
                            mKeyItemNum = currentNum;
                        }
                    }
                } else {
                    continue;
                }
            }
            if (mKeyItemNum != -1) {
                mCalList.add(mKeyItemNum);
            }
            //改变item值
            for (int j = 0; j < mCalList.size(); j++) {
                mGameMatrix[mGameLines-1-j][i].setNum(mCalList.get(j));
            }
            for (int j =0 ; j < mGameLines-mCalList.size(); j++) {
                mGameMatrix[j][i].setNum(0);
            }
            //重置行参数
            mKeyItemNum = -1;
            mCalList.clear();
        }
    }

    /**
     * 报保存上一步的矩阵，方便撤销功能
     */
    private void saveHistoryMatrix() {
        mHighScore = MyApplication.SCORE;
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                mGameMatrixHistory[i][j] = mGameMatrix[i][j].getNum();
            }
        }
    }

    /**
     * 判断是否移动过，是否需要新增item
     */
    private boolean isMoved() {
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                if (mGameMatrixHistory[i][j] != mGameMatrix[i][j].getNum()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 撤销上一次移动
     */
    private void revertGame() {
        //第一次不能移动
        int sum = 0;
        for (int[] element :
                mGameMatrixHistory) {
            for (int i :
                    element) {
                sum += i;
            }
        }
        if (sum != 0) {
            //修改显示分数
//                    Game.getGameActivity().setScore(MyApplication.SCORE,0);
            MyApplication.SCORE = mScoreHistory;
            for (int i = 0; i < mGameLines; i++) {
                for (int j = 0; j < mGameLines; j++) {
                    mGameMatrix[i][j].setNum(mGameMatrixHistory[i][j]);
                }
            }
        }
    }

    /**
     * 获取空格item数组
     */
    private void getBlanks() {
        mBlanks.clear();
        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                if (mGameMatrix[i][j].getNum() == 0) {
                    mBlanks.add(new Point(i, j));
                }
            }
        }
    }

    /**
     * 判断是否结束
     * 0结束，1正常 ，2成功
     */
    private void checkCompleted() {
        int result = checkNums();
    }

    /**
     * 检测所有数字，查看是否有满足条件的
     *
     * @return 0结束，1正常 ，2成功
     */
    private int checkNums() {
        getBlanks();
        if (mBlanks.size() == 0) {
            for (int i = 0; i < mGameLines; i++) {
                for (int j = 0; j < mGameLines; j++) {
                    if (j < mGameLines - 1) {
                        if (mGameMatrix[i][j].getNum() == mGameMatrix[i][j + 1].getNum()) {
                            return 1;
                        }
                    }
                    if (i < mGameLines - 1) {
                        if (mGameMatrix[i][j].getNum() == mGameMatrix[i + 1][j].getNum()) {
                            return 1;
                        }
                    }
                }
            }
            return 0;
        }

        for (int i = 0; i < mGameLines; i++) {
            for (int j = 0; j < mGameLines; j++) {
                if (mGameMatrix[i][j].getNum() == mTarget) {
                    return 2;
                }
            }
        }
        return 1;
    }
}
