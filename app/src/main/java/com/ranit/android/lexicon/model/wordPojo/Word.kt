package com.ranit.android.lexicon.model.wordPojo

data class Word(val word: String, val description: String, val id: Int = 0) {

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

    var wordId: Int = 0

    init {
        wordTitle = word
        wordDescription = description
        wordId = id
    }
}