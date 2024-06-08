package cn.sichuan.cd.zsh.mvvm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LoginUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM userslogin WHERE username = :username")
    suspend fun getUser(username: String):   User?

    @Query("SELECT * FROM userslogin LIMIT 1")
    suspend fun getAnyUser(): User?

    @Query("DELETE FROM userslogin")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM userslogin")
    fun getAllUsers():  List<User>

}


