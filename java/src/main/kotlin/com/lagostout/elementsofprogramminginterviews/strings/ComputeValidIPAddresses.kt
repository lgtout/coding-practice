package com.lagostout.elementsofprogramminginterviews.strings

/* Problem 7.10.1  Page 106 */

fun computeValidIPAddresses(address:String): Int {
    data class Frame(val octetLength: Int,
                     val lengthOfIPStringRemainder: Int,
                     val remainingOctetCount: Int)

    fun computeOctets(frames: List<Frame>): List<Frame> {
        return frames.map { frame ->
            (1..3).map {
                Frame(it, frame.lengthOfIPStringRemainder - it,
                        frame.remainingOctetCount - 1)
            }.filter {
                it.run {
                    lengthOfIPStringRemainder in
                            (remainingOctetCount * 1)..
                                    (remainingOctetCount * 3)
                }
            }
        }.flatten()
    }
    (0..3).map {
        { frames: List<Frame> -> computeOctets(frames) }
    }.fold(listOf(Frame(0, address.length, 4))) {
        acc, fn -> fn(acc) }
    return 0
}
