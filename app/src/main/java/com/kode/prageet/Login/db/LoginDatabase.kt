package com.kode.prageet.Login.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
private var INSTANCE:LoginDatabase?=null
@Database(entities = [UserProfile::class], version = 1, exportSchema = false)
abstract class LoginDatabase:RoomDatabase() {

    abstract fun getLoginDao():LoginDAO
    companion object{


        fun getLoginDatabaseInstance(context: Context):LoginDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null)
            {
                return tempInstance
            }
            else{
                synchronized(this){
                    val instance= Room.databaseBuilder(
                        context.applicationContext,
                        LoginDatabase::class.java,
                        "login_database"
                    ).build()
                    INSTANCE=instance
                    return instance
                }
            }
        }
    }

}