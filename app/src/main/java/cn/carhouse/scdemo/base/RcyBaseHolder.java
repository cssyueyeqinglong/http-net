package cn.carhouse.scdemo.base;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Administrator on 2015/9/11.
 */
public class RcyBaseHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> views;

    public RcyBaseHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<View>();
    }

    public View getView() {
        return this.itemView;
    }

    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = this.itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置TextView的文本
     */
    public RcyBaseHolder setText(int viewId, String value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }


    /**
     * 设置图片背景颜色
     */
    public RcyBaseHolder setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置控件背景
     */
    public RcyBaseHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * 设置图片背景
     */
    public RcyBaseHolder setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * 设置图片BitMap
     */
    public RcyBaseHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置控件是否可见
     */
    public RcyBaseHolder setVisible(int viewId, boolean visible) {
        View view = retrieveView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置TextView控件连接
     */
    public RcyBaseHolder linkfy(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * 设置控件选中
     */
    public RcyBaseHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) retrieveView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置控件点击
     */
    public RcyBaseHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置控件触摸
     */
    public RcyBaseHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = retrieveView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 设置控件点长按
     */
    public RcyBaseHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
    
    public RcyBaseHolder setImageUrl(int viewId, String imageUrl,
			int failedResId) {
		ImageView view = retrieveView(viewId);
		//BitmapManager.displayImageView(view, imageUrl, failedResId);
		// BmUtils.display(view, imageUrl, failedResId);
		return this;
	}
}