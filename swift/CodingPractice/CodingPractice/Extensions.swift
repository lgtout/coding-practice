// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

extension String {
    func distanceTo(_ index: String.Index) -> Int {
        return distance(from: self.startIndex, to: index)
    }
    var lastIndex: String.Index {
        return index(endIndex, offsetBy: -1)
    }
    subscript (bounds: CountableClosedRange<Int>) -> String {
        let start = index(startIndex, offsetBy: bounds.lowerBound)
        let end = index(startIndex, offsetBy: bounds.upperBound)
        return String(self[start...end])
    }
    subscript (bounds: CountableRange<Int>) -> String {
        let start = index(startIndex, offsetBy: bounds.lowerBound)
        let end = index(startIndex, offsetBy: bounds.upperBound)
        return String(self[start..<end])
    }
}

extension Substring {
    var lastIndex: String.Index {
        return index(endIndex, offsetBy: -1)
    }
}

extension MutableCollection {
    /// Shuffles the contents of this collection.
    mutating func shuffle() {
        let c = count
        guard c > 1 else { return }

        for (firstUnshuffled, unshuffledCount) in zip(indices, stride(from: c, to: 1, by: -1)) {
            // Change `Int` in the next line to `IndexDistance` in < Swift 4.1
            let d: Int = numericCast(arc4random_uniform(numericCast(unshuffledCount)))
            let i = index(firstUnshuffled, offsetBy: d)
            swapAt(firstUnshuffled, i)
        }
    }
}

extension Sequence {
    /// Returns an array with the contents of this sequence, shuffled.
    func shuffled() -> [Element] {
        var result = Array(self)
        result.shuffle()
        return result
    }
}

extension Array where Element : Equatable {
    func distinct() -> Array {
        return withoutDuplicates()
    }
    func withoutDuplicates() -> Array {
        return self.reduce([]) {
            (acc: Array, curr: Element) -> Array in
            if (!acc.contains(curr)) {
                return acc + [curr]
            }
            return acc
        }
    }
}

// TODO How do we get rid of the Int type, and make the method generic?
//extension Array where Element == [Equatable] {
////extension Array where Element == Array<AnyClass> {
//    func selectLongest() -> [Element] {
//        return reduce([Element]()) { acc, curr in
//            if acc.isEmpty {
//                return [curr]
//            } else {
//                let subsequenceCount = acc.first?.count ?? 0
//                let currCount = curr.count
//                if currCount > subsequenceCount {
//                    return [curr]
//                } else if currCount == subsequenceCount {
//                    return acc + [curr]
//                }
//                return acc
//            }
//        }
//    }
//}
