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

        private val topRingSize: Int
            get() = rings.last().size

        val isEmpty: Boolean
            get() = rings.isEmpty()

        val isNotEmpty: Boolean
            get() = !isEmpty

        val size: Int
            get() = rings.size

        fun push(ring: Ring) {
            if (isNotEmpty && ring.size > topRingSize)
                throw IllegalStateException(
                        "Placing a ring on top of " +
                                "a smaller one is verbotten." +
                                "\n peg: $this " +
                                "\n ring: $ring")
            rings.add(ring)
        }

        fun pop(): Ring {
            return rings.removeAt(rings.lastIndex)
        }

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

        override fun toString(): String {
            return "Peg(position=$position, rings=$rings)"
        }

    }

    class Pegs(ringCount: Int, from: PegPosition) {

        // TODO Populate peg
        init {

        }

        val left: Peg = Peg(LEFT, ringCount)
        private val middle: Peg = Peg(MIDDLE)
        val right: Peg = Peg(RIGHT)

        fun at(position: PegPosition): Peg {
            return when (position) {
                LEFT -> left
                MIDDLE -> middle
                RIGHT -> right
            }
        }

        fun extra(pegs: List<Peg>): Peg {
            return list.filter { it !in pegs }[0]
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Pegs) return false

            if (left != other.left) return false
            if (middle != other.middle) return false
            if (right != other.right) return false

            return true
        }

        override fun hashCode(): Int {
            var result = left.hashCode()
            result = 31 * result + middle.hashCode()
            result = 31 * result + right.hashCode()
            return result
        }

        override fun toString(): String {
            return "Pegs(left=$left, middle=$middle, right=$right)"
        }

        private val list: List<Peg>
            get() = listOf(left, middle, right)

    }

    data class RingMove(val from: PegPosition, val to: PegPosition, val ring: Ring)

    fun transferRingsFromOnePegToAnother(pegs: Pegs, from: PegPosition, to: PegPosition,
                                         countOfRingsToMove: Int, operations: MutableList<RingMove>) {
        if (countOfRingsToMove < 1) return
        println("pegs: $pegs")
        println("from: $from, to: $to, ringCount: $countOfRingsToMove")
        println("operations: $operations")
        println()
        val fromPeg = pegs.at(from)
        val toPeg = pegs.at(to)
        val extraPeg = pegs.extra(listOf(fromPeg, toPeg))
        transferRingsFromOnePegToAnother(
                pegs, from, extraPeg.position,
                countOfRingsToMove - 1,
                operations)
        fromPeg.pop().let {
            toPeg.push(it)
            operations.add(RingMove(from, to, it))
        }
        transferRingsFromOnePegToAnother(pegs, extraPeg.position,
                to, extraPeg.size, operations)
    }

}
