package com.ranit.android.lexicon.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.controller.MainActivityController
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.db.WordDbOperations
import com.ranit.android.lexicon.model.wordPojo.Word

class MainActivityViewImpl(private val context: Context, private val viewGroup: ViewGroup?)
    : MainActivityView {
    var rootView: View = LayoutInflater.from(context).inflate(R.layout.activity_main, viewGroup)
    private var modelImpl: ModelImpl = ModelImpl(WordDbOperations.getWordDbOperationsInstance(context.applicationContext))
    private var mainActivityController: MainActivityController = MainActivityController(modelImpl,
        this)
    private var addWordDialogBuilder : MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(context)

    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var addWordDialogView : View
    private lateinit var addWordTitleTextField : TextInputLayout
    private lateinit var addWordDescriptionTextField : TextInputLayout

    private var isAddWordAlertDialogInflated : Boolean = false

    override fun initView() {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        floatingActionButton = rootView.findViewById(R.id.floating_action_button)

        // Floating action button click listener
        floatingActionButton.setOnClickListener(View.OnClickListener {
            addWordDialogView = LayoutInflater.from(context).inflate(R.layout.add_word_custom_dialog, viewGroup)

            if (!isAddWordAlertDialogInflated) {
                inflateAddWordAlertDialog()
            } else {
                buildAndShowAddWordDialog()
            }
        })
    }

    override fun bindDataToView() {
       mainActivityController.fetchData()
    }

    override fun displayMessage(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun updateViewOnAddingWord(wordsList: ArrayList<Word>) {
        getDataForRecyclerView(wordsList)
    }

    override fun getDataForRecyclerView(wordsList: ArrayList<Word>) {
        TODO("Pass the list as adapter to recycler view")
    }

    override fun inflateAddWordAlertDialog(): Boolean {
        isAddWordAlertDialogInflated = true

        addWordTitleTextField = addWordDialogView.findViewById(R.id.word_title_text_field)
        addWordDescriptionTextField = addWordDialogView.findViewById(R.id.word_description_text_field)

        buildAndShowAddWordDialog()

        return isAddWordAlertDialogInflated
    }

    private fun buildAndShowAddWordDialog() {
        addWordDialogBuilder.setView(addWordDialogView)
            .setTitle(R.string.add_word_dialog_title)
            .setMessage(R.string.add_word_dialog_subtitle)
            .setPositiveButton(R.string.add) { dialog, _ ->
                val wordTitle : String = addWordTitleTextField.editText?.text.toString()
                val wordDescription : String = addWordDescriptionTextField.editText?.text.toString()

                mainActivityController.onAddButtonClicked(wordTitle, wordDescription)

                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                displayMessage(rootView.resources.getString(R.string.cancel_button_clicked))
                dialog.dismiss()
            }
            .show()
    }
}