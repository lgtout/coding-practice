package com.lagostout.elementsofprogramminginterviews.strings
import com.lagostout.elementsofprogramminginterviews.strings.ShortestValidRomanNumber.RomanDigit.Companion.ceiling
import com.lagostout.elementsofprogramminginterviews.strings.ShortestValidRomanNumber.RomanDigit.Companion.floor
import com.lagostout.elementsofprogramminginterviews.strings.ShortestValidRomanNumber.RomanDigit.Companion.isException
import java.util.*

/**
 * Problem 7.9.3 page 106
 */
object ShortestValidRomanNumber {

    // Even with the stack we're using, we won't exceed O(1) space
    // because the stack will never contain more entries than there
    // are distinct roman digits.
    // Introducing TreeSet doesn't affect time complexity.  The
    // set's size is the number of distinct roman digits, which is
    // constant.  So time complexity of a single lookup will also
    // be constant.
    // The time complexity of the entire solution depends on the
    // number of lookups of remainders we do.  It follows that
    // since TreeSet doesn't affect the number of lookups, it
    // doesn't affect the complexity of the entire solution.

    enum class RomanDigit (val value: Int, val symbol: Char) {
        I(1, 'I'),
        V(5,'V'),
        X(10,'X'),
        L(50,'L'),
        C(100,'C'),
        D(500,'D'),
        M(1000,'M');
        companion object {
            fun floor(value: Int): RomanDigit? {
                return valueToDigitMap[romanDigitsTree.floor(value)]
            }
            fun ceiling(value: Int): RomanDigit? {
                return valueToDigitMap[romanDigitsTree.ceiling(value)]
            }
            fun isException(digit: RomanDigit): Boolean {
                return orderExceptionMap.containsKey(digit)
            }
            private val orderExceptionMap = mapOf(
                    V to I, X to I, L to X,
                    C to X, D to C, M to C)
            private var valueToDigitMap: Map<Int, RomanDigit>
            private val romanDigitsTree: TreeSet<Int>
            init {
                val orderedDigits = listOf(I,V,X,L,C,D,M)
                valueToDigitMap = orderedDigits.map {
                    it.value to it }.toMap()
                romanDigitsTree = TreeSet<Int>().apply {
                    addAll(orderedDigits.map { it.value })
                }
                println("orderedDigits $orderedDigits")
                println("valueToDigitMap $valueToDigitMap")
                println("romanDigitsTree $romanDigitsTree")
            }
        }
    }

    fun computeShortestValidRomanNumber(number: Int): String {
        val stringBuilder = StringBuilder()
        val stack = LinkedList<Int>().apply {
            push(number)
        }
        var remainder: Int
        while (true) {
            if (stack.isEmpty()) break
            remainder = stack.pop()
            // Compute remainder if we use lower roman digit.
            val lowerDigit = floor(remainder)!!
            val lowerDigitRemainder = lowerDigit.let {
                remainder - it.value
            }
            // Compute remainder if we can use higher roman digit.
            var higherDigitRemainder: Int? = null
            val higherDigit: RomanDigit? = ceiling(remainder)?.let {
                if (isException(it)) {
                    higherDigitRemainder = it.value - remainder
                    it
                } else null
            }
            // Decide whether to use higher or lower roman digit.
            if (higherDigit == null ||
                    (higherDigitRemainder?.let { lowerDigitRemainder < it } == true))
                Pair(lowerDigit, lowerDigitRemainder)
            else {
                stack.push(higherDigit.value)
                Pair(higherDigit, higherDigitRemainder)
            }.let { (digit, remainder) ->
                remainder?.let {
                    if (remainder > 0)
                        stack.push(remainder)
                }
                stringBuilder.append(digit.symbol)
            }
        }
        return stringBuilder.toString()
    }
}
