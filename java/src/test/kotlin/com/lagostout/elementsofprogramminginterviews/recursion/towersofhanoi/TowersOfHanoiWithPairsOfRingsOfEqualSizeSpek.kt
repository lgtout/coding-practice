package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

object TowersOfHanoiWithPairsOfRingsOfEqualSizeSpek : Spek({

    describe("computeMinimumNumberOfOperationsWithPairsOfRingsOfEqualSize()") {

        data class TestCase(val fromPegPosition: PegPosition,
                            val toPegPosition: PegPosition,
                            val rings: List<Int> = emptyList(),
                            val expectedOperationCount: Int? = null) {
            constructor (fromPegPosition: PegPosition, toPegPosition: PegPosition,
                         range: IntRange, expectedOperationCount: Int? = null) :
                    this(fromPegPosition, toPegPosition, range.flatMap { listOf(it, it) },
                            expectedOperationCount)
        }

        val testCases = listOfNotNull(
                TestCase(FIRST, SECOND, expectedOperationCount = 0),
                TestCase(SECOND, THIRD, expectedOperationCount = 0),
                TestCase(THIRD, FIRST, expectedOperationCount = 0),
                TestCase(FIRST, THIRD, expectedOperationCount = 0),
                TestCase(THIRD, SECOND, expectedOperationCount = 0),
                TestCase(SECOND, FIRST, expectedOperationCount = 0),
                TestCase(FIRST, SECOND, listOf(1,1), 2),
                TestCase(SECOND, THIRD, listOf(1,1), 2),
                TestCase(THIRD, FIRST, listOf(1,1), 2),
                TestCase(FIRST, THIRD, listOf(1,1), 2),
                TestCase(THIRD, SECOND, listOf(1,1), 2),
                TestCase(SECOND, FIRST, listOf(1,1), 2),
                TestCase(FIRST, SECOND, (1..2), 6),
                TestCase(FIRST, SECOND, (1..3), 14),
                TestCase(THIRD, THIRD, (1..5)),
                TestCase(SECOND, THIRD, (1..5)),
                TestCase(THIRD, SECOND, (1..5)),
                TestCase(THIRD, FIRST, (1..5)),
                null)

        testCases.forEach {
            (fromPegPosition, toPegPosition, rings, expectedOperationCount) ->
            given("rings: $rings, from peg: $fromPegPosition, " +
                    "to peg: $toPegPosition") {
                it("moves rings between pegs ${ expectedOperationCount?.let {"in $it moves"} }") {
                    val pegs = TowersOfHanoi.Pegs<Ring>(rings, fromPegPosition)
                    val operations = mutableListOf<RingMove<Ring>>()
                    computeMinimumNumberOfOperationsWithPairsOfRingsOfEqualSize(
                            pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), operations)
                    assertEquals(pegs, pegsFromRunningOperations(
                            rings, fromPegPosition, operations))
                    // Verify that operations performed are only the ones permitted.
                    // I'm only verifying expected operation count for trivial cases since
                    // expected counts have to be computed by hand.
                    expectedOperationCount?.let {
                        assertEquals(expectedOperationCount, operations.size)
                    }
                }
            }
        }
    }
})
