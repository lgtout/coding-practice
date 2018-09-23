package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/* Problem 18.6.2 page 350 */

import com.lagostout.elementsofprogramminginterviews
        .greedyalgorithmsandinvariants.GasupProblem.City

object GasupProblemWhenAmpleCityMayNotExist {

    fun findAmpleCityIfItExists(city: City, mpg: Double): City? {
        if (city.next == city) return city
        var startCity = city
        var currentCity = city
        var gasRemaining = city.gas
        var ampleCity: City? = null
        outer@ while (true) {
            println("currentCity $currentCity")
            val gasRequired = currentCity.distanceToNextCity / mpg
            if (gasRemaining < gasRequired) {
                currentCity = currentCity.next!!
                do {
                    startCity = startCity.next!!
                    if (startCity == city) break@outer
                } while (startCity != currentCity)
                gasRemaining = currentCity.gas
            } else {
                currentCity = currentCity.next!!
                gasRemaining += currentCity.gas - gasRequired
                if (currentCity == startCity) {
                    ampleCity = startCity
                    break
                }
            }
        }
        return ampleCity
    }

}

