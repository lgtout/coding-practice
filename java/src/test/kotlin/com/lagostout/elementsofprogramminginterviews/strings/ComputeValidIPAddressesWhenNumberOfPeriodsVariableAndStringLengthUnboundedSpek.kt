package com.lagostout.elementsofprogramminginterviews.strings

import com.lagostout.common.reproducibleRdg
import org.assertj.core.api.Assertions
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object ComputeValidIPAddressesWhenNumberOfPeriodsVariableAndStringLengthUnboundedSpek : Spek({

    fun bruteForceComputeValidIPAddresses(
            mangledAddress: String, periodCount: Int): List<String> {
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
                if (octets.count() <= periodCount && startIndex < mangledAddress.count())
                    acc.first.add(Pair(octets, startIndex))
                else if (octets.count() == periodCount + 1 &&
                        startIndex == mangledAddress.count())
                    acc.second.add(octets.joinToString(separator = "."))
                acc
            }.let {
                addresses = it.first
                completedAddresses.addAll(it.second)
            }
            if (addresses.isEmpty()) break
        }
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
        val periodCount = 3
        data(it, periodCount, bruteForceComputeValidIPAddresses(it, periodCount))
    }.toTypedArray()

    val randomData = run {
        val caseCount = 100
        val maxMangledAddressLength = 20
        val random = reproducibleRdg()
        (0 until caseCount).map {
            val mangledAddressLength = random.nextInt(2, maxMangledAddressLength)
            val periodCount = random.nextInt(1, mangledAddressLength - 1)
            val mangledAddress = (0 until mangledAddressLength).map { _ ->
                random.nextInt(0, 9)
            }.joinToString("")
            data(mangledAddress, periodCount,
                bruteForceComputeValidIPAddresses(mangledAddress, periodCount))
        }.toTypedArray()
    }

    describe("computeValidIPAddressesWhenNumberOfPeriodsVariableAndStringLengthUnbounded") {
//        on("mangled address: %s", with = * data) { mangledAddress, periodCount, expected ->
        on("mangled address: %s, periodCount: %s", with = * randomData) {
                mangledAddress, periodCount, expected ->
            it("should return $expected") {
                Assertions.assertThat(
                    computeValidIPAddressesWhenNumberOfPeriodsVariableAndStringLengthUnbounded(
                        mangledAddress, periodCount).count()).isEqualTo(expected.count())
            }
        }
    }

})
