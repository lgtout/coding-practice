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
