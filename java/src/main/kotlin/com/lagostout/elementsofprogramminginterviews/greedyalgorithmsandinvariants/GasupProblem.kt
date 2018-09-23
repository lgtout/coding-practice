package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.MultilineShortPrefixRecursiveToStringStyle
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ReflectionToStringBuilder

/* Problem 18.6.1 page 348 */

object GasupProblem {

    data class City(val name: String, val distanceToNextCity: Int,
                    val gas: Double, var next: City? = null) {

        override fun hashCode(): Int {
            // As long as we don't repeat names, this will generate
            // a unique hashcode for each city.
            return HashCodeBuilder()
                    .append(name)
                    .hashCode()
        }

        override fun toString(): String {
            return ReflectionToStringBuilder(this,
                MultilineShortPrefixRecursiveToStringStyle()).toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is City) return false

            if (name != other.name) return false

            return true
        }
    }

    fun findAmpleCity(city: City, mpg: Double): City {
        if (city.next == city) return city
        var startCity = city
        var currentCity = city
        var gasRemaining = city.gas
        while (true) {
            val distance = currentCity.distanceToNextCity
            val gasRequired = distance / mpg
            if (gasRemaining < gasRequired) {
                currentCity = currentCity.next!!
                startCity = currentCity
                gasRemaining = currentCity.gas
            } else {
                currentCity = currentCity.next!!
                gasRemaining += currentCity.gas - gasRequired
                if (currentCity == startCity) break
            }
        }
        return startCity
    }

}

