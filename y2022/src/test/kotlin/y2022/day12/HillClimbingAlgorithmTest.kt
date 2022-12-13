package y2022.day12

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Ignore
import org.junit.Test
import kotlin.Int.Companion.MIN_VALUE

class HillClimbingAlgorithmTest {

    @Test
    fun examplePartOne() {
        val puzzle = HillClimbingAlgorithm(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartOne() {
        val puzzle = HillClimbingAlgorithm()
        assertThat(puzzle.solvePartOne(), equalTo(MIN_VALUE))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = HillClimbingAlgorithm(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(0))
    }

    @Test
    @Ignore
    fun actualPartTwo() {
        val puzzle = HillClimbingAlgorithm()
        assertThat(puzzle.solvePartTwo(), equalTo(MIN_VALUE))
    }

}


private const val testInput = """Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi"""
