package com.ranit.android.lexicon.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.ranit.android.lexicon.LexiconApplication
import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.controller.MainActivityController
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.wordPojo.Word

class MainActivityViewImpl constructor(context: Context, viewGroup : ViewGroup)
    : MainActivityView {

    private val applicationInstance : LexiconApplication = LexiconApplication()

    var rootView: View = LayoutInflater.from(context).inflate(R.layout.activity_main, viewGroup)
    var modelImpl: ModelImpl = ModelImpl(applicationInstance.getWordDbOperation())
    var mainActivityController: MainActivityController = MainActivityController(modelImpl,
        this)

    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton

    override fun initView() {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        floatingActionButton = rootView.findViewById(R.id.floating_action_button)

        floatingActionButton.setOnClickListener(View.OnClickListener {
            mainActivityController.launchDialogOnFloatingButtonClicked()
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
}