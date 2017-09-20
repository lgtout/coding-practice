package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
import java.util.*

object TowersOfHanoiWithoutRecursionSpek {

    fun transferRingsBetweenPegsWithoutRecursion(
            pegs: Pegs, from: Peg, to: Peg) {
        data class Frame(val from: Peg,
                         val to: Peg, val countOfRingsToMove: Int)
        val operations: MutableList<RingMove> = mutableListOf()
        val stack = LinkedList<Frame>().apply {
            push(Frame(from, to, from.size - 1))
        }
        while (stack.isNotEmpty()) {
            val frame = stack.pop()
            if (frame.countOfRingsToMove == 1) {
                from.pop().apply {
                    to.push(this)
                    operations.add(RingMove(from.position, to.position, this))
                }
            }
            if (frame.countOfRingsToMove <= 1) continue
            stack.push(frame.copy(countOfRingsToMove = frame.countOfRingsToMove - 1))
            val extraPeg = pegs.extra(listOf(from, to))
            stack.push(Frame(extraPeg, to, extraPeg.size))
            stack.push(Frame(from, to, 1))
            stack.push(Frame(to, extraPeg, to.size))
        }
    }

}
