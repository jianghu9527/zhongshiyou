package cn.sichuan.cd.zsh.zsh

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.sichuan.cd.zsh.R
import cn.sichuan.cd.zsh.databinding.ActivityLoginBinding
import java.io.File


class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this

        binding.loginButton.setOnClickListener {
            loginViewModel.login(binding.usernameEditText.text.toString(), binding.passwordEditText.text.toString())
        }

        binding.readDataButton.setOnClickListener {
            loginViewModel.getUser();
        }

        loginViewModel.User_saved.observe(this, Observer { user ->
            user?.let {
                Toast.makeText(getApplication(), "读取数据成功", Toast.LENGTH_SHORT).show();
                binding.usernameEditText.setText(it.username)
                binding.passwordEditText.setText(it.password)

            }
        })


        binding.cleardataButton.setOnClickListener {
             Log.d("-------------------------","----------cleardataButton------------创建缓存文件---");

            creatFileDir();

        }

        checkAndRequestPermissions();


    }

    /**
     * 初始化下载路径
     */
   fun  creatFileDir() {

        var public_img: String ="";
        var public_video: String ="";

        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            public_img = Environment.getExternalStorageDirectory().toString() +  Constants.path_base + Constants.path_img
            public_video = Environment.getExternalStorageDirectory().toString() + Constants.path_base + Constants.path_video;
        } else {
            public_img = Environment.getDataDirectory().absolutePath +  Constants.path_base + Constants.path_img;
            public_video = Environment.getDataDirectory().absolutePath +  Constants.path_base + Constants.path_video
        }


        Log.d("------------------初始化下载路径------------","------------------public_img：${public_img}");
        Log.d("------------------初始化下载路径------------","------------------public_video：${public_video}");

        var mdocuments=  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        Log.d("------------------初始化下载路径------------","------------------mdocuments：${mdocuments}");


       var  newFile= File(public_img);
        if (!newFile.exists()){
             var  created=  newFile.mkdirs();
            Log.d("","---------------newFile------created:${created}");

        }


   }


    companion object {
        const val REQUEST_PERMISSION_CODE = 1
    }



    fun checkAndRequestPermissions(){
        Log.d("","-----------------checkAndRequestPermissions-------1-----");

//        val request = XXPermissions.with(this) // 申请单个权限
//            .permission(Permission.RECORD_AUDIO) // 申请多个权限
//            //.permission(Permission.Group.CALENDAR)
//            // 申请安装包权限
//            //.permission(Permission.REQUEST_INSTALL_PACKAGES)
//            // 申请悬浮窗权限
//            //.permission(Permission.SYSTEM_ALERT_WINDOW)
//            // 申请通知栏权限
//            //.permission(Permission.NOTIFICATION_SERVICE)
//            // 申请系统设置权限
//            //.permission(Permission.WRITE_SETTINGS)
//            // 设置权限请求拦截器
//            //.interceptor(new PermissionInterceptor())
//            // 设置不触发错误检测机制
//            //.unchecked()
//            .request(object : OnPermissionCallback() {
//                fun onGranted(permissions: List<String?>?, all: Boolean) {
//                    if (all) {
//                        toast("获取录音和日历权限成功")
//                    } else {
//                        toast("获取部分权限成功，但部分权限未正常授予")
//                    }
//                }
//
//                fun onDenied(permissions: List<String?>?, never: Boolean) {
//                    if (never) {
//                        toast("被永久拒绝授权，请手动授予录音和日历权限")
//                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
//                        XXPermissions.startPermissionActivity(this@MainActivity, permissions)
//                    } else {
//                        toast("获取录音和日历权限失败")
//                    }
//                }
//            })

         



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            Log.d("","-----------------checkAndRequestPermissions-------2-----");
            ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),REQUEST_PERMISSION_CODE)
        }else{
            Log.d("","-----------------checkAndRequestPermissions------3-----");
            creatFileDir();
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: kotlin.Array<out String>,
        grantResults: IntArray
    ) {
        Log.d("","-----------------onRequestPermissionsResult-------1-----");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("","-----------------onRequestPermissionsResult-------2-----"+requestCode);
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // 权限被用户同意，可以进行文件夹创建操作
                    creatFileDir()
                } else {
                    Log.d("","-----------------onRequestPermissionsResult-------被拒绝----");
                }
                return
            }
        }
    }

}
