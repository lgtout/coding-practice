package com.lagostout.elementsofprogramminginterviews.hashtables

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeAllStringDecompositionsSpek : Spek({
    val data = listOf(
//            data("", listOf(""), emptyList()),
//            data("man", listOf("man"), listOf(0)),
//            data("manman", listOf("man"), listOf(0,3)),
//            data("mancan", listOf("man","can"), listOf(0)),
//            data("mancan", listOf("can","man"), listOf(0)),
//            data("aplcanman", listOf("man","can"), listOf(3)),
//            data("aplcanman", listOf("man","can"), listOf(3)),
//            data("canaplman", listOf("man","can"), listOf()),
//            data("canxman", listOf("man","can"), listOf()),
//            data("canmancan", listOf("man","can"), listOf(0,3)),
//            data("aplcanmanaplcan", listOf("man","can"), listOf(3)),
            // TODO
            // Move forward one character at a time, not wordLength.
            data("amanaplanacanal", listOf("can","apl","ana"), listOf(4)),
            null
    ).filterNotNull().toTypedArray()
    on("sentence: %s, words: %s", with = *data) { sentence, words, expected ->
        it("returns $expected") {
            assertThat(computeAllStringDecompositions(sentence, words))
                    .isEqualTo(expected)
        }
    }
})
