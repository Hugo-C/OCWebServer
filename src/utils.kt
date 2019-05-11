package com.example

import com.google.gson.Gson
import model.Course

/**
 * Return a collection of course as a json
 *
 * Each course is represented by using course.toJson()
 * @return the collection as a json array
 */
fun Collection<Course>.toJson(): String = Gson().toJson(this.map { c -> c.toJson() })