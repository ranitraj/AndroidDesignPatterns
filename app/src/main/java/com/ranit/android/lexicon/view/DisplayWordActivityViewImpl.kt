package com.ranit.android.lexicon.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.ranit.android.lexicon.DisplayWordActivity
import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.controller.DisplayWordActivityController
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.db.WordDbOperations
import com.ranit.android.lexicon.model.wordPojo.Word

class DisplayWordActivityViewImpl(private val context: Context, private val viewGroup: ViewGroup?,
                                  private val intent: Intent, private val activityInstance: DisplayWordActivity)
    : DisplayWordActivityView {
    val rootView : View = LayoutInflater.from(context).inflate(R.layout.activity_display_word, viewGroup)
    private val modelInstance : ModelImpl = ModelImpl(WordDbOperations.getWordDbOperationsInstance(context))
    private val displayWordActivityControllerInstance : DisplayWordActivityController =
        DisplayWordActivityController(modelInstance, this)

    private var wordId : Int = 0
    private lateinit var word : Word
    private lateinit var alertDialogBuilder: MaterialAlertDialogBuilder

    private lateinit var wordTitleTextView : TextView
    private lateinit var wordDescriptionTextView : TextView
    private lateinit var modifyWordButton: Button
    private lateinit var removeWordButton: Button
    private lateinit var modifyWordDialogView : View
    private lateinit var modifyWordTitleTextField : TextInputLayout
    private lateinit var modifyWordDescriptionTextField : TextInputLayout

    /**
     * This method initializes all the views necessary on inflation of Display word activity
     */
    override fun initView() {
        wordTitleTextView = rootView.findViewById(R.id.word_title_text_view)
        wordDescriptionTextView = rootView.findViewById(R.id.word_description_text_view)
        modifyWordButton = rootView.findViewById(R.id.modify_word_button)
        removeWordButton = rootView.findViewById(R.id.delete_word_button)

        modifyWordButton.setOnClickListener(View.OnClickListener {
            modifyWordDialogView = LayoutInflater.from(context).inflate(R.layout.add_word_custom_dialog,
                viewGroup, false)
            buildAndShowModifyWordDialog()
        })

        removeWordButton.setOnClickListener(View.OnClickListener {
            buildAndShowRemoveWordDialog()
        })
    }

    /**
     * This method is used to extract data (the ID of the word which has been clicked)
     * from the intent and then fetch the Data for the same from using Controller
     */
    override fun bindDataToView() {
        wordId = intent.getIntExtra("wordId", 0)
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

    /**
     * This method displays the alert dialog for remove word
     */
    override fun buildAndShowRemoveWordDialog() {
        alertDialogBuilder = MaterialAlertDialogBuilder(context)
        alertDialogBuilder.setTitle(R.string.remove_word_confirmation)
            .setPositiveButton(R.string.remove) { dialog, _ ->

                displayWordActivityControllerInstance.onRemoveButtonClicked(wordId)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                displayMessage(rootView.resources.getString(R.string.cancel_button_clicked))
                dialog.dismiss()
            }
            .show()
    }

    /**
     * This method displays the custom alert dialog for modify word
     */
    override fun buildAndShowModifyWordDialog() {
        modifyWordTitleTextField = modifyWordDialogView.findViewById(R.id.word_title_text_field)
        modifyWordDescriptionTextField = modifyWordDialogView.findViewById(R.id.word_description_text_field)

        alertDialogBuilder = MaterialAlertDialogBuilder(context)
        alertDialogBuilder.setView(modifyWordDialogView)
            .setTitle(R.string.modify_word_dialog_title)
            .setMessage(R.string.modify_word_dialog_subtitle)
            .setPositiveButton(R.string.modify) { dialog, _ ->
                val wordTitle : String = modifyWordTitleTextField.editText?.text.toString()
                val wordDescription : String = modifyWordDescriptionTextField.editText?.text.toString()

                word = Word(wordTitle, wordDescription, wordId)
                displayWordActivityControllerInstance.onModifyButtonClicked(word)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                displayMessage(rootView.resources.getString(R.string.cancel_button_clicked))
                dialog.dismiss()
            }
            .show()
    }

    /**
     * This method is invoked by the Controller upon successful removal of word from DB
     * This method finishes the current Activity
     */
    override fun launchMainActivityOnWordDeletion() {
        activityInstance.finish()
    }
}