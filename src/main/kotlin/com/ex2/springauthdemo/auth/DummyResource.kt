package com.ex2.springauthdemo.auth

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation

@DgsComponent
class DummyResource2 {

    @VisitorOnly
    @DgsMutation
    fun registerUser2(): String = "dummy-member-id"

    @BuyerOnly
    @DgsMutation
    fun placeOrder(): String = "dummy-order-id"

    @SellerOnly
    @DgsMutation
    fun addProduct(): String = "dummy-product-id"

}