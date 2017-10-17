package com.lagostout.elementsofprogramminginterviews.primitivetypes

fun swapBits(number: Long, from: Int, to: Int): Long {
    // Let's assume position 0 is the LSB, and 63 is the MSB.
    var swappedNumber = number
    // Extract the values at both positions
    val fromMask = (1L shl from)
    val toMask = (1L shl to)
    val fromValue = number and fromMask
    val toValue = number and toMask
    if (fromValue != toValue) {
        listOf(fromValue to toMask, toValue to fromMask).forEach {
            (value, mask) ->
            swappedNumber = swappedNumber.let {
                if (value == 0L) it and mask.inv()
                else it or mask
            }
        }
    }
    return swappedNumber
}