package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/* Problem 18.6.1 page 348 */

object GasupProblem {

    data class City(val name: String, val distanceToNextCity: Int,
                    val gas: Double, var next: City? = null)

    fun findAmpleCity(city: City, mpg: Double): City {
        var startCity = city
        var currentCity = city
        var gasRemaining = city.gas
        while (true) {
            val distance = city.distanceToNextCity
            if (currentCity == startCity) break
            currentCity = currentCity.next!!
            val gasUsed = distance / mpg
            gasRemaining -= gasUsed
            if (gasRemaining < 0) {
                gasRemaining = currentCity.gas
                startCity = currentCity
            } else {
                gasRemaining += currentCity.gas
            }
        }
        return startCity
    }

}

