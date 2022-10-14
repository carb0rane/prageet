package com.kode.prageet.Login.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDAO {

    @Query("SELECT userPassword FROM registered_users where userEmail = :usermail")
    suspend fun checkUserEmail(usermail:String):String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(userdetail:UserProfile):Long

    @Query("SELECT * FROM registered_users")
    suspend fun getAllUsers():UserProfile

}