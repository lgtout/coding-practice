//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

let degugging = true

class IslandPerimeter {
    func islandPerimeter(_ rawGrid: [[Int]]) -> Int {
        let grid:Grid = Grid(rawGrid)
        print("finding perimeter of \(grid)")
        let randomLand:Square! = grid.randomLandSquare()
        print("found random land \(randomLand)")
        var unexplored = [randomLand]
        var explored = Set<Square>()
        var perimeterLength = 0;
        while !unexplored.isEmpty {
            let square:Square! = unexplored.removeFirst()
            print("exploring", square)
            explored.insert(square)
            print("finding neighbors")
            for neighbor in [
                grid.left(square), grid.right(square),
                grid.above(square), grid.below(square)] {
                    print("checking out neighbor")
                    if neighbor == nil {
                        print("neighbor is not land")
                        perimeterLength += 1
                    } else {
                        print("adding neighbor \(neighbor) to unexplored")
                        unexplored.append(neighbor)
                    }
            }
        }
        print("return perimeter length", perimeterLength)
        return perimeterLength
    }
}

var indent = ""

func ==(lhs: Square, rhs: Square) -> Bool {
    return lhs.hashValue == rhs.hashValue }

class Grid : CustomDebugStringConvertible {
    
    let grid: [[Int]]
    
    init(_ grid: [[Int]]) {
        self.grid = grid
    }
    
    public var debugDescription: String {
        return "Grid(grid: \(grid))"
    }
    
    var width: Int {
        return self.grid[0].count
    }
    
    var height: Int {
        return self.grid.count
    }
    
    func squareIsLand(_ square:Square) -> Bool {
        let squareIsInsideGrid = (0...width - 1).contains(square.col)
            && (1...height - 1).contains(square.row)
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
    
    func randomLandSquare() -> Square? {
        var square:Square?
        repeat {
            square = Square(Int(arc4random_uniform(UInt32(grid[0].count))),
                            Int(arc4random_uniform(UInt32(grid.count))))
        } while (square == Optional.none || !squareIsLand(square!))
        return square
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
    
}
