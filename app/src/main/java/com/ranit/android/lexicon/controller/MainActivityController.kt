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

    fun onFloatingActionButtonClicked() {

    }

    fun launchDialogOnFloatingButtonClicked() {

    }

    fun onAddButtonClicked() {

    }

    fun onCancelButtonClicked() {

    }

    fun onRecyclerViewItemSelected() {

    }

    fun getData() {

    }
}