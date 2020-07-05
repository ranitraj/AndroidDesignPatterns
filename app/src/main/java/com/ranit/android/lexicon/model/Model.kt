package com.ranit.android.lexicon.model

import com.ranit.android.lexicon.model.wordPojo.Word

interface Model {
    fun addWord(word: String, description: String)
    fun modifyWord(word: String, description: String)
    fun removeWord(id: Long)
    fun getWord() : Word
    fun getAllWords() : List<Word>
}