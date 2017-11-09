package com.lagostout.elementsofprogramminginterviews.strings

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ReverseWordsInSentenceSpek : Spek({
    val data = listOf(
            "",
            "a",
            "ab",
            "abc",
            "a b",
            "a b c",
            "a b  c",
            "abc de  fghi",
            null
    ).filterNotNull().map {
        val reversed = it.reversed()
                .split(Regex("(?=\\s)|(?<=\\s)"))
                .apply {println(this)}
                .joinToString("") {
                    if (it.matches(Regex("\\s"))) it
                    else it.reversed()
                }
                .apply {println(this)}
        data(it, reversed)
    }.toTypedArray()
    on("sentence %s", with = *data) { sentence: String, expected: String ->
        it("returns $expected") {
            assertThat(reverseWordsInSentence(sentence))
                    .isEqualTo(expected)
        }
    }
})