package com.ex2.springauthdemo.auth.session

import org.springframework.stereotype.Service

@Service
class DummyUserService {

    val role = mutableMapOf(
        "token-buyer" to Roles.BUYER,
        "token-seller" to Roles.SELLER,
        // Any other value will be identified as invalid session
    )

    fun identifyRole(token: String?): Roles? {
        return if (token == null)
            Roles.VISITOR
        else
            role[token]
    }
}

enum class Roles {
    VISITOR,
    BUYER,
    SELLER
}