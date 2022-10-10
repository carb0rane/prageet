package com.kode.proj1.AppStatus

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AppStatus::class], version = 1, exportSchema = false)
abstract class AppStatusDatabase : RoomDatabase() {
    abstract fun appStatusDao(): AppStatusDAO

    companion object{
        @Volatile
        private var INSTANCE: AppStatusDatabase?=null
         fun getAppStatFromDataBase(context:Context): AppStatusDatabase {
             val tempInstance= INSTANCE
             if(tempInstance!=null){
                 return tempInstance
             }
                 synchronized(this){
                     val instance= Room.databaseBuilder(
                         context.applicationContext,
                         AppStatusDatabase::class.java,
                         "app_status"
                     ).build()
                     INSTANCE =instance
                     return instance
                 }
             }



    }
}

