package cn.sichuan.cd.zsh.mvvm

import androidx.room.*



@Entity(tableName = "userslogin")
class User( @PrimaryKey
            val username: String,
            val password: String)



