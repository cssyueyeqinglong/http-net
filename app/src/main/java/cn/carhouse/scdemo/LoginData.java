package cn.carhouse.scdemo;

/**
 * Created by Administrator
 * on 2016/12/7.
 * des:
 */

public class LoginData {

    /**
     * code : 1
     * responseTime : 1481091379598
     * bmessage : Success!
     * message : Success!
     * bcode : 1
     */

    public HeadBean head;
    /**
     * result : true
     * userInfo : {"businessId":8421,"userType":1,"loginName":"13242429815","avatar":"http://img.car-house.cn/none.png","mobile":"13242429815","nickName":"132****9815","referralCode":"007551020","isCheck":100,"remark":"","paidGoodsOrderNumber":0,"hasSixCodePass":1,"score":0,"money":0,"status":1,"infoStatus":100,"belongBusinessId":37,"IMUserName":"dd04ebab3e834c5c961510197bb6d522","IMUserPass":"d8dd453d6349a1e43a23c3502ca03319","IMNickName":"13242429815","imuserName":"dd04ebab3e834c5c961510197bb6d522","imuserPass":"d8dd453d6349a1e43a23c3502ca03319","imnickName":"13242429815","businessInfo":{}}
     * adminIMInfo : {"adminUserName":"kefu1","adminUserAvatar":"http://img.car-house.cn/Upload/customer/customerservice-icon/kefu_touxiang@3x.png","adminNickName":"爱车小屋"}
     * token : {"tokenStatus":100,"userToken":"942854c3081b4963b2dce460e73b02c8","createTime":null,"appId":1,"expireDuration":600000,"startTime":1481091379652,"endTime":null,"userTokenId":137132,"userType":1,"userId":8421}
     */

    public DataBean data;

    public static class HeadBean {
        public int code;
        public long responseTime;
        public String bmessage;
        public String message;
        public int bcode;
    }

    public static class DataBean {
        public boolean result;
        /**
         * businessId : 8421
         * userType : 1
         * loginName : 13242429815
         * avatar : http://img.car-house.cn/none.png
         * mobile : 13242429815
         * nickName : 132****9815
         * referralCode : 007551020
         * isCheck : 100
         * remark :
         * paidGoodsOrderNumber : 0
         * hasSixCodePass : 1
         * score : 0
         * money : 0.0
         * status : 1
         * infoStatus : 100
         * belongBusinessId : 37
         * IMUserName : dd04ebab3e834c5c961510197bb6d522
         * IMUserPass : d8dd453d6349a1e43a23c3502ca03319
         * IMNickName : 13242429815
         * imuserName : dd04ebab3e834c5c961510197bb6d522
         * imuserPass : d8dd453d6349a1e43a23c3502ca03319
         * imnickName : 13242429815
         * businessInfo : {}
         */

        public UserInfoBean userInfo;
        /**
         * adminUserName : kefu1
         * adminUserAvatar : http://img.car-house.cn/Upload/customer/customerservice-icon/kefu_touxiang@3x.png
         * adminNickName : 爱车小屋
         */

        public AdminIMInfoBean adminIMInfo;
        /**
         * tokenStatus : 100
         * userToken : 942854c3081b4963b2dce460e73b02c8
         * createTime : null
         * appId : 1
         * expireDuration : 600000
         * startTime : 1481091379652
         * endTime : null
         * userTokenId : 137132
         * userType : 1
         * userId : 8421
         */

        public TokenBean token;

        public static class UserInfoBean {
            public int businessId;
            public int userType;
            public String loginName;
            public String avatar;
            public String mobile;
            public String nickName;
            public String referralCode;
            public int isCheck;
            public String remark;
            public int paidGoodsOrderNumber;
            public int hasSixCodePass;
            public int score;
            public double money;
            public int status;
            public int infoStatus;
            public int belongBusinessId;
            public String IMUserName;
            public String IMUserPass;
            public String IMNickName;
            public String imuserName;
            public String imuserPass;
            public String imnickName;
            public BusinessInfoBean businessInfo;

            public static class BusinessInfoBean {
            }
        }

        public static class AdminIMInfoBean {
            public String adminUserName;
            public String adminUserAvatar;
            public String adminNickName;
        }

        public static class TokenBean {
            public int tokenStatus;
            public String userToken;
            public Object createTime;
            public int appId;
            public int expireDuration;
            public long startTime;
            public Object endTime;
            public int userTokenId;
            public int userType;
            public int userId;
        }
    }
}
