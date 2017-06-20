package com.lagostout.elementsofprogramminginterviews.dynamicprogramming

class CountNumberOfDistinctSequencesOfPlayPointsOfScoreOfGameWithTwoTeams {

    int compute(int score) {
        0
    }

    enum Team {
        A, B
    }

    class PlayPoints {
        int points
        Team team
        String toString() {
            "(${team.name()},$points)"
        }

        static List<List<PlayPoints>> toPlayPointSequences(List<List<List>> sequences) {
            sequences.collect {
                it.collect {
                    [team:it[0], points:it[1]] as PlayPoints
                }
            }
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (!(o instanceof PlayPoints)) return false

            PlayPoints that = (PlayPoints) o

            if (points != that.points) return false
            if (team != that.team) return false

            return true
        }

        int hashCode() {
            int result
            result = points
            result = 31 * result + team.hashCode()
            return result
        }
    }

}
