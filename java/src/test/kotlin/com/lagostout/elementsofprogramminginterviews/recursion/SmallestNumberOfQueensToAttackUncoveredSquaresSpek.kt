package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.common.Position
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object SmallestNumberOfQueensToAttackUncoveredSquaresSpek : Spek({

    fun toPosition(n: Int, dim: Int): Position {
        return Position((n - 1) / dim + 1, n % dim)
    }

    val data = listOfNotNull(
        1, 2, 3, 4,
        null
    ).map { dim ->
        val allSquares = (1..dim * dim).map { toPosition(it, dim) }
        val minQueenCount = Generator.subset(allSquares).simple().map { selectedSquares ->
            val uncoveredSquares = allSquares.toMutableSet()
            selectedSquares.forEach { selectedSquare ->
                uncoveredSquares.removeIf {
                    selectedSquare.row == it.row ||
                            selectedSquare.col == it.col ||
                            selectedSquare.run { row + col } == it.run { row + col } ||
                            selectedSquare.run { row - col } == it.run { row - col }
                }
            }
            Pair(selectedSquares.size, uncoveredSquares)
        }.filter { it.second.isEmpty() }.distinct().map { it.first }.min()!!
        data(dim, minQueenCount)
    }.toTypedArray()

    describe("smallestNumberOfQueensToAttackUncoveredSquares") {
        on("dim: %s", with = *data) { dim, expected ->
            it("should return $expected") {
                assertThat(smallestNumberOfQueensToAttackUncoveredSquares(dim))
                        .isEqualTo(expected)
            }
        }
    }

})