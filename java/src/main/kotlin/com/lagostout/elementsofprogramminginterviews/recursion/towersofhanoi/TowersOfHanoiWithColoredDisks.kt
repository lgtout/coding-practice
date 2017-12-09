package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoiWithColoredDisks.Color.BLACK
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

    // This problem is not challenging enough to make it worth writing tests
    // to verify.

    // Forum post http://bit.ly/2BnIWz1

    fun computeMinimumNumberOfOperationsWithColoredDisksAreColored(
            pegs: Pegs<ColoredRing>, fromPeg: Peg<ColoredRing>, toPeg: Peg<ColoredRing>,
            operations: MutableList<RingMove<ColoredRing>>) {

        if (fromPeg.isEmpty) return
        val extraPeg = pegs.extra(fromPeg, toPeg)

        // According to Tsung-Hsien Lee, OK to peek at a peg's bottom disk
        // without removing disks from the peg.

        val bottomDisk = fromPeg.bottom!!
        val topDisk = fromPeg.peek()!!
        if (bottomDisk.color == WHITE && topDisk.color == BLACK) {
            // Move all black disks aside to get to the white ones
            var done = false
            while (!done) {
                fromPeg.peek()?.let {
                    if (it.color == WHITE) {
                        done = true
                    } else {
                        fromPeg.pop()
                        extraPeg.push(it)
                        operations.add(RingMove(fromPeg, extraPeg, it))
                    }
                } ?: break
            }
        }

        // Move all white disks to destination peg
        while (true) {
            if (fromPeg.isEmpty) break
            fromPeg.pop().let {
                toPeg.push(it)
                operations.add(RingMove(fromPeg, toPeg, it))
            }
        }

        // Move black disks that were moved aside to destination peg
        while (true) {
            if (extraPeg.isEmpty) break
            extraPeg.pop().let {
                toPeg.push(it)
                operations.add(RingMove(extraPeg, toPeg, it))
            }
        }
    }

}
