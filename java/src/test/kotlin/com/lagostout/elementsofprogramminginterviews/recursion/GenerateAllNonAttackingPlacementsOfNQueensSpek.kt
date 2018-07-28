package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.GenerateAllNonAttackingPlacementsOfNQueens.Position
import com.lagostout.elementsofprogramminginterviews.recursion.GenerateAllNonAttackingPlacementsOfNQueens.compute
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.math.absoluteValue

@Suppress("NestedLambdaShadowedImplicitParameter")
object GenerateAllNonAttackingPlacementsOfNQueensSpek : Spek({

    fun computeWithBruteForce(n: Int): Set<Set<Position>> {
        val allPositions = (1..n).flatMap { row ->
            (1..n).map { col -> Position(row, col) }
        }
        return Generator.combination(allPositions)
                .simple(n).filter {
                    it.map { it.row }.toSet().count() == n &&
                            it.map { it.col }.toSet().count() == n && run {
                        Generator.combination(it).simple(2).asSequence().none { (first, second) ->
                            (first.row - second.row).absoluteValue ==
                                    (first.col - second.col).absoluteValue
                        }
                    }
                }.map { it.toSet() }.toSet()
    }

    val randomData by memoized {
        val nRange = Pair(0,7)
        (nRange.first..nRange.second).map {
            data(it, computeWithBruteForce(it))
        }.toTypedArray()
    }

    val data = listOfNotNull(
        data(2, setOf()),
        data(1, setOf(setOf(Position(1,1)))),
        data(4, setOf(
            setOf(Position(row=1, col=2),
                Position(row=2, col=4),
                Position(row=3, col=1),
                Position(row=4, col=3)),
            setOf(Position(row=1, col=3),
                Position(row=2, col=1),
                Position(row=3, col=4),
                Position(row=4, col=2)))),
        data(0, emptySet()),
        null
    ).toTypedArray()

    describe("generateAllNonAttackingPlacementsOfNQueens") {
//        on("n: %s", with = *data) { n, expected ->
        on("n: %s", with = *randomData) { n, expected ->
            val placements = compute(n)
            it("should return $expected") {
                assertThat(placements).containsOnlyElementsOf(expected)
            }
        }
    }

})