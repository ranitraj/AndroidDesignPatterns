package com.ranit.android.lexicon.controller

import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.view.MainActivityViewImpl

class MainActivityController constructor(modelImpl: ModelImpl,
                                         mainActivityViewImpl: MainActivityViewImpl){

    var modelInstance : ModelImpl = modelImpl
    var mainActivityViewInstance : MainActivityViewImpl = mainActivityViewImpl

    // Operations present in MainActivity
    fun onViewLoaded() {

    }

    fun launchDialogOnFloatingButtonClicked() {
        mainActivityViewInstance.showAddWordDialog()
    }

    fun onAddButtonClicked(wordTitle : String, wordDescription : String) {

    }

    fun onCancelButtonClicked() {
        mainActivityViewInstance.displayMessage("Operation cancelled")
    }

    fun sendDataToRecyclerView() {

    }

    fun onRecyclerViewItemSelected() {

    }

    fun getData() {

    }
}