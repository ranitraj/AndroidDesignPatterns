package com.ranit.android.lexicon.controller

import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.wordPojo.Word
import com.ranit.android.lexicon.view.MainActivityViewImpl

/**
 * Controller and Views have 1-1 Mapping.
 * So, this class acts as the Controller for the View MainActivityImpl.
 */

class MainActivityController constructor(modelImpl: ModelImpl,
                                         mainActivityViewImpl: MainActivityViewImpl){

    private var modelInstance : ModelImpl = modelImpl
    private var mainActivityViewInstance : MainActivityViewImpl = mainActivityViewImpl

    /**
     * This method fetches all the words present in the DB.
     * The Controller fetches the data from Model using View's method
     * It makes the data available to be passed to the recycler view by the View
     */
    fun fetchData() {
        try {
            mainActivityViewInstance.setDataToRecyclerView(modelInstance.getAllWords())
        } catch (exception : Exception) {
            mainActivityViewInstance.displayMessage(exception.toString())
        }
    }

    /**
     * This method performs the following operations:
     * 1. The editText parameters received from the View i.e:
     *    onAddButtonClicked(wordTitle, wordDescription) is mapped to Word Object (POJO)
     * 2. Invokes the Model method, addWord(newWord) which returns a 'boolean'
     * 3. The 'boolean' returned refers to the status depicting successful/unsuccessful
     *    addition of word into DB.
     * 4. On successful addition, View invokes updateViewOnAddingWord() method on Controller
     *    This method invokes getAllWords() from Model.
     *
     * @param wordTitle refers to the user input corresponding to the actual word
     * @param wordDescription refers to the user input corresponding to description of the word
     * @param newWord refers to the instance of Word Object
     * @param message refers to the exception to be displayed in the Snack-bar
     *
     */
    fun onAddButtonClicked(wordTitle : String, wordDescription : String) {
        val newWord : Word = Word(wordTitle, wordDescription)

        val isWordAdded : Boolean = modelInstance.addWord(newWord)
        try {
            if (isWordAdded) {
                mainActivityViewInstance.updateViewOnAddingWord(modelInstance.getAllWords())
            }
        } catch (exception: Exception) {
            mainActivityViewInstance.displayMessage(exception.toString())
        }
    }

    /**
     * Thi method is used by the Controller to launch Display Word Activity through view
     */
    fun navigateToDisplayWordActivity(position : Int) {
        mainActivityViewInstance.launchDisplayWordActivity(position)
    }
}