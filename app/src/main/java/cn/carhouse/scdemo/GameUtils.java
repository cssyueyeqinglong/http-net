package cn.carhouse.scdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.carhouse.scdemo.act.Game01Act;

/**
 * Created by Administrator
 * on 2017/3/2.
 * des:
 */

public class GameUtils {

    //装有数组的值
    public static List<ItemBean> mItemBeans = new ArrayList<ItemBean>();
    public static ItemBean mBlackItemBean;

    /**
     * 生成随机item
     */
    public static void getPuzzleGenerator() {
        int index = 0;
        for (int i = 0; i < mItemBeans.size(); i++) {
            index = (int) (Math.random() * Game01Act.TYPE * Game01Act.TYPE);
            swapItems(mItemBeans.get(index), GameUtils.mBlackItemBean);
        }
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < mItemBeans.size(); i++) {
            data.add(mItemBeans.get(i).getmBitmapId());
        }

        //判断生成是否有解
        if (canSolve(data)) {
            return;
        } else {
            getPuzzleGenerator();
        }
    }

    /**
     * 交换空格与点击item的位置
     *
     * @param from
     * @param black
     */
    public static void swapItems(ItemBean from, ItemBean black) {
        ItemBean tempItemBean = new ItemBean();
        //交换BitmapId,
        tempItemBean.setmBitmapId(from.getmBitmapId());
        from.setmBitmapId(black.getmBitmapId());
        black.setmBitmapId(tempItemBean.getmBitmapId());

        //交换bitmap
        tempItemBean.setmBitmap(from.getmBitmap());
        from.setmBitmap(black.getmBitmap());
        black.setmBitmap(tempItemBean.getmBitmap());

        GameUtils.mBlackItemBean = from;
    }

    /**
     * 判断是否有解
     *
     * @param data 拼图数组数据
     * @return
     */
    public static boolean canSolve(List<Integer> data) {
        //获取空格id
        int blackId = GameUtils.mBlackItemBean.getmItemId();
        if (data.size() % 2 == 1) {
            return getInversions(data) % 2 == 0;
        } else {
            //从下往上数，空格位于奇数行，
            if (((blackId - 1) / Game01Act.TYPE) % 2 == 1) {
                return getInversions(data) % 2 == 0;
            } else {
                //从下往上数，空格位于偶数行，
                return getInversions(data) % 2 == 1;
            }
        }
    }

    /**
     * 计算倒置和算法
     *
     * @param data
     * @return 该序列的倒置和
     */
    public static int getInversions(List<Integer> data) {
        int inversions = 0;
        int inversionCount = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                int index = data.get(i);
                if (data.get(j) != 0 && data.get(j) < index) {
                    inversionCount++;
                }
            }
            inversions += inversionCount;
            inversionCount = 0;
        }
        return inversions;
    }

    /**
     * 判断点击的item能否移动
     *
     * @param position
     * @return
     */
    public static boolean isMoveable(int position) {
        int type = Game01Act.TYPE;
        //获取空格item
        int blackId = GameUtils.mBlackItemBean.getmItemId() - 1;
        //不同行相差为type
        if (Math.abs(blackId - position) == type) {
            return true;
        }
        //相同行相差为1
        if ((blackId / type == position / type) && Math.abs(blackId - position) == 1) {
            return true;
        }
        return false;
    }

    /**
     * 是否拼图成功
     *
     * @return
     */
    public static boolean isSuccess() {

        for (ItemBean tempBean :
                GameUtils.mItemBeans) {
            if (tempBean.getmBitmapId() != 0 && (tempBean.getmItemId() == tempBean.getmBitmapId())) {
                continue;
            } else if (tempBean.getmBitmapId() == 0 && tempBean.getmItemId() == Game01Act.TYPE * Game01Act.TYPE) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
