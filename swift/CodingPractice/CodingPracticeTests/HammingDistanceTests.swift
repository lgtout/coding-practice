//
//  HammingDistanceTests.swift
//  CodingPractice
//
//  Created by julian abiodun on 1/12/17.
//  Copyright © 2017 lagostout. All rights reserved.
//

import XCTest
import Nimble
import Dollar

class HammingDistanceTests: XCTestCase {
    
    var hd:HammingDistance?
    
    override func setUp() {
        super.setUp()
        hd = HammingDistance()
    }
    
    override func tearDown() {
        super.tearDown()
    }
    
    func testComputesHammingDistance() {
        let testCases = [([1,4],2), ([2,2],0)]
        for testCase in testCases {
            let input = testCase.0
            expect(self.hd?.hammingDistance(
                input[0], input[1])).to(equal(testCase.1))
        }
    }
    
}
