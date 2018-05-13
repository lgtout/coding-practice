// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

func printJson<T>(_ o: T, _ prettyPrint: Bool = true) where T : Encodable {
    let encoder = JSONEncoder()
    if prettyPrint {
        encoder.outputFormatting = .prettyPrinted
    }
    let data = try! encoder.encode(o)
    print(String(data: data, encoding: .utf8)!)
}
