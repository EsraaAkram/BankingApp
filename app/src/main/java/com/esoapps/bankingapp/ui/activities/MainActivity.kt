package com.esoapps.bankingapp.ui.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.esoapps.bankingapp.MyApp
import com.esoapps.bankingapp.R
import com.esoapps.bankingapp.data.Constants.FIRST_TIME_OPEN
import com.esoapps.bankingapp.databinding.MainActivityBinding


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((this.application as MyApp).repository)
    }

    private var sharedpreferences: SharedPreferences? = null

    private var binding: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.main_activity
        )


        sharedpreferences = getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        )

        if (sharedpreferences?.getBoolean(
                FIRST_TIME_OPEN,
                false
            ) == false
        ) {//ADD USERS I IT ONLY FIRST OPEN

            viewModel.addUsers()

            sharedpreferences?.edit()?.putBoolean(
                FIRST_TIME_OPEN,
                true
            )?.apply()
        }

    }


}