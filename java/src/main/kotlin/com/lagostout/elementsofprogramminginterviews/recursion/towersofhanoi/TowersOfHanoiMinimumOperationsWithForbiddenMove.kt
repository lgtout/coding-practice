package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.THIRD
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Ring
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import java.util.*

fun minimumNumberOfOperationsWithForbiddenMove(
        pegs: Pegs<Ring>, fromPeg: Peg<Ring>, toPeg: Peg<Ring>,
        operations: MutableList<RingMove<Ring>>) {

    if (fromPeg.isEmpty) return

    data class Frame(val from: Peg<Ring>, val to: Peg<Ring>,
                     val countOfRingsToMove: Int) {
        val move: Pair<PegPosition, PegPosition>
            get() = Pair(from.position, to.position)
        operator fun component4() = move
    }

    val forbiddenMove = Pair(PegPosition.FIRST, PegPosition.SECOND)

    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }

    while (stack.isNotEmpty()) {
        stack.pop().let { (from, to, countOfRingsToMove, move) ->
            if (countOfRingsToMove >= 1) {
                if (move == forbiddenMove) {
                    val countOfRingsToMoveAside = countOfRingsToMove - 1
                    val rightPeg = pegs.at(THIRD)
                    Pair(from, to).let { (leftPeg, middlePeg) ->
                        listOf(Frame(leftPeg, rightPeg, countOfRingsToMoveAside),
                                Frame(rightPeg, middlePeg, countOfRingsToMoveAside),
                                Frame(leftPeg, rightPeg, 1),
                                Frame(middlePeg, leftPeg, countOfRingsToMoveAside),
                                Frame(rightPeg, middlePeg, 1),
                                Frame(leftPeg, middlePeg, countOfRingsToMoveAside))
                                .reversed().forEach { stack.push(it) }
                    }
                } else {
                    if (countOfRingsToMove == 1) {
                        val ring = from.pop()
                        operations.add(RingMove(from.position, to.position, ring))
                        to.push(ring)
                    } else {
                        val extraPeg = pegs.extra(from, to)
                        val countOfRingsToMoveAside = countOfRingsToMove - 1
                        listOf(Frame(from, extraPeg, countOfRingsToMoveAside),
                                Frame(from, to, 1),
                                Frame(extraPeg, to, countOfRingsToMoveAside))
                                .reversed().forEach { stack.push(it) }
                    }
                }
            }
        }
    }

}
