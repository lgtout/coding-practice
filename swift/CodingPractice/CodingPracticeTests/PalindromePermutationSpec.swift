//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class PalindromePermutationSpec : QuickSpec {
    override func spec() {
        describe("PalindromePermutation") {
            it("determines if a palindrome can be formed") {
                let cases = [["code",false],
                             ["aab",true],
                             ["carerac",true]]
                for aCase in cases {
                    let canBePalindrome:Bool =
                        PalindromePermutation.Solution()
                            .canPermutePalindrome(
                                aCase[0] as! String)
                    expect(canBePalindrome).to(
                        equal(aCase[1] as? Bool))
                }
            }
        }
    }
}
