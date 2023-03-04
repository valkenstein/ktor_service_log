package com.example.data.model.dto

data class LogCateDto(
    val id: String,
    val response: String,
    val request: String,
    val url: String,
    val body: String,
    val code: String,
)