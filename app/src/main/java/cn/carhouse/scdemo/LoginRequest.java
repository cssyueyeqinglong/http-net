package cn.carhouse.scdemo;

import cn.carhouse.scdemo.base.Data;
import cn.carhouse.scdemo.base.Head;

/**
 * Created by Administrator
 * on 2016/12/7.
 * des:
 */

public class LoginRequest {
    public Head head;
    public Data data;

    public LoginRequest(Head head, Data data) {
        this.head = head;
        this.data = data;
    }
}
