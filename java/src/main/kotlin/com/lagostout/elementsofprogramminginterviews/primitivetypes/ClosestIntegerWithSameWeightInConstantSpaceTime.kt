package com.lagostout.elementsofprogramminginterviews.primitivetypes

import kotlin.math.log2

/**
 * Problem 5.4.2 page 50
 */
fun findClosestIntegerWithSameWeightInConstantSpaceTime(number: Int): Int {

    fun shiftRightmostOneBitRightByOneBit(
            number: Int, mask: Int): Int {
        return (number xor mask) or (mask ushr 1)
    }

    fun findRightmostOneBit(number: Int): Int {
        return (number - 1).inv() and number
    }

    fun findLeftmostOneBit(number: Int): Int {
        return findRightmostOneBit(number.inv())
    }

    if (number == 0) return 0

    // Find rightmost 0 bit
    val rightmostZeroBitMask = (number.inv()).let { inverse ->
        (inverse - 1).inv() and inverse
    }

    return when {
        rightmostZeroBitMask == 1 ->
            // Rightmost 0 bit is the rightmost bit.
            // 0010000
            //       ^
            // Find rightmost 1 bit.
            findRightmostOneBit(number).let {
                // Shift rightmost 1 bit right by 1 bit.
                shiftRightmostOneBitRightByOneBit(number, it)
            }
        rightmostZeroBitMask > number ->
            // Rightmost 0 bit is left of the leftmost 1 bit.
            // 0011111
            //  ^
            // Shift the leftmost 1 bit left by 1 bit.
            (number or rightmostZeroBitMask) xor (rightmostZeroBitMask ushr 1)
        else ->
            // Rightmost 0 bit is between 1 bits.
            // 00110011
            //      ^
            // 00110111
            //     ^
            // Shift the first 1 bit that's to the left of the rightmost
            // 0 bit right by 1 bit.
            (rightmostZeroBitMask - 1).let { rightOneBits ->
                // Clear all 1 bits to the right of the rightmost 0 bit.
                var numberWithoutRightOneBits = number xor rightOneBits
                // Find the 1 bit to the left of the 0 bit.
                // This is the position of the 1 bit that we need
                // to move right by 1 bit.
                var leftmostOneBitOfRightOneBitsAfterShiftLeft =
                        findRightmostOneBit(numberWithoutRightOneBits)

                // Shift that 1 bit right by 1 bit.
                numberWithoutRightOneBits = shiftRightmostOneBitRightByOneBit(
                        numberWithoutRightOneBits,
                        leftmostOneBitOfRightOneBitsAfterShiftLeft)

                // TODO Needs work

                // Shift 1 bits that were cleared left, so they are
                // adjacent to the 1 bit we moved right.
                leftmostOneBitOfRightOneBitsAfterShiftLeft =
                        leftmostOneBitOfRightOneBitsAfterShiftLeft ushr 2
                println(leftmostOneBitOfRightOneBitsAfterShiftLeft)
                val numberOfBitsToShiftOneBitsLeft =
                        log2(leftmostOneBitOfRightOneBitsAfterShiftLeft.toDouble()) -
                                (log2(findLeftmostOneBit(rightOneBits).toDouble()) + 1)
                println(numberOfBitsToShiftOneBitsLeft)
                val shiftedRightOneBits = rightOneBits shl numberOfBitsToShiftOneBitsLeft.toInt()
                // Reapply the removed right 1 bits to the number.
                numberWithoutRightOneBits or shiftedRightOneBits
            }
    }
}