package com.lagostout.elementsofprogramminginterviews.primitivetypes

/**
 * Problem 5.4.1 page 50
 */
fun findClosestIntegerWithSameWeight(number: Int): Int {

    fun shiftRightmostOneBitRightByOneBit(
            number: Int, mask: Int): Int {
        return (number xor mask) or (mask ushr 1)
    }

    fun findRightmostOneBit(number: Int): Int {
        return (number - 1).inv() and number
    }

    if (number == 0) return 0

    // Find rightmost 0 bit
    val rightmostZeroBitMask = (number.inv()).let { inverse ->
        (inverse - 1).inv() and inverse
    }
    // TODO Cleanup
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
                var rightmostOneBitOfNumberWithoutRightOneBits =
                        findRightmostOneBit(numberWithoutRightOneBits)

                // Shift that 1 bit right by 1 bit.
                numberWithoutRightOneBits = shiftRightmostOneBitRightByOneBit(
                        numberWithoutRightOneBits,
                        rightmostOneBitOfNumberWithoutRightOneBits)

                // Shift 1 bits that were cleared left, so they are
                // adjacent to the 1 bit we moved right.
                rightmostOneBitOfNumberWithoutRightOneBits =
                        rightmostOneBitOfNumberWithoutRightOneBits ushr 1
                var oneBits = rightOneBits
                while (true) {
                    val nextOneBits = oneBits shl 1
                    if (nextOneBits < rightmostOneBitOfNumberWithoutRightOneBits)
                        oneBits = nextOneBits else break
                }

                // Reapply the removed right 1 bits to the number.
                numberWithoutRightOneBits or oneBits
            }
    }
}