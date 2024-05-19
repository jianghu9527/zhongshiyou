package cn.sichuan.cd.zsh.mvvm

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
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
        loadUser() // 加载默认用户
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

                Log.d("----------login---------------", "-----password-----------"+username.value);
                Log.d("----------login---------------", "------password----------"+password.value);
            repository.deleteAllUsers();
            var   muser= User(username.value.toString(), password.value.toString());
            repository.insert(muser);
            Toast.makeText(getApplication(), "账号保存成功", Toast.LENGTH_SHORT).show();

            Log.d("----------login---------------","--------login------loginStatus-----1-------"+username .value.toString());
            Log.d("----------login---------------","--------login------loginStatus-----2-------"+password.value.toString());


        }
    }

    fun saveUser() {
        viewModelScope.launch {
            val user =  User(username.value ?: "", password.value ?: "")
            repository.insert(user)

            Log.d("----------saveUser---------------","----saveUser----------loginStatus-----1-------"+user.password );
            Log.d("----------saveUser---------------","-----saveUser---------loginStatus-----2-------"+user.username );


        }
    }

    fun loadUser() {
        viewModelScope.launch {
            val user =   repository.getUserNamePassWord();
            if (user != null) {
                Log.d("----------loadUser---------------","----loadUser----------loginStatus-----2-------"+user.username );
                Log.d("----------loadUser---------------","----loadUser----------loginStatus-----1-------"+user.password );

                this@LoginViewModel.username.value = user.username
                this@LoginViewModel.password.value = user.password
            }
        }
    }


    private val _UserData = MutableLiveData<List<User>>()
    val mUserData: LiveData<List<User>> = _UserData

    fun loadAllUser() {
        // 假设我们有一个异步的函数来加载数据
        // 这里只是简单地模拟一下
        viewModelScope.launch (Dispatchers.IO){
            val user =   repository.loadAllUsers();

            _UserData.postValue(user);
//            if (user != null&& user.value?.size!! >0) {
//                Log.d("----------loadUser---------------","---loadAllUser-----------loginStatus-----1-------"+user.value?.size!!);
//
//
//
//            }
        }
    }

    var  Userinfor =UserInfo();
//    binding.setLoginViewModel(this);
//    binding.setUserInfo(Userinfor);


     var nameInputListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            Userinfor.name.set( charSequence.toString())  ;
            Log.d("---------LoginViewModel---------","----------nameInputListener-------------------------${Userinfor.name}");
        }
        override fun afterTextChanged(editable: Editable) {

        }
    }

    var pwdInputListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

        }
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            Userinfor.pwd.set( charSequence.toString())  ;

            Log.d("---------LoginViewModel---------","----------pwdInputListener-------------------------${Userinfor.pwd}");

        }
        override fun afterTextChanged(editable: Editable) {

        }
    }

    var mlickButtom = View.OnClickListener {

        Log.d("---------LoginViewModel---------","----------mlickButtom-------------------------${Userinfor.name}");
        Log.d("---------LoginViewModel---------","----------mlickButtom-------------------------${Userinfor.pwd}");
    }

}
