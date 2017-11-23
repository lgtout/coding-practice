package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
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
                TestCase(LEFT, MIDDLE, expectedOperationCount = 0),
                TestCase(MIDDLE, RIGHT, expectedOperationCount = 0),
                TestCase(RIGHT, LEFT, expectedOperationCount = 0),
                TestCase(LEFT, RIGHT, expectedOperationCount = 0),
                TestCase(RIGHT, MIDDLE, expectedOperationCount = 0),
                TestCase(MIDDLE, LEFT, expectedOperationCount = 0),
                TestCase(LEFT, MIDDLE, listOf(1,1), 2),
                TestCase(MIDDLE, RIGHT, listOf(1,1), 2),
                TestCase(RIGHT, LEFT, listOf(1,1), 2),
                TestCase(LEFT, RIGHT, listOf(1,1), 2),
                TestCase(RIGHT, MIDDLE, listOf(1,1), 2),
                TestCase(MIDDLE, LEFT, listOf(1,1), 2),
                TestCase(LEFT, MIDDLE, (1..2), 6),
                TestCase(LEFT, MIDDLE, (1..3), 14),
                TestCase(RIGHT, RIGHT, (1..5)),
                TestCase(MIDDLE, RIGHT, (1..5)),
                TestCase(RIGHT, MIDDLE, (1..5)),
                TestCase(RIGHT, LEFT, (1..5)),
                null)

        testCases.forEach {
            (fromPegPosition, toPegPosition, rings, expectedOperationCount) ->
            given("rings: $rings, from peg: $fromPegPosition, " +
                    "to peg: $toPegPosition") {
                it("moves rings between pegs ${ expectedOperationCount?.let {"in $it moves"} }") {
                    val pegs = TowersOfHanoi.Pegs(rings, fromPegPosition)
                    val operations = mutableListOf<RingMove>()
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
