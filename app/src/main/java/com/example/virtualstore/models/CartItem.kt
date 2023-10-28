package com.example.virtualstore.models

data class CartItem(
    val id: Int,
    val clientId: Int,
    val productId: Int,
    val quantity: Int,
    val productName: String
)
