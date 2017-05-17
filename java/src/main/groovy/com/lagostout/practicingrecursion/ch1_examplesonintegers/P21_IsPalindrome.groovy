package com.lagostout.practicingrecursion.ch1_examplesonintegers

class P21_IsPalindrome {

    static public boolean isPalindrome(int n, int length) {
        println ""
        if (length <= 1) return true
        int first = n / Math.pow(10, length - 1)
        int last = n % 10
        int middle = (n % Math.pow(10, length - 1)) / 10
        return first == last && isPalindrome(middle, length - 2)
    }

}
