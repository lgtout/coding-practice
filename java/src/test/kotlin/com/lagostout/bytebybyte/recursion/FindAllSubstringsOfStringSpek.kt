package com.lagostout.bytebybyte.recursion

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object FindAllSubstringsOfStringSpek : Spek({

    fun computeByIteration(string: String): List<String> {
        val result = mutableListOf<String>()
        for (startIndex in 0..string.lastIndex) {
            for (endIndex in startIndex + 1..string.length) {
                result.add(string.substring(startIndex, endIndex))
            }
        }
        return result
    }

    val data = listOfNotNull(
        "a", "ab", "abc", "abcd",
        null
    ).map {
        data(it, computeByIteration(it))
    }.toTypedArray()

    describe("findAllSubstringsOfString") {
        on("string %s", with = *data) { string, expected ->
            it("should return $expected") {
                assertThat(findAllSubstringsOfString(string))
                        .containsOnlyElementsOf(expected)
            }
        }
    }

})