package cn.carhouse.scdemo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator
 * on 2016/12/7.
 * des:
 */

public interface LoginInterface {
    @POST("/mapi/user/business/login.json")
    Observable<LoginData> login(@Body LoginRequest request);
}
