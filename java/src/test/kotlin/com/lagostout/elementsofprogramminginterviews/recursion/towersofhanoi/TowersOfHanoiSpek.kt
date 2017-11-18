package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
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
        listOfNotNull(
                TestCase(LEFT, MIDDLE),
                TestCase(LEFT, MIDDLE, 1),
                TestCase(LEFT, MIDDLE, 2),
                TestCase(LEFT, MIDDLE, 3),
                TestCase(RIGHT, RIGHT, 5),
                TestCase(MIDDLE, RIGHT, 5),
                TestCase(RIGHT, MIDDLE, 5),
                TestCase(RIGHT, LEFT, 5),
                null).forEach {
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
})