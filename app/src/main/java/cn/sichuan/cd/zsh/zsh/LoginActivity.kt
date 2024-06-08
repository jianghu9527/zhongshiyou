package cn.sichuan.cd.zsh.zsh

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.sichuan.cd.zsh.R
import cn.sichuan.cd.zsh.databinding.ActivityLoginBinding

import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import java.io.File


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LogMangeUtil.d("----------------LoginActivity-----6666-----")
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )


         var  muser=User();
        muser.username="ck123";
        muser.password="6666"
        muser.isLogin=true;
//        binding.userview=muser;

        binding.userview=muser;

        Handler().postDelayed(Runnable {
            LogMangeUtil.d("-------LoginActivity------username------------------"+muser.username)
            LogMangeUtil.d("------LoginActivity-----password--------------------"+muser.password)

        },15000)

    }




}
