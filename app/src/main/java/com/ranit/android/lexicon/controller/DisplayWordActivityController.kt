package com.ranit.android.lexicon.controller

import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.wordPojo.Word
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
     * 1. Receives the ID of the word to be deleted as the parameter
     * 2. Invokes the Model method removeWord(id) which receives word ID as the parameter
     * 3. The 'boolean' returned refers to the status depicting successful/unsuccessful
     *    removal of word from DB.
     * 4. On successful removal, launchMainActivityOnWordDeletion() method is invoked
     *    by the controller on the view
     *
     * @param id denotes the ID of the word to be deleted
     */
    fun onRemoveButtonClicked(id : Int) {
        val isWordRemoved : Boolean = modelImplInstance.removeWord(id)
        try {
            if (isWordRemoved) {
                displayWordActivityViewInstance.launchMainActivityOnWordDeletion()
            }
        } catch (exception : Exception) {
            displayWordActivityViewInstance.displayMessage(exception.toString())
        }

    }
    /**
     * This method does the following:
     * 1. Receives the Word object of the modified word as the parameter
     * 2. Invokes the Model method modifyWord(word) which receives word object as the parameter
     * 3. The 'boolean' returned refers to the status depicting successful/unsuccessful
     *    update of word in DB.
     * 4. On successful removal, setDataToView() method is invoked
     *    by the controller on the view
     *
     * @param word denotes the Word object to be modified
     */
    fun onModifyButtonClicked(word: Word) {
        val isWordModified : Boolean = modelImplInstance.modifyWord(word)
        try {
            if (isWordModified) {
                val id : Int = word.wordId ?: 0
                displayWordActivityViewInstance.setDataToView(modelImplInstance.getWord(id))
                displayWordActivityViewInstance.displayMessage(displayWordActivityViewInstance
                    .rootView.resources.getString(R.string.successful_modify))
            }
        } catch (exception : Exception) {
            displayWordActivityViewInstance.displayMessage(exception.toString())
        }
    }

}