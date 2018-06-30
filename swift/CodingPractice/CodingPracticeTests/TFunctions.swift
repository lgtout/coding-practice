// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

func reproducibleRandom(endExclusive: Int) -> Int {
    let dr = drand48()
    return Int(dr * Double(endExclusive))
}

func reproducibleRandom(endInclusive: Int) -> Int {
    return reproducibleRandom(endExclusive: endInclusive + 1)
}