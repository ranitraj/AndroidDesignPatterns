package com.ranit.android.lexicon.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.controller.MainActivityController
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.db.WordDbOperations
import com.ranit.android.lexicon.model.wordPojo.Word

class MainActivityViewImpl(context: Context, viewGroup: ViewGroup?)
    : MainActivityView {

    var rootView: View = LayoutInflater.from(context).inflate(R.layout.activity_main, viewGroup)
    var modelImpl: ModelImpl = ModelImpl(WordDbOperations.getWordDbOperationsInstance(context.applicationContext))
    var mainActivityController: MainActivityController = MainActivityController(modelImpl,
        this)

    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton
    var addWordDialog : MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(context)

    override fun initView() {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        floatingActionButton = rootView.findViewById(R.id.floating_action_button)

        floatingActionButton.setOnClickListener(View.OnClickListener {
            showAddWordDialog()
        })
    }

    override fun bindDataToView() {
       mainActivityController.onViewLoaded()
    }

    override fun displayMessage(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
    }

    override fun updateViewOnAddingWord() {
        mainActivityController.onAddButtonClicked()
    }

    override fun displayData(listOfWords: ArrayList<Word>) {
        if (listOfWords.isNotEmpty()) {
            mainActivityController.getData()
        }
    }

    private fun showAddWordDialog() {
        addWordDialog.setTitle("Add new word")
            .setMessage("Add a word along with a brief description")
            .show()
    }
}