package com.ranit.android.lexicon.controller

import com.ranit.android.lexicon.LexiconApplication
import com.ranit.android.lexicon.model.ModelImpl

class MainActivityController {
    var application : LexiconApplication = LexiconApplication()
    var modelImpl : ModelImpl

    init {
        modelImpl = ModelImpl(application.getWordDbOperation())
    }

    // Operations present in MainActivity
    fun onFloatingActionButtonClicked() {

    }

    fun onAddButtonClicked() {

    }

    fun onCancelButtonClicked() {

    }

    fun onItemSelected() {

    }

    fun showErrorMessage(message : String) {

    }
}