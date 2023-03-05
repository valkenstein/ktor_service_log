package com.example.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LogCateResponse(
    val id: String,
    val response: String,
    val request: String,
    val url: String,
    val body: String,
    val code: String,
)