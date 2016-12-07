package cn.carhouse.scdemo.base;


/**
 * Created by Administrator
 * on 2016/12/6.
 * des:
 */

public class Head {
    public int _device_type_ = 1;
    public long _request_time_stamp_=System.currentTimeMillis();
    public String _request_token_= MD5.getHexMD5("car-mapi-" + _request_time_stamp_ + "-house");
    public String _user_token_;
}
