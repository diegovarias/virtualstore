package db.seeders

import android.content.ContentValues
import android.content.Context
import db.contracts.ClientContract
import db.helpers.ClientDbHelper

class ClientDataSeeder(private val context: Context) {

    fun seed() {
        val dbHelper = ClientDbHelper(context)
        val db = dbHelper.writableDatabase

        val clients = listOf(
            ContentValues().apply {
                put(ClientContract.ClientEntry.COLUMN_NAME_NAME, "Diego Vásquez")
                put(ClientContract.ClientEntry.COLUMN_NAME_EMAIL, "diego@vs.com")
                put(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD, "\$2a\$10\$.qoOlpKfFwMC7pRg9/XXau/FLhFFzPWq/iKoRZbScKONnr8L4b3R2")
            },
            ContentValues().apply {
                put(ClientContract.ClientEntry.COLUMN_NAME_NAME, "José Bardales")
                put(ClientContract.ClientEntry.COLUMN_NAME_EMAIL, "jose@vs.com")
                put(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD, "\$2a\$10\$.qoOlpKfFwMC7pRg9/XXau/FLhFFzPWq/iKoRZbScKONnr8L4b3R2")
            },
            ContentValues().apply {
                put(ClientContract.ClientEntry.COLUMN_NAME_NAME, "Fernanda Martínez")
                put(ClientContract.ClientEntry.COLUMN_NAME_EMAIL, "fernanda@vs.com")
                put(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD, "\$2a\$10\$.qoOlpKfFwMC7pRg9/XXau/FLhFFzPWq/iKoRZbScKONnr8L4b3R2")
            },
            ContentValues().apply {
                put(ClientContract.ClientEntry.COLUMN_NAME_NAME, "Jairo Soto")
                put(ClientContract.ClientEntry.COLUMN_NAME_EMAIL, "jose@vs.com")
                put(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD, "\$2a\$10\$.qoOlpKfFwMC7pRg9/XXau/FLhFFzPWq/iKoRZbScKONnr8L4b3R2")
            },
            ContentValues().apply {
                put(ClientContract.ClientEntry.COLUMN_NAME_NAME, "María Martínez")
                put(ClientContract.ClientEntry.COLUMN_NAME_EMAIL, "maria@vs.com")
                put(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD, "\$2a\$10\$.qoOlpKfFwMC7pRg9/XXau/FLhFFzPWq/iKoRZbScKONnr8L4b3R2")
            },
        )

        db.beginTransaction()
        try {
            for (client in clients) {
                db.insert(ClientContract.ClientEntry.TABLE_NAME, null, client)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        dbHelper.close()
    }
}
