package com.lagostout.elementsofprogramminginterviews.primitivetypes

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeProductWithoutArithmeticOperatorsSpek : Spek({

    val data = listOfNotNull(
        data(Pair(0,0), 0),
        data(Pair(1,0), 0),
        data(Pair(0,1), 0),
        data(Pair(1,1), 1),
        data(Pair(2,1), 2),
        data(Pair(5,1), 5),
        data(Pair(5,1), 5),
        data(Pair(3,2), 6),
        data(Pair(3,3), 9),
        data(Pair(3,5), 15),
        data(Pair(3,8), 24),
        data(Pair(3,11), 33),
        null
    ).toTypedArray()

    describe("productWithoutArithmeticOperators") {
        on("operands: %s", with = *data) { (left, right), expected ->
            it("returns $expected") {
                assertThat(productWithoutArithmeticOperators(left, right))
                        .isEqualTo(expected)
            }
        }
    }

})