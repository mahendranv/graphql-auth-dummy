package com.ex2.springauthdemo.auth

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation

@DgsComponent
class DummyResource {

    @VisitorOnly
    @DgsMutation
    fun registerUser(): String = "dummy-member-id"

    @BuyerOnly
    @DgsMutation
    fun placeOrder(): String = "dummy-order-id"

    @SellerOnly
    @DgsMutation
    fun addProduct(): String = "dummy-product-id"

}