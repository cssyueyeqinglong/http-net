package cn.carhouse.scdemo.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.TabPageIndicator;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.fmt.VpFmt;

/**
 * Created by Administrator
 * on 2016/11/29.
 * des:
 */

public class TestAct extends FragmentActivity {
    TabPageIndicator mIndicator;
    ViewPager mVp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test);
        mIndicator = (TabPageIndicator) findViewById(R.id.tain);
        mVp = (ViewPager) findViewById(R.id.vp);

        mVp.setAdapter(new TestPagerAdapter(getSupportFragmentManager()));
        mIndicator.setViewPager(mVp);
    }

    private class TestPagerAdapter extends FragmentPagerAdapter {

        public TestPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            return VpFmt.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "测试"+position;
        }
    }
}
