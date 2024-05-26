package cn.sichuan.cd.zsh.zsh
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import cn.sichuan.cd.zsh.R
import cn.sichuan.cd.zsh.databinding.ActivityLoginBinding

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

    }
}
