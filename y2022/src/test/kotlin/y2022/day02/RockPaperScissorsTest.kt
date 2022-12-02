package y2022.day02

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class RockPaperScissorsTournamentTest {

    @Test
    fun examplePartOne() {
        val puzzle = RockPaperScissorsTournament(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(15))

        verifyScoreOfOneRound("A X", 4)
        verifyScoreOfOneRound("A Y", 8)
        verifyScoreOfOneRound("A Z", 3)

        verifyScoreOfOneRound("B X", 1)
        verifyScoreOfOneRound("B Y", 5)
        verifyScoreOfOneRound("B Z", 9)

        verifyScoreOfOneRound("C X", 7)
        verifyScoreOfOneRound("C Y", 2)
        verifyScoreOfOneRound("C Z", 6)
    }

    private fun verifyScoreOfOneRound(testInput1: String, expected: Int) {
        assertThat(RockPaperScissorsTournament(testInput1).solvePartOne(), equalTo(expected))
    }

    @Test
    fun actualPartOne() {
        val puzzle = RockPaperScissorsTournament()
        val actual = puzzle.solvePartOne()
        assertThat(actual, greaterThan(10484))
        assertThat(actual, equalTo(15422))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = RockPaperScissorsTournament(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(12))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = RockPaperScissorsTournament()
        assertThat(puzzle.solvePartTwo(), equalTo(15442))
    }

}


private const val testInput = """A Y
B X
C Z"""
