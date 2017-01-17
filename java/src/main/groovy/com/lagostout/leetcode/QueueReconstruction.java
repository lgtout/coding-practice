package com.lagostout.leetcode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class QueueReconstruction {
    Gson gson = new GsonBuilder().create();
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> result = new LinkedList<>();
        Map<int[], Integer> numberAllowedInFrontOfPerson = new HashMap<>();
        if (people.length == 0) return new int[0][];
        for (int i = 0; i < people.length; i++) {
            int[] person = people[i];
            int resultPersonIndex = 0;
            int numberAllowed = person[1];
            while (numberAllowed > 0 && resultPersonIndex < result.size()) {
                if (result.get(resultPersonIndex)[0] >= person[0]) {
                    --numberAllowed;
                }
                resultPersonIndex++;
            }
            numberAllowedInFrontOfPerson.put(person, numberAllowed);
            result.add(resultPersonIndex, person);
            resultPersonIndex++;
            int[] addedPerson = person;
            while (resultPersonIndex < result.size()) {
                person = result.get(resultPersonIndex);
                if (person[0] <= addedPerson[0]) {
                    numberAllowedInFrontOfPerson.put(
                            person, numberAllowedInFrontOfPerson.get(person)-1);
                }
                if (numberAllowedInFrontOfPerson.get(person) < 0) {
                    System.out.println("swapping " + gson.toJson(person));
                    result.remove(person);
                    int tempResultPersonIndex = resultPersonIndex;
                    numberAllowed = numberAllowedInFrontOfPerson.get(person);
                    while (numberAllowed < 0) {
                        tempResultPersonIndex--;
                        int[] otherPerson = result.get(tempResultPersonIndex);
                        if (otherPerson[0] >= person[0]) {
                            numberAllowed++;
                        }
                    }
                    numberAllowedInFrontOfPerson.put(person, numberAllowed);
                    result.add(tempResultPersonIndex, person);
                    System.out.println(gson.toJson(result));
                }
                resultPersonIndex++;
            }
            System.out.println(gson.toJson(addedPerson));
            System.out.println(gson.toJson(result));
        }
        int[][] resultArray = new int[result.size()][];
        return result.toArray(resultArray);
    }
}
