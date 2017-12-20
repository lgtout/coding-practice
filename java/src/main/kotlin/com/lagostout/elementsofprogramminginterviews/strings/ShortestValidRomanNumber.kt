package com.lagostout.elementsofprogramminginterviews.strings
import java.util.*

object ShortestValidRomanNumber {
    @Suppress("PropertyName", "unused")
    object RomanDigits {
        class RomanDigit (val value: Int, val symbol: Char)
        val I = RomanDigit(1,'I')
        val V = RomanDigit(5,'V')
        val X = RomanDigit(10,'X')
        val L = RomanDigit(50,'L')
        val C = RomanDigit(100,'C')
        val D = RomanDigit(500,'D')
        val M = RomanDigit(1000,'M')
        val values = listOf(I,V,X,L,C,D,M)
    }
    fun computeShortestValidRomanNumber(number: Int): String {
        // Idea: Use TreeSet to cut searching this down to O(log n).
        // Or apply binary search.
        // Is there a solution that used O(1) space?  Or does using
        // stack not increase space used beyond O(1), since there
        // cannot be more entries in the stack than there are roman
        // digits available.
        val exceptionMap = with (RomanDigits) {
            mapOf(V to I, X to I, L to X,
                    C to X, D to C, M to C)
        }
        val reversedRomanDigits = RomanDigits.values.toList().reversed()
        val stringBuilder = StringBuilder()
        var romanDigitsIndex = 0
        val stack = LinkedList<Int>().apply {
            push(number)
        }
        val romanDigitsTree = TreeSet<RomanDigits.RomanDigit>(
                { o1, o2 -> o1.value.compareTo(o2.value) })
        var remainder: Int
        while (true) {
            if (stack.isEmpty()) break
            remainder = stack.pop()
            // Find appropriate position in reversedRomanDigits for current
            // remainder either by searching (tree, binary search) or iterating.
            while (true) {
                val lowerRomanDigit = reversedRomanDigits[romanDigitsIndex]
                val higherRomanDigit = if (romanDigitsIndex == 0) null else
                    reversedRomanDigits[romanDigitsIndex + 1]
                if (remainder > lowerRomanDigit.value) {

                }
                break
            }
//            romanDigitsIndex <= reversedRomanDigits.lastIndex
            val romanDigit = reversedRomanDigits[romanDigitsIndex]
            if (number == romanDigit.value) {
                stringBuilder.append(romanDigit.symbol)
                stack.push(remainder - romanDigit.value)
            } else if (number > romanDigit.value) {
                if (romanDigitsIndex == 0) {
                    val romanDigitCount = remainder / romanDigit.value
                    (1..romanDigitCount).forEach {
                        stringBuilder.append(romanDigit.symbol)
                    }
                    stack.push(remainder % romanDigit.value)
                } else {
                    val higherRomanDigit = reversedRomanDigits[romanDigitsIndex - 1]
                    if (higherRomanDigit in exceptionMap.keys) {
                        val halfwayBetweenAdjacentRomanDigits =
                                ((higherRomanDigit.value - romanDigit.value) / 2) +
                                        romanDigit.value
                        if (remainder > halfwayBetweenAdjacentRomanDigits) {
                            val difference = higherRomanDigit.value - remainder

                        } else {

                        }
                    } else {

                    }
                }
            }
        }
        return ""
    }
}
