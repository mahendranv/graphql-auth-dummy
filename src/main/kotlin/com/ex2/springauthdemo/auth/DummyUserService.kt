package com.ex2.springauthdemo.auth

import org.springframework.stereotype.Service

@Service
class DummyUserService {

    val sessions = mapOf(
        "token-buyer" to Roles.BUYER,
        "token-seller" to Roles.SELLER
    )

    fun identifyRole(token: String?): Roles {
        return sessions[token] ?: Roles.VISITOR
    }
}

enum class Roles {
    VISITOR,
    BUYER,
    SELLER
}