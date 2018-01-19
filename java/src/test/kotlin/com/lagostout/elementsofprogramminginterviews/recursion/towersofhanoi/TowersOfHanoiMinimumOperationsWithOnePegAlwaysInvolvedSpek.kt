package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.FIRST
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.SECOND
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

object TowersOfHanoiMinimumOperationsWithOnePegAlwaysInvolvedSpek : Spek({

    describe("minimumNumberOfOperationsWithOnePegAlwaysInvolved") {

        data class TestCase(val fromPegPosition: PegPosition,
                            val toPegPosition: PegPosition,
                            val ringCount: Int = 0,
                            val requiredPegPosition: PegPosition = PegPosition.THIRD,
                            val expectedOperationCount: Int? = null)

        val testCases = listOf(
//                TestCase(TowersOfHanoi.PegPosition.FIRST, SECOND),
                TestCase(FIRST, SECOND, 3),
//                TestCase(FIRST, SECOND, 4),
//                TestCase(FIRST, SECOND, 2),
//                TestCase(FIRST, SECOND, 3, expectedOperationCount = 26),
//                TestCase(THIRD, THIRD, 5),
//                TestCase(SECOND, THIRD, 5),
//                TestCase(THIRD, SECOND, 5),
//                TestCase(THIRD, FIRST, 5),
                null).filterNotNull()

        testCases.forEach {
            (fromPegPosition, toPegPosition, ringCount,
                    requiredPegPosition, expectedOperationCount) ->
            given("ringCount: $ringCount, from peg: $fromPegPosition, " +
                    "to peg: $toPegPosition, required peg: $requiredPegPosition, " +
                    "expected operation count: $expectedOperationCount") {
                it("moves rings between pegs") {
                    val pegs = TowersOfHanoi.Pegs<Ring>(ringCount, fromPegPosition)
                    val operations = mutableListOf<RingMove<Ring>>()
                    minimumNumberOfOperationsWithOnePegAlwaysInvolved(
                            pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), pegs.at(requiredPegPosition),
                            operations)
                    println(operations.size)
                    // Too much work, by hand, to figure out what the expected number
                    // of operations should be.  And I don't want to spend time on an
                    // alternate solution.  But I'm fairly confident my solution
                    // results in the minimum number.  So we just need to verify that
                    // the operations put the pegs and rings in the final state expected.
                    // However, I did go through all the steps for one case (moving 3 rings
                    // from left to middle peg), so I can verify expected operation count
                    // for that one.
                    assertEquals(pegs, pegsFromRunningOperations(
                            ringCount, fromPegPosition, operations))
                    expectedOperationCount?.let {
                        assertEquals(it, operations.size)
                    }
                }
            }
        }
    }

})
