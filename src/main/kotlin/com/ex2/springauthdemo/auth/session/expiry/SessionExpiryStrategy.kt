package com.ex2.springauthdemo.auth.session.expiry

import com.ex2.springauthdemo.auth.session.SessionToken

/**
 * Contract for when the session should end
 */
interface SessionExpiryStrategy {

    fun isSessionExpired(token: SessionToken): Boolean

}