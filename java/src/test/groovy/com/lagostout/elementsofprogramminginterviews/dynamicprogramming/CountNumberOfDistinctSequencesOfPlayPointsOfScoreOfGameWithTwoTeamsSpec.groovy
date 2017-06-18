package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.PlayPoints
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.Team
import org.apache.commons.collections4.CollectionUtils
import spock.lang.Specification
import spock.lang.Unroll

import static com.lagostout.elementsofprogramminginterviews.dynamicprogramming.
        CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsSpec.
        BruteForceHelper.scoreSequences

class CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsSpec extends Specification {

    @Unroll
    def '''counts number of distinct sequences of play points
    combinations by two teams that result in a game score'''(
            int firstTeamScore, int secondTeamScore,
            List<PlayPoints> availablePlayPoints, int expected) {

        where:
        [firstTeamScore, secondTeamScore,
         availablePlayPoints, expected] << [
                 []
         ]
    }

    int countNumberOfDistinctSequencesWithBruteForce(
            int firstTeamScore, int secondTeamScore,
            List<Integer> allPlayPoints) {
        def firstTeamSequences = scoreSequences(
                firstTeamScore, allPlayPoints)
        0
    }


    static class BruteForceHelperSpec extends Specification {

        // TODO
        // Test 2-score brute force sequence generator

        def 'computes sequences of a score using brute force'(
                int score, List<Integer> allPlayPoints, List<Integer> expected) {

            expect:
            CollectionUtils.isEqualCollection(
                    scoreSequences(score, allPlayPoints), expected)

            where:
            [score, allPlayPoints, expected] << [
                    [2, [2], [[2]]],
                    [2, [2,3], [[2]]],
                    [4, [2,3], [[2,2]]],
                    [5, [2,3], [[3,2],[2,3]]],
                    [5, [2,3,4], [[3,2],[2,3]]],
                    [4, [2,3,4], [[2,2],[4]]],
                    [6, [2,3,4], [[2,2,2],[2,4],[4,2],[3,3]]]
            ]

        }
    }

    static class BruteForceHelper {

        static Set<List<PlayPoints>> scoreSequences(
                int firstScore, int secondScore,
                List<Integer> allPlayPoints) {
            if (firstScore == 0 && secondScore == 0) return [[]]
            else if (firstScore < 0 || secondScore < 0) return []
            def sequences = [allPlayPoints, allPlayPoints].combinations().collectMany {
                List<Integer> pointsCombination ->
                    def firstTeamPoints = [points:pointsCombination[0], team:Team.A] as PlayPoints
                    def secondTeamPoints = [points:pointsCombination[1], team:Team.B] as PlayPoints
                    def nextFirstTeamScore = firstScore == 0 ? 0 : firstScore - firstTeamPoints.points
                    def nextSecondTeamScore = secondScore == 0 ? 0 : secondScore - secondTeamPoints.points
                    scoreSequences(nextFirstTeamScore, nextSecondTeamScore, allPlayPoints).collectMany {
                        List<PlayPoints> sequence ->
                            def permutationGenerator =
                                    new PermutationGenerator(sequence +
                                            [firstTeamPoints, secondTeamPoints])
                            new HashSet<Integer>(permutationGenerator.collect())
                    }
            }
            sequences
        }

        static Set<List<Integer>> scoreSequences(int score, List<Integer> allPlayPoints) {
            if (score == 0) return [[]]
            else if (score < 0) return []
            def sequences = allPlayPoints.collectMany { int points ->
                scoreSequences(score - points, allPlayPoints).collectMany {
                    List<Integer> sequence ->
                        def permutationGenerator = new PermutationGenerator(sequence + points)
                        new HashSet<List<Integer>>(permutationGenerator.collect())
                }
            }
            sequences
        }

    }
}
