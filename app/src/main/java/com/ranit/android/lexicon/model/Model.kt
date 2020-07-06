package com.ranit.android.lexicon.model

import com.ranit.android.lexicon.model.wordPojo.Word

interface Model {
    fun addWord(word: Word?)
    fun modifyWord(word: Word?)
    fun removeWord(id: Long)
    fun getWord(id: Long) : Word?
    fun getAllWords() : List<Word>?
}