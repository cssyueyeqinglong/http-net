package cn.carhouse.scdemo.base;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import cn.carhouse.scdemo.R;

/**
 * Created by Administrator on 2016/9/1.
 */

public abstract class AppActivity extends BaseCyFmtActivity {

    public TextView mTvTitle;
    public TextView mTvBack;
    public TextView mTvNext;


    //获取第一个fragment
    protected abstract BaseCyFragment getFirstFragment();

    //获取Intent
    protected void handleIntent(Intent intent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }

        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            Fragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvBack = (TextView) findViewById(R.id.tv_back);
        mTvNext = (TextView) findViewById(R.id.tv_next);

    }

    public void handleTitle() {

        final BaseTitleFmt fragment= (BaseTitleFmt) getSupportFragmentManager().findFragmentById(getFragmentContentId());
        mTvTitle.setText("" + fragment.getTitle());
        mTvBack.setText("" + fragment.getBackTitle());
        mTvNext.setText("" + fragment.getNextTitle());

        if (fragment.showBackIicon() != 0) {
            Drawable left = getResources().getDrawable(fragment.showBackIicon());
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mTvBack.setCompoundDrawables(left, null, null, null);
        }

        if (fragment.showNextIcon() != 0) {
            Drawable right = getResources().getDrawable(fragment.showNextIcon());
            right.setBounds(0, 0, right.getMinimumWidth(), right.getMinimumHeight());
            mTvNext.setCompoundDrawables(right, null, null, null);
        }


        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//           removeFragment();
                fragment.onBackPressToDo();
            }
        });

        mTvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.onNextPressToDo();
            }
        });

        mTvBack.setVisibility(fragment.isShowBack() ? View.VISIBLE : View.GONE);
        mTvNext.setVisibility(fragment.isShowRight() ? View.VISIBLE : View.GONE);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(getFragmentContentId());
        if (null != fragment) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
