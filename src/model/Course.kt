package model

import com.google.gson.Gson

data class Course(val id: Int,
                  var title: String,
                  var level: Int,
                  var isActive: Boolean) {
    companion object GSON {
        val gson = Gson()
    }

    fun toJson(): String {
        return gson.toJson(this)
    }
}