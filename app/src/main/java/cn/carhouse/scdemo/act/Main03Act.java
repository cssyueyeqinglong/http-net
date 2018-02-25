package cn.carhouse.scdemo.act;

import cn.carhouse.scdemo.base.AppActivity;
import cn.carhouse.scdemo.base.BaseCyFragment;
import cn.carhouse.scdemo.fmt.Fmt01;
import cn.carhouse.scdemo.fmt.Fmt13;

/**
 * Created by Administrator
 * on 2016/11/26.
 * des:
 */

public class Main03Act extends AppActivity {

    @Override
    protected BaseCyFragment getFirstFragment() {

        return Fmt01.newInstance();
//        return Fmt13.newInstance();
    }
}
