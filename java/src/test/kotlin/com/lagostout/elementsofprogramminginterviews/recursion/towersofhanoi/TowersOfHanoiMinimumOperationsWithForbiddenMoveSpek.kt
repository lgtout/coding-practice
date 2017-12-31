package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

object TowersOfHanoiMinimumOperationsWithForbiddenMoveSpek : Spek({

    val testCases = listOf(
            TestCase(FIRST, SECOND, expectedOperationCount = 0),
            TestCase(SECOND, THIRD, expectedOperationCount = 0),
            TestCase(THIRD, FIRST, expectedOperationCount = 0),
            TestCase(FIRST, THIRD, expectedOperationCount = 0),
            TestCase(THIRD, SECOND, expectedOperationCount = 0),
            TestCase(SECOND, FIRST, expectedOperationCount = 0),
            TestCase(FIRST, SECOND, 1, 2),
            TestCase(SECOND, THIRD, 1, 1),
            TestCase(THIRD, FIRST, 1, 1),
            TestCase(FIRST, THIRD, 1, 1),
            TestCase(THIRD, SECOND, 1, 1),
            TestCase(SECOND, FIRST, 1, 1),
            TestCase(FIRST, SECOND, 2, 7),
            TestCase(FIRST, SECOND, 3),
            TestCase(THIRD, THIRD, 5),
            TestCase(SECOND, THIRD, 5),
            TestCase(THIRD, SECOND, 5),
            TestCase(THIRD, FIRST, 5),
            null).filterNotNull()

    testCases.forEach {
        (fromPegPosition, toPegPosition, ringCount, expectedOperationCount) ->
        given("ringCount: $ringCount, from peg: $fromPegPosition, " +
                "to peg: $toPegPosition") {
            it("moves rings between pegs ${ expectedOperationCount?.let {"in $it moves"} }") {
                val pegs = TowersOfHanoi.Pegs<Ring>(ringCount, fromPegPosition)
                val operations = mutableListOf<RingMove<Ring>>()
                minimumNumberOfOperationsWithForbiddenMove(
                        pegs, pegs.at(fromPegPosition),
                        pegs.at(toPegPosition), operations)
                println(operations)
                println(operations.size)
                assertEquals(pegs, pegsFromRunningOperations(
                        ringCount, fromPegPosition, operations))
                // Verify that operations performed are only the ones permitted.
                assertThat(operations.map { Pair(it.from, it.to) }, not(contains(
                        anyOf(equalTo(Pair(FIRST, SECOND)))
                )))
                // I'm only verifying expected operation count for trivial cases since
                // expected counts have to be computed by hand.
                expectedOperationCount?.let {
                    assertEquals(expectedOperationCount, operations.size)
                }
            }
        }
    }
})