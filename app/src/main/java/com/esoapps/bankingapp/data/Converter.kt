package com.esoapps.bankingapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromUser(value: User) = Gson().toJson(value)

    @TypeConverter
    fun toUser(value: String) = Gson().fromJson(value, User::class.java)
}