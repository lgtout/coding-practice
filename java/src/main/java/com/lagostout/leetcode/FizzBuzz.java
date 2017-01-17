package com.lagostout.leetcode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            String s = Integer.toString(i);
            if (i % 15 == 0) s = "FizzBuzz";
            else if (i % 3 == 0) s = "Fizz";
            else if (i % 5 == 0) s = "Buzz";
            result.add(s);
        }
        return result;
    }

    public static List<String> fizzBuzzFaster(int n) {
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            String s;
            if (i % 15 == 0) s = "FizzBuzz";
            else if (i % 3 == 0) s = "Fizz";
            else if (i % 5 == 0) s = "Buzz";
            else s = Integer.toString(i);
            result.add(s);
        }
        return result;
    }

}
