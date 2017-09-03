package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.RingMove
import java.util.*

object TowersOfHanoiWithoutRecursionSpek {

    enum class Step {
        MOVE_N_RINGS {
            override fun next(): Step {
                return MOVE_RING
            }
        },
        MOVE_RING {
            override fun next(): Step {
                throw UnsupportedOperationException()
            }
        };
        abstract fun next(): Step
    }

    fun transferRingsBetweenPegsWithoutRecursion(
            pegs: Pegs, from: Peg, to: Peg, countOfRingsToMove: Int) {
        data class Frame(var step: Step, val from: Peg,
                         val to: Peg, val countOfRingsToMove: Int)
        val operations: MutableList<RingMove> = mutableListOf()
        val stack = LinkedList<Frame>().apply {
            push(Frame(Step.MOVE_N_RINGS, from, to, from.size - 1))
        }
        while (true) {
            val frame = stack.pop()
            when (frame.step) {
                Step.MOVE_N_RINGS -> {
//                    stack.push(frame. = Step.MOVE_RING
                    stack.push(Frame(Step.MOVE_N_RINGS,
                            from, pegs.extra(listOf(from, to)),
                            frame.countOfRingsToMove - 1))
                }
                Step.MOVE_RING -> {

                    frame.run {
                        from.push(to.pop())
                    }
                }
            }
        }
    }

}
