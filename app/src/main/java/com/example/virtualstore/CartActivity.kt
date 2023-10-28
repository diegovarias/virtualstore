package com.example.virtualstore

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.virtualstore.adapters.CartAdapter
import com.example.virtualstore.adapters.ProductAdapter
import com.example.virtualstore.models.CartItem
import db.helpers.DatabaseHelper

class CartActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        val userId = sharedPreferences.getInt("UserId", -1)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val cartItems = getCartItems(userId)
        val adapter = CartAdapter(cartItems)
        recyclerView.adapter = adapter
    }

    private fun getCartItems(userId: Int): List<CartItem> {
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.readableDatabase

        val query = """
            SELECT cart_item.id, client_id, product_id, quantity, product.name
            FROM cart_item
            INNER JOIN product ON cart_item.product_id = product.id
            WHERE client_id = ?
        """

        val cursor = db.rawQuery(query, arrayOf(userId.toString()))

        val items = mutableListOf<CartItem>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val clientId = getInt(getColumnIndexOrThrow("client_id"))
                val productId = getInt(getColumnIndexOrThrow("product_id"))
                val quantity = getInt(getColumnIndexOrThrow("quantity"))
                val productName = getString(getColumnIndexOrThrow("name"))
                items.add(CartItem(id, clientId, productId, quantity, productName))
            }
        }
        cursor.close()

        return items
    }

}