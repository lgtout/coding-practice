// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class EPMinimumWeightPathInTriangleSpec : QuickSpec {

    static let cases = [
        ([], []),
        ([[1]], [0]),
        ([[1],[2,3]], [0,0]),
        ([[1],[3,2]], [0,1]),
        ([[3],[2,1]], [0,1]),
        ([[2],[4,4],[8,5,6],[4,2,6,2],[1,5,2,3,4]], [0,0,1,1,2]),
    ].map { (aCase: ([[Int]], [Int])) -> ([[Int]], EPMinimumWeightPathInTriangle.Result) in
        let (triangle, path) = aCase
        let pathWeight = path.enumerated().reduce(0, { (acc: Int, item) in
            return acc + triangle[item.offset][item.element]
        })
        return (triangle, (path, pathWeight))
    }

    class SharedExamples : QuickConfiguration {
        override class func configure(_ configuration: Configuration!) {
            sharedExamples("minimumWeightPathInTriangle") { context in
                let fn = context()["fn"] as! (Array<Array<Int>>) ->
                    EPMinimumWeightPathInTriangle.Result
                for (triangle, expected) in cases {
                    describe("given triangle \(triangle)") {
                        it("should return \(expected)") {
                            let (path, weight) = fn(triangle)
                            expect(path).to(equal(expected.path))
                            expect(weight).to(equal(expected.weight))
                        }
                    }
                }
            }
        }
    }

    
    
    override func spec() {
        
        let examplesName = "minimumWeightPathInTriangle"
        
        describe("computeWithBruteForceAndRecursion") {
            itBehavesLike(examplesName) {
                ["fn": EPMinimumWeightPathInTriangle.computeWithRecursionAndBruteForce]
            }
        }

        describe("computeWithRecursionAndMemoization") {
            itBehavesLike(examplesName) {
                ["fn": EPMinimumWeightPathInTriangle.computeWithRecursionAndMemoization]
            }
        }

        describe("computeBottomUpWithMemoization") {
            itBehavesLike(examplesName) {
                ["fn": EPMinimumWeightPathInTriangle.computeBottomUpWithMemoization]
            }
        }

    }
}
