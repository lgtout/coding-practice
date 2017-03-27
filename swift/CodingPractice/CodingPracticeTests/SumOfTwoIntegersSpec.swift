
import Foundation
import Quick
import Nimble

class SumOfTwoIntegersSpec : QuickSpec {
    override func spec() {
        describe("SumOfTwoIntegers") {
            let cases = [
                ((0,-1), -1),
                ((1,-1), 0),
                ((1,1), 2),
                ((1,2), 3),
                ((0,0), 0),
                ((0,1), 1),
                ((2,4), 6),
                ((3, 1000), 1003),
                ((40, 1000), 1040)
            ]
            let sumOfTwoIntegers = SumOfTwoIntegers()
            for ((leftOperand, rightOperand), expected) in cases {
                it("sums \(leftOperand) and \(rightOperand) without using operators") {
                    let sum = sumOfTwoIntegers.getSum(leftOperand, rightOperand)
                    expect(sum).to(equal(expected))
                }
            }
        }
    }
}
