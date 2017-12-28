package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.5.1 page 69
 */
fun deleteDuplicatesFromArray(array: IntArray): Int {
    if (array.isEmpty()) return 0
    var pointer1 = 0
    var pointer2: Int = pointer1
    var pointer1Number = array[pointer1]
    var nextNumber: Int = pointer1Number
    while (true) {
        // Find next number
        while (pointer2 < array.lastIndex) {
            nextNumber = array[++pointer2]
            array[pointer2] = 0
            if (nextNumber != pointer1Number) break
        }
        // We didn't advance pointer2
        if (array[pointer1] == nextNumber) break
        // Otherwise, advance pointer1
        array[++pointer1] = nextNumber
        pointer1Number = nextNumber
    }
    return pointer1 + 1
}
