package com.kode.proj1.AppStatus

import androidx.room.*

@Dao
interface AppStatusDAO {

    @Query("SELECT * FROM app_status ORDER BY UserLoggdIn LIMIT 1")
    fun isUserLoggedIn():Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addAppStatus(status: AppStatus)

}