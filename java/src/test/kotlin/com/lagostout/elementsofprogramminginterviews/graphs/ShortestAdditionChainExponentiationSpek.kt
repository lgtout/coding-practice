package com.lagostout.elementsofprogramminginterviews.graphs

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ShortestAdditionChainExponentiation : Spek({

    // TODO More cases. What does brute force solution look like?

    val data = listOfNotNull(
        data(1, listOf(1)),
        data(2, listOf(1,2)),
        data(3, listOf(1,2,3)),
        data(4, listOf(1,2,4)),
        data(5, listOf(1,2,4,5)),
        null
    ).toTypedArray()

    describe("shortestAdditionChainExponentiation") {
        on("exponent: %s", with = *data) { exponent, expected ->
            it("should return $expected") {
                val chain = shortestAdditionChainExponentiation(exponent)
                assertThat(chain).isEqualTo(expected)
            }
        }
    }

})