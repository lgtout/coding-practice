package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
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
                    val pegs = TowersOfHanoi.Pegs(ringCount, fromPegPosition)
                    val operations = mutableListOf<RingMove>()
                    TowersOfHanoiWithoutRecursion.
                            transferRingsBetweenPegsWithoutRecursion(
                                    pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), operations)
                    assertEquals(pegs, TowersOfHanoiSpek.pegsFromRunningOperations(
                            ringCount, fromPegPosition, operations))
                }
            }
        }
    }
}) {
    companion object {

        data class TestCase(val fromPegPosition: PegPosition,
                            val toPegPosition: PegPosition,
                            val ringCount: Int = 0)

        val testCases = listOf(
                TestCase(TowersOfHanoi.PegPosition.LEFT, MIDDLE),
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
