package cn.carhouse.scdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.List;

import cn.carhouse.scdemo.act.Game01Act;

/**
 * Created by Administrator
 * on 2017/3/2.
 * des:
 */

public class ImageUtils {
    public ItemBean itemBean;

    /**
     * 切图初始状态（正常顺序）
     *
     * @param type        游戏种类
     * @param picSelected 选择的图片
     * @param context
     */
    public void createInitBitmaps(int type, Bitmap picSelected, Context context) {
        GameUtils.mItemBeans.clear();
        Bitmap bitmap = null;
        List<Bitmap> bitmapItems = new ArrayList<Bitmap>();
        int itemWidth = picSelected.getWidth() / type;
        int itemHeight = picSelected.getHeight() / type;
        for (int i = 1; i <= type; i++) {
            for (int j = 1; j <= type; j++) {
                bitmap = Bitmap.createBitmap(picSelected, (j - 1) * itemWidth, (i - 1) * itemHeight, itemWidth, itemHeight);
                bitmapItems.add(bitmap);
                itemBean = new ItemBean((i - 1) * type + j, (i - 1) * type + j, bitmap);
                GameUtils.mItemBeans.add(itemBean);
            }
        }
//保存最后一个图片在拼图完成时填充
        Game01Act.mLastBitmap=bitmapItems.get(type*type-1);
        //设置最后一个为空item
        bitmapItems.remove(type * type - 1);
        GameUtils.mItemBeans.remove(type * type - 1);
        Bitmap blackBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        blackBitmap = Bitmap.createBitmap(blackBitmap, 0, 0, itemWidth, itemHeight);

        bitmapItems.add(blackBitmap);
        GameUtils.mItemBeans.add(new ItemBean(type*type,0,blackBitmap));
        GameUtils.mBlackItemBean=GameUtils.mItemBeans.get(type*type-1);
    }

    /**
     * 处理图片放大缩小到适合位置
     *
     * @param newWidth
     * @param newHeight
     * @param bitmap
     * @return
     */
    public Bitmap resizeBitmap(float newWidth, float newHeight, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth / bitmap.getWidth(), newHeight / bitmap.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return newBitmap;
    }


}
