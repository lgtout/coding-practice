package com.lagostout.leetcode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

class QueueReconstruction {
    Gson gson = new GsonBuilder().create();
    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][];
        Map<Integer, Set<Integer>> atLeastAsTallToHeightMap = new HashMap<>();
        for (int[] person : people) {
            Set<Integer> heights = atLeastAsTallToHeightMap.computeIfAbsent(
                    person[1], k -> new TreeSet<>());
            heights.add(person[0]);
        }
        System.out.println(gson.toJson(atLeastAsTallToHeightMap));
        List<int[]> ranges = new ArrayList<>();
        // TODO
        // The range max isn't necessarily the size of the queue!
        ranges.add(new int[]{1, 500, 0});
//        ranges.add(new int[]{1, people.length, 0});
        for (int j = 0; j < people.length; ++j){
            for (int[] range : ranges) {

                // Find next person
                System.out.println("range: " + gson.toJson(range));
                Set<Integer> heights = atLeastAsTallToHeightMap.get(range[2]);
                if (heights == null || heights.isEmpty())
                    continue;
                Iterator<Integer> iterator = heights.iterator();
                int height = iterator.next();
                int[] nextPerson = new int[]{height, range[2]};
                System.out.println("nextPerson: " + gson.toJson(nextPerson));
                iterator.remove();
                System.out.println(gson.toJson(atLeastAsTallToHeightMap));

                // Update ranges
                int rangesSize = ranges.size();
                int[] lastRange = ranges.get(rangesSize-1);
                int[] nextRange = Arrays.copyOf(lastRange, 3);
                nextRange[0] = nextPerson[0] + 1;
                lastRange[1] = nextPerson[0];
                for (int[] range1 : ranges) {
                    range1[2]++;
                }
                ranges.add(nextRange);
                System.out.println("ranges: " + gson.toJson(ranges));
                result[j] = nextPerson;
                break;
            }
        }
        return result;
    }
}
