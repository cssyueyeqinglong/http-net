package cn.carhouse.scdemo.act;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.RcyAdapterHelper;
import cn.carhouse.scdemo.base.RcyBaseHolder;

public class MainActivity extends AppCompatActivity {

    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.m_toolbar);
        setSupportActionBar(mToolbar);
        for (int i = 0; i <= 100; i++) {
            mDatas.add("Test Data  " + i);
        }

        RecyclerView mRcyView = (RecyclerView) findViewById(R.id.rcy_view);
        mRcyView.setLayoutManager(new LinearLayoutManager(this));
        mRcyView.setAdapter(new RcyAdapterHelper<String>(R.layout.item_tv, mDatas) {
            @Override
            public void convert(RcyBaseHolder holder, String item, int position) {
                holder.setText(R.id.tv, mDatas.get(position));
                holder.getView().setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        Intent intent1 = new Intent();
                        Log.e("isAviable","isAviable=0="+isIntentAvailable(MainActivity.this,intent));
                        Log.e("isAviable","isAviable=1="+isIntentAvailable(MainActivity.this,intent1));
                        startActivity(intent);
                    }
                });
            }

        });
        //mViewPager = (ViewPager) findViewById(R.id.viewPager);

        //mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        mRcyView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("LOG", "===========onScrollStateChanged===============");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("LOG", dx + "===========onScrolled===============" + dy);
            }
        });


        Uri uri=Uri.parse("content://cy.com.allview.contentprovider.bookprovider/user");
        Cursor cursor = getContentResolver().query(uri, new String[]{"_id,name,sex"}, null, null, null);
        if(cursor==null)return;
        while (cursor.moveToNext()){//有数据
            int id=cursor.getInt(0);
            String name=cursor.getString(1);
            int sex=cursor.getInt(2);
            Logger.d("name==" + name + ",id==" + id + ",age=" + sex);
        }
        cursor.close();
    }
    @SuppressWarnings("WrongConstant")
    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        int getActivities = PackageManager.GET_ACTIVITIES;
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, getActivities);
        if (list == null) {
            return false;
        }
        return list.size() > 0;
    }

}
