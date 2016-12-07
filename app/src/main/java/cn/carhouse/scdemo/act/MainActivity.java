package cn.carhouse.scdemo.act;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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
                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
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
    }


}
