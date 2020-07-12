package com.ranit.android.lexicon.controller

import android.util.Log
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.view.DisplayWordActivityViewImpl
import java.lang.Exception

class DisplayWordActivityController constructor(modelImpl: ModelImpl,
                                                displayWordActivityViewImpl: DisplayWordActivityViewImpl){
    private val modelImplInstance : ModelImpl = modelImpl
    private val displayWordActivityViewInstance : DisplayWordActivityViewImpl = displayWordActivityViewImpl

    /**
     * This method is invoked by View to fetch the selected word from DB based on ID
     */
    fun fetchWordBasedOnId(id : Int) {
        try {
            displayWordActivityViewInstance.setDataToView(modelImplInstance.getWord(id))
        } catch (exception :Exception) {
            displayWordActivityViewInstance.displayMessage(exception.toString())
        }
    }

}