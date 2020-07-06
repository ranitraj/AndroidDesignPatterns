package com.ranit.android.lexicon.model

import com.ranit.android.lexicon.model.wordPojo.Word

class ModelImpl : Model {
    override fun addWord(word : Word?) {
        TODO("To be implemented during TDD")
    }

    override fun modifyWord(word : Word?) {
        TODO("To be implemented during TDD")
    }

    override fun removeWord(id: Long) {
        TODO("To be implemented during TDD")
    }

    override fun getWord(id: Long): Word? {
        TODO("To be implemented during TDD")
    }

    override fun getAllWords(): List<Word>? {
        TODO("To be implemented during TDD")
    }

    // Method to Trim empty spaces
    fun trimWhiteSpaces(word: Word?) : Word? {
        return word
    }
}