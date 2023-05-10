package com.esoapps.bankingapp

import android.app.Application
import com.esoapps.bankingapp.data.AppDatabase
import com.esoapps.bankingapp.data.MainRepository

class MyApp : Application() {


    private val database by lazy { AppDatabase.getAppDatabase(this) }

    val repository by lazy {
        MainRepository(
            database.userDao,
            database.transactionDao
        )
    }


    override fun onCreate() {
        super.onCreate()

        instance = this

    }


    companion object {
        lateinit var instance: MyApp
            private set
    }


}