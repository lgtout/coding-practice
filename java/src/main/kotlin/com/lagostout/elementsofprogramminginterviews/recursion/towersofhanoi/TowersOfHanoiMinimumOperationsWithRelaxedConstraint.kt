package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import java.util.*

/**
 * Problem 16.1.6 page 290
 */
fun minimumOperationsWithRelaxedConstraint(
        pegs: TowersOfHanoi.Pegs<Ring>, fromPeg: Peg<Ring>, toPeg: Peg<Ring>,
        operations: MutableList<RingMove<Ring>>) {

    if (fromPeg.isEmpty) return

    data class Frame(val from: Peg<Ring>, val to: Peg<Ring>,
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
                if (to.bottom == null ||
                        compareValues(to.bottom, from.peek()) > 0) {
                    from.pop().let {
                        to.push(it)
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
                // Alternate approach:

                // We would perform fewer operations if we first traversed
                // the rings we're about to move, to find the largest one.
                // Then compared that to the bottom ring of the toPeg.
                // If it were larger, then we'd perform operations to make
                // the largest one the bottom ring of the toPeg. Then we'd
                // move the rings below that directly to the toPeg.  But
                // we're subject to the restriction that we can only move
                // the top ring of one peg<Ring> to another peg<Ring>.  We presume this
                // means we can't iterate the rings on a peg<Ring> except by moving
                // them to another peg<Ring>.  This rules out finding the largest
                // ring without moving the pegs i.e. by iterating.

                // Current approach:

                // If all the rings to move are smaller than the bottom
                // toPeg ring, we'll transfer the rings directly from
                // fromPeg to toPeg.  If one of them is larger than the
                // bottom ring, we'll move the toPeg rings out of the
                // way to the extra peg<Ring> first.  Then move the larger
                // ring. Then proceed with the rest of the rings on the
                // fromPeg.

                // The idea is to only use the extra peg<Ring> when necessary.
                // Otherwise, we do direct moves between fromPeg and
                // toPeg. The indirect moves will be handled by the
                // first branch of the current if-else.

                val topRings =  countOfRingsToMove - 1
                listOf(Frame(from, to, topRings), Frame(from, to, 1))
                        .reversed().forEach {
                    stack.push(it)
                }
            }
        }
    }

}
