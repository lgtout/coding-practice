package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
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
                            Frame(extra, to, to.size)).reversed().forEach {
                        stack.push(it)
                    }
                }
            } else {
                val topRings =  countOfRingsToMove - 1
                // If all the rings to move are smaller than the bottom
                // toPeg ring, we'll transfer the rings directly from
                // fromPeg to toPeg.  If one of them is larger than the
                // bottom ring, we'll move the toPeg rings out of the
                // way to the extra peg first.
                // The idea is to only use the extra peg when necessary.
                // Otherwise, we do direct moves between fromPeg and
                // toPeg.
                listOf(Frame(from, to, topRings), Frame(from, to, 1))
                        .reversed().forEach {
                    stack.push(it)
                }
            }
        }
    }

}
