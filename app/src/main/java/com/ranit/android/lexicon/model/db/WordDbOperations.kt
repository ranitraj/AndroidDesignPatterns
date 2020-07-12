package com.ranit.android.lexicon.model.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.ranit.android.lexicon.model.wordPojo.Word

/**
 * 1. This is a Singleton class
 * 2. Contains all SQLite CRUD Operations
 */

class WordDbOperations private constructor(private val context: Context) {

    companion object {
        private lateinit var sqLiteDatabase: SQLiteDatabase

        const val DB_NAME: String = "lexicon"
        const val DB_VERSION: Int = 1

        const val TABLE_WORDS: String = "words_table"

        const val WORD_ID_COLUMN: String = "primary_key"
        const val WORD_TITLE_COLUMN: String = "word_title"
        const val WORD_DESCRIPTION_COLUMN: String = "word_description"

        const val CREATE_TABLE: String = "CREATE TABLE $TABLE_WORDS" +
                "($WORD_ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

    fun getWordFromDB(id: Int): Word {
        val query: String = "SELECT * FROM $TABLE_WORDS WHERE $WORD_ID_COLUMN=$id"
        val wordCursorObject: Cursor = sqLiteDatabase.rawQuery(
            query,
            null
        )
        wordCursorObject.moveToFirst()

        val word: Word = Word(
            wordCursorObject.getString(1),
            wordCursorObject.getString(2), wordCursorObject.getInt(0)
        )
        wordCursorObject.close()
        return word
    }

    fun getAllWordsFromDB(): ArrayList<Word> {
        val listOfWords: ArrayList<Word> = ArrayList<Word>()

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
                    cursorObject.getString(2), cursorObject.getInt(0)
                )
                listOfWords.add(word)
            }
            cursorObject.close()
        }
        return listOfWords
    }

}