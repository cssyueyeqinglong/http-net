package cn.carhouse.scdemo;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:
 */

public interface OnTitleLisenter {

    String getTitle();//设置标题

    boolean isShowBack();//是否显示返回键

    String getBackTitle();//设置标题栏左边的文字

    void onBackPressToDo();//按下标题栏左边按钮的事件处理

    int showBackIicon();//设置标题栏左边的图片

    boolean isShowRight();//是否显示标题栏右边文字或者图片

    String getNextTitle();//设置标题栏右边文字

    void onNextPressToDo();//按下标题栏右边的事件处理

    int showNextIcon();//设置标题栏右边的图片
}
