package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import java.util.*

/**
 * Problem 16.1.3 page 290
 */
fun minimumNumberOfOperationsWithOnePegAlwaysInvolved(
        pegs: Pegs, fromPeg: Peg, toPeg: Peg, requiredPeg: Peg,
        operations: MutableList<RingMove>) {

    if (fromPeg.isEmpty) return

    data class Frame(val from: Peg, val to: Peg,
                     val countOfRingsToMove: Int)

    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }

    while (stack.isNotEmpty()) {
        val frame = stack.pop()
        if (frame.from != requiredPeg && frame.to != requiredPeg) {
            listOf(Frame(frame.from, requiredPeg, frame.countOfRingsToMove),
                    Frame(requiredPeg, frame.to, frame.countOfRingsToMove)).reversed().forEach {
                stack.push(it)
            }
        } else if (frame.to == requiredPeg) {
            if (frame.countOfRingsToMove == 1) {
                frame.from.pop().let {
                    frame.to.push(it)
                    operations.add(RingMove(
                            frame.from.position, frame.to.position, it))
                }
            } else {
                val extraPeg = pegs.extra(listOf(frame.from, frame.to))
                val ringCount = frame.countOfRingsToMove - 1
                listOf(Frame(frame.from, requiredPeg, ringCount),
                        Frame(requiredPeg, extraPeg, ringCount),
                        Frame(frame.from, frame.to, 1),
                        Frame(extraPeg, frame.to, ringCount))
                        .reversed().forEach { stack.push(it) }
            }
        } else if (frame.from == requiredPeg) {
            if (frame.countOfRingsToMove == 1) {
                frame.from.pop().let {
                    frame.to.push(it)
                    operations.add(RingMove(
                            frame.from.position, frame.to.position, it))
                }
            } else {
                val extraPeg = pegs.extra(listOf(frame.from, frame.to))
                val ringCount = frame.countOfRingsToMove - 1
                listOf(Frame(frame.from, extraPeg, ringCount),
                        Frame(frame.from, frame.to, 1),
                        Frame(extraPeg, frame.to, ringCount)).reversed().forEach {
                    stack.push(it)
                }
            }
        }
    }
}
