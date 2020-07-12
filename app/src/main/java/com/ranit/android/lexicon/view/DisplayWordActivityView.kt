package com.ranit.android.lexicon.view

import com.ranit.android.lexicon.model.wordPojo.Word

interface DisplayWordActivityView : LexiconView {
    fun setDataToView(word : Word)
}