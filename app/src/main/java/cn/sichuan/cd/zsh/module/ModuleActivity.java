package cn.sichuan.cd.zzsy.module;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import cn.sichuan.cd.common.BaseCommonApplication;
import cn.sichuan.cd.common.LoginManager;
import cn.sichuan.cd.common.NetManageUtils;
import cn.sichuan.cd.order.OrderMainActivity;
import cn.sichuan.cd.order.Order_MainActivity;
import cn.sichuan.cd.zsh.compose.ComposeManager;
import cn.sichuan.cd.zsh.utils.WorkManagerHelper;
import cn.sichuan.cd.zzsy.BuildConfig;
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
        LogMangeUtil.d("-------------------ModuleActivity-------------BUILD_TIME:"+ BuildConfig.BUILD_TIME);


        LogMangeUtil.d("-------------------ModuleActivity----------IS_RELEASE:"+ BuildConfig.IS_RELEASE);
            if (BuildConfig.IS_RELEASE){
                Log.i("-------------------", "onCreate: 集成化环境");
            }else{
                Log.i("-------------------", "onCreate: 组件化环境");
            }






    }

    public void toOrderModule(View view){

        startActivity(new Intent(ModuleActivity.this, Order_MainActivity.class));
    }


    public void toPersionModule(View view){
        startActivity(new Intent(ModuleActivity.this, OrderMainActivity.class));
    }
    public void toCompose(View view){
        startActivity(new Intent(ModuleActivity.this, ComposeManager.class));
    }



    public void toWorkManager(View view){
        WorkManagerHelper.schedulePeriodicWork(this);
    }


}
