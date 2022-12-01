package y2021.day01

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class SonarSweepTest {

    @Test
    fun examplePartOne() {
        val puzzle = SonarSweep(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(7))
    }
    @Test
    fun actualPartOne() {
        val puzzle = SonarSweep()
        assertThat(puzzle.solvePartOne(), equalTo(1529))
    }
    @Test
    fun examplePartTwo() {
        val puzzle = SonarSweep(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(5))
    }

    @Test
    fun actualPartTwo() {
        val puzzle = SonarSweep()
        assertThat(puzzle.solvePartTwo(), equalTo(1567))
    }
}


private const val testInput = """199
200
208
210
200
207
240
269
260
263"""
