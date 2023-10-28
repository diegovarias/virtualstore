package db.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import db.contracts.CartItemContract

class CartDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Cart.db"

        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${CartItemContract.CartItemEntry.TABLE_NAME} (" +
                    "${CartItemContract.CartItemEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                    "${CartItemContract.CartItemEntry.COLUMN_NAME_CLIENT_ID} INTEGER," +
                    "${CartItemContract.CartItemEntry.COLUMN_NAME_PRODUCT_ID} INTEGER," +
                    "${CartItemContract.CartItemEntry.COLUMN_NAME_QUANTITY} INTEGER)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${CartItemContract.CartItemEntry.TABLE_NAME}"
    }
}
