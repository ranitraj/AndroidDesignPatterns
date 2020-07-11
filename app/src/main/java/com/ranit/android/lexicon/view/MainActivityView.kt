package com.ranit.android.lexicon.view

import com.ranit.android.lexicon.model.wordPojo.Word

interface MainActivityView : LexiconView {
    fun updateViewOnAddingWord(wordsList : ArrayList<Word>)
    fun buildAndShowAddWordDialog()
    fun setDataToRecyclerView(wordsList : ArrayList<Word>)
}