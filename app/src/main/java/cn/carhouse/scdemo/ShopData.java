package cn.carhouse.scdemo;

import java.util.List;

/**
 * Created by Administrator
 * on 2016/10/20.
 * des:
 */

public class ShopData {

    /**
     * bcode : 1
     * bmessage : Success!
     * code : 1
     * message : Success!
     * responseTime : 1473856592612
     */

    public HeadBean head;
    /**
     * childIndexTopicItems : [{"layout_style":"1","pic_path":"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_chezhu_jiayouchongzhi@3px.png","target_type":603,"tip_pic":"","title":"加油充值","title_ext":"车主服务-加油充值"},{"layout_style":"1","pic_path":"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_chezhu_daijia@3px.png","target_type":604,"tip_pic":"","title":"代驾","title_ext":"车主服务-代驾"},{"layout_style":"1","pic_path":"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_chezhu_weizhangdaijiao@3px.png","target_type":602,"tip_pic":"","title":"违章代缴","title_ext":"车主服务-违章代缴"}]
     * layout_style : 1
     * pic_path :
     * tip_pic :
     * title : 车主服务
     * title_ext : 车主服务
     */

    public List<DataBean> data;

    public static class HeadBean {
        public int bcode;
        public String bmessage;
        public int code;
        public String message;
        public long responseTime;
    }

    public static class DataBean {
        public String layout_style;
        public String pic_path;
        public String tip_pic;
        public String title;
        public String title_ext;
        /**
         * layout_style : 1
         * pic_path : http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_chezhu_jiayouchongzhi@3px.png
         * target_type : 603
         * tip_pic :
         * title : 加油充值
         * title_ext : 车主服务-加油充值
         */

        public List<ChildIndexTopicItemsBean> childIndexTopicItems;

        public static class ChildIndexTopicItemsBean {
            public String layout_style;
            public String pic_path;
            public int target_type;
            public String tip_pic;
            public String title;
            public String title_ext;
            public int type;
        }
    }
}
