package com.lagostout.elementsofprogramminginterviews.strings

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object LookAndSayProblemSpek : Spek({
    describe("computeLookAndSaySequence()") {
        val data = listOf(
                data(1,"1"),
                data(2, "11"),
                data(3, "21"),
                data(4, "1211"),
                data(5, "111221"),
                data(6, "312211"),
                data(7, "13112221"),
                data(8, "1113213211"),
                null
        ).filterNotNull().toTypedArray()
        on("n: %s", with = *data) { n, expected ->
            it("returns $expected") {
                assertThat(computeLookAndSaySequence(n))
                        .isEqualTo(expected)
            }
        }
    }
})