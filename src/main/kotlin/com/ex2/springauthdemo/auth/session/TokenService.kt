package com.ex2.springauthdemo.auth.session

import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class TokenService {

    private val store = mutableMapOf<String, SessionToken>()

    init {
        store["token-buyer"] =
            SessionToken("token-buyer", OffsetDateTime.now())
        store["token-seller"] =
            SessionToken("token-seller", OffsetDateTime.now())
    }

    // TODO createToken on Login

    fun peekToken(token: String?): SessionToken? = store[token]

    fun updateLastAccessTime(token: String) {
        // TODO: Token not found in store - fishy!!
        store[token]?.lastAccess = OffsetDateTime.now()
    }
}

data class SessionToken(

    val token: String,

    val created: OffsetDateTime,

    var lastAccess: OffsetDateTime = created

)