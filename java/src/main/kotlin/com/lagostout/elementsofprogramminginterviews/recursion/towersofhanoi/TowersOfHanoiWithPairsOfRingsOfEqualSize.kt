package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import java.util.*

fun computeMinimumNumberOfOperationsWithPairsOfRingsOfEqualSize(
        pegs: Pegs, fromPeg: Peg, toPeg: Peg, operations: MutableList<RingMove>) {

    if (fromPeg.isEmpty) return

    data class Frame(val from: Peg, val to: Peg,
                     val countOfRingsToMove: Int)

    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }

    while (stack.isNotEmpty()) {
        val frame = stack.pop()
        with (frame) {
            when (countOfRingsToMove) {
                2 -> {
                    (1..2).forEach {
                        from.pop().let {
                            to.push(it)
                            operations.add(RingMove(from, to, it))
                        }
                    }
                }
                else -> {
                    val extraPeg = pegs.extra(listOf(from, to))
                    val countOfRingsToMoveAside = countOfRingsToMove - 2
                    listOf(frame.copy(to = extraPeg,
                            countOfRingsToMove = countOfRingsToMoveAside),
                            frame.copy(countOfRingsToMove = 2),
                            frame.copy(from = extraPeg, to = to,
                                    countOfRingsToMove = countOfRingsToMoveAside)
                    ).forEach {
                        stack.push(it)
                    }
                }
            }
        }
    }
}