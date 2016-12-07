package cn.carhouse.scdemo.act;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.carhouse.scdemo.R;
import cn.carhouse.scdemo.base.RcyBaseHolder;
import cn.carhouse.scdemo.base.RcyMultiItemTypeSupport;
import cn.carhouse.scdemo.base.RcyQuickAdapter;
import cn.carhouse.scdemo.ShopData;

public class Main2Activity extends AppCompatActivity {

    private RecyclerView mRcv;
    private String data = "{\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"childIndexTopicItems\": [\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_chezhu_jiayouchongzhi@3px.png\",\n" +
            "                    \"target_type\": 603,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"加油充值\",\n" +
            "                    \"title_ext\": \"车主服务-加油充值\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_chezhu_daijia@3px.png\",\n" +
            "                    \"target_type\": 604,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"代驾\",\n" +
            "                    \"title_ext\": \"车主服务-代驾\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_chezhu_weizhangdaijiao@3px.png\",\n" +
            "                    \"target_type\": 602,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"违章代缴\",\n" +
            "                    \"title_ext\": \"车主服务-违章代缴\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"layout_style\": \"1\",\n" +
            "            \"pic_path\": \"\",\n" +
            "            \"tip_pic\": \"\",\n" +
            "            \"title\": \"车主服务\",\n" +
            "            \"title_ext\": \"车主服务\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"childIndexTopicItems\": [\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_xiche@3px.png\",\n" +
            "                    \"serviceParentId\": 1,\n" +
            "                    \"target_global_id\": \"5\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"洗车\",\n" +
            "                    \"title_ext\": \"美容装潢-洗车\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_dala@3px.png\",\n" +
            "                    \"serviceParentId\": 2,\n" +
            "                    \"target_global_id\": \"8\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"打蜡\",\n" +
            "                    \"title_ext\": \"美容装潢-打蜡\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_neishiqingxi@3px.png\",\n" +
            "                    \"serviceParentId\": 1,\n" +
            "                    \"target_global_id\": \"9\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"内饰清洗\",\n" +
            "                    \"title_ext\": \"美容装潢-内饰清洗\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_paoguang@3px.png\",\n" +
            "                    \"serviceParentId\": 2,\n" +
            "                    \"target_global_id\": \"29\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"抛光\",\n" +
            "                    \"title_ext\": \"美容装潢-抛光\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_fengzhou@3px.png\",\n" +
            "                    \"serviceParentId\": 2,\n" +
            "                    \"target_global_id\": \"30\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"封轴\",\n" +
            "                    \"title_ext\": \"美容装潢-封轴\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_dumo@3px.png\",\n" +
            "                    \"serviceParentId\": 2,\n" +
            "                    \"target_global_id\": \"31\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"镀膜\",\n" +
            "                    \"title_ext\": \"美容装潢-镀膜\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_dujing@3px.png\",\n" +
            "                    \"serviceParentId\": 2,\n" +
            "                    \"target_global_id\": \"17\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"镀晶\",\n" +
            "                    \"title_ext\": \"美容装潢-镀晶\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_banjin@3px.png\",\n" +
            "                    \"serviceParentId\": 2,\n" +
            "                    \"target_global_id\": \"32\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"钣金\",\n" +
            "                    \"title_ext\": \"美容装潢-钣金\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_penqi@3px.png\",\n" +
            "                    \"serviceParentId\": 2,\n" +
            "                    \"target_global_id\": \"10\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"喷漆\",\n" +
            "                    \"title_ext\": \"美容装潢-喷漆\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_tiemo@3px.png\",\n" +
            "                    \"serviceParentId\": 4,\n" +
            "                    \"target_global_id\": \"16\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"贴膜\",\n" +
            "                    \"title_ext\": \"美容装潢-贴膜\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_chezaianzhuang@3px.png\",\n" +
            "                    \"serviceParentId\": 4,\n" +
            "                    \"target_global_id\": \"33\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"车载安装\",\n" +
            "                    \"title_ext\": \"美容装潢-车载安装\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_meirong_zuoyibaopi@3px.png\",\n" +
            "                    \"serviceParentId\": 4,\n" +
            "                    \"target_global_id\": \"34\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"坐椅包皮\",\n" +
            "                    \"title_ext\": \"美容装潢-坐椅包皮\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"layout_style\": \"1\",\n" +
            "            \"pic_path\": \"\",\n" +
            "            \"tip_pic\": \"\",\n" +
            "            \"title\": \"美容装潢\",\n" +
            "            \"title_ext\": \"美容装潢\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"childIndexTopicItems\": [\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_baoyang_fadongjiqingxi@3px.png\",\n" +
            "                    \"serviceParentId\": 3,\n" +
            "                    \"target_global_id\": \"13\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"发动机清洗\",\n" +
            "                    \"title_ext\": \"保养服务-发动机清洗\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_baoyang_kongtiaoguanliqingxi@3px.png\",\n" +
            "                    \"serviceParentId\": 3,\n" +
            "                    \"target_global_id\": \"15\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"空调管路清洗\",\n" +
            "                    \"title_ext\": \"保养服务-空调管路清洗\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_baoyang_kongqizhilengji@3px.png\",\n" +
            "                    \"serviceParentId\": 3,\n" +
            "                    \"target_global_id\": \"22\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"空调制冷剂\",\n" +
            "                    \"title_ext\": \"保养服务-空调制冷剂\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_baoyang_kongtiaoluqingqi@3px.png\",\n" +
            "                    \"serviceParentId\": 3,\n" +
            "                    \"target_global_id\": \"14\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"空调滤清器\",\n" +
            "                    \"title_ext\": \"保养服务-空调滤清器\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_baoyang_jiyou@3px.png\",\n" +
            "                    \"serviceParentId\": 3,\n" +
            "                    \"target_global_id\": \"23\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"换机油\",\n" +
            "                    \"title_ext\": \"保养服务-换机油\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_baoyang_huanshacheyou@3px.png\",\n" +
            "                    \"serviceParentId\": 3,\n" +
            "                    \"target_global_id\": \"24\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"换刹车油\",\n" +
            "                    \"title_ext\": \"保养服务-换刹车油\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"layout_style\": \"1\",\n" +
            "            \"pic_path\": \"\",\n" +
            "            \"tip_pic\": \"\",\n" +
            "            \"title\": \"保养服务\",\n" +
            "            \"title_ext\": \"保养服务\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"childIndexTopicItems\": [\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_luntai_butai@3px.png\",\n" +
            "                    \"serviceParentId\": 42,\n" +
            "                    \"target_global_id\": \"35\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"补胎\",\n" +
            "                    \"title_ext\": \"轮胎服务-补胎\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_luntai_huantai@3px.png\",\n" +
            "                    \"serviceParentId\": 42,\n" +
            "                    \"target_global_id\": \"36\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"换胎\",\n" +
            "                    \"title_ext\": \"轮胎服务-换胎\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_luntai_silundingwei@3px.png\",\n" +
            "                    \"serviceParentId\": 42,\n" +
            "                    \"target_global_id\": \"37\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"四轮定位\",\n" +
            "                    \"title_ext\": \"轮胎服务-四轮定位\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_luntai_huanshachepian@3px.png\",\n" +
            "                    \"serviceParentId\": 42,\n" +
            "                    \"target_global_id\": \"38\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"换刹车片\",\n" +
            "                    \"title_ext\": \"轮胎服务-换刹车片\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"layout_style\": \"1\",\n" +
            "            \"pic_path\": \"\",\n" +
            "            \"tip_pic\": \"\",\n" +
            "            \"title\": \"轮胎服务\",\n" +
            "            \"title_ext\": \"轮胎服务\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"childIndexTopicItems\": [\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_weixiu_yijiantuoche@3px.png\",\n" +
            "                    \"target_type\": 600,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"一键拖车\",\n" +
            "                    \"title_ext\": \"快修/维修-一键拖车\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"layout_style\": \"1\",\n" +
            "                    \"pic_path\": \"http://img.car-house.cn/Upload/customer/car-icon/icon_chexiaomi_weixiu_fadongjichaizhuang@3px.png\",\n" +
            "                    \"serviceParentId\": 42,\n" +
            "                    \"target_global_id\": \"41\",\n" +
            "                    \"target_type\": 8,\n" +
            "                    \"tip_pic\": \"\",\n" +
            "                    \"title\": \"发动机拆装\",\n" +
            "                    \"title_ext\": \"快修/维修-发动机拆装\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"layout_style\": \"1\",\n" +
            "            \"pic_path\": \"\",\n" +
            "            \"tip_pic\": \"\",\n" +
            "            \"title\": \"快修/维修\",\n" +
            "            \"title_ext\": \"快修/维修\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"head\": {\n" +
            "        \"bcode\": 1,\n" +
            "        \"bmessage\": \"Success!\",\n" +
            "        \"code\": 1,\n" +
            "        \"message\": \"Success!\",\n" +
            "        \"responseTime\": 1473856592612\n" +
            "    }\n" +
            "}";

