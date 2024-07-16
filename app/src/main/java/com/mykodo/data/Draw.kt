package com.mykodo.data

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Draw(
    val id: String,
    val drawDate: Date,
    val number1: String,
    val number2: String,
    val number3: String,
    val number4: String,
    val number5: String,
    val number6: String,
    @SerializedName("bonus-ball")
    val bonusBall: String,
    val topPrize: Long
)
