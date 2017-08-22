package com.lagostout.elementsofprogramminginterviews.recursion

import java.lang.IllegalStateException

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
            rings.add(ring)
        }
        fun pop(): Ring {
            return rings.removeAt(rings.lastIndex)
        }

//        override fun toString(): String {
//            return "Peg(position=$position, rings=$rings)"
//        }
    }

    data class RingMove(val from: PegPosition, val to: PegPosition, val ring: Ring)

    fun transferRingsFromOnePegToAnother(ringCount: Int): List<RingMove> {
        val operations = mutableListOf<RingMove>()
        transferRingsFromOnePegToAnother(Peg(PegPosition.LEFT, ringCount),
                Peg(PegPosition.MIDDLE), Peg(PegPosition.RIGHT), ringCount,
                operations)
        return operations
    }

    fun transferRingsFromOnePegToAnother(fromPeg: Peg, toPeg: Peg,
                                         extraPeg: Peg, ringCount: Int,
                                         operations: MutableList<RingMove>) {
        if (fromPeg.isEmpty) return
        transferRingsFromOnePegToAnother(fromPeg, extraPeg, toPeg,
                ringCount - 1, operations)
        val ring = fromPeg.pop()
        if (toPeg.isNotEmpty && ring.size > toPeg.topRingSize)
            throw IllegalStateException(
                    "Attempted to place a ring on the destination peg " +
                            "that's larger than that peg's top ring.")
        toPeg.push(ring)
        operations.add(RingMove(fromPeg.position, toPeg.position, ring))
        transferRingsFromOnePegToAnother(extraPeg, toPeg, fromPeg,
                extraPeg.size, operations)
    }

}
