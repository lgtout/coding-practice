package com.lagostout.practicingrecursion.ch1_examplesonintegers

import spock.lang.Specification
import spock.lang.Unroll

class PrintHelloSpec extends Specification {

   @Unroll('#n #expected')
   def 'print hello'(int n, String expected) {
      expect:
//      P1_PrintHello.printHello(n) == expected
      P1_PrintHello.printHello_moreRecursion(n) == expected
      where:
      [n, expected] << [
              [2, "Hello_1_Hello_2_"],
              [1, "Hello_1_"],
      ]
   }

}
