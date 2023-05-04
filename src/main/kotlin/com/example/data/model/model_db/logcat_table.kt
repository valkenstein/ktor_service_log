package com.example.data.model.model_db

import com.example.data.model.dto.LogCateDto
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


object logcat_table : Table() {
    private val id = logcat_table.varchar("id", 20)
    private val response = logcat_table.varchar("response", 400)
    private val request = logcat_table.varchar("request", 200)
    private val url = logcat_table.varchar("url", 50)
    private val body = logcat_table.varchar("body", 200)
    private val code = logcat_table.varchar("code", 10)

    fun insert(userDTO: LogCateDto) {
        transaction {
            logcat_table.insert {
                it[id] = userDTO.id?: ""
                it[response] = userDTO.response?: ""
                it[request] = userDTO.request?: ""
                it[url] = userDTO.url ?: ""
                it[body] = userDTO.body ?: ""
                it[code] = userDTO.code ?: ""
            }
        }
    }

    fun fetchUser(login: String): LogCateDto? {
        return try {
            transaction {
                val userModel = logcat_table.select { logcat_table.id.eq(login) }.single()
                LogCateDto(
                    response = userModel[response],
                    request = userModel[request],
                    url = userModel[url],
                    body = userModel[body],
                    code = userModel[code],
                    id = userModel[this@logcat_table.id],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}