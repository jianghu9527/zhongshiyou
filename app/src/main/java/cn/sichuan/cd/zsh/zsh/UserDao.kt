package cn.sichuan.cd.zsh.zsh
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUser(username: String): User?


    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>



    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getAnyUser(): User?

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()


}
