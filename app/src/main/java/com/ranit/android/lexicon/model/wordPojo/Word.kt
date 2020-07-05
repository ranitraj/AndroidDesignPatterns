package com.ranit.android.lexicon.model.wordPojo

class Word {
    var wordTitle : String = ""
        set(value) {
            // The first character of the string should be Uppercase
            field = value.capitalize()
        }

    var wordDescription : String = ""
}