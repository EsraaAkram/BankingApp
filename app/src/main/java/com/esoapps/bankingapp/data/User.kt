package com.esoapps.bankingapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.USER_TABLE_NAME)
@Parcelize
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "mail") val mail: String? = "$name@gmail.com",
    @ColumnInfo(name = "currentBalance") var currentBalance: Int,
) : Parcelable


@Entity(tableName = Constants.TRANSACTIONS_TABLE_NAME)
@Parcelize
data class Transaction(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = null,

    @ColumnInfo(name = "fromUser") val fromUser: User,
    @ColumnInfo(name = "toUser") val toUser: User,
    @ColumnInfo(name = "amount") val amount: Int,

    ) : Parcelable

