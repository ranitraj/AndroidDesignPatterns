package com.ranit.android.lexicon.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.controller.DisplayWordActivityController
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.db.WordDbOperations
import com.ranit.android.lexicon.model.wordPojo.Word

class DisplayWordActivityViewImpl(private val context: Context,
                                  private val viewGroup: ViewGroup?, private val intent: Intent)
    : DisplayWordActivityView {

    val rootView : View = LayoutInflater.from(context).inflate(R.layout.activity_display_word, viewGroup)
    private val modelInstance : ModelImpl = ModelImpl(WordDbOperations.getWordDbOperationsInstance(context))
    private val displayWordActivityControllerInstance : DisplayWordActivityController =
        DisplayWordActivityController(modelInstance, this)

    private lateinit var wordTitleTextView : TextView
    private lateinit var wordDescriptionTextView : TextView
    private lateinit var modifyWordButton: Button
    private lateinit var removeWordButton: Button

    /**
     * This method initializes all the views necessary on inflation of Display word activity
     */
    override fun initView() {
        wordTitleTextView = rootView.findViewById(R.id.word_title_text_view)
        wordDescriptionTextView = rootView.findViewById(R.id.word_description_text_view)
        modifyWordButton = rootView.findViewById(R.id.modify_word_button)
        removeWordButton = rootView.findViewById(R.id.delete_word_button)

        modifyWordButton.setOnClickListener(View.OnClickListener {
            TODO("Launch Modify word alert dialog")
        })

        removeWordButton.setOnClickListener(View.OnClickListener {
            TODO("Launch Remove word alert dialog")
        })
    }

    /**
     * This method is used to extract data (the ID of the word which has been clicked)
     * from the intent and then fetch the Data for the same from using Controller
     */
    override fun bindDataToView() {
        val wordId : Int = intent.getIntExtra("wordId", 2)
        displayWordActivityControllerInstance.fetchWordBasedOnId(wordId)
    }

    /**
     * This method is used to display any exception or message to the user
     * through a Android Snack-bar
     */
    override fun displayMessage(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * This method sets the data i.e the Word which is fetched from the
     * DB through Model via Controller to the text views.
     */
    override fun setDataToView(word: Word) {
        wordTitleTextView.text = word.wordTitle
        wordDescriptionTextView.text = word.wordDescription
    }
}