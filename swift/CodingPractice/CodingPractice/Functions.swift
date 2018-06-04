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

/**
 Compares the length of arrays contained in s1 and s2.  If they are equal,
 it returns a merged array of the arrays in s1 and s2.  If they are different,
 it returns whichever of s1 or s2 contains the longest arrays.

 To determine the lengths of arrays within s1 and s2, longestSubsequences only
 reads the length of the first array.
 with s1 and s2.

 - Precondition: All the arrays in s1 are the same length.  So are all the arrays
 in s2.
*/
func longestSubsequences(_ s1: [[Int]], _ s2: [[Int]]) -> [[Int]] {
    let s1Count = s1.first?.count ?? 0
    let s2Count = s2.first?.count ?? 0
    var result = s1
    if s2Count > s1Count {
        result = s2
    } else if s2Count == s1Count {
        result += s2
    }
    return result
}
