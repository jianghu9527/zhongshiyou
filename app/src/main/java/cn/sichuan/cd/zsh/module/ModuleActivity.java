package cn.sichuan.cd.zzsy.module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.scwang.smart.drawable.paint.BuildConfig;

import cn.sichuan.cd.common.BaseCommonApplication;
import cn.sichuan.cd.common.LoginManager;
import cn.sichuan.cd.common.NetManageUtils;
import cn.sichuan.cd.order.OrderMainActivity;
import cn.sichuan.cd.order.Order_MainActivity;
import cn.sichuan.cd.zsh.utils.WorkManagerHelper;
import cn.sichuan.cd.zzsy.R;
import cn.sichuan.cd.zzsy.zsh.LogMangeUtil;


/**
 * 模块组件化
 *
 * 壳子
 */
public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_layout);
        LogMangeUtil.d("-------------------ModuleActivity---------------000-------");
        new NetManageUtils().NetStat(true);
        LoginManager.INSTANCE.login("ck","123456");
        LogMangeUtil.d("-------------------ModuleActivity---------------1111-------"+ BuildConfig.BUILD_TYPE);

    }

    public void toOrderModule(View view){

        startActivity(new Intent(ModuleActivity.this, Order_MainActivity.class));
    }


    public void toPersionModule(View view){
        startActivity(new Intent(ModuleActivity.this, OrderMainActivity.class));
    }
    public void toWorkManager(View view){
        WorkManagerHelper.schedulePeriodicWork(this);
    }


}
