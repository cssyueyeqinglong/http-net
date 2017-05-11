package cn.carhouse.scdemo;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator
 * on 2017/3/2.
 * des:
 */

public class ItemBean implements Parcelable{
    private int mItemId;
    private int mBitmapId;
    private Bitmap mBitmap;
    private BaseBean mBean;

    public ItemBean() {
    }

    public ItemBean(int mItemId, int mBitmapId, Bitmap mBitmap) {
        this.mItemId = mItemId;
        this.mBitmapId = mBitmapId;
        this.mBitmap = mBitmap;
    }

    public int getmItemId() {
        return mItemId;
    }

    public void setmItemId(int mItemId) {
        this.mItemId = mItemId;
    }

    public int getmBitmapId() {
        return mBitmapId;
    }

    public void setmBitmapId(int mBitmapId) {
        this.mBitmapId = mBitmapId;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//序列化
        dest.writeInt(mBitmapId);
        dest.writeInt(mItemId);
        dest.writeParcelable(mBitmap,flags);
        dest.writeParcelable(mBean,flags);
    }

    //反序列化
    public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
        @Override
        public ItemBean createFromParcel(Parcel in) {
            return new ItemBean(in);
        }

        @Override
        public ItemBean[] newArray(int size) {
            return new ItemBean[size];
        }
    };
    protected ItemBean(Parcel in) {
        mItemId = in.readInt();
        mBitmapId = in.readInt();
        mBitmap = in.readParcelable(Bitmap.class.getClassLoader());
        mBean=in.readParcelable(Thread.currentThread().getContextClassLoader());
    }


}
