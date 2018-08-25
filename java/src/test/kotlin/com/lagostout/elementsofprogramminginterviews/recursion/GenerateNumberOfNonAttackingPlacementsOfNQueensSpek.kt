package com.lagostout.elementsofprogramminginterviews.recursion

import assertk.assert
import assertk.assertions.isEqualTo
import com.lagostout.common.Position
import com.lagostout.elementsofprogramminginterviews.recursion.GenerateNumberOfNonAttackingPlacementsOfNQueens.compute
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.math.absoluteValue

@Suppress("NestedLambdaShadowedImplicitParameter")
object GenerateNumberOfNonAttackingPlacementsOfNQueensSpek : Spek({

    fun computeWithBruteForce(n: Int): Int {
        val allPositions = (1..n).flatMap { row ->
            (1..n).map { col -> Position(row, col) }
        }
        return Generator.combination(allPositions)
                .simple(n).filter {
                    // No two queens occupy the same row
                    it.map { it.row }.toSet().count() == n &&
                            // No two queens occupy the same column
                            it.map { it.col }.toSet().count() == n && run {
                        // No two queens occupy the same diagonal
                        Generator.combination(it).simple(2).asSequence().none { (first, second) ->
                            (first.row - second.row).absoluteValue ==
                                    (first.col - second.col).absoluteValue
                        }
                    }
                }.count()
    }

    val data = listOfNotNull(
        0, 1, 2, 3,
        4, 5, 6,
        null
    ).map {
        data(it, computeWithBruteForce(it))
    }.toTypedArray()

    describe("generateAllNonAttackingPlacementsOfNQueens") {
        on("n: %s", with = *data) { n, expected ->
            val placements = compute(n)
            it("should return $expected") {
                assert(placements).isEqualTo(expected)
            }
        }
    }

})
