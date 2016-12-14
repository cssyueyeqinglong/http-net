package cn.carhouse.scdemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import cn.carhouse.scdemo.LoginData;
import cn.carhouse.scdemo.LoginInterface;
import cn.carhouse.scdemo.LoginRequest;
import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.Data;
import cn.carhouse.scdemo.base.Head;
import cn.carhouse.scdemo.base.MD5;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator
 * on 2016/12/6.
 * des:
 */

public class Test01Act extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_01);
//        getData();
//        xutlsData();
//        retrofitData();
        okhttpData();
    }

    private void okhttpData() {

        Log.e("thread",Thread.currentThread().getName());


        OkHttpClient client = new OkHttpClient();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("head", new Head());
        maps.put("data", new Data());

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(maps));
        Request request = new Request.Builder().url("http://mapi.linshaopeng.com:8080/mapi/user/business/login.json").post(body).build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                String str = response.body().string();
                Log.i("wangshu", str);
                Log.e("thread",Thread.currentThread().getName());
            }
        });
    }

    private void retrofitData() {

        String BASE_URL = "http://mapi.linshaopeng.com:8080";
        String url = "http://mapi.linshaopeng.com:8080/mapi/user/business/login.json";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginInterface logInterface = retrofit.create(LoginInterface.class);

        Observable<LoginData> login = logInterface.login(new LoginRequest(new Head(), new Data()));

        login.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<LoginData>() {
                    @Override
                    public void call(LoginData des) {
                        Log.e("retrofit", "call: ===" + new Gson().toJson(des));
//                        mText.setText(des.getDes().toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("tag", "call: " + throwable.getLocalizedMessage());
                    }
                });

    }

    private void xutlsData() {
//        x.image().bind(iv, "http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg");
        Gson gson = new Gson();
        RequestParams params = new RequestParams("http://mapi.linshaopeng.com:8080/mapi/user/business/login.json");
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("head", new Head());
        maps.put("data", new Data());

//        params.addBodyParameter("", gson.toJson(maps), "application/json");
        params.setBodyContent(gson.toJson(maps));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("xutils", "====xutils===" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {

            }
        });
    }


    /**
     * AsyncHttpClient
     */
    private void getData() {
        Gson gson = new Gson();
        String url = "http://mapi.linshaopeng.com:8080/mapi/user/business/login.json";
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject data = new JSONObject();
        JSONObject head = new JSONObject();
        try {
            head.put("_device_type_", 1);
            String time = "" + System.currentTimeMillis();
            head.put("_request_time_stamp_", time);
            head.put("_request_token_", MD5.getHexMD5("car-mapi-" + time + "-house"));
            head.put("_device_type_", 1);

            data.put("loginName", "13242429815");
            data.put("loginPass", "123456");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject req = new JSONObject();
        try {
            req.put("head", head);
            req.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
//            entity = new StringEntity(req.toString());
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("head", new Head());
            maps.put("data", new Data());
            entity = new StringEntity(gson.toJson(maps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        client.post(this, url, entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.e("async", "succeed" + statusCode);
                String s = new String(responseBody);
                Log.e("async", "===async-http====" + s);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("tag", "" + statusCode);
            }
        });
    }
}
