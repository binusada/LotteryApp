package com.mykodo.utils

import android.content.Context
import com.google.gson.Gson
import com.mykodo.data.Draw
import java.io.IOException
import javax.inject.Inject

class JsonHelper @Inject constructor() {
    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun parseJsonToModel(jsonString: String, ): List<Draw> {
        var result : DrawsModel? = null
        val gson = Gson()
        try {
            result  = gson.fromJson(jsonString, DrawsModel::class.java)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return result?.draws ?: emptyList()
    }
}

data class DrawsModel(
    var draws: List<Draw>
)