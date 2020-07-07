package com.ranit.android.lexicon

import com.ranit.android.lexicon.model.wordPojo.Word
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class WordPojoUnitTest {
    private lateinit var word : Word

    @Test
    @DisplayName("Test: Is first character uppercase")
    fun testFirstCharacterForUppercase() {
        word = Word("lexicon","a dictionary")
        val expected : String = "Lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim Whitespaces before Word title")
    fun testTrimWhitespacesBeforeWordTitle() {
        word = Word("   lexicon", "")
        // NOTE: capitalize() operation tested earlier will make the first alphabet capital
        val expected : String = "Lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim Whitespaces after Word title")
    fun testTrimWhitespacesAfterWordTitle() {
        word = Word("lexicon    ", "")
        // NOTE: capitalize() operation tested earlier will make the first alphabet capital
        val expected : String = "Lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim Whitespaces at start and end of Word title")
    fun testTrimWhitespacesStartAndEndWordTitle() {
        word = Word("    lexicon  ", "")
        // NOTE: capitalize() operation tested earlier will make the first alphabet capital
        val expected : String = "Lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim all Whitespaces from Word title")
    fun testTrimAllWhitespacesFromWordTitle() {
        word = Word("   l e x i c o n     ", "")
        // NOTE: capitalize() operation tested earlier will make the first alphabet capital
        val expected : String = "Lexicon"
        val actual : String = word.wordTitle
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim whitespaces before Word title")
    fun testTrimWhitespacesBeforeWordDescription() {
        word = Word("", "   a dictionary")
        // NOTE: capitalize() operation tested earlier will make the first alphabet capital
        val expected : String = "a dictionary"
        val actual : String = word.wordDescription
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim Whitespaces after Word description")
    fun testTrimWhitespacesAfterWordDescription() {
        word = Word("", "a dictionary  ")
        // NOTE: capitalize() operation tested earlier will make the first alphabet capital
        val expected : String = "a dictionary"
        val actual : String = word.wordDescription
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("Test: Trim all whitespaces at start and end of Word title")
    fun testTrimAllWhitespacesFromWordDescription() {
        word = Word("", "   a dictionary    ")
        // NOTE: capitalize() operation tested earlier will make the first alphabet capital
        val expected : String = "a dictionary"
        val actual : String = word.wordDescription
        assertEquals(expected, actual)
    }

}