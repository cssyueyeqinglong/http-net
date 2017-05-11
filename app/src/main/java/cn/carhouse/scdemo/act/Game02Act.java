package cn.carhouse.scdemo.act;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;

import java.util.ArrayList;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.MyApplication;
import cn.carhouse.scdemo.view.GameItem;

/**
 * Created by Administrator
 * on 2017/3/2.
 * des:2048游戏
 */

public class Game02Act extends AppCompatActivity {
    GridLayout mGl;
    private int mScoreHistory;
    private int mGameLines;
    private GameItem[][] mGameMatrix;
    private int[][] mGameMatrixHistory;
    private ArrayList<Integer> mCalList;
    private ArrayList<Point> mBlanks;
    private int mHighScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_game_02);
        mGl= (GridLayout) findViewById(R.id.gl);
    }


}
