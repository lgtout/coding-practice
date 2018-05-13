// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

func reproducibleRandom(_ endExclusive: Int) -> Int {
    let dr = drand48()
    return Int(dr * Double(endExclusive))
}
