package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import org.apache.commons.collections4.IteratorUtils.loopingListIterator
import java.util.*

/* Problem 16.1.9 page 290 */

/*
1. Move all the rings above the bottom-most ring of the source
peg to the two pegs that are not the destination peg.  As we do
move each of these rings, we alternate between the two pegs which
one we place the ring on.  This stripes the rings i.e peg 1: [1,3,5],
peg 2: [2,4,6]

2. Move the bottom-most ring from the source peg to the destination
peg.

3. Alternating between whichever two pegs that hold the striped pegs,
we use the 2 non-striped pegs to move rings above the bottom-most ring
of a (source) striped peg aside to the (only) empty peg.  We move the
bottom-most ring of the source striped peg to the final destination peg.
The source striped peg is now the empty peg.  And the peg we moved rings
aside to is now the source striped peg for future rounds.
 */

private enum class Step {

}

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
    val stripedPegsIterator = loopingListIterator(listOf(extraPegs))

    while (stack.isNotEmpty()) {
        stack.pop().let { (from, to, countOfRingsToMove) ->

        }
    }

}
