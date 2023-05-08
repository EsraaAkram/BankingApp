package com.esoapps.bankingapp.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {


    @Query("SELECT * FROM ${Constants.USER_TABLE_NAME}")
    fun getAllDbUsers(): Flow<List<User>>

    @Query("SELECT * from ${Constants.USER_TABLE_NAME} WHERE id = :id")
    suspend fun getUserById(id: Long): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg user: User)

    @Update
    suspend fun updateUser(user: User?)



}

@Dao
interface TransactionDao{

    @Query("SELECT * FROM ${Constants.TRANSACTIONS_TABLE_NAME}")
    fun getAllDbTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(vararg transaction: Transaction)
}
