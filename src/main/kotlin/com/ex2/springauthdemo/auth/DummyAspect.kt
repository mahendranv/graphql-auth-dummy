package com.ex2.springauthdemo.auth

import graphql.GraphQLException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
@Component
class DummyAspect {

    @Autowired
    private lateinit var requestManager: DummyRequestManager

    @Before("@annotation(SellerOnly)")
    fun restrictSellerOnly(joinPoint: JoinPoint) {
        val role = requestManager.getUserRole()
        if (role != Roles.SELLER) {
            throw GraphQLException("This operation is specific to Seller accounts")
        }
    }

    @Before("@annotation(BuyerOnly)")
    fun restrictBuyerOnly(joinPoint: JoinPoint) {
        val role = requestManager.getUserRole()
        if (role != Roles.BUYER) {
            throw GraphQLException("This operation is specific to Buyer accounts")
        }
    }

    @Before("@annotation(VisitorOnly)")
    fun restrictVisitorOnly(joinPoint: JoinPoint) {
        val role = requestManager.getUserRole()
        if (role != Roles.BUYER) {
            throw GraphQLException("Please logout from existing session")
        }
    }
}