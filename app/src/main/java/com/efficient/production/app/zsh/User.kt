package com.efficient.production.app.zsh

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey val username: String,
    val password: String,
    val isLogin: Boolean
)