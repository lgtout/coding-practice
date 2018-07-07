package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.common.nextInt
import com.lagostout.elementsofprogramminginterviews.recursion.GenerateAllNonAttackingPlacementsOfNQueens.Position
import com.lagostout.elementsofprogramminginterviews.recursion.GenerateAllNonAttackingPlacementsOfNQueens.compute
import org.apache.commons.math3.random.RandomDataGenerator
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator
import kotlin.math.absoluteValue

object GenerateAllNonAttackingPlacementsOfNQueensSpek : Spek({

    val randomData by memoized {
        val caseCount = 10
        val nRange = Pair(0,6)
        val random = RandomDataGenerator().apply { reSeed(1) }
        (1..caseCount).map {
            val n = random.nextInt(nRange)
            val allPositions = (1..n).flatMap { row ->
                (1..n).map { col -> Position(row, col) }
            }
            val expected = Generator.combination(allPositions)
                    .simple(n).filter {
                        it.map { it.row }.toSet().count() == n &&
                                it.map { it.col }.toSet().count() == n && run {
                            Generator.combination(it).simple(2).asSequence().none { (first, second) ->
                                (first.row - second.row).absoluteValue ==
                                        (first.col - second.col).absoluteValue
                            }
                        }
                    }
            data(n, expected)
        }.toTypedArray()
    }

    val data = listOfNotNull(
//        data(2, emptyList<List<Position>>())
        data(1, listOf(listOf(Position(1,1)))),
        null)
            .toTypedArray()

    describe("generateAllNonAttackingPlacementsOfNQueens") {
        on("n: %s", with = *data) { n, expected ->
//        on("n: %s", with = *randomData) { n, expected ->
            it("should return $expected") {
                assertThat(compute(n))
                        .containsOnlyElementsOf(expected)
            }
        }
    }

})