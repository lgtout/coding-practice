package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

// There's a lot of duplication with TowersOfHanoiSpek, but solving
// that problem is less valuable than moving on to other EPIJ problems.
class TowersOfHanoiWithoutRecursionSpek : Spek({
    describe("transferRingsBetweenPegsWithoutRecursion") {
        testCases.forEach {
            (fromPegPosition, toPegPosition, ringCount) ->
            given("ringCount: $ringCount, from peg: $fromPegPosition, to peg: $toPegPosition") {
                it("moves rings between pegs") {
                    val pegs = TowersOfHanoi.Pegs<Ring>(ringCount, fromPegPosition)
                    val operations = mutableListOf<RingMove<Ring>>()
                    TowersOfHanoiWithoutRecursion.transferRingsBetweenPegsWithoutRecursion(
                            pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), operations)
                    assertEquals(pegs, pegsFromRunningOperations(
                            ringCount, fromPegPosition, operations))
                }
            }
        }
    }
}) {
    companion object {

        val testCases = listOf(
                TestCase(LEFT, MIDDLE),
                TestCase(LEFT, MIDDLE, 1),
                TestCase(LEFT, MIDDLE, 2),
                TestCase(LEFT, MIDDLE, 3),
                TestCase(RIGHT, RIGHT, 5),
                TestCase(MIDDLE, RIGHT, 5),
                TestCase(RIGHT, MIDDLE, 5),
                TestCase(RIGHT, LEFT, 5),
                null).filterNotNull()

    }
}
