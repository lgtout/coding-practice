package com.lagostout.elementsofprogramminginterviews.primitivetypes

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object DivideSpek : Spek({

    val data = listOfNotNull(
        data(1,1,1),
        data(1,2,0),
        data(0,2,0),
        data(2,2,1),
        data(5,2,2),
        data(8,2,4),
        data(11,2,5),
        data(13,2,6),
        data(13,3,4),
        data(17,5,3),
        null
    ).toTypedArray()

    describe("divide") {
        on("x %s y %s", with = *data) { x, y, expected ->
            val result = divide(x, y)
            it("should return $expected") {
                assertThat(result).isEqualTo(expected)
            }
        }
    }

})