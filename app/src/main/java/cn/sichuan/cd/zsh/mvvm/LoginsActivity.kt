package cn.sichuan.cd.zsh.mvvm

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.sichuan.cd.zsh.R
import cn.sichuan.cd.zsh.databinding.ActivityVLoginBinding


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
        var muserinfor=UserInfo();

//        muserinfor.name.set("张三");
//        muserinfor.pwd.set("123");

//        viewModel.loadUser();


//        binding.userinformation=muserinfor;
        Log.d("-------------LoginsActivity-----------","----------name-------------${muserinfor.name.get()}");
        Log.d("-------------LoginsActivity-----------","----------pwd--------------${muserinfor.pwd.get()}");

//      Handler().postDelayed(Runnable {
//
////          muserinfor.name.set("赵四");
////          muserinfor.pwd.set("321");
//          Log.d("-------------LoginsActivity-----------","----Handler------name---------${muserinfor.name.get()}-----");
//          Log.d("-------------LoginsActivity-----------","-----Handler-----pwd---------${muserinfor.pwd.get()}-----");
//
//      },20000);

        // 监听登录状态变化
        viewModel.loginStatus.observe(this, Observer { isSuccess ->


            if (isSuccess) {
                // 登录成功的逻辑处理
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                Log.d("--------------------------","--------------loginStatus-----1-------password："+viewModel.password.value);
                Log.d("--------------------------","--------------loginStatus-----2-------username："+viewModel.username.value);
            } else {
                // 登录失败的逻辑处理
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        });


        viewModel.mUserData.observe(this,Observer{
            mUserData->
            if (mUserData!=null&&mUserData.size>0){
                Log.d("--------------------------","--------------loginStatus----mUserData------mUserData："+mUserData.size);
            }else{
                Log.d("--------------------------","--------------00000000000----mUserData------：");
            }

        })


    }
}





