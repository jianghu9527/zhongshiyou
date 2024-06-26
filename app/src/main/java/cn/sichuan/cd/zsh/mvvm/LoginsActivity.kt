package cn.sichuan.cd.zzsy.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.sichuan.cd.zzsy.R
import cn.sichuan.cd.zzsy.databinding.ActivityVLoginBinding
import cn.sichuan.cd.zzsy.zsh.LogMangeUtil


// LoginActivity.kt
class LoginsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_v_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        Toast.makeText(this, "启动成功", Toast.LENGTH_SHORT).show()
        var muserinfor= UserInfo();

//        muserinfor.name.set("张三");
//        muserinfor.pwd.set("123");

//        viewModel.loadUser();


//        binding.userinformation=muserinfor;
        LogMangeUtil.d("-------------LoginsActivity-----------","----------name-------------${muserinfor.name.get()}");
        LogMangeUtil.d("-------------LoginsActivity-----------","----------pwd--------------${muserinfor.pwd.get()}");

//      Handler().postDelayed(Runnable {
//
////          muserinfor.name.set("赵四");
////          muserinfor.pwd.set("321");
//          LogMangeUtil.d("-------------LoginsActivity-----------","----Handler------name---------${muserinfor.name.get()}-----");
//          LogMangeUtil.d("-------------LoginsActivity-----------","-----Handler-----pwd---------${muserinfor.pwd.get()}-----");
//
//      },20000);

        // 监听登录状态变化
        viewModel.loginStatus.observe(this, Observer { isSuccess ->


            if (isSuccess) {
                // 登录成功的逻辑处理
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                LogMangeUtil.d("--------------------------","--------------loginStatus-----1-------password："+viewModel.password.value);
                LogMangeUtil.d("--------------------------","--------------loginStatus-----2-------username："+viewModel.username.value);
            } else {
                // 登录失败的逻辑处理
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        });


        viewModel.mUserData.observe(this,Observer{
            mUserData->
            if (mUserData!=null&&mUserData.size>0){
                LogMangeUtil.d("--------------------------","--------------loginStatus----mUserData------mUserData："+mUserData.size);
            }else{
                LogMangeUtil.d("--------------------------","--------------00000000000----mUserData------：");
            }

        })



        binding.videoPlayer.setOnClickListener {
            LogMangeUtil.d("--------------------------","--------------录制视频------------");
//            val intent = Intent(this, VideoActivity::class.java)
//            startActivity(intent)


        }




//************************************************************************************
        //第二种方法
        // 观察用户名的变化
        viewModel.usernamem.observe(this, Observer { username ->
            binding.etUsername.setText(username)
        })

        // 观察密码的变化
        viewModel.passwordm.observe(this, Observer { password ->
            binding.etPassword .setText(password)
        })

        // 设置登录按钮的点击事件
        binding.loginButton.setOnClickListener {
            viewModel.setUsername( binding.etUsername.text.toString())
            viewModel.setPassword( binding.etPassword.text.toString())
            viewModel.login()
        }


    }

}





