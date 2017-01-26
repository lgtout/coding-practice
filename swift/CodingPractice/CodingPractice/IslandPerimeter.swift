//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

class IslandPerimeter {
    class Solution {
        func islandPerimeter(_ rawGrid: [[Int]]) -> Int {
            var perimeterLength:Int = 0
            var previousSquare:Int
            for row in rawGrid {
                previousSquare = 0
                for square in row {
                    if (square != previousSquare) {
                        perimeterLength += 1
                    }
                    previousSquare = square
                }
                if row.last == 1 {
                    perimeterLength += 1
                }
            }
            for i in (0...rawGrid[0].count-1) {
                previousSquare = 0
                for j in (0...rawGrid.count-1) {
                    let square = rawGrid[j][i]
                    if (square != previousSquare) {
                        perimeterLength += 1
                    }
                    previousSquare = square
                }
                if rawGrid.last![i] == 1 {
                    perimeterLength += 1
                }
            }
            return perimeterLength
        }
    }
}
