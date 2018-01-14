package com.lagostout.elementsofprogramminginterviews.strings

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe

object ComputeValidIPAddressesSpek : Spek({
    fun bruteForceComputeValidIpAddresses(
            addresses: String, remainingOctetCount: Int, startIndex: Int): Int {
        if (remainingOctetCount == 0 || startIndex >= addresses.length) return 0
        var count = 0 // ??
        for (n in (1..3)) {
            bruteForceComputeValidIpAddresses(addresses, remainingOctetCount - 1, startIndex + n)
        }
        return count
    }
    val data = listOfNotNull(
            "1921",
            "19216",
            "192168",
            "1921682",
            "19216825",
            "192168256",
            "1921682561",
            "19216825612",
            "192168256123",
            null
    ).map {

    }.toTypedArray()
    describe("computeValidIPAddresses") {

    }
})