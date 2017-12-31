package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import java.util.*

fun computeMinimumNumberOfOperationsWithFourPegs(
        pegs: Pegs<Ring>, fromPeg: Peg<Ring>, toPeg: Peg<Ring>,
        operations: MutableList<RingMove<Ring>>) {

    if (fromPeg.isEmpty) return

    data class Frame(val from: Peg<Ring>, val to: Peg<Ring>,
                     val countOfRingsToMove: Int)

    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }
    val extraPegs = pegs.extras(fromPeg, toPeg)

    while (stack.isNotEmpty()) {
        stack.pop().let { (from, to, countOfRingsToMove) ->

        }
    }

}