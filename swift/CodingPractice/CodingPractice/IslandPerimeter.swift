//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

let debugging = false

func prnt(_ items: Any...) {
    if debugging { print(items) }
}

class Grid : CustomDebugStringConvertible {
    
    let grid: [[Int]]
    
    init(_ grid: [[Int]]) {
        self.grid = grid
    }
    
    public var debugDescription: String {
        let gridStr = grid.map(){ $0.description }.joined(separator: "\n")
        return "Grid(\ngrid: \n\(gridStr))"
    }
    
    var width: Int {
        return self.grid[0].count
    }
    
    var height: Int {
        return self.grid.count
    }
    
    func squareIsLand(_ square:Square) -> Bool {
        let squareIsInsideGrid = (0...width - 1).contains(square.col)
            && (0...height - 1).contains(square.row)
        guard squareIsInsideGrid else {
            return false
        }
        let isLand = grid[square.row][square.col] == 1
        return isLand
    }
    
    func right(_ square: Square) -> Square? {
        let otherSquare:Square = Square(square.col + 1, square.row)
        return squareIsLand(otherSquare) ? otherSquare : Optional.none
    }
    
    func left (_ square: Square) -> Square? {
        let otherSquare:Square = Square(square.col - 1, square.row)
        return squareIsLand(otherSquare) ? otherSquare : Optional.none
    }
    
    func above (_ square: Square) -> Square? {
        let otherSquare:Square = Square(square.col, square.row + 1)
        return squareIsLand(otherSquare) ? otherSquare : Optional.none
    }
    
    func below (_ square: Square) -> Square? {
        let otherSquare:Square = Square(square.col, square.row - 1)
        return squareIsLand(otherSquare) ? otherSquare : Optional.none
    }
    
//    Use of arc4random_uniform isn't allowed on leetcode OJ.
    
//    func randomLandSquare() -> Square? {
//        var square:Square?
//        repeat {
//            square = Square(Int(arc4random_uniform(UInt32(grid[0].count))),
//                            Int(arc4random_uniform(UInt32(grid.count))))
//        } while (square == Optional.none || !squareIsLand(square!))
//        return square
//    }
    
    func findLandSquare() -> Square {
        var square:Square?
        for (i, row) in grid.enumerated() {
            for (j, col) in row.enumerated() {
                if col == 1 {
                    square = Square(j, i)
                    break
                }
            }
            if square != nil { break }
        }
        return square!
    }
    
}

class Square : Hashable, CustomDebugStringConvertible {
    
    let col: Int
    let row: Int
    
    convenience init(_ position:[Int]) {
        self.init(position[0], position[1])
    }
    
    init(_ col: Int, _ row: Int) {
        self.col = col
        self.row = row
    }
    
    public var debugDescription: String {
        return "Square(row: \(row), col: \(col))"
    }
    
    var hashValue: Int {
        get {
            return "\(col)_\(row)".hashValue
        }
    }
    
    static func == (lhs:Square, rhs:Square) -> Bool {
        return lhs.col == rhs.col && lhs.row == rhs.row
    }
    
}

class Solution {
    func islandPerimeter(_ rawGrid: [[Int]]) -> Int {
        let grid:Grid = Grid(rawGrid)
        prnt()
        prnt("finding perimeter of \(grid)")
        let land = grid.findLandSquare()
        prnt("found random land \(land)")
        var unexplored = [land]
        var explored = Set<Square>()
        var perimeterLength = 0;
        while !unexplored.isEmpty {
            prnt("unexplored \(unexplored)")
            let square:Square! = unexplored.removeFirst()
            prnt("exploring", square)
            explored.insert(square)
            prnt("finding neighbors")
            for neighbor in [
                grid.left(square), grid.right(square),
                grid.above(square), grid.below(square)] {
                    prnt("checking out neighbor")
                    if neighbor == nil {
                        prnt("neighbor is not land")
                        perimeterLength += 1
                        prnt("incremented perimeter to \(perimeterLength)")
                    } else if !unexplored.contains(neighbor!) && !explored.contains(where: {
                        $0 == neighbor!
                    }) {
                        prnt("adding neighbor \(neighbor) to unexplored")
                        unexplored.append(neighbor!)
                    } else {
                        prnt("already explored \(neighbor)")
                    }
            }
        }
        prnt("return perimeter length", perimeterLength)
        return perimeterLength
    }
}
