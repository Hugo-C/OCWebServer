package com.example.routes

import com.example.NO_COURSE_FOUND_MESSAGE
import com.example.toJson
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.*
import model.Course

fun Route.course() {

    route("/course") {

        // FIXME hard-coded set of courses, to be replaced by a database
        val myCourses = setOf(
            Course(1, "How to troll a Troll ?", 5, true),
            Course(2, "Kotlin for troll", 1, true),
            Course(3, "My kotlin course", 2, false)
        )

        get("/"){
            call.respond(myCourses.toJson())
        }

        get("/{id}") {
            try {
                val id = call.parameters["id"]?.toInt()!!
                val course: Course? = myCourses.find { c -> c.id == id }
                if(course != null)
                    call.respond(course.toJson())
                else
                    call.respond("{\"status\":404, \"message\":$NO_COURSE_FOUND_MESSAGE}")
            } catch (numberException: NumberFormatException){
                call.respond(HttpStatusCode.BadRequest)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/top") {
            try {
                val course: Course? = myCourses.maxBy { c -> c.level }
                if(course != null)
                    call.respond(course.toJson())
                else
                    call.respond("{\"status\":404, \"message\":$NO_COURSE_FOUND_MESSAGE}")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}