package com.ranit.android.lexicon.model.wordPojo

class Word constructor(word : String, description : String){

    var wordTitle : String = word
        set(value) {
            // The first character of the string should be Uppercase
            field = value.capitalize()
        }

    var wordDescription : String = description
    var wordId : Long? = 0
}