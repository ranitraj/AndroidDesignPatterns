package com.ranit.android.lexicon.model.wordPojo

class Word constructor(word: String, description: String, id: Long = 0) {

    var wordTitle: String = ""
        set(value) {
            // Trimming all white spaces and making the first alphabet capital
            field = value.replace("\\s+".toRegex(), "").capitalize()
        }

    var wordDescription: String = ""
        set(value) {
            // Trimming white spaces at start and end of description
            field = value.trim()
        }

    var wordId: Long? = 0

    init {
        wordTitle = word
        wordDescription = description
    }
}