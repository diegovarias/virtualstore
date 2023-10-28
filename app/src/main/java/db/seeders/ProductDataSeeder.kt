package db.seeders

import android.content.ContentValues
import android.content.Context
import db.contracts.ProductContract
import db.helpers.DatabaseHelper

class ProductDataSeeder(private val context: Context) {

    private val preferences = context.getSharedPreferences("seed_preferences", Context.MODE_PRIVATE)

    fun seed() {
        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.writableDatabase

        val alreadySeeded = preferences.getBoolean("product_data_already_seeded", false)

        if (!alreadySeeded) {

            val products = listOf(
                ContentValues().apply {
                    put(ProductContract.ProductEntry.COLUMN_NAME_NAME, "Bocina Portátil")
                    put(ProductContract.ProductEntry.COLUMN_NAME_PRICE, 79.99)
                },
                ContentValues().apply {
                    put(ProductContract.ProductEntry.COLUMN_NAME_NAME, "Mouse Inalámbrico")
                    put(ProductContract.ProductEntry.COLUMN_NAME_PRICE, 29.99)
                },
                ContentValues().apply {
                    put(ProductContract.ProductEntry.COLUMN_NAME_NAME, "Impresor Multifuncional")
                    put(ProductContract.ProductEntry.COLUMN_NAME_PRICE, 220.00)
                },
                ContentValues().apply {
                    put(ProductContract.ProductEntry.COLUMN_NAME_NAME, "Cámara Web")
                    put(ProductContract.ProductEntry.COLUMN_NAME_PRICE, 25.00)
                },
                ContentValues().apply {
                    put(ProductContract.ProductEntry.COLUMN_NAME_NAME, "Consola de Videojuegos")
                    put(ProductContract.ProductEntry.COLUMN_NAME_PRICE, 499.95)
                },
            )

            db.beginTransaction()
            try {
                for (product in products) {
                    db.insert(ProductContract.ProductEntry.TABLE_NAME, null, product)
                }
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }

            dbHelper.close()

            preferences.edit().putBoolean("product_data_already_seeded", true).apply()
        }
    }
}
