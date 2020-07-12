package com.ranit.android.lexicon.model

import com.ranit.android.lexicon.model.wordPojo.Word
import kotlin.Exception

interface Model {
    @Throws(Exception::class)
    fun addWord(word: Word) : Boolean

    @Throws(Exception::class)
    fun modifyWord(word: Word) : Boolean

    @Throws(Exception::class)
    fun removeWord(id: Int) : Boolean

    @Throws(Exception::class)
    fun getWord(id: Int) : Word?

    @Throws(Exception::class)
    fun getAllWords() : List<Word>?
}