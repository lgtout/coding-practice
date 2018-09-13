package com.lagostout.bytebybyte.recursion

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.xdescribe
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object GreatestProductPathSpek : Spek({

    xdescribe("greatestProduct") {
        val data = listOfNotNull(
            data(arrayOf(arrayOf(1)), 1),
            data(arrayOf(arrayOf(-1)), -1),
            data(arrayOf( arrayOf(1,2,2), arrayOf(1,-3,1), arrayOf(10,1,-5)), 30),
            null
        ).toTypedArray()
        on("matrix %s", with = *data) { matrix, expected ->
            it("should return $expected") {
                assertThat(greatestProduct(matrix))
                        .isEqualTo(expected)
            }
        }
    }

    describe("greatestProductPath") {
        val data = listOfNotNull(
            data(arrayOf(arrayOf(1)), listOf(Coordinate(0,0))),
            data(arrayOf(arrayOf(-1)), listOf(Coordinate(0,0))),
            data(arrayOf( arrayOf(1,2,2), arrayOf(1,-3,1), arrayOf(10,2,-5)),
                listOf(Coordinate(0,0), Coordinate(0,1), Coordinate(1,1),
                    Coordinate(2,1), Coordinate(2,2))),
            null
        ).toTypedArray()
        on("matrix %s", with = *data) { matrix, expected ->
            it("should return $expected") {
                assertThat(greatestProductPath(matrix))
                        .isEqualTo(expected)
            }
        }
    }
})