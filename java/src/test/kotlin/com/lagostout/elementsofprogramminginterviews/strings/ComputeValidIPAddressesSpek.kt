package com.lagostout.elementsofprogramminginterviews.strings

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeValidIPAddressesSpek : Spek({

    // Too slow.
    @Suppress("NAME_SHADOWING")
    fun bruteForceComputeValidIPAddresses1(
            mangledAddress: String): List<String> {
        val result = mutableListOf<String>()
        (1..256).forEach { firstOctet ->
            val number = firstOctet.toString()
            (1..256).forEach { secondOctet ->
                val number = number + secondOctet.toString()
                (1..256).forEach { thirdOctet ->
                    val number = number + thirdOctet.toString()
                    (1..256).forEach { fourthOctet ->
                        val number = number + fourthOctet.toString()
                        print(number)
                        if (number == mangledAddress)
                            result.add(number)
                    }
                }
            }
        }
        return result
    }

    // Better - fast!
    fun bruteForceComputeValidIPAddresses2(
            mangledAddress: String): List<String> {
        val completedAddresses = mutableListOf<String>()
        var addresses = listOf(Pair(emptyList<String>(), 0))
        while (true) {
            addresses.flatMap { (octets, startIndex) ->
                (1..3).mapNotNull { octetLength ->
                    val nextStartIndex = startIndex + octetLength
                    if (nextStartIndex > mangledAddress.count()) null
                    else {
                        Pair(octets + listOf(mangledAddress.substring(
                            startIndex, nextStartIndex)), nextStartIndex)
                    }
                }
            }.fold(Pair(mutableListOf<Pair<List<String>, Int>>(),
                mutableListOf<String>())) { acc, (octets, startIndex) ->
                if (octets.count() < 4 && startIndex < mangledAddress.count())
                    acc.first.add(Pair(octets, startIndex))
                else if (octets.count() == 4 && startIndex == mangledAddress.count())
                    acc.second.add(octets.joinToString(separator = "."))
                acc
            }.let {
                addresses = it.first
                completedAddresses.addAll(it.second)
            }
            if (addresses.isEmpty()) break
        }
        println(completedAddresses)
        return completedAddresses
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
        data(it, bruteForceComputeValidIPAddresses2(it))
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