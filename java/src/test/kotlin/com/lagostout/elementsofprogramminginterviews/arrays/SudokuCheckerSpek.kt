package com.lagostout.elementsofprogramminginterviews.arrays

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object SudokuCheckerSpek : Spek({

    val data = listOfNotNull(
        data(listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 0, 0, 0, 0, 1, 2, 0, 0),
            listOf(0, 0, 0, 0, 6, 0, 4, 0, 0),
            listOf(0, 0, 0, 2, 0, 0, 0, 0, 5),
            listOf(7, 0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 8, 3, 0, 0, 0, 0, 0, 0),
            listOf(0, 0, 0, 0, 0, 0, 3, 3, 0),
            listOf(0, 0, 4, 0, 0, 0, 0, 0, 0)), false),
        data(listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0, 6),
            listOf(0, 4, 7, 0, 0, 2, 0, 9, 1),
            listOf(8, 3, 0, 0, 7, 0, 4, 0, 0),
            listOf(0, 9, 0, 0, 8, 0, 0, 7, 0),
            listOf(3, 6, 1, 0, 0, 0, 5, 0, 0),
            listOf(0, 7, 0, 0, 0, 1, 6, 3, 9),
            listOf(0, 0, 4, 0, 0, 0, 0, 0, 0),
            listOf(5, 0, 0, 0, 6, 0, 9, 0, 4),
            listOf(9, 0, 0, 3, 2, 0, 8, 5, 0)), true),
        null
    ).toTypedArray()

    describe("sudokuIsValid") {
        on("grid: %s", with = *data) { grid, expected ->
            it("should return $expected") {
                assertThat(sudokuIsValid(grid)).isEqualTo(expected)
            }
        }
    }

})