package com.lagostout.elementsofprogramminginterviews.graphs

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object TransformOneStringToAnother : Spek({
    describe("lengthOfShortestProductionSequence") {
        val data = listOfNotNull(
//                data("a", "a", emptyList(), 0),
//                data("a", "a", listOf("a"), 0),
//                data("a", "a", listOf("c","d"), 0),
//                data("a", "b", listOf("a", "b"), 1),
//                data("a", "b", listOf("c", "b", "a"), 1),
//                data("a", "b", listOf("b", "a"), 1),
//                data("a", "b", listOf("b", "a", "c"), 1),
//                data("ab", "cb", listOf("ab", "ac", "cb"), 2),
                // TODO More cases
                data("ab", "bc", listOf("ab", "ad", "cb", "bc"), -1),
//                data("cat", "dog", listOf("bat", "cot", "dog", "dag", "dot", "cat"), 3),
                null
        ).toTypedArray()
        on("from: %s, to: %s, dictionary: %s", with = *data) {
            from: String, to: String, dictionary: List<String>, expected: Int? ->
            it("returns $expected") {
                assertThat(lengthOfShortestProductionSequence(
                        from, to, dictionary) ?: -1).isEqualTo(expected)
            }
        }
    }
})
