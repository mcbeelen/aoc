package y2021.day03

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test

class BinaryDiagnosticTest {

    @Test
    fun examplePartOne() {
        val puzzle = BinaryDiagnostic(testInput)
        assertThat(puzzle.solvePartOne(), equalTo(198))
    }

    @Test
    fun actualPartOne() {
        val puzzle = BinaryDiagnostic()
        assertThat(puzzle.solvePartOne(), equalTo(4147524))
    }

    @Test
    fun examplePartTwo() {
        val puzzle = BinaryDiagnostic(testInput)
        assertThat(puzzle.solvePartTwo(), equalTo(230))
    }
    @Test
    fun actualPartTwo() {
        val puzzle = BinaryDiagnostic()
        assertThat(puzzle.solvePartTwo(), equalTo(3570354))
    }

}


private const val testInput = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010"""
