package com.ranit.android.lexicon

import com.ranit.android.lexicon.model.ModelImpl
import com.ranit.android.lexicon.model.wordPojo.Word
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ModelImplUnitTest {
    private lateinit var modelImpl : ModelImpl

    @MockK
    lateinit var word : Word

    @BeforeEach
    fun init() {
        modelImpl = ModelImpl()
    }

    @Test
    @DisplayName("Test: Is Word object not null")
    fun testIsWordObjectNotNull() {
        word = Word("", "")
        val expected : Word? = word
        val actual : Word? = modelImpl.trimWhiteSpaces(word)
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Is first character uppercase")
    fun testFirstCharacterForUppercase() {
        word = Word("lexicon","a dictionary")
        val expected : String = "Lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim whitespaces before word title")
    fun testWhiteSpaceBeforeWordTitle() {
        word = Word("   lexicon","a dictionary")
        val expected : String = "lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim whitespaces after word title")
    fun testWhiteSpaceAfterWordTitle() {
        word = Word("lexicon    ","a dictionary")
        val expected : String = "lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim whitespaces before word description")
    fun testWhiteSpacesBeforeWordDescription() {
        word = Word("lexicon", "    a dictionary")
        val expected : String = "a dictionary"
        val actual : String = word.wordDescription
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim whitespaces after word description")
    fun testWhiteSpacesAfterWordDescription() {
        word = Word("lexicon", "a dictionary    ")
        val expected : String = "a dictionary"
        val actual : String = word.wordDescription
        assertEquals(expected, actual)
    }
}