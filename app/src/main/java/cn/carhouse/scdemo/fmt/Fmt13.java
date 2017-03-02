package cn.carhouse.scdemo.fmt;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.carhouse.scdemo.MyOwnAnimator3D;
import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.act.Game01Act;
import cn.carhouse.scdemo.base.BaseTitleFmt;
import cn.carhouse.scdemo.base.ScreenUtils;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:拼图游戏
 * <p>
 */

public class Fmt13 extends BaseTitleFmt {

    private static final int RESULT_IMAGE = 100;//本地图库
    private static final String IMAGE_TYPE = "image/*";

    //TEMP相机照片路径
    public static String TEMP_IMAGEPATH = Environment.getExternalStorageDirectory().getPath() + "/temp.png";
    private static final int RESULT_CAMERA = 200;//返回码，相机
    private View mPopView;
    private PopupWindow mPopWindow;
    private GridView mGv;
    private List<Bitmap> picList;
    private int[] mResPicIds;

    public static Fmt13 newInstance() {

        Bundle args = new Bundle();
        Fmt13 fragment = new Fmt13();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fmt_018;
    }

    @Override
    protected void initView(View view) {


        picList = new ArrayList<Bitmap>();

        mResPicIds = new int[]{R.mipmap.ic_me01, R.mipmap.ic_me02, R.mipmap.ic_me03, R.mipmap.ic_me04
                , R.mipmap.ic_me05, R.mipmap.ic_me06, R.mipmap.ic_me07, R.mipmap.ic_me08
                , R.mipmap.ic_me09, R.mipmap.ic_me10, R.mipmap.ic_me11, R.mipmap.ic_me12
                , R.mipmap.ic_me13, R.mipmap.ic_me14, R.mipmap.ic_me15, R.mipmap.ic_launcher};
        Bitmap[] bitmaps = new Bitmap[mResPicIds.length];
        for (int i = 0; i < bitmaps.length; i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(), mResPicIds[i]);
            picList.add(bitmaps[i]);
        }
        mGv = (GridView) view.findViewById(R.id.gv_list);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("tag", "itemclicke");
                if (position == mResPicIds.length - 1) {
                    showPop(mGv);
                } else {
                    Intent intent = new Intent(mActivity, Game01Act.class);
                    intent.putExtra("picSelectedId", mResPicIds[position]);
                    intent.putExtra("mType", 0);
                    startActivity(intent);
                }
            }
        });

        mGv.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return picList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv_pic_item = null;
            int density = (int) ScreenUtils.getDeviceDensity(mActivity);
            if (convertView == null) {
                iv_pic_item = new ImageView(mActivity);
                iv_pic_item.setLayoutParams(new GridView.LayoutParams(80 * density, 100 * density));
                iv_pic_item.setScaleType(ImageView.ScaleType.FIT_XY);
            } else {
                iv_pic_item = (ImageView) convertView;
            }

            iv_pic_item.setBackgroundColor(Color.BLACK);
            iv_pic_item.setImageBitmap(picList.get(position));
            return iv_pic_item;
        }
    }

    @Override
    protected void handleData() {


    }

    @Override
    protected void initEvents() {
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                addFragment(Fmt11.newInstance());
//            }
//        });


    }

    @Override
    public String getTitle() {
        return this.getClass().getSimpleName();
    }

    private void showPop(View view) {
        int density = (int) ScreenUtils.getDeviceDensity(getContext());//获取屏幕密度
        mPopWindow = new PopupWindow(getContentView(), 200 * density, 300 * density);
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);

        //透明背景
        ColorDrawable drawable = new ColorDrawable(Color.TRANSPARENT);
        mPopWindow.setBackgroundDrawable(drawable);
        //获取位置
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - 40 * density, location[1] + 30 * density);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_IMAGE && data != null) {//相册
                Cursor cursor = getContext().getContentResolver().query(data.getData(), null, null, null, null);
                String imagePath = "";
                if (cursor == null) {
                    imagePath = data.getData().getPath();
                } else {
                    cursor.moveToFirst();
                    imagePath = cursor.getString(cursor.getColumnIndex("_data"));
                    cursor.close();
                }
                Intent intent = new Intent(mActivity, Game01Act.class);
                intent.putExtra("mImagePath", imagePath);
                intent.putExtra("mType", 1);
                startActivity(intent);
            } else if (requestCode == RESULT_CAMERA) {//相机
                Intent intent = new Intent(mActivity, Game01Act.class);
                intent.putExtra("mImagePath", TEMP_IMAGEPATH);
                intent.putExtra("mType", 1);
                startActivity(intent);
            }
        }
    }

    private View getContentView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fmt_item_110, null);
        view.findViewById(R.id.tv_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机
                //系统相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri photoUri = Uri.fromFile(new File(TEMP_IMAGEPATH));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, RESULT_CAMERA);
            }
        });
        view.findViewById(R.id.tv_02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //本地图库
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_TYPE);
                startActivityForResult(intent, RESULT_IMAGE);
            }
        });
        return view;
    }
}
