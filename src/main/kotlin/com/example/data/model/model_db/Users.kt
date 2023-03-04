package com.example.data.model.model_db

import com.example.data.model.dto.LogCateDto
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
object LogCateModel : Table() {
    private val id = LogCateModel.varchar("id", 20)
    private val response = LogCateModel.varchar("response", 400)
    private val request = LogCateModel.varchar("request", 200)
    private val url = LogCateModel.varchar("url", 50)
    private val body = LogCateModel.varchar("body", 200)
    private val code = LogCateModel.varchar("code", 10)

    fun insert(userDTO: LogCateDto) {
        transaction {
            LogCateModel.insert {
                it.set(id, userDTO.id?: "")
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
                val userModel = LogCateModel.select { LogCateModel.id.eq(login) }.single()
                LogCateDto(
                    id = userModel[LogCateModel.id],
                    response = userModel[response],
                    request = userModel[request],
                    url = userModel[url],
                    body = userModel[body],
                    code = userModel[code],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}