/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package test.text

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CharTest {
    private fun charToCategory() = mapOf(
        '\u0378' to "Cn",
        'A' to "Lu",    // \u0041
        'a' to "Ll",    // \u0061
        'ǅ' to "Lt",    // \u01C5
        'ʰ' to "Lm",    // \u02B0
        'ƻ' to "Lo",    // \u01BB
        '\u0300' to "Mn",
        '\u0489' to "Me",
        '\u0903' to "Mc",
        '0' to "Nd",    // \u0030
        'Ⅰ' to "Nl",    // \u2160
        '²' to "No",    // \u00B2
        ' ' to "Zs",    // \u0020
        '\u2028' to "Zl",
        '\u2029' to "Zp",
        '\u0018' to "Cc",
        '\u00AD' to "Cf",
        '\uE000' to "Co",
        '\uD800' to "Cs",
        '\u002D' to "Pd",
        '(' to "Ps",    // \u0028
        ')' to "Pe",    // \u0029
        '_' to "Pc",    // \u005F
        '!' to "Po",    // \u0021
        '+' to "Sm",    // \u002B
        '$' to "Sc",    // \u0024
        '^' to "Sk",    // \u005E
        '©' to "So",    // \u00A9
        '«' to "Pi",    // \u00AB
        '»' to "Pf"     // \u00BB
    )

    @Test
    fun charCategory() {
        for ((char, categoryCode) in charToCategory()) {
            assertEquals(categoryCode, char.category.code, "char code: ${char.toInt().toString(radix = 16)}")
        }
    }

    @Test
    fun charCategoryUnassigned() {
        val unassignedChar = '\u0378'
        assertFalse(unassignedChar.isDefined())
        assertEquals(CharCategory.UNASSIGNED, unassignedChar.category)
        assertEquals("Cn", CharCategory.UNASSIGNED.code)
    }

    @Test
    fun charCategoryUppercaseLetter() {
        val latinCapitalLetterA = 'A' // \u0041
        assertTrue(latinCapitalLetterA.isLetterOrDigit())
        assertTrue(latinCapitalLetterA.isLetter())
        assertTrue(latinCapitalLetterA.isUpperCase())
        assertEquals(CharCategory.UPPERCASE_LETTER, latinCapitalLetterA.category)
        assertEquals("Lu", CharCategory.UPPERCASE_LETTER.code)
    }

    @Test
    fun charCategoryLowercaseLetter() {
        val latinSmallLetterA = 'a' // \u0061
        assertTrue(latinSmallLetterA.isLetterOrDigit())
        assertTrue(latinSmallLetterA.isLetter())
        assertTrue(latinSmallLetterA.isLowerCase())
        assertEquals(CharCategory.LOWERCASE_LETTER, latinSmallLetterA.category)
        assertEquals("Ll", CharCategory.LOWERCASE_LETTER.code)
    }

    @Test
    fun charCategoryTitlecaseLetter() {
        val latinCapitalLetterDz = 'ǅ' // \u01C5
        assertTrue(latinCapitalLetterDz.isLetterOrDigit())
        assertTrue(latinCapitalLetterDz.isLetter())
        assertTrue(latinCapitalLetterDz.isTitleCase())
        assertEquals(CharCategory.TITLECASE_LETTER, latinCapitalLetterDz.category)
        assertEquals("Lt", CharCategory.TITLECASE_LETTER.code)
    }

    @Test
    fun charCategoryModifierLetter() {
        val modifierLetterSmallH = 'ʰ' // \u02B0
        assertTrue(modifierLetterSmallH.isLetterOrDigit())
        assertTrue(modifierLetterSmallH.isLetter())
        assertEquals(CharCategory.MODIFIER_LETTER, modifierLetterSmallH.category)
        assertEquals("Lm", CharCategory.MODIFIER_LETTER.code)
    }

    @Test
    fun charCategoryOtherLetter() {
        val twoWithStroke = 'ƻ' // \u01BB
        assertTrue(twoWithStroke.isLetterOrDigit())
        assertTrue(twoWithStroke.isLetter())
        assertEquals(CharCategory.OTHER_LETTER, twoWithStroke.category)
        assertEquals("Lo", CharCategory.OTHER_LETTER.code)
    }

    @Test
    fun charCategoryDecimalDigitNumber() {
        val digitZero = '0' // \u0030
        assertTrue(digitZero.isLetterOrDigit())
        assertTrue(digitZero.isDigit())
        assertEquals(CharCategory.DECIMAL_DIGIT_NUMBER, digitZero.category)
        assertEquals("Nd", CharCategory.DECIMAL_DIGIT_NUMBER.code)
    }

    @Test
    fun charCategoryLetterNumber() {
        val romanNumberOne = 'Ⅰ' // \u2160
        assertFalse(romanNumberOne.isDigit())
        assertEquals(CharCategory.LETTER_NUMBER, romanNumberOne.category)
        assertEquals("Nl", CharCategory.LETTER_NUMBER.code)
    }

    @Test
    fun charCategoryOtherNumber() {
        val superscriptTwo = '²' // \u00B2
        assertFalse(superscriptTwo.isDigit())
        assertEquals(CharCategory.OTHER_NUMBER, superscriptTwo.category)
        assertEquals("No", CharCategory.OTHER_NUMBER.code)
    }

    @Test
    fun charCategorySpaceSeparator() {
        val superscriptTwo = ' ' // \u0020
        assertTrue(superscriptTwo.isWhitespace())
        assertEquals(CharCategory.SPACE_SEPARATOR, superscriptTwo.category)
        assertEquals("Zs", CharCategory.SPACE_SEPARATOR.code)
    }

    @Test
    fun charCategoryLineSeparator() {
        val lineSeparator = '\u2028'
        assertTrue(lineSeparator.isWhitespace())
        assertEquals(CharCategory.LINE_SEPARATOR, lineSeparator.category)
        assertEquals("Zl", CharCategory.LINE_SEPARATOR.code)
    }

    @Test
    fun charCategoryParagraphSeparator() {
        val paragraphSeparator = '\u2029'
        assertTrue(paragraphSeparator.isWhitespace())
        assertEquals(CharCategory.PARAGRAPH_SEPARATOR, paragraphSeparator.category)
        assertEquals("Zp", CharCategory.PARAGRAPH_SEPARATOR.code)
    }

    @Test
    fun charCategoryControl() {
        val controlCancel = '\u0018'
        assertTrue(controlCancel.isISOControl())
        assertEquals(CharCategory.CONTROL, controlCancel.category)
        assertEquals("Cc", CharCategory.CONTROL.code)
    }
}
