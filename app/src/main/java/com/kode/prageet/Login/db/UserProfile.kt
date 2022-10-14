package com.kode.prageet.Login.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registered_users")
data class UserProfile (
    @PrimaryKey(autoGenerate = true)
    val Id:Int?,
    val userEmail:String,
    val userPassword:String?,
)