package com.ex2.springauthdemo.auth.session.expiry

import com.ex2.springauthdemo.auth.session.SessionToken
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

/**
 * If user hasn't accessed web service for a while - expire the token. Like in banking apps.
 */
class TimeoutExpiryStrategy : SessionExpiryStrategy {

    override fun isSessionExpired(token: SessionToken): Boolean {
        val durationSinceLastAccess = ChronoUnit.SECONDS.between(
            token.lastAccess,
            OffsetDateTime.now()
        )
        println("TimeoutExpiryStrategy.isSessionExpired -- $durationSinceLastAccess")
        return durationSinceLastAccess > MAX_DURATION
    }

    companion object {

        const val MAX_DURATION = 30
    }
}