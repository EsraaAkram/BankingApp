package com.esoapps.bankingapp.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter


@SuppressLint("SetTextI18n")
@BindingAdapter("from")
fun setFromText(
    tv: TextView, txt: String,

    ) {
    tv.text = "From: $txt"
    Log.d("text", txt)

}

@SuppressLint("SetTextI18n")
@BindingAdapter("to")
fun setToText(
    tv: TextView, txt: String,

    ) {
    tv.text = "To: $txt"

}

@SuppressLint("SetTextI18n")
@BindingAdapter("amount")
fun setAmountText(
    tv: TextView, amount: Int,

    ) {
    tv.text = "amount: ${amount}$"

}


@SuppressLint("SetTextI18n")
@BindingAdapter("balance")
fun setBalanceText(
    tv: TextView, balance: Int,

    ) {
    tv.text = "Balance :${balance}$"

}

