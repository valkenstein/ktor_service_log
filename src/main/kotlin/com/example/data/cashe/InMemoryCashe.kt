package com.example.data.cashe

import com.example.data.model.dto.TokenCache
import com.example.data.model.response.RegisterReceiveRemote

object InMemoryCache {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}