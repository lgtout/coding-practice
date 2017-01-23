//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

public func _bin(_ n: Int) -> String {
    return String(n, radix: 2)
}

public func _pow(_ n:Decimal, _ p:Int) -> Int {
    return Int(NSDecimalNumber(decimal: pow(n, p)))
}
