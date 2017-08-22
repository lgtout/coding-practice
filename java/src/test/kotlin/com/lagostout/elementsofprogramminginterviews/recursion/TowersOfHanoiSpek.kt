package com.lagostout.elementsofprogramminginterviews.recursion

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
    describe("") {
        testCases.forEach {
            (ringCount) ->
            given("") {
                it("") {
                    val pegs = Pegs(ringCount)
                    val operations = mutableListOf<RingMove>()
                    transferRingsFromOnePegToAnother(pegs, LEFT, MIDDLE, ringCount, operations)
                    assertTrue(operationSequenceIsValid(ringCount, operations, pegs))
                }
            }
        }
    }
}) {
    companion object {
        data class TestCase(val ringCount: Int = 0)

        val testCases = listOf(TestCase(),
                null).filterNotNull()

        fun operationSequenceIsValid(ringCount: Int,
                                     operations: List<TowersOfHanoi.RingMove>,
                                     resultPegs: Pegs): Boolean {
            val pegs = Pegs(ringCount)
            operations.forEach { (fromPosition, toPosition) ->
                pegs.at(toPosition).push(pegs.at(fromPosition).pop())
            }
            return pegs == resultPegs
        }
    }
}