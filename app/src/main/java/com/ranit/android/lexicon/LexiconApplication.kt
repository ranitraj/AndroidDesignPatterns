package com.ranit.android.lexicon

import android.app.Application
import com.ranit.android.lexicon.model.db.WordDbOperations

class LexiconApplication : Application() {
    private lateinit var wordDbOperationsInstance: WordDbOperations

    @Override
    override fun onCreate() {
        super.onCreate()
        wordDbOperationsInstance = WordDbOperations.getWordDbOperationsInstance(this)
    }

    fun getWordDbOperation() : WordDbOperations {
        return wordDbOperationsInstance
    }
}