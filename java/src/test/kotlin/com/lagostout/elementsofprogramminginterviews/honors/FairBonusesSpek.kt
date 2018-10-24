package com.lagostout.elementsofprogramminginterviews.honors

import com.lagostout.common.nextInt
import com.lagostout.common.reproducibleRdg
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on
import org.paukov.combinatorics3.Generator

object FairBonusesSpek : Spek({

    fun computeExpected(linesOfCode: List<Int>): List<Int> {
        return Generator.permutation((1..linesOfCode.size).toList())
                .withRepetitions(linesOfCode.size).first { bonuses ->
                    var isSolution = false
                    for (index in bonuses.indices) {
                        val lines = linesOfCode[index]
                        val bonus = bonuses[index]
                        isSolution = listOf(index - 1, index + 1)
                                .filter { it >= 0 && it <= linesOfCode.lastIndex }
                                .map { Pair(linesOfCode[it], bonuses[it]) }
                                .all {
                                    (it.first > lines && it.second > bonus) ||
                                            (it.first < lines && it.second < bonus) ||
                                            (it.first == lines && it.second == bonus)
                                }
                        if (!isSolution) break
                    }
                    isSolution
                }
    }

    val randomData = run {
        val rdg = reproducibleRdg()
        val developerCountRange = (1..6)
        val caseCount = 100
        val locRange = (1..10)
        (1..caseCount).map {
            val developerCount = rdg.nextInt(developerCountRange)
            (1..developerCount).map {
                rdg.nextInt(locRange)
            }
        }
    }.map { linesOfCode ->
        data(linesOfCode, computeExpected(linesOfCode))
    }.toTypedArray()

    val data = listOfNotNull(
        listOf(1),
        listOf(1,2),
        null
    ).map { linesOfCode ->
        data(linesOfCode, computeExpected(linesOfCode))
    }.toTypedArray()

    describe("fairBonuses") {
        on("linesOfCode %s", with = *randomData) { linesOfCode, expected ->
            it("should return $expected") {
                assertThat(fairBonuses(linesOfCode)).isEqualTo(expected)
            }
        }
    }

})