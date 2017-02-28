package cn.carhouse.scdemo.fmt;

import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import cn.carhouse.scdemo.MyOwnAnimator3D;
import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.BaseTitleFmt;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:svg动画实例
 * <p>
 */

public class Fmt12 extends BaseTitleFmt {


    public static Fmt12 newInstance() {

        Bundle args = new Bundle();
        Fmt12 fragment = new Fmt12();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv;
    private RecyclerView mRcv;

    @Override
    protected int getLayoutId() {
        return R.layout.fmt_017;
    }

    @Override
    protected void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);
        mRcv = (RecyclerView) view.findViewById(R.id.rcv);

    }

    @Override
    protected void handleData() {
        tv.setTextColor(Color.parseColor("#00ff00"));
        tv.setText(this.getClass().getSimpleName() + "=========");

        //3d动画
        MyOwnAnimator3D animator = new MyOwnAnimator3D();
        animator.setDuration(2000);
        tv.setAnimation(animator);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 12);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {

                if (position == 0) return 3;
                else if (position == 1) return 6;
                else if (position == 2) return 12;
                else return 1;
            }
        });
        mRcv.setLayoutManager(manager);
        mRcv.setAdapter(new MyAdapter());

    }


    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(getContext(), R.layout.item_0201, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (position % 2 == 0) {
                holder.tv.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            } else {
                holder.tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
            holder.tv.setText("测试数据" + position);
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }


    @Override
    protected void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                addFragment(Fmt11.newInstance());
            }
        });

    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

}
