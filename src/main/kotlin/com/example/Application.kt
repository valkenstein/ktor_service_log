package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.example.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/logcate_db",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "123987"
    )

    embeddedServer(
        CIO,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureRoutingLog()
}
