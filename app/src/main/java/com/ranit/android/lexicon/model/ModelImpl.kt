package com.ranit.android.lexicon.model

import android.util.Log
import com.ranit.android.lexicon.model.db.WordDbOperations
import com.ranit.android.lexicon.model.wordPojo.Word
import kotlin.Exception

class ModelImpl constructor(wordDbOperations: WordDbOperations): Model {
    private val TAG : String = "ModelImpl"
    private var wordDbOperationsInstance : WordDbOperations = wordDbOperations
    private var wordsList : ArrayList<Word> = ArrayList()

    init {
        Log.e(TAG, "Inside init block ")
        wordsList = wordDbOperationsInstance.getAllWordsFromDB()
    }

    // Get updated list after successful DB operation
    private fun refreshData() {
        wordsList.clear()
        wordsList = wordDbOperationsInstance.getAllWordsFromDB()
    }

    override fun addWord(word : Word) : Boolean {
        val isWordAdded : Boolean = wordDbOperationsInstance.insertWordToDB(word)
        if (isWordAdded) {
            refreshData()
        } else {
            throw Exception("Could not insert word")
        }
        return isWordAdded
    }

    override fun modifyWord(word : Word) : Boolean {
        val isWordUpdated : Boolean = wordDbOperationsInstance.modifyWordInDB(word)
        if (isWordUpdated) {
            refreshData()
        } else {
            throw Exception("Could not modify word")
        }
        return isWordUpdated
    }

    override fun removeWord(id: Long) : Boolean {
        val isWordDeleted : Boolean = wordDbOperationsInstance.removeWordFromDB(id)
        if (isWordDeleted) {
            refreshData()
        } else {
            throw Exception("Could not remove word")
        }
        return isWordDeleted
    }

    override fun getWord(id: Long): Word {
        if (wordsList.size > 0) {
            return wordDbOperationsInstance.getWordFromDB(id)
        } else {
            throw Exception("No word to fetch")
        }
    }

    override fun getAllWords(): ArrayList<Word> {
        if (wordsList.size > 0) {
            return wordsList
        } else {
            throw Exception("No words to fetch")
        }
    }
}