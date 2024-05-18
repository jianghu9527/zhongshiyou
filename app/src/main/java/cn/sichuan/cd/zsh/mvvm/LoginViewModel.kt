package cn.sichuan.cd.zsh.mvvm

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// LoginViewModel.kt
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginStatus = MutableLiveData<Boolean>()

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        loadUser("default_user") // 加载默认用户
    }

//    fun login(username: String, password: String) {
       fun login() {
            viewModelScope.launch {


            if (username.value!!.isEmpty()|| password.value!!.isEmpty()){
                Toast.makeText(getApplication(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                // 返回，不执行下面的代码
                loginStatus.value = false
                return@launch;
            }else{
                loginStatus.value = true
                saveUser() // 登录成功后保存用户数据
            }

            repository.deleteAllUsers();
            var   muser= User(username.value.toString(), password.value.toString());
            repository.insert(muser);
            Toast.makeText(getApplication(), "账号保存成功", Toast.LENGTH_SHORT).show();

            Log.d("----------login---------------","--------------loginStatus-----1-------"+username .value.toString());
            Log.d("----------login---------------","--------------loginStatus-----2-------"+password.value.toString());


        }
    }

    fun saveUser() {
        viewModelScope.launch {
            val user =  User(username.value ?: "", password.value ?: "")
            repository.insert(user)

            Log.d("----------saveUser---------------","--------------loginStatus-----1-------"+user.password );
            Log.d("----------saveUser---------------","--------------loginStatus-----2-------"+user.username );


        }
    }

    fun loadUser(username: String) {
        viewModelScope.launch {
            val user =   repository.getUserNamePassWord();
            if (user != null) {
                Log.d("----------loadUser---------------","--------------loginStatus-----1-------"+user.password );
                Log.d("----------loadUser---------------","--------------loginStatus-----2-------"+user.username );


                this@LoginViewModel.username.value = user.username
                this@LoginViewModel.password.value = user.password
            }
        }
    }


    fun loadAllUser() {
        // 假设我们有一个异步的函数来加载数据
        // 这里只是简单地模拟一下
        viewModelScope.launch {
            val user =   repository.loadAllUsers();
            if (user != null&& user.value?.size!! >0) {
                Log.d("----------loadUser---------------","--------------loginStatus-----1-------"+user.value?.size!!);



            }
        }
    }
}
