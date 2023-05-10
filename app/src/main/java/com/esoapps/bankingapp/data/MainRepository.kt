package com.esoapps.bankingapp.data

import kotlinx.coroutines.flow.Flow


class MainRepository(
    private val userDao: UserDao,
    private val transactionDao: TransactionDao,
) {

    fun getAllDbUsers(): Flow<List<User>> = userDao.getAllDbUsers()
    suspend fun getUserById(id: Long) = userDao.getUserById(id = id)

    suspend fun insertUser(vararg user: User) = userDao.insertUser(user = user)


    fun getAllDbTransactions(): Flow<List<Transaction>> = transactionDao.getAllDbTransactions()
    suspend fun insertTransactions(transaction: Transaction) =
        transactionDao.insertTransactions(transaction)

    suspend fun updateUser(user: User) = userDao.updateUser(user = user)


}