package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Peg
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.Pegs
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.RingMove
import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*
import java.util.*

/**
 * Problem 16.1.4 page 290
 */
fun minimumNumberOfOperationsWithOneOfThreeMovesRequired(
        pegs: Pegs, fromPeg: Peg, toPeg: Peg,
        operations: MutableList<RingMove>) {

    if (fromPeg.isEmpty) return

    data class Frame(val from: Peg, val to: Peg,
                     val countOfRingsToMove: Int)

    val stack = LinkedList<Frame>().apply {
        push(Frame(fromPeg, toPeg, fromPeg.size))
    }

    val leftToMiddleMove = Pair(LEFT, MIDDLE)
    val middleToRightMove = Pair(MIDDLE, RIGHT)
    val rightToLeftMove = Pair(RIGHT, LEFT)

    val allowedMoves = listOf(leftToMiddleMove, middleToRightMove, rightToLeftMove)

    val leftToRightMove = Pair(LEFT, RIGHT)
    val rightToMiddleMove = Pair(RIGHT, MIDDLE)
    val middleToLeftMove = Pair(MIDDLE, LEFT)

    val proposedMoveToAllowedMovesMap = mapOf(
            leftToRightMove to listOf(leftToMiddleMove, middleToRightMove),
            rightToMiddleMove to listOf(rightToLeftMove, leftToMiddleMove),
            middleToLeftMove to listOf(middleToRightMove, rightToLeftMove))

    while (stack.isNotEmpty()) {
        val frame = stack.pop()
        with (frame) {
            val proposedMove = Pair(from.position, to.position)
            if (proposedMove in allowedMoves) {
                if (countOfRingsToMove == 1) {
                    val ring = from.pop()
                    operations.add(RingMove(from.position, to.position, ring))
                    to.push(ring)
                } else {
                    val extraPeg = pegs.extra(from, to)
                    val countOfRingsToMoveAside = countOfRingsToMove - 1
                    listOf(Frame(from, extraPeg, countOfRingsToMoveAside),
                            Frame(from, to, 1),
                            Frame(extraPeg, to, countOfRingsToMoveAside)).reversed().forEach {
                        stack.push(it)
                    }
                }
            } else {
                proposedMoveToAllowedMovesMap[proposedMove]?.let {
                    it.map {
                        Frame(pegs.at(it.first), pegs.at(it.second), countOfRingsToMove)
                    }.reversed().forEach {
                        stack.push(it)
                    }
                }
            }
        }
    }

}