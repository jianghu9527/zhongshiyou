package cn.sichuan.cd.zsh.module;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.sichuan.cd.common.LoginManager;
import cn.sichuan.cd.common.NetManageUtils;
import cn.sichuan.cd.zsh.R;
import cn.sichuan.cd.zsh.zsh.LogMangeUtil;


/**
 * 模块组件化
 */
public class ModuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_layout);
        LogMangeUtil.d("-------------------ModuleActivity---------------000-------");
        new NetManageUtils().NetStat(true);
        LoginManager.INSTANCE.login("ck","123456");

        LogMangeUtil.d("-------------------ModuleActivity---------------1111-------");


    }
}
