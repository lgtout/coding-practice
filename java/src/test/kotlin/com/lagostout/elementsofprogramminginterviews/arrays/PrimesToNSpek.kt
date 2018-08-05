package com.lagostout.elementsofprogramminginterviews.arrays

import org.apache.commons.math3.primes.Primes
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object PrimesToNSpek : Spek({

    val data = (2..20).map { n ->
        data(n, (2..n).mapNotNull { candidate ->
            if (Primes.isPrime(candidate)) candidate else null })
    }.toTypedArray()

    describe("primesToN") {
        on("n %s", with = * data) { n, expected ->
            val primes = primesToN(n)
            it("should return $expected") {
                assertThat(primes).isEqualTo(expected)
            }
        }
    }

})
