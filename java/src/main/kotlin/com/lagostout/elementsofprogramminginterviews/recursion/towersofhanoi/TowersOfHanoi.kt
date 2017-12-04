package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition.*

/**
 * Problem 16.1 page 288
 */
object TowersOfHanoi {

    enum class PegPosition {
        LEFT, MIDDLE, RIGHT
    }

    open class Ring(val size: Int) : Comparable<Ring> {
        override fun compareTo(other: Ring): Int {
            return size - other.size
        }
        fun component1(): Int = size
    }

    class Peg<T : Ring>(val position: PegPosition,
              private val rings: MutableList<T> = mutableListOf()) {

        val isEmpty: Boolean
            get() = rings.isEmpty()

        val isNotEmpty: Boolean
            get() = !isEmpty

        val size: Int
            get() = rings.size

        fun push(ring: T) {
            rings.add(ring)
        }

        fun pop(): T = rings.removeAt(rings.lastIndex)

        fun peek(): T? {
            return if (rings.isEmpty()) null
            else rings.last()
        }

        val bottom: T?
            get() = if (rings.isEmpty()) null else rings.first()

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Peg<*>) return false

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

    @Suppress("UNCHECKED_CAST")
    class Pegs<T : Ring>(rings: List<Int>, ringsPeg: PegPosition,
                         ringFactory: (Int) -> T = { size -> Ring(size) as T }) {

        constructor (ringCount: Int, ringsPeg: PegPosition) :
                this((0..ringCount).toList(), ringsPeg)

        private val left: Peg<T> = Peg(LEFT)
        private val middle: Peg<T> = Peg(MIDDLE)
        private val right: Peg<T> = Peg(RIGHT)
        private val positionToPegMap: Map<PegPosition, Peg<T>>

        init {
            positionToPegMap = pegsList.map { it.position to it }.toMap()
            positionToPegMap[ringsPeg]?.apply {
                rings.reversed().forEach {
                    push(ringFactory(it))
                }
            }
        }

        fun at(position: PegPosition): Peg<T> {
            return positionToPegMap[position]!!
        }

        fun extra(pegs: List<Peg<T>>): Peg<T> {
            return extra(*pegs.toTypedArray())
        }

        fun extra(vararg pegs: Peg<T>): Peg<T> {
            return pegsList.filter { it !in pegs }[0]
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Pegs<*>) return false

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

        private val pegsList: List<Peg<T>>
            get() = listOf(left, middle, right)

    }

    data class RingMove<T : Ring>(val from: PegPosition, val to: PegPosition, val ring: Ring) {
        constructor(fromPeg: Peg<T>, toPeg: Peg<T>, ring: T) :
                this(fromPeg.position, toPeg.position, ring)
    }

    fun <T : Ring> transferRingsFromOnePegToAnother(pegs: Pegs<T>, from: Peg<T>, to: Peg<T>,
                                         countOfRingsToMove: Int,
                                         operations: MutableList<RingMove<T>>) {
        if (from == to) return
        if (countOfRingsToMove < 1) return
        val extraPeg = pegs.extra(listOf(from, to))
        transferRingsFromOnePegToAnother(
                pegs, from, extraPeg,
                countOfRingsToMove - 1,
                operations)
        from.pop().let {
            to.push(it)
            operations.add(RingMove(from.position, to.position, it))
        }
        transferRingsFromOnePegToAnother(
                pegs, extraPeg,
                to, countOfRingsToMove - 1, operations)
    }

}
