package cn.carhouse.scdemo;

import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

/**
 * Created by Administrator
 * on 2017/2/4.
 * des://自定义动画,3d旋转动画
 */

public class MyOwnAnimator3D extends Animation {


    private float mCenterWidth;
    private float mCenterHeight;
    Camera mCamera;
    private float mRotateY=5f;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(2000);//设置默认时长
        setFillAfter(true);//动画结束后保留状态
        setInterpolator(new BounceInterpolator());//设置默认差值器
        mCenterHeight = height / 2;
        mCenterWidth = width / 2;
        mCamera=new Camera();

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        mCamera.save();
        mCamera.rotateY(mRotateY * interpolatedTime);//使用camera设置旋转角度
        mCamera.getMatrix(matrix);//将旋转变换作用到矩阵上
        mCamera.restore();
        //通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        matrix.preTranslate(mCenterWidth,mCenterHeight);
        matrix.preTranslate(-mCenterWidth,-mCenterHeight);
//        matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeight);
    }
}
