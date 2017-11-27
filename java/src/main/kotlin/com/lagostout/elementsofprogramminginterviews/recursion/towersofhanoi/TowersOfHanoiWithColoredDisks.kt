package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoiWithColoredDisks.Color.*
import java.util.*

object TowersOfHanoiWithColoredDisks {

    enum class Color {
        BLACK, WHITE
    }

    fun computeMinimumNumberOfOperationsWithColoredDisksAreColored(
            pegs: Pegs, fromPeg: Peg, toPeg: Peg, operations: MutableList<RingMove>) {

        if (fromPeg.isEmpty) return

        data class Frame(val from: Peg, val to: Peg,
                         val colorOfRingsToMove: Color)

        val stack = LinkedList<Frame>().apply {
            listOf(Frame(fromPeg, toPeg, WHITE),
                    Frame(fromPeg, toPeg, BLACK)).forEach {
                add(it)
            }
        }

        while (stack.isNotEmpty()) {
            val frame = stack.pop()
            with (frame) {
            }
        }
    }
}
