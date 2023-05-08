package com.esoapps.bankingapp.ui.activities


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoapps.bankingapp.data.User
import com.esoapps.bankingapp.data.MainRepository
import com.esoapps.bankingapp.data.Transaction

import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    private var _allDbUsers = MutableLiveData<List<User>?>(null)
    val allDbUsers: LiveData<List<User>?> get() = _allDbUsers

    private var _allDbTransactions = MutableLiveData<List<Transaction>?>(null)
    val allDbTransactions: LiveData<List<Transaction>?> get() = _allDbTransactions


    fun addUsers() {

        val users = mutableListOf<User>()

        users.add(User(name = "Roman", currentBalance = 2000))
        users.add(User(name = "William", currentBalance = 1000))
        users.add(User(name = "James", currentBalance = 2500))
        users.add(User(name = "Emma", currentBalance = 2300))
        users.add(User(name = "Alex", currentBalance = 4000))
        users.add(User(name = "Henry", currentBalance = 3000))
        users.add(User(name = "Samanta", currentBalance = 10000))
        users.add(User(name = "Karen", currentBalance = 15000))
        users.add(User(name = "Mark", currentBalance = 1000))
        users.add(User(name = "Bill", currentBalance = 200))
        users.add(User(name = "Noah", currentBalance = 1000))
        users.add(User(name = "Oliver", currentBalance = 6000))
        users.add(User(name = "Jackson", currentBalance = 4000))
        users.add(User(name = "Jack", currentBalance = 4050))
        users.add(User(name = "Mia", currentBalance = 4500))

        viewModelScope.launch {
            mainRepository.insertUser(*users.toTypedArray())
        }

    }


    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            mainRepository.insertTransactions(transaction)
        }

    }

    fun getAllDbUsers() {

        viewModelScope.launch {
            mainRepository.getAllDbUsers()
                .collect {
                    _allDbUsers.value = it

                }
        }
    }

    fun getAllDbTransactions() {
        viewModelScope.launch {
            mainRepository.getAllDbTransactions()
                .collect {
                    _allDbTransactions.value = it

                }
        }

    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            mainRepository.updateUser(user = user)
        }

    }


}