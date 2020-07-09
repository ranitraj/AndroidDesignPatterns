package com.ranit.android.lexicon.view

import com.ranit.android.lexicon.model.wordPojo.Word

interface MainActivityView : LexiconView {
    fun updateViewOnAddingWord()
    fun displayData(listOfWords : ArrayList<Word>)
}