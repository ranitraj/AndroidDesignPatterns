package com.ranit.android.lexicon.model

import com.ranit.android.lexicon.model.db.WordDbOperations
import com.ranit.android.lexicon.model.wordPojo.Word
import kotlin.Exception

class ModelImpl constructor(wordDbOperations: WordDbOperations): Model {
    private var wordDbOperationsInstance : WordDbOperations = wordDbOperations
    private var wordsList : ArrayList<Word> = ArrayList()

    init {
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

    override fun removeWord(id: Int) : Boolean {
        val isWordDeleted : Boolean = wordDbOperationsInstance.removeWordFromDB(id)
        if (isWordDeleted) {
            refreshData()
        } else {
            throw Exception("Could not remove word")
        }
        return isWordDeleted
    }

    override fun getWord(id: Int): Word {
        return wordDbOperationsInstance.getWordFromDB(id)
    }

    override fun getAllWords(): ArrayList<Word> {
        if (wordsList.size > 0) {
            return wordDbOperationsInstance.getAllWordsFromDB()
        } else {
            throw Exception("No words to fetch")
        }
    }
}