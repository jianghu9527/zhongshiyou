package com.efficient.production.app.zsh

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.efficient.production.app.R
import com.efficient.production.app.databinding.ActivityLoginBinding
import com.hjq.permissions.OnPermissionCallback
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
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
             LogMangeUtil.d(
                 "-------------------------",
                 "----------cleardataButton------------创建缓存文件---"
             );

//            creatFileDir();

//            val bitmap: Bitmap =Bitmap. // 获取或生成您的 Bitmap
//            val bitmap = BitmapFactory.decodeFile("path")
//            MediaStoreUtils(). saveImageToPublicDirectory(this, bitmap, "example_image")

        }


        getPermissions();

    }


    /**
     * 初始化下载路径
     */
    @SuppressLint("SuspiciousIndentation")
    fun  creatFileDir() {

        var public_img: String ="";
        var public_video: String ="";

        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            public_img = Environment.getExternalStorageDirectory().absolutePath + Constants.path_base + Constants.path_img
            public_video = Environment.getExternalStorageDirectory().absolutePath + Constants.path_base + Constants.path_video;
        } else {
            public_img = Environment.getDataDirectory().absolutePath + Constants.path_base + Constants.path_img;
            public_video = Environment.getDataDirectory().absolutePath + Constants.path_base + Constants.path_video
        }
        LogMangeUtil.d(
            "------------------初始化下载路径------------",
            "------------------Environment：${Environment.getExternalStorageState()}"
        );


        LogMangeUtil.d(
            "------------------初始化下载路径------------",
            "------------------public_img：${public_img}"
        );
        LogMangeUtil.d(
            "------------------初始化下载路径------------",
            "------------------public_video：${public_video}"
        );

//        var mdocuments=  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
//        LogMangeUtil.d("------------------初始化下载路径------------","------------------mdocuments：${mdocuments}");
        val folderName = "1_MyFolder"
        val folderPath = getExternalFilesDir(null)?.absolutePath + File.separator + folderName
        val newFile = File(folderPath)

//       var  newFile= File(public_img);

//       var mcreateOrExistsDir=   FileUtils.createOrExistsFile(newFile)
//        LogMangeUtil.d("","---------------mcreateOrExistsDir------created:${mcreateOrExistsDir}");


        if (!newFile.exists()){
             var  created=  newFile.mkdirs();
            LogMangeUtil.d("", "---------------newFile------created:${created}");

        }else{
            LogMangeUtil.d("", "---------------newFile----------5555----------------created:");
        }

      var  file = File(public_video)
        if (!file.exists()) {
            file.mkdirs()
            LogMangeUtil.d(
                "",
                "---------------newFile----------file-----------能在data的data下创建-----created:" + file.absolutePath
            );

        }

    var mfiles=    Environment.getExternalStorageDirectory();
         var mnfile= File(mfiles,"1_1_zsh");
        if (!mnfile.exists()) {
          var mesfile=  mnfile.mkdirs()
            LogMangeUtil.d("", "------------mnfile---------------created:" + mnfile.absolutePath);
            LogMangeUtil.d("", "------------mnfile-------mesfile--------created:" + mesfile);

            try {
                var messfile= mnfile.createNewFile();
                LogMangeUtil.d(
                    "",
                    "------------mnfile-------mesfile--11------messfile:" + messfile
                );
            }catch (e:Exception){
                LogMangeUtil.d(
                    "",
                    "------------mnfile-------mesfile--message------messfile:" + e.message
                )
            }

        }


        val appSpecificExternalDir = File(getExternalFilesDir(null), "1_2_MyFolder")
        if (!appSpecificExternalDir.exists()) {
            val isCreated = appSpecificExternalDir.mkdirs()
            LogMangeUtil.d(
                "-----------------------------MainActivity",
                "----------------appSpecificExternalDir:${appSpecificExternalDir}"
            )
            if (isCreated) {
                LogMangeUtil.d(
                    "-----------------------------MainActivity",
                    "------------------------Directory created"
                )
            } else {
                LogMangeUtil.d(
                    "------------------------MainActivity",
                    "------------------------Directory not created"
                )
            }
        }





   }

    private val REQUSTCODE = 101
    fun getPermissions() {
        Tools().askForPerssion(this@LoginActivity, REQUSTCODE)
        LogMangeUtil.d("------------getPermisssions----------------开始申请权限------------")
        XXPermissions.with(this) // 申请单个权限
            .permission(Permission.RECORD_AUDIO) //麦克风权限
            .permission(Permission.CAMERA) //相机权限
            .permission(Permission.ACCESS_COARSE_LOCATION) //获取粗略位置
            .permission(Permission.ACCESS_FINE_LOCATION) //获取精确位置
                            .permission(Permission.WRITE_EXTERNAL_STORAGE)

            //                .permission(Permission.MANAGE_EXTERNAL_STORAGE)
            //                .permission(Permission.ACCESS_BACKGROUND_LOCATION)
            // 申请多个权限
            //.permission(Permission.Group.CALENDAR)
            // 申请安装包权限
            //.permission(Permission.REQUEST_INSTALL_PACKAGES)
            //                 读写的权限

            .permission(Permission.READ_EXTERNAL_STORAGE) //读取外部存储
            //                 读写的权限
            .permission(Permission.WRITE_EXTERNAL_STORAGE) //写入外部存储
            // 申请系统设置权限
            //.permission(Permission.WRITE_SETTINGS)
            // 设置权限请求拦截器
            //.interceptor(new PermissionInterceptor())
            // 设置不触发错误检测机制
            //.unchecked()
            .request(object : OnPermissionCallback {
                override fun onGranted(permissions: List<String>, all: Boolean) {
                    LogMangeUtil.d("---------------------LoginActivity-------getPermsissions--------onGranted----获取权限---$all")


                    if (all) {
//                        toast("获取录音和日历权限成功");
                    } else {
//                        toast("获取部分权限成功，但部分权限未正常授予");
                    }
                }

                override fun onDenied(permissions: List<String>, never: Boolean) {
                    LogMangeUtil.d("---------------------LoginActivity-------getPersmissions--------onDenied----获取权限---$never")

                    //                        MyApplication.getInstance().initComment();
                    if (never) {
//                        toast("被永久拒绝授权，请手动授予录音和日历权限");
                        // 如果是被永久拒绝就跳转到应用权限系统设置页面
//                        XXPermissions.startPermissionActivity(this@LoginActivity, permissions)
                    } else {
//                        toastast("获取录音和日历权限失败");
                    }
                }
            })

        ManagerNotifyUtils.isNotifyEnabled(this@LoginActivity)




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUSTCODE)
        }


    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUSTCODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限已授予，执行操作
                LogMangeUtil.d("-------------------权限已授予，执行操作------------")
            } else {
                Toast.makeText(this, "需要存储权限来保存图片", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
