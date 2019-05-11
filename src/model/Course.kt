package model

import com.google.gson.Gson

data class Course(val id: Int,
                  var title: String,
                  var level: Int,
                  var isActive: Boolean) {
    companion object GSON {
        val gson = Gson()
    }

    /**
     * Return the course in a json format
     *
     * ex : {"id":42,"title":"title of the course","level":1,"isActive":true}
     * @return all the values of the course as a json
     */
    fun toJson(): String = gson.toJson(this)
}