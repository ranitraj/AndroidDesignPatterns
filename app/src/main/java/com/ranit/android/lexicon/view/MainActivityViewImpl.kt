package com.ranit.android.lexicon.view

import android.app.LauncherActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.ranit.android.lexicon.DisplayWordActivity
import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.controller.MainActivityController
import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.db.WordDbOperations
import com.ranit.android.lexicon.model.wordPojo.Word
import com.ranit.android.lexicon.view.adapter.WordsListAdapter

class MainActivityViewImpl(private val context: Context, private val viewGroup: ViewGroup?)
    : MainActivityView, WordsListAdapter.RecyclerViewItemClickListener {
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

    private lateinit var recyclerViewAdapter : WordsListAdapter

    /**
     * This method initializes all the views necessary on inflation of main activity
     */
    override fun initView() {
        recyclerView = rootView.findViewById(R.id.recycler_view)
        floatingActionButton = rootView.findViewById(R.id.floating_action_button)

        recyclerView.layoutManager = LinearLayoutManager(context)

        // Floating action button click listener
        floatingActionButton.setOnClickListener(View.OnClickListener {
            addWordDialogView = LayoutInflater.from(context).inflate(R.layout.add_word_custom_dialog, viewGroup)

            buildAndShowAddWordDialog()
        })
    }

    /**
     * This method is used to provide necessary data to the recycler view
     * present in the main activity
     */
    override fun bindDataToView() {
       mainActivityController.fetchData()
    }

    /**
     * This method is used to display any exception or message to the user
     * through a Android Snack-bar
     */
    override fun displayMessage(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * This method updates the recycler view whenever a new word is added
     * to the DB
     */
    override fun updateViewOnAddingWord(wordsList: ArrayList<Word>) {
        setDataToRecyclerView(wordsList)
    }

    /**
     * This method sets the data (List of all words) which is fetched from the
     * DB through Model via Controller to the recycler view's adapter
     */
    override fun setDataToRecyclerView(wordsList: ArrayList<Word>) {
        recyclerViewAdapter = WordsListAdapter(context, wordsList, this)
        recyclerView.adapter = recyclerViewAdapter
    }

    /**
     * This method does the following:
     * 1. Builds the Custom Alert Dialog
     * 2. Passes the edit-text inputs to the Controller using the Controller's
     *    onAddButtonClicked(wordTitle, wordDescription) method.
     *
     * @param wordTitle is of type String which takes the user input for word title
     * @param wordDescription is of type String which takes the user input for word descriptions
     */
    override fun buildAndShowAddWordDialog() {
        addWordTitleTextField = addWordDialogView.findViewById(R.id.word_title_text_field)
        addWordDescriptionTextField = addWordDialogView.findViewById(R.id.word_description_text_field)

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

    /**
     * This method passes control to Controller's navigateToDisplayWordActivity(position)
     * method. This takes the Recycler view position clicked as the parameter
     *
     * @param position is the position of the selected recycler view view-holder
     */
    override fun onItemClicked(position: Int) {
        mainActivityController.navigateToDisplayWordActivity(position)
    }

    /**
     * This method launches another activity through 'Intent' and passes position as
     * the parameter
     */
    fun launchDisplayWordActivity(position: Int) {
        val intent : Intent = Intent(context, DisplayWordActivity::class.java)
        intent.putExtra("itemPosition", position)
        context.startActivity(intent)
    }
}