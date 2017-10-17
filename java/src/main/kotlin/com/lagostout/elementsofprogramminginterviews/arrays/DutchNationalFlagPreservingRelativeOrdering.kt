package com.lagostout.elementsofprogramminginterviews.arrays

/**
 * Problem 6.1.5 page 65
 */
object DutchNationalFlagPreservingRelativeOrdering {

    data class Element(val order: Int, val value: Boolean)

    fun arrangeAsDutchNationalFlag(array: MutableList<Element>) {
        var lastFalseIndex = array.lastIndex
        var lastTrueIndex = array.lastIndex
        while (lastTrueIndex >= 0 && lastFalseIndex >= 0) {
            if (array[lastFalseIndex].value)
                --lastFalseIndex
            else if (!array[lastTrueIndex].value ||
                    lastTrueIndex > lastFalseIndex)
                --lastTrueIndex
            else {
                val trueElement = array[lastTrueIndex]
                array[lastTrueIndex] = array[lastFalseIndex]
                array[lastFalseIndex] = trueElement
            }
        }
    }

}