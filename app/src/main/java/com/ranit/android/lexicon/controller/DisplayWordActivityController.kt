package com.ranit.android.lexicon.controller

import com.ranit.android.lexicon.R
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

    /**
     * This method does the following:
     * 1. Receives the ID of the word to ve deleted as the parameter
     * 2. Invokes the Model method removeWord(id) which receives word ID as the parameter
     * 3. The 'boolean' returned refers to the status depicting successful/unsuccessful
     *    addition of word into DB.
     * 4. On successful removal, launchMainActivityOnWordDeletion() method is invoked
     *    by the controller on the view
     *
     * @param id denotes the ID of the word to be deleted
     */
    fun onRemoveButtonClicked(id : Int) {
        val isWordRemoved : Boolean = modelImplInstance.removeWord(id)
        try {
            if (isWordRemoved) {
                displayWordActivityViewInstance.displayMessage(displayWordActivityViewInstance
                    .rootView.resources.getString(R.string.successful_delete))
                displayWordActivityViewInstance.launchMainActivityOnWordDeletion()
            }
        } catch (exception : Exception) {
            displayWordActivityViewInstance.displayMessage(exception.toString())
        }

    }

}