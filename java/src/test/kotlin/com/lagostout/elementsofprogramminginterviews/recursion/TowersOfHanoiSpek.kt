package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition.LEFT
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition.MIDDLE
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.transferRingsFromOnePegToAnother
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertTrue

class TowersOfHanoiSpek : Spek({
    describe("transferRingsFromOnePegToAnother") {
        testCases.forEach {
            (fromPegPosition, toPegPosition, ringCount) ->
            given("ringCount: $ringCount, from peg: $fromPegPosition, to peg: $toPegPosition") {
                it("moves rings between pegs") {
                    val pegs = Pegs(ringCount)
                    val operations = mutableListOf<RingMove>()
                    transferRingsFromOnePegToAnother(pegs, fromPegPosition,
                            toPegPosition, ringCount, operations)
                    println(operations)
                    assertTrue(operationSequenceIsValid(ringCount, operations, pegs))
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
//                TestCase(LEFT, MIDDLE),
//                TestCase(LEFT, MIDDLE, 1),
//                TestCase(LEFT, MIDDLE, 2),
                TestCase(LEFT, MIDDLE, 3),
                null).filterNotNull()

        fun operationSequenceIsValid(ringCount: Int,
                                     operations: List<TowersOfHanoi.RingMove>,
                                     resultPegs: Pegs): Boolean {
            // TODO
//            val pegs = Pegs(ringCount, fromPosition)
//            operations.forEach { (fromPosition, toPosition) ->
//                pegs.at(toPosition).push(pegs.at(fromPosition).pop())
//            }
//            return pegs == resultPegs
            return false
        }
    }
}