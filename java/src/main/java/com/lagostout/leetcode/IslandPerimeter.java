package com.lagostout.leetcode;

//import com.google.gson.Gson;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.stream.Stream;

public class IslandPerimeter {

    private int[][] grid;
    private int height;
    private int width;

    private static class Square {
        int row;
        int col;

        Square(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Square)) return false;

            Square square = (Square) o;

            if (row != square.row) return false;
            return col == square.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }

    public int islandPerimeter(int[][] grid) {
        // Find land
        // How about a binary approach?  That should be faster.
        // We would split the grid into halves repeatedly till
        // the center of one of the resulting halves is land.
        this.grid = grid;
        height = grid.length;
        width = height > 0 ? grid[0].length : 0;
        Square square = null;
        boolean found = false;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                square = new Square(i, j);
                if (isLand(square)) {
                    found = true;
                    break;
                }
            }
            if (found) break;
        }
        if (square == null) return 0;
//        print(grid);
        // Explore land
        HashSet<Square> explored = new HashSet<>();
        Queue<Square> unexplored = new ArrayDeque<>();
        unexplored.add(square);
        final int[] perimeter = {0};
        Square currLand;
//        print(square);
//        print("");
        while (!unexplored.isEmpty()) {
//            print (unexplored);
//            print(explored);
            currLand = unexplored.remove();
            explored.add(currLand);
            Stream<Square> land = Stream.of(
                    new Square(currLand.row, currLand.col-1),
                    new Square(currLand.row, currLand.col+1),
                    new Square (currLand.row-1, currLand.col),
                    new Square(currLand.row+1, currLand.col));
            land.forEach(sq -> {
                if (isLand(sq)) {
                    if (!explored.contains(sq))
                        unexplored.add(sq);
                } else {
//                    if (!explored.contains(sq))
                        perimeter[0]++;
                }});
        }
        return perimeter[0];
    }

    private void print(Object a) {
//        System.out.println(new Gson().toJson(a));
    }

    private boolean isLand(Square land) {
        int row = land.row, col = land.col;
        return row < height && row >= 0 &&
                col < width && col >= 0 &&
                grid[row][col] == 1;
    }

}
