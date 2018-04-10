// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

extension String {
    func distanceTo(_ index: String.Index) -> Int {
        return distance(from: self.startIndex, to: index)
    }
    var lastIndex: String.Index {
        return index(endIndex, offsetBy: -1)
    }
}

extension Substring {
    var lastIndex: String.Index {
        return index(endIndex, offsetBy: -1)
    }
}

