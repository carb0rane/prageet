package com.kode.prageet.AppStatus

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_status")
data class AppStatus (
    @PrimaryKey(autoGenerate = true)
    val Id:Int?,
    val UserLoggdIn:Boolean?=false,
    val loggedInUserid:String?,
    val playWhenReady:Boolean=true,
    val currentItem:Int=0,
    val playBackPosition:Long=0L

)