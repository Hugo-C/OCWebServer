package com.example.routes

import com.example.NO_COURSE_FOUND_MESSAGE
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.*
import model.Course

fun Route.course() {

    route("/course") {

        val myCourses = listOf(
            Course(0, "How to troll a Troll ?", 5, true),
            Course(1, "How to troll a Troll ?", 5, true),
            Course(2, "Kotlin for troll", 1, true),
            Course(3, "My kotlin course", 2, false)
        )

        get("/"){
            call.respond("try course/<id> to get information on a course")
        }

        get("/{id}") {
            try {
                val id = call.parameters["id"]?.toInt()!!
                if(id >= myCourses.size){
                    call.respond("{\"status\":404, \"message\":$NO_COURSE_FOUND_MESSAGE}")
                } else {
                    val course = myCourses[id]
                    call.respond(course.toJson())
                }
            } catch (numberException: NumberFormatException){
                call.respond(HttpStatusCode.BadRequest)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}