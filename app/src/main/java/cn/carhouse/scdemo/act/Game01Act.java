package cn.carhouse.scdemo.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.carhouse.scdemo.GameUtils;
import cn.carhouse.scdemo.ImageUtils;
import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.ScreenUtils;

/**
 * Created by Administrator
 * on 2017/3/1.
 * des:
 */

public class Game01Act extends AppCompatActivity {
    private static int time;
    private static int step;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private TextView tvTime;
    private TextView tvStep;
    public static int TYPE = 3;
    public static Bitmap mLastBitmap;//缺失的那张图片，当图片完全拼接正确的时候，最后一张也显示出来
    private GridView mGv;
    private ImageUtils imageUtils;
    private ImageAdapter madapter;
    private Bitmap scrBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_game_01);

        imageUtils = new ImageUtils();

        int typeScale = getIntent().getIntExtra("mType", -1);
        if (typeScale == 0) {//默认图片
            int res = getIntent().getIntExtra("picSelectedId", R.mipmap.ic_launcher);
            scrBitmap = BitmapFactory.decodeResource(getResources(), res);

        } else {
            String mImagePath = getIntent().getStringExtra("mImagePath");
            Log.d("tag", "mImagePath======" + mImagePath);
            scrBitmap = BitmapFactory.decodeFile(mImagePath);
            scrBitmap = imageUtils.resizeBitmap(200, 200, scrBitmap);
        }
        imageUtils.createInitBitmaps(TYPE, scrBitmap, this);//生成有序的GameUtils.mItemBeans
        for (int i = 0; i < GameUtils.mItemBeans.size(); i++) {
            Log.d("tag", GameUtils.mItemBeans.get(i).toString());
        }
//        打乱顺序
        GameUtils.getPuzzleGenerator();

        tvTime = (TextView) findViewById(R.id.tv_time);
        tvStep = (TextView) findViewById(R.id.tv_step);
        mGv = (GridView) findViewById(R.id.gv);
        mGv.setNumColumns(TYPE);
        mGv.setHorizontalSpacing(0);
        mGv.setVerticalSpacing(0);
        if (madapter == null) {
            madapter = new ImageAdapter();
        } else {
            madapter.notifyDataSetChanged();
        }
        mGv.setAdapter(new ImageAdapter());
        mTimer = new Timer(true);
        mTimerTask = new TimerTask() {

            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1;
                mHander.sendMessage(msg);
            }
        };

        mTimer.schedule(mTimerTask, 0, 1000);

    }

    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GameUtils.mItemBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView iv_pic_item = null;
            int density = (int) ScreenUtils.getDeviceDensity(Game01Act.this);
            if (convertView == null) {
                iv_pic_item = new ImageView(Game01Act.this);
                int itemWidth = (int) ((ScreenUtils.getScreenSize(Game01Act.this).widthPixels - density * 20) / TYPE);
                GridView.LayoutParams layoutParams = new GridView.LayoutParams(itemWidth, 100 * density);
                iv_pic_item.setLayoutParams(layoutParams);
                iv_pic_item.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                iv_pic_item = (ImageView) convertView;
            }

            iv_pic_item.setBackgroundColor(Color.BLACK);
            iv_pic_item.setImageBitmap(GameUtils.mItemBeans.get(position).getmBitmap());
            iv_pic_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (GameUtils.isMoveable(position)) {//当前点击页面是活动的
                        GameUtils.swapItems(GameUtils.mItemBeans.get(position), GameUtils.mBlackItemBean);
                        step += 1;
                        tvStep.setText("" + step);
                        if (GameUtils.isSuccess()) {
                            Log.d("tag", "成功了");
                            GameUtils.mItemBeans.get(TYPE * TYPE - 1).setmBitmap(mLastBitmap);
                            mTimerTask.cancel();
                            mTimer.cancel();
                        }
                        notifyDataSetChanged();
                    }
                }
            });
            return iv_pic_item;
        }
    }

    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                time += 1;
                tvTime.setText("" + time);
            }
        }
    };

    public void back(View view) {
        finish();
    }

    public void restart(View view) {
        imageUtils.createInitBitmaps(TYPE, scrBitmap, this);//生成有序的GameUtils.mItemBeans
//        打乱顺序
        GameUtils.getPuzzleGenerator();
        madapter = new ImageAdapter();
        mGv.setAdapter(madapter);
        time = 0;
        step = 0;
        tvStep.setText("" + step);
        tvTime.setText("" + time);
    }

    public void nextAct(View view) {
        startActivity(new Intent(this, Game02Act.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        mTimer = null;
        mTimerTask.cancel();
        mTimerTask = null;
    }

}
