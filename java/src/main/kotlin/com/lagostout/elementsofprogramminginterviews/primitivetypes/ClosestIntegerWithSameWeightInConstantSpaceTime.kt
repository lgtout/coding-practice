package com.lagostout.elementsofprogramminginterviews.primitivetypes

import com.lagostout.common.toBinaryString
import kotlin.math.log2
import kotlin.math.roundToInt

/**
 * Problem 5.4.2 page 50
 */
fun findClosestIntegerWithSameWeightInConstantSpaceTime(number: Int): Int {

    fun shiftOneBitRightByOneBit(
            number: Int, mask: Int): Int {
        return (number xor mask) or (mask ushr 1)
    }

    fun findRightmostOneBit(number: Int): Int {
        return (number - 1).inv() and number
    }

    fun findLeftmostOneBit(number: Int): Int {
        return findRightmostOneBit(number.inv())
    }

    fun createMask(leftmostOneBitPosition: Int,
                   rightmostOneBitPosition: Int): Int {
        // Remove excess 1 bits to the right of the mask
        return (-1 xor ((1 shl rightmostOneBitPosition) - 1)) xor
                // Remove excess 1 bits to the left of the mask
                ((1 shl (leftmostOneBitPosition + 1)) - 1).inv()
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
                shiftOneBitRightByOneBit(number, it)
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
            // Shift the first 1 bit that's to the left of the middle 0 bits
            // right by 1 bit.
            (rightmostZeroBitMask - 1).let { rightOneBits ->
                // Clear all 1 bits to the right of the rightmost 0 bit.
                var numberWithoutRightOneBits = number xor rightOneBits
                // If there's more than 1 bit position separating the 1 bits
                // on either side of the 0 bits, we should shift the 1 bit on
                // the left side right by 1 bit position.
                val rightmostOneBitMaskOfNumberWithoutRightOneBits =
                        findRightmostOneBit(numberWithoutRightOneBits)
                val leftmostOneBitMaskOfRightOneBits =
                        findLeftmostOneBit(rightOneBits)
                var countOfZeroBits = log2(
                        (rightmostOneBitMaskOfNumberWithoutRightOneBits /
                                leftmostOneBitMaskOfRightOneBits).toDouble())
                        .roundToInt()
                if (countOfZeroBits > 1) {
                    numberWithoutRightOneBits = shiftOneBitRightByOneBit(
                            numberWithoutRightOneBits,
                            rightmostOneBitMaskOfNumberWithoutRightOneBits)
                    --countOfZeroBits
                }
                // Shift right 1 bits left up to the 1 bit shifted right and
                // reapply right 1 bits to number.
                numberWithoutRightOneBits or (rightOneBits shl countOfZeroBits)
            }
    }.apply { println("result: ${this.toBinaryString()}") }
}