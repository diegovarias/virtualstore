package db.repositories

import android.content.ContentValues
import android.content.Context
import db.contracts.ClientContract
import db.helpers.ClientDbHelper
import org.mindrot.jbcrypt.BCrypt

class ClientRepository(context: Context) {
    private val dbHelper = ClientDbHelper(context)

    fun signIn(email: String, password: String): Boolean {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD)
        val selection = "${ClientContract.ClientEntry.COLUMN_NAME_EMAIL} = ?"
        val selectionArgs = arrayOf(email)

        val cursor = db.query(
            ClientContract.ClientEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            if (moveToFirst()) {
                val storedPassword = getString(getColumnIndexOrThrow(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD))
                return BCrypt.checkpw(password, storedPassword)
            }
        }
        return false
    }

    fun register(name: String, email: String, password: String): Boolean {
        val db = dbHelper.writableDatabase

        val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())

        val values = ContentValues().apply {
            put(ClientContract.ClientEntry.COLUMN_NAME_NAME, name)
            put(ClientContract.ClientEntry.COLUMN_NAME_EMAIL, email)
            put(ClientContract.ClientEntry.COLUMN_NAME_PASSWORD, hashedPassword)
        }

        val newRowId = db?.insert(ClientContract.ClientEntry.TABLE_NAME, null, values)

        return newRowId != -1L
    }


}