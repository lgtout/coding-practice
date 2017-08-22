package com.lagostout.elementsofprogramminginterviews.recursion

import com.lagostout.elementsofprogramminginterviews.recursion.TowersOfHanoi.PegPosition.*

/**
 * Problem 16.1 page 288
 */
object TowersOfHanoi {

    enum class PegPosition {
        LEFT, MIDDLE, RIGHT
    }

    data class Ring(val size: Int)

    class Peg(val position: PegPosition, ringCount: Int = 0,
              private val rings: MutableList<Ring> = mutableListOf()) {

        init {
            var ringSize = ringCount
            while (rings.size < ringCount){
                rings.add(Ring(ringSize--))
            }
        }

        val topRingSize: Int
            get() = rings.last().size

        val isEmpty: Boolean
            get() = rings.isEmpty()

        val isNotEmpty: Boolean
            get() = !isEmpty

        val size: Int
            get() = rings.size

        fun push(ring: Ring) {
            if (ring.size > topRingSize)
                throw IllegalStateException(
                        "Placing a ring on top of " +
                                "a smaller one is verbotten.")
            rings.add(ring)
        }

        fun pop(): Ring {
            return rings.removeAt(rings.lastIndex)
        }

        val ringCount: Int
            get() = rings.size

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Peg) return false

            if (position != other.position) return false
            if (rings != other.rings) return false

            return true
        }

        override fun hashCode(): Int {
            var result = position.hashCode()
            result = 31 * result + rings.hashCode()
            return result
        }

//        override fun toString(): String {
//            return "Peg(position=$position, rings=$rings)"
//        }
    }

    data class Pegs(val ringCount: Int) {
        val left: Peg = Peg(LEFT, ringCount)
        val middle: Peg = Peg(MIDDLE)
        val right: Peg = Peg(RIGHT)

        fun at(position: PegPosition): Peg {
            return when (position) {
                LEFT -> left
                MIDDLE -> middle
                RIGHT -> right
            }
        }
        fun extra(pegs: List<Peg>): Peg {
            return list.filter { (it !in pegs) }[0]
        }
        private val list: List<Peg>
            get() = listOf(left, middle, right)
    }

    data class RingMove(val from: PegPosition, val to: PegPosition)

    fun transferRingsFromOnePegToAnother(pegs: Pegs, from: PegPosition, to: PegPosition,
                                         countOfRingsToMove: Int, operations: MutableList<RingMove>) {
        // TODO Am I using countOfRingsToMove correctly?
        val fromPeg = pegs.at(from)
        if (fromPeg.isEmpty) return
        val toPeg = pegs.at(to)
        val extraPeg = pegs.extra(listOf(fromPeg, toPeg))
        transferRingsFromOnePegToAnother(
                pegs, extraPeg.position, toPeg.position,
                fromPeg.ringCount - 1, operations)
        toPeg.push(fromPeg.pop())
        operations.add(RingMove(fromPeg.position, toPeg.position))
        transferRingsFromOnePegToAnother(pegs, extraPeg.position, toPeg.position,
                extraPeg.size, operations)
    }

}
