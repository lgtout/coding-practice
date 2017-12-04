package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoiWithColoredDisks.Color.WHITE

/**
 * Problem 16.1.8 page 290
 */
object TowersOfHanoiWithColoredDisks {

    enum class Color {
        BLACK, WHITE
    }

    class ColoredRing(size: Int, val color: Color) : TowersOfHanoi.Ring(size)

    // From the problem description, we know there are only 3 cases to cover.
    // 1. All white rings.  2. All black rings.  3. One or more black rings
    // on top of 1 or more white rings.
    // This makes for a much simpler approach than the previous Tower of Hanoi
    // problems.  We don't need a stack and conditionals are simpler.

    // TODO
    // See posted question on forum

    fun computeMinimumNumberOfOperationsWithColoredDisksAreColored(
            pegs: Pegs<ColoredRing>, fromPeg: Peg<ColoredRing>, toPeg: Peg<ColoredRing>,
            operations: MutableList<RingMove<ColoredRing>>) {

        if (fromPeg.isEmpty) return
        val extraPeg = pegs.extra(fromPeg, toPeg)

        while (true) {
            val ring = fromPeg.pop()
            if (ring.color == WHITE) break
            extraPeg.push(ring)
            operations.add(RingMove(fromPeg, extraPeg, ring))
        }

        while (true) {
            if (fromPeg.isEmpty) break
            fromPeg.pop().let {
                toPeg.push(it)
                operations.add(RingMove(fromPeg, toPeg, it))
            }
        }

        while (true) {
            if (extraPeg.isEmpty) break
            extraPeg.pop().let {
                toPeg.push(it)
                operations.add(RingMove(extraPeg, toPeg, it))
            }
        }
    }

}
