package com.lagostout.elementsofprogramminginterviews.strings

/* Problem 7.10.1  Page 106 */

fun computeValidIPAddresses(addressWithNoPeriods:String): List<String> {
    // TODO Should octetOrdinal be increasing or decreasing?
    fun computeOctets(addressWithNoPeriods: String,
                      start: Int, octetOrdinal: Int): List<String> {
        val addressWithPeriods = mutableListOf<String>()
        Pair(addressWithNoPeriods.length - start !in
                (octetOrdinal..octetOrdinal * 3),
                (octetOrdinal > 4)).let {
            (tooManyOrTooFewNumbersRemaining, illegalOctetOrdinal) ->
            if (tooManyOrTooFewNumbersRemaining || illegalOctetOrdinal)
                return addressWithPeriods
        }
        var currentOctet: String
        (1..4).forEach {
            val nextOctetStart = start + it
            currentOctet = addressWithNoPeriods.substring(start, nextOctetStart)
            computeOctets(addressWithNoPeriods, nextOctetStart, octetOrdinal + 1).map {
                addressWithPeriods.add("$currentOctet.$it")
            }
        }
        return addressWithPeriods
    }
    return computeOctets(addressWithNoPeriods, 0, 1)
}
