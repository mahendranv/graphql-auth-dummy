package com.ex2.springauthdemo.auth

import com.ex2.springauthdemo.auth.session.DummyRequestManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Interceptor that executed once per http request
 */
@Component
class DummyRequestFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var requestManager: DummyRequestManager

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // Feed the request to request manager for session preparation
            requestManager.saveSession(request)

            // Resume with request
            filterChain.doFilter(request, response)
        } catch (e: HttpClientErrorException) {
            response.sendError(e.rawStatusCode, e.statusText)
        }
    }
}