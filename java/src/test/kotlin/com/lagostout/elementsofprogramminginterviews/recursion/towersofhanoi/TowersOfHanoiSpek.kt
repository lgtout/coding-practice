package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.transferRingsFromOnePegToAnother
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class TowersOfHanoiSpek : Spek({
    describe("transferRingsFromOnePegToAnother") {
        testCases.forEach {
            (fromPegPosition, toPegPosition, ringCount) ->
            given("ringCount: $ringCount, from peg: $fromPegPosition, to peg: $toPegPosition") {
                it("moves rings between pegs") {
                    val pegs = Pegs(ringCount, fromPegPosition)
                    val operations = mutableListOf<RingMove>()
                    transferRingsFromOnePegToAnother(pegs, pegs.at(fromPegPosition),
                            pegs.at(toPegPosition), ringCount, operations)
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

        fun pegsFromRunningOperations(
                ringCount: Int,
                fromPosition: PegPosition,
                operations: List<TowersOfHanoi.RingMove>): Pegs {
            return pegsFromRunningOperations(
                    (1..ringCount).toList(), fromPosition, operations)
        }

        fun pegsFromRunningOperations(
                rings: List<Int>,
                fromPosition: PegPosition,
                operations: List<TowersOfHanoi.RingMove>,
                constraint: (from: Peg, to: Peg, ring: Ring) -> Unit = { _, _, _ -> }): Pegs {
            val pegs = Pegs(rings, fromPosition)
            operations.forEach { (fromPosition, toPosition) ->
                pegs.at(toPosition).let { toPeg ->
                    pegs.at(fromPosition).let { fromPeg ->
                        val ring = fromPeg.pop()
                        toPeg.push(ring)
                        constraint(fromPeg, toPeg, ring)
                    }
                }
            }
            return pegs
        }
    }
}