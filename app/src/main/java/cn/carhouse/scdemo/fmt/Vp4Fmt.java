package cn.carhouse.scdemo.fmt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator
 * on 2016/11/29.
 * des:
 */

public class Vp4Fmt extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private ImageView iv;
    private float hue;
    private float saturation;
    private float lum;

    SeekBar sb1, sb2, sb3;
    private Bitmap bitmap;

    public static Vp4Fmt newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("state", position);
        Vp4Fmt fragment = new Vp4Fmt();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_07, null);
        iv = (ImageView) view.findViewById(R.id.iv);
        sb1 = (SeekBar) view.findViewById(R.id.sb1);
        sb2 = (SeekBar) view.findViewById(R.id.sb2);
        sb3 = (SeekBar) view.findViewById(R.id.sb3);

        sb1.setOnSeekBarChangeListener(this);
        sb2.setOnSeekBarChangeListener(this);
        sb3.setOnSeekBarChangeListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_road5);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.sb1:
                hue = (progress - 50) * 1.0f / 50 * 180;
                break;
            case R.id.sb2:
                saturation = progress * 1f / 50;
                break;
            case R.id.sb3:
                lum = progress * 1f / 50;
                break;
        }
        iv.setImageBitmap(handleImgEffect());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    public Bitmap handleImgEffect(){
        Bitmap tB = Bitmap.createBitmap(this.bitmap.getWidth(), this.bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(tB);
        Paint paint=new Paint();


        ColorMatrix hueColorMatrix = new ColorMatrix();
        hueColorMatrix.setRotate(0, hue);//设置色调,红色色调
        hueColorMatrix.setRotate(1, hue);//设置色调，绿色色调
        hueColorMatrix.setRotate(2, hue);//设置色调，蓝色色调

        ColorMatrix satColorMatrix = new ColorMatrix();
        satColorMatrix.setSaturation(saturation);//设置饱和度

        ColorMatrix lumColorMatrix = new ColorMatrix();
        lumColorMatrix.setScale(lum, lum, lum, 1);//设置亮度

        ColorMatrix imgMatrix = new ColorMatrix();
        imgMatrix.postConcat(hueColorMatrix);
        imgMatrix.postConcat(satColorMatrix);
        imgMatrix.postConcat(lumColorMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(imgMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);

        return tB;
    }
}
