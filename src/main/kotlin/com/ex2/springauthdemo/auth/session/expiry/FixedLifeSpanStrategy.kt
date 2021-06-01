package com.ex2.springauthdemo.auth.session.expiry

import com.ex2.springauthdemo.auth.session.SessionToken
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

@Component
class FixedLifeSpanStrategy : SessionExpiryStrategy {

    override fun isSessionExpired(token: SessionToken): Boolean {
        val durationSinceLogin = ChronoUnit.SECONDS.between(
            token.created,
            OffsetDateTime.now()
        )
        println("FixedTimeStampStrategy.isSessionExpired -- $durationSinceLogin")
        return durationSinceLogin > MAX_DURATION
    }

    companion object {

        const val MAX_DURATION = 30

    }
}