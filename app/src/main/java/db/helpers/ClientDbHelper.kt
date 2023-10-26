package db.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import db.contracts.ClientContract

class ClientDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Clients.db"

        private const val SQL_CREATE_ENTRIES = "CREATE TABLE ${ClientContract.ClientEntry.TABLE_NAME} (" +
                "${ClientContract.ClientEntry.COLUMN_NAME_ID} INTEGER PRIMARY KEY," +
                "${ClientContract.ClientEntry.COLUMN_NAME_NAME} TEXT," +
                "${ClientContract.ClientEntry.COLUMN_NAME_EMAIL} TEXT," +
                "${ClientContract.ClientEntry.COLUMN_NAME_PASSWORD} TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ClientContract.ClientEntry.TABLE_NAME}"
    }
}
