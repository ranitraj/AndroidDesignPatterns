package com.ranit.android.lexicon.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ranit.android.lexicon.R
import com.ranit.android.lexicon.model.wordPojo.Word

class WordsListAdapter constructor(private val context: Context, private val wordsList: ArrayList<Word>,
                                   private val clickListener: RecyclerViewItemClickListener)
    : RecyclerView.Adapter<WordsListAdapter.WordViewHolder>()  {

    /**
     * This is used to pass the ID of the word which is clicked
     * to the next activity.
     */
    interface RecyclerViewItemClickListener {
        fun onItemClicked(id: Int)
    }

    override fun getItemCount(): Int {
        if (wordsList.size > 0) {
            return wordsList.size
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : WordsListAdapter.WordViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.recycler_view_item,
        parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordsListAdapter.WordViewHolder, position: Int) {
        val word : Word = wordsList[position]
        holder.wordTitleTextView.text = word.wordTitle

        holder.wordItemContainer.setOnClickListener(View.OnClickListener {
            val id : Int = word.wordId ?: 0
            clickListener.onItemClicked(id)
        })
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var wordItemContainer : ConstraintLayout = itemView.findViewById(R.id.item_container)
        var wordTitleTextView : TextView = itemView.findViewById(R.id.word_title)
    }
}