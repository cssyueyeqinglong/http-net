package cn.carhouse.scdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/9/1.
 */

public abstract class BaseCyFragment extends StatedFragment {

    protected AppActivity mActivity;


    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    //获取宿主Activity
    protected BaseCyFmtActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (AppActivity) activity;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mActivity != null) {
            mActivity = null;
        }
    }

    @Override
    public void onDestroy() {
        if (mActivity != null) {
            mActivity = null;
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    //添加fragment
    protected void addFragment(BaseCyFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        initEvents();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleData();
    }

    /**
     * 获取数据，处理数据，先于getsucceedView（）调用,所以在此方法中只有返回值是成功的view时才可以调用view，否则会暴空指针
     */
    protected abstract void handleData();

    protected abstract void initEvents();


    public void onEventMainThread(String ev) {

    }

}
