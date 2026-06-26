/*
 * Student ID: 2024eb02437
 */

package com.example.bookcollection

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        const val DATABASE_NAME = "BooksDB.db"
        const val DATABASE_VERSION = 1
        const val TABLE_BOOKS = "Books"
        const val COL_ID = "id"
        const val COL_TITLE = "title"
        const val COL_AUTHOR = "author"
    }

    private val createTableQuery = """
        CREATE TABLE $TABLE_BOOKS (
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_TITLE TEXT NOT NULL,
            $COL_AUTHOR TEXT NOT NULL
        )
    """.trimIndent()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKS")
        onCreate(db)
    }

    fun insertBook(title: String, author: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_TITLE, title)
            put(COL_AUTHOR, author)
        }
        return db.insert(TABLE_BOOKS, null, values)
    }

    fun getAllBooks(): List<Book> {
        val books = mutableListOf<Book>()
        val db = readableDatabase
        val cursor: Cursor = db.query(
            TABLE_BOOKS,
            null, null, null, null, null,
            "$COL_ID DESC"
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COL_ID))
                val title = getString(getColumnIndexOrThrow(COL_TITLE))
                val author = getString(getColumnIndexOrThrow(COL_AUTHOR))
                books.add(Book(id, title, author))
            }
            close()
        }
        return books
    }
}
