package com.lagostout.elementsofprogramminginterviews.strings

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeValidIPAddressesSpek : Spek({

    // Too slow.
    fun bruteForceComputeValidIPAddresses1(
            mangledAddress: String): List<String> {
        val result = mutableListOf<String>()
        (1..256).forEach { firstOctet ->
            (1..256).forEach { secondOctet ->
                (1..256).forEach { thirdOctet ->
                    (1..256).forEach { fourthOctet ->
                        println("$firstOctet$secondOctet$thirdOctet$fourthOctet")
                        if ("$firstOctet$secondOctet$thirdOctet$fourthOctet" == mangledAddress)
                            result.add("$firstOctet.$secondOctet.$thirdOctet.$fourthOctet")
                    }
                }
            }
        }
        return result
    }

    fun bruteForceComputeValidIPAddresses2(
            mangledAddress: String): List<String> {
        val result = mutableListOf<String>()
        var addresses = mutableListOf(Pair(listOf(""), 0))
        while (true) {
            break
//            addresses = addresses.flatMap { address ->
//                (1..3).map { octetLength ->
//                    address.first.add(mangledAddress.substring(
//                        address.second+ octetLength))
//
//                    if (address.right >= mangledAddress.length) {
//                        addresses.remove(address)
//                        if (address.left.count() == 4) {
//                            result.add(address.left.joinToString("."))
//                        }
//                    }
//                }
//            }
        }
        return emptyList()
    }

    val data = listOfNotNull(
//            "1921",
//            "19216",
//            "192168",
            "1921682",
//            "19216825",
//            "192168256",
//            "1921682561",
//            "19216825612",
//            "192168256123",
            null
    ).map {
        data(it, bruteForceComputeValidIPAddresses1(it))
    }.toTypedArray()

    describe("computeValidIPAddresses") {
        on("mangled address: %s", with = * data) { mangledAddress, expected ->
            it("should return $expected") {
                assertThat(computeValidIPAddresses(mangledAddress).count())
                        .isEqualTo(expected.count())
            }
        }
    }

})