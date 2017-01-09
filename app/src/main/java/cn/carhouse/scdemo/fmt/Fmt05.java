package cn.carhouse.scdemo.fmt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.BaseTitleFmt;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:颜色矩阵
 */

public class Fmt05 extends BaseTitleFmt implements View.OnClickListener {

    private Bitmap bitmap;
    private int mEtWidth;
    private int mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrixs = new float[20];

    public static Fmt05 newInstance() {

        Bundle args = new Bundle();

        Fmt05 fragment = new Fmt05();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;
    private ImageView iv;
    private GridLayout mGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_010;
    }

    @Override
    protected void initView(View view) {
        iv = (ImageView) view.findViewById(R.id.iv);
        tv = (TextView) view.findViewById(R.id.tv);
        mGroup = (GridLayout) view.findViewById(R.id.group);
        view.findViewById(R.id.btn1).setOnClickListener(this);
        view.findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText("Fmt03==========");


        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_road5);
        iv.setImageBitmap(bitmap);

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();initMatrix();
            }
        });

    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText et = new EditText(getContext());
            mEts[i] = et;
            mGroup.addView(et, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText("1");
            } else {
                mEts[i].setText("0");
            }
        }
    }


    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(Fmt06.newInstance());
            }
        });
    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1://change

                break;
            case R.id.btn2://reset
                initMatrix();

                break;
        }
        getMatrix();
        setImgMatrix();
    }

    public void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrixs[i] = Float.valueOf(mEts[i].getText().toString().trim());
        }
    }

    public void setImgMatrix() {
        Bitmap bmap = Bitmap.createBitmap(this.bitmap.getWidth(), this.bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrixs);
        Canvas canvas = new Canvas(bmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);

        iv.setImageBitmap(bmap);
    }
}
