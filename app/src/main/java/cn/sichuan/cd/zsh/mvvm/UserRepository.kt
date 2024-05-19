package cn.sichuan.cd.zsh.mvvm

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

// UserRepository.kt
class UserRepository(private val userDao:  LoginUserDao) {

    suspend fun insert(user:  User) {
        userDao.insert(user)
    }

    suspend fun getUser(username: String):  User? {
        return userDao.getUser(username)
    }


    suspend fun getUserNamePassWord():  User? {
        return userDao.getAnyUser()
    }

    suspend fun deleteAllUsers(){
        return userDao.deleteAllUsers()
    }



    suspend fun  loadAllUsers():  List<User>  {
     var mlist=   userDao.getAllUsers();

        return mlist;
    }
}
