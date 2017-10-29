package com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi

import com.lagostout.elementsofprogramminginterviews.recursion.towersofhanoi.TowersOfHanoi.PegPosition

data class TestCase(val fromPegPosition: PegPosition,
                    val toPegPosition: PegPosition,
                    val ringCount: Int = 0,
                    val expectedOperationCount: Int? = null)
