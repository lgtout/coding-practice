package com.lagostout.elementsofprogramminginterviews.searching

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object DivideWithoutUsingDivisionOperatorSpek : Spek({
    describe("divideWithoutUsingDivisionOperator()") {
        val tolerance = 0.000001
        var data = listOfNotNull(
                Pair(0.0, 1.0),
                Pair(1.0, 2.0),
                Pair(1.0, -2.0),
                Pair(-1.0, 2.0),
                Pair(-1.0, -2.0),
                Pair(1.5, 2.0),
                Pair(100.5, 8.06),
                null
        ).map { (divisor, dividend) ->
            data(divisor, dividend, (divisor / dividend).let {
                Pair(it - tolerance, it + tolerance)
            })
        }.toTypedArray()
        on("divisor: %s, dividend: %s", with = *data) {
            divisor, dividend, expected ->
            it("returns result in range $expected") {
                assertThat(divideWithoutUsingDivisionOperator(
                        divisor, dividend, tolerance))
                        .isBetween(expected.first, expected.second)
            }
        }
    }
})