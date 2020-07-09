package com.ranit.android.lexicon.view

/*
 * Contains generic methods related to all views
 */

interface LexiconView {
    fun initView()
    fun bindDataToView()
    fun displayMessage(message : String)
}