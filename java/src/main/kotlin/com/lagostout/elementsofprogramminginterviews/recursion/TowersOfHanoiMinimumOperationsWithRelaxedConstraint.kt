package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition.*
import java.util.*

/**
 * Problem 16.1.6 page 290
 */
fun minimumOperationsWithRelaxedConstraint(
        pegs: TowersOfHanoi.Pegs, fromPeg: Peg, toPeg: Peg,
        operations: MutableList<RingMove>) {

    if (fromPeg.isEmpty) return

    data class Frame(val from: Peg, val to: Peg,
                     val countOfRingsToMove: Int) {
        val move: Pair<PegPosition, PegPosition>
            get() = Pair(from.position, to.position)
        operator fun component4() = move
    }

    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }
    while (stack.isNotEmpty()) {
        stack.pop().let { (from, to, countOfRingsToMove) ->
            // TODO
            // Not sure about this.
            // Duplication is staring to appear in both cases:
            // 1 ring to move or more.
            if (countOfRingsToMove == 1) {
                if (toPeg.bottom == null ||
                        compareValues(toPeg.bottom, from.peek()) > 0) {
                    from.pop().let {
                        toPeg.push(it)
                        operations.add(RingMove(from.position, to.position, it))
                    }
                } else {
                    val extra = pegs.extra(from, to)
                    listOf(Frame(to, extra, to.size),
                            Frame(from, to, 1),
                            Frame(to, extra, to.size)).reversed().forEach {
                        stack.push(it)
                    }
                }
            } else {
                val topRings =  countOfRingsToMove - 1
                val bottomRingToMove = from.peek(countOfRingsToMove)
                if (compareValues(toPeg.bottom, bottomRingToMove) > 1) {
                    listOf(Frame(from, to, 1), Frame(from, to , topRings)).forEach {
                        stack.push(it)
                    }
                } else {

                }
                val extra = pegs.extra(from, to)
//                val topRings =  countOfRingsToMove - 1
//                // TODO
//                // Let's compare the bottom
//                listOf(Frame(from, extra, topRings),
//                        Frame(from, to, 1),
//                        Frame(extra, to, topRings)).reversed().forEach {
//                    stack.push(it)
//                }
            }
        }
        val bottomRingOfToPeg = toPeg.bottom
        if (bottomRingOfToPeg == null) {

        }
    }

}
