package cn.carhouse.scdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import cn.carhouse.scdemo.OnTitleLisenter;
import cn.carhouse.scdemo.base.BaseCyFragment;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:extends BaseCyFragment
 */

public abstract class BaseTitleFmt extends BaseCyFragment implements OnTitleLisenter {

    @Override
    public boolean isShowBack() {
        return true;
    }

    @Override
    public String getBackTitle() {
        return "返回";
    }

    @Override
    public void onBackPressToDo() {
        Log.d("back","backpressed");
        removeFragment();
    }

    @Override
    public int showBackIicon() {
        return 0;
    }

    @Override
    public boolean isShowRight() {
        return false;
    }

    @Override
    public String getNextTitle() {
        return "";
    }

    @Override
    public void onNextPressToDo() {

    }

    @Override
    public int showNextIcon() {
        return 0;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        try {
            mActivity.handleTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onViewCreated(view, savedInstanceState);
    }
}
