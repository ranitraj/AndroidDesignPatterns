package com.ranit.android.lexicon.model.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.ranit.android.lexicon.model.wordPojo.Word

/*
 * 1. This is a Singleton class
 * 2. Contains all SQLite CRUD Operations
 */

class WordDbOperations private constructor(private val context: Context) {
    private val TAG: String = "WordDbOperations"

    companion object {
        private lateinit var sqLiteDatabase: SQLiteDatabase

        val DB_NAME: String = "lexicon"
        val DB_VERSION: Int = 1

        val TABLE_WORDS: String = "words_table"

        val WORD_ID_COLUMN: String = "primary_key"
        val WORD_TITLE_COLUMN: String = "word_title"
        val WORD_DESCRIPTION_COLUMN: String = "word_description"

        val CREATE_TABLE: String = "CREATE TABLE $TABLE_WORDS" +
                "($WORD_ID_COLUMN LONG PRIMARY KEY, " +
                "$WORD_TITLE_COLUMN TEXT NOT NULL, " +
                "$WORD_DESCRIPTION_COLUMN TEXT NOT NULL)"

        @Volatile
        private var INSTANCE: WordDbOperations? = null

        @Synchronized
        fun getWordDbOperationsInstance(context: Context): WordDbOperations {
            INSTANCE ?: let { INSTANCE = WordDbOperations(context) }
            return INSTANCE!!
        }
    }

    init {
        Log.e(TAG, "Inside init block")
        sqLiteDatabase = DatabaseHelper(context, DB_NAME, null, DB_VERSION)
            .writableDatabase
    }

    // CRUD Operations
    fun insertWordToDB(word: Word): Boolean {
        val contentValues: ContentValues = ContentValues()
        contentValues.put(WORD_TITLE_COLUMN, word.wordTitle)
        contentValues.put(WORD_DESCRIPTION_COLUMN, word.wordDescription)

        // Returns positive value on insertion success, so >0 is true (-1 on failure)
        return sqLiteDatabase.insert(TABLE_WORDS, null, contentValues) > 0
    }

    fun modifyWordInDB(word: Word): Boolean {
        val contentValues: ContentValues = ContentValues()
        contentValues.put(WORD_TITLE_COLUMN, word.wordTitle)
        contentValues.put(WORD_DESCRIPTION_COLUMN, word.wordDescription)

        val whereClause: String = "$WORD_ID_COLUMN=${word.wordId}"

        return sqLiteDatabase.update(
            TABLE_WORDS, contentValues, whereClause
            , null
        ) > 0
    }

    fun removeWordFromDB(id: Long): Boolean {
        val whereClause: String = "$WORD_ID_COLUMN=$id"

        return sqLiteDatabase.delete(TABLE_WORDS, whereClause, null) > 0
    }

    fun getWord(id: Long): Word {
        val query: String = "SELECT * FROM $TABLE_WORDS WHERE $WORD_ID_COLUMN=$id"
        val wordCursorObject: Cursor = sqLiteDatabase.rawQuery(
            query,
            null
        )

        val word: Word = Word(
            wordCursorObject.getString(1),
            wordCursorObject.getString(2), wordCursorObject.getLong(0)
        )

        wordCursorObject.close()
        return word
    }

    fun getAllWords(): ArrayList<Word> {
        var listOfWords: ArrayList<Word> = ArrayList<Word>()

        val cursorObject: Cursor = sqLiteDatabase.query(
            TABLE_WORDS, arrayOf(
                WORD_ID_COLUMN,
                WORD_TITLE_COLUMN, WORD_DESCRIPTION_COLUMN
            ),
            null, null, null, null, null, null
        )

        // Loop through the entire data
        if (cursorObject.count > 0) {
            while (cursorObject.moveToNext()) {
                // Convert DB data into Word(POJO) and add it into ArrayList
                val word: Word = Word(
                    cursorObject.getString(1),
                    cursorObject.getString(2), cursorObject.getLong(0)
                )
            }
            cursorObject.close()
        }
        return listOfWords
    }

}