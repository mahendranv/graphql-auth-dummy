package com.ex2.springauthdemo.auth.session

import graphql.GraphQLException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder
import javax.servlet.http.HttpServletRequest

@Component
class DummyRequestManager {

    @Autowired
    private lateinit var userService: DummyUserService

    // Save the session info per request. Retrieve it throughout the request
    fun saveSession(request: HttpServletRequest) {
        // Retrieve auth token from request - if any
        val token: String? = request.getHeader(HEADER_TOKEN)

        // Identify role
        val role = userService.identifyRole(token)

        if (role == null) {
            throw HttpClientErrorException.create(
                HttpStatus.UNAUTHORIZED,
                "",
                HttpHeaders.EMPTY,
                ByteArray(0),
                Charsets.UTF_8
            )
        } else {
            request.setAttribute(
                KEY_SESSION, DummySession(
                    role = role
                )
            )
        }
    }

    // A non-null guaranteed session. Everyone has a role here.
    fun getSession(): DummySession {
        val session = RequestContextHolder
            .getRequestAttributes()!!.getAttribute(KEY_SESSION, RequestAttributes.SCOPE_REQUEST)
        return session as DummySession
    }

    /**
     * Convenience method to retrieve role
     */
    fun getUserRole(): Roles = getSession().role

    companion object {

        private const val KEY_SESSION = "userSession"

        private const val HEADER_TOKEN = "x-auth-token"
    }
}

data class DummySession(

    val role: Roles

)