    List<ShopData.DataBean.ChildIndexTopicItemsBean> mDatas = new ArrayList<ShopData.DataBean.ChildIndexTopicItemsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRcv = (RecyclerView) findViewById(R.id.rcv);

        Gson gson = new Gson();
        ShopData shopData = gson.fromJson(data, ShopData.class);
        for (int i = 0; i < shopData.data.size(); i++) {
            ShopData.DataBean dataBean = shopData.data.get(i);
            ShopData.DataBean.ChildIndexTopicItemsBean itemsBean = new ShopData.DataBean.ChildIndexTopicItemsBean();
            itemsBean.title = dataBean.title;
            itemsBean.type = 1;
            mDatas.add(itemsBean);
            for (int j = 0; j < dataBean.childIndexTopicItems.size(); j++) {
                mDatas.add(dataBean.childIndexTopicItems.get(j));
            }
        }

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                ShopData.DataBean.ChildIndexTopicItemsBean item = mDatas.get(position);
                if (item.type == 1) {
                    return 4;
                }
                return 1;
            }
        });

        mRcv.setLayoutManager(manager);

        RcyMultiItemTypeSupport<ShopData.DataBean.ChildIndexTopicItemsBean> support = new RcyMultiItemTypeSupport<ShopData.DataBean.ChildIndexTopicItemsBean>() {
            @Override
            public int getLayoutId(int type) {
                if (type == 1) {
                    return R.layout.act_title;
                }
                return R.layout.act_item;
            }

            @Override
            public int getItemViewType(int postion, ShopData.DataBean.ChildIndexTopicItemsBean item) {
                return item.type;
            }
        };
        RcyQuickAdapter<ShopData.DataBean.ChildIndexTopicItemsBean> mAdapter=new RcyQuickAdapter<ShopData.DataBean.ChildIndexTopicItemsBean>(mDatas,support) {
            @Override
            public void convert(RcyBaseHolder holder, ShopData.DataBean.ChildIndexTopicItemsBean item, int position) {
                holder.setText(R.id.tv_theme,item.title);
                if(item.type!=1){
                    ImageView iv=holder.getView(R.id.iv_icon);
                    Glide.with(Main2Activity.this).load(item.pic_path).into(iv);
                }
                holder.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Main2Activity.this,Main03Act.class));
                    }
                });
            }
        };
        mRcv.setAdapter(mAdapter);
    }
}
