package cn.sichuan.cd.zsh.zsh

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Entity
import androidx.room.PrimaryKey


  class User : BaseObservable(){

    var username: String = ""
        @Bindable get
        set(value) {
            field = value
        }

    var password: String = ""
        @Bindable get
        set(value) {
            field = value
        }


      var isLogin: Boolean =true
          @Bindable get
          set(value) {
              field = value
//              notifyPropertyChanged(BR.isLogin)
          }




}