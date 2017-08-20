package com.lagostout.elementsofprogramminginterviews.recursion

import java.lang.IllegalStateException

object TowersOfHanoi {

    enum class PegPosition {
        LEFT, MIDDLE, RIGHT
    }

    data class Ring(val size: Int)

    class Peg(val position: PegPosition, var ringCount: Int = 0,
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
//        val size: Int
//            get() = rings.size
        fun push(ring: Ring) {
            rings.add(ring)
        }
        fun pop(): Ring {
            return rings.removeAt(rings.lastIndex)
        }
    }

    data class MoveRing(val from: PegPosition, val to: PegPosition)

    fun transferRingsFromOnePegToAnother(fromPeg: Peg, toPeg: Peg,
                                         extraPeg: Peg, ringCount: Int,
                                         operations: List<MoveRing>) {
        fromPeg.apply {
            when {
                isEmpty -> return
                isNotEmpty && toPeg.isNotEmpty &&
                        topRingSize > toPeg.topRingSize -> {
                    throw IllegalStateException(
                            "Attempted to place a ring on the destination peg " +
                                    "that's larger than that peg's top ring")
                }
            }
        }
        toPeg.apply {
            if (isEmpty || topRingSize < fromPeg.topRingSize) {
                push(fromPeg.pop())
                return
            }
        }
        transferRingsFromOnePegToAnother(fromPeg, extraPeg, toPeg,
                ringCount - 1, operations)
    }

}
