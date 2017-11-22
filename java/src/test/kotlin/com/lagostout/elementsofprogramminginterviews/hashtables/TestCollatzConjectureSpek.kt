package com.lagostout.elementsofprogramminginterviews.hashtables

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object TestCollatzConjectureSpek : Spek({
    describe("testCollatzConjecture()") {
        val numberCount = 10_000_000
        given("first $numberCount positive numbers") {
            it("returns true") {
                assertThat(testCollatzConjecture(numberCount))
                        .isEqualTo(true)
            }
        }
    }
})