package com.ex2.springauthdemo.auth.session.expiry

import com.ex2.springauthdemo.auth.session.SessionToken
import org.springframework.stereotype.Component

@Component("extended")
class ExtendedLifeSpanStrategy : SessionExpiryStrategy {

    private val timeoutExpiryStrategy = TimeoutExpiryStrategy()
    private val fixedLifeSpanStrategy = FixedLifeSpanStrategy()

    override fun isSessionExpired(token: SessionToken): Boolean {

        return if (fixedLifeSpanStrategy.isSessionExpired(token)) {
            // 30 days past, if user is in the mid of something try the timeout
            // to extend his session
            timeoutExpiryStrategy.isSessionExpired(token)
        } else {
            // 30 days not past yet Let him use the system
            false
        }
    }
}