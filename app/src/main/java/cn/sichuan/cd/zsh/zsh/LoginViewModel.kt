package cn.sichuan.cd.zsh.zsh

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult
    private var _savedUser = MutableLiveData<User?>()
    val savedUser: LiveData<User?> get() = _savedUser

    private var _Usersaved = MutableLiveData<User?>()
    val User_saved: LiveData<User?> get() = _Usersaved

    init {
        fetchSavedUser()
    }

    private fun fetchSavedUser() {
        viewModelScope.launch {
            val user = userDao.getAnyUser()
            _savedUser.postValue(user)
        }
    }




    fun login(username: String, password: String) {
        viewModelScope.launch {

            if (username.isEmpty()|| password.isEmpty()){
                Toast.makeText(getApplication(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                // 返回，不执行下面的代码
                _loginResult.value = false
                return@launch;
            }

            userDao.deleteAllUsers();
            var   muser=   User(username,password,false);
            userDao.insert(muser);
            Toast.makeText(getApplication(), "账号保存成功", Toast.LENGTH_SHORT).show();
        }

    }


    fun getUser() {
        viewModelScope.launch {

            val users = userDao.getAnyUser();
            _Usersaved.postValue(users)

            LogMangeUtil.d("----------------","--------2----username----"+users?.username);
            LogMangeUtil.d("----------------","--------2------password--"+users?.password);
        }
    }


}
