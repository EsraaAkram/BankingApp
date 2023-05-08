package com.esoapps.bankingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.esoapps.bankingapp.data.Constants.APP_DATABASE_NAME


@Database(entities = [User::class, Transaction::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val transactionDao: TransactionDao

    companion object {

        @Volatile //ALWAYS UP-TO-DATE
        private var INSTANCE: AppDatabase? = null


        fun getAppDatabase(context: Context): AppDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        APP_DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }


        }


    }


}