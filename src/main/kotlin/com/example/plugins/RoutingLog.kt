package com.example.plugins

import com.example.data.model.dto.LogCateDto
import com.example.data.model.model_db.logcat_table
import com.example.data.model.response.LogCateResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Application.configureRoutingLog() {
    routing {
        post("/logcat") {
            val reciver = call.receive<LogCateResponse>()
            try {
                logcat_table.insert(
                    LogCateDto(
                        response = reciver.response,
                        request = reciver.request,
                        body = reciver.body,
                        code = reciver.code,
                        url = reciver.url,
                        id = reciver.id,
                    )
                )
                call.respondText("ok")
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
            }
        }
    }
}