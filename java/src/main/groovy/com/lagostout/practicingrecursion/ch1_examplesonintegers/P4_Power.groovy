package com.lagostout.practicingrecursion.ch1_examplesonintegers

class P4_Power {

    static double power1(double base, int exponent) {
        if (exponent == 0) return 1
        return base * power1(base, exponent - 1)
    }

    static double power2(double base, int exponent) {
        if (exponent == 0) return 1
        else {
            double result = power2(base, exponent >> 1) * power2(base, exponent >> 1)
            if (exponent & 1) {
                return base * result
            } else {
                return result
            }
        }
    }

    static double power3(double base, int exponent) {
        if (exponent == 0) return 1
        else {
            double result = power2(base, exponent >> 1)
            result *= result
            if (exponent & 1) {
                return base * result
            } else {
                return result
            }
        }
    }
}
