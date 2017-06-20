package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.PlayPoints
import com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.Team
import org.apache.commons.collections4.CollectionUtils
import org.apache.commons.collections4.iterators.PermutationIterator
import org.apache.commons.math3.util.CombinatoricsUtils
import spock.lang.Specification
import spock.lang.Unroll

import static com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams.PlayPoints.toPlayPointSequences
import static com.lagostout.elementsofprogramminginterviews.dynamicprogramming.CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsSpec.BruteForceHelper.computeScoreSequencesOfBothTeamsUsingPermutationGenerator1
import static com.lagostout.elementsofprogramminginterviews.dynamicprogramming.
        CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeamsSpec.
        BruteForceHelper.computeScoreSequencesOfOneTeam
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection

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

    static class BruteForceHelperSpec extends Specification {

        @Unroll
        def 'computes sequences of a single score using brute force'(
                int score, List<Integer> allPlayPoints, List<Integer> expected) {

            when:
            def sequences = computeScoreSequencesOfOneTeam(
                    score, allPlayPoints)

            then:
            isEqualCollection(sequences, expected)

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

        def 'computes sequences of game scores of two teams using brute force'() {
            expect:
            isEqualCollection(
                    computeScoreSequencesOfBothTeamsUsingPermutationGenerator1(2,4, [0,2]),
                    toPlayPointSequences([
                            [[Team.A, 2],[Team.B, 2],[Team.B, 2]],
                            [[Team.B, 2],[Team.A, 2],[Team.B, 2]],
                            [[Team.B, 2],[Team.B, 2],[Team.A, 2]]]))
            // More cases
            //assert isEqualCollection(scoreSequences(2,0, [0]), [[]])
            //assert isEqualCollection(scoreSequences(2,0, [0,2]),
            //        toPlayPointSequences([[[Team.A, 2]]]))
            //assert isEqualCollection(scoreSequences(2,2, [0,2]),
            //        toPlayPointSequences([
            //                [[Team.A, 2],[Team.B, 2]],
            //                [[Team.B, 2],[Team.A, 2]]]))
            //assert isEqualCollection(scoreSequences(2,1, [0,2]),
            //        toPlayPointSequences([]))
        }

    }

    static class BruteForceHelper {

        // TODO Purely recursive 2-team sequence generator, without using permutation generator.
        static Set<List<PlayPoints>> computeScoreSequencesOfBothTeams(
                int firstScore, int secondScore,
                List<Integer> allPlayPoints) {

        }

        static Set<List<PlayPoints>> computeScoreSequencesOfBothTeamsUsingPermutationGenerator1(
                int firstScore, int secondScore, List<Integer> allPlayPoints) {
            computeScoreSequencesOfBothTeamsUsingPermutationGenerator(
                    firstScore, secondScore, [allPlayPoints, allPlayPoints].combinations() - [[0,0]])
        }

        /**
         * Brute force approach.
         * Computes sequences of two team scores.
         * Uses permutation generator to add subtracted play-points back into
         * subscores of both teams.
         * @param firstScore
         * @param secondScore
         * @param combinationsOfPointsToSubtract
         * @return
         */
        static Set<List<PlayPoints>> computeScoreSequencesOfBothTeamsUsingPermutationGenerator(
                int firstScore, int secondScore,
                List<List<Integer>> combinationsOfPointsToSubtract) {
            println "first score $firstScore second score $secondScore " +
                    "combinations of points to subtract $combinationsOfPointsToSubtract"
            if (combinationsOfPointsToSubtract.isEmpty()) return [[]]
            if (firstScore == 0 && secondScore == 0) return [[]]
            if (firstScore < 0 || secondScore < 0) return []
            def sequences = combinationsOfPointsToSubtract.collectMany {
                List<Integer> pointsToSubtract ->
                    println "points to subtract $pointsToSubtract"
                    def subtractedPoints = []
                    def firstTeamPoints = [points:pointsToSubtract[0], team:Team.A] as PlayPoints
                    def firstTeamSubscore = firstScore - firstTeamPoints.points
                    if (pointsToSubtract[0] != 0)
                        subtractedPoints << firstTeamPoints
                    def secondTeamPoints = [points:pointsToSubtract[1], team:Team.B] as PlayPoints
                    def secondTeamSubscore = secondScore - secondTeamPoints.points
                    if (pointsToSubtract[1] != 0)
                        subtractedPoints << secondTeamPoints
                    // Insert every permutation of subtracted points in every way
                    // into each subSequence.
                    println "subtracted points $subtractedPoints"
                    def sequencesWithSubtractedPointsAddedBackIn =
                            computeScoreSequencesOfBothTeamsUsingPermutationGenerator(
                                    firstTeamSubscore, secondTeamSubscore,
                                    combinationsOfPointsToSubtract).collectMany {
                                List<PlayPoints> sequenceWithPointsSubtracted ->
                                    def pointsCount = subtractedPoints.size()
                                    def subSequenceSize = sequenceWithPointsSubtracted.size()
                                    def combinationsIterator = CombinatoricsUtils.combinationsIterator(
                                            subSequenceSize + pointsCount, pointsCount)
                                    // For every way of picking pointsCount items from the complete sequence...
                                    combinationsIterator.collectMany {
                                        int[] sequenceIndicesAtWhichToInsertPoints ->
                                            // For every way of arranging pointsCount items in pointsCount places...
                                            new PermutationIterator(subtractedPoints).collect {
                                                subtractedPointsPermutation ->
                                                    def sequence = [].withDefault { null }
                                                    // Add in subseqence play points
                                                    sequence.addAll(sequenceWithPointsSubtracted)
                                                    // Insert subtracted play points
                                                    [subtractedPointsPermutation, sequenceIndicesAtWhichToInsertPoints]
                                                            .transpose().each {
                                                        PlayPoints points, int indexAtWhichToInsertPointInSequence ->
                                                            sequence.add(indexAtWhichToInsertPointInSequence, points)
                                                    }
                                                    sequence
                                            }
                                    }
                            }
                    println "sequences with subtracted points added back in " +
                            "$sequencesWithSubtractedPointsAddedBackIn"
                    sequencesWithSubtractedPointsAddedBackIn
            }
            sequences
        }

        /**
         * Brute force approach.
         * Computes sequences of a single team score.
         * @param score
         * @param allPlayPoints
         * @return
         */
        static Set<List<Integer>> computeScoreSequencesOfOneTeam(
                int score, List<Integer> allPlayPoints) {
            if (score == 0) return [[]]
            else if (score < 0) return []
            def sequences = allPlayPoints.collectMany { int points ->
                computeScoreSequencesOfOneTeam(
                        score - points, allPlayPoints).collect {
                    List<Integer> sequence ->
                        sequence + points
                }
            }
            sequences
        }

    }
}